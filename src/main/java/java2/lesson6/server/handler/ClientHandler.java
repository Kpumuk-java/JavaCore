package java2.lesson6.server.handler;


import java2.lesson6.server.inter.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean authOK;

    private String nick;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.nick = "";
            this.authOK = false;
            new Thread(() -> {
                try {
                    Long countTime = System.currentTimeMillis();
                    Thread t1 = new Thread(() -> {
                        try {
                            authentication();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    t1.setDaemon(true);
                    t1.start();

                    while ((System.currentTimeMillis() - countTime) < 120000) {
                        Thread.sleep(100);
                        if (authOK) {
                            readMessage();
                        }
                    }
                    if (!authOK) {
                        this.sendMsg("Время вышло");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void authentication() throws IOException {

        while (true) {
            String str = dis.readUTF();
            if (str.startsWith("/auth")) {
                String[] dataArray = str.split("\\s");
                String nick = server.getAuthService().getNick(dataArray[1], dataArray[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMsg("/authOk " + nick);
                        this.nick = nick;
                        server.broadcastMsg(this.nick + " Join to chat");
                        server.subscribe(this);
                        this.authOK = true;
                        return;
                    } else {
                        sendMsg("You are logged in");
                    }
                } else {
                    sendMsg("Incorrect password or login");
                }
            }
        }

    }

    public void sendMsg(String msg) {
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String clientStr = dis.readUTF();
            if (clientStr.startsWith("/")) {
                if (clientStr.equals("/exit")) {
                    return;
                }
                if (clientStr.startsWith("/w")) {
                    String[] strArray = clientStr.split("\\s");
                    if (strArray.length > 2) {
                        messagePrivate(this, strArray, clientStr);
                        continue;
                    } else {
                        this.sendMsg("Неверная команда");
                    }
                }
                if (clientStr.startsWith("/swap")) {
                    String[] strArray = clientStr.split("\\s");
                    if (strArray.length > 2 && strArray[2].trim().length() > 3) {
                        server.serverSwapNick(strArray[1], strArray[2]);
                        continue;
                    } else {
                        this.sendMsg("Неверная команда");
                    }
                }
                if (clientStr.startsWith("/client")) {
                    this.sendMsg(server.broadcastClientList());
                    continue;
                }
            }
            server.broadcastMsg(this.nick + ": " + clientStr);

        }
    }

    /**
     * Отправляет сообщение только конкретному пользователю который в сети
     *
     * @param strArray массив слов разбитый из строки которая пришла на сервер
     * @param msg      целая строка не разбитая на массив strArray
     */
    private void messagePrivate(ClientHandler client, String[] strArray, String msg) {
        msg = msg.substring(strArray[0].length() + strArray[1].length() + 2);
        if (server.isNickBusy(strArray[1])) {
            server.getClientHandler(strArray[1]).sendMsg("От " + strArray[1] + ": " + msg);
            client.sendMsg("Кому " + strArray[1] + ": " + msg);
            return;
        } else {
            client.sendMsg(strArray[1] + "не подключен к чату");
        }
    }

    private void closeConnection() {
        if (authOK) {
            server.unsubscribe(this);
            server.broadcastMsg(this.nick + ": покинул чат");
        }

        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientHandler setNick(String nick) {
        this.nick = nick;
        return this;
    }
}