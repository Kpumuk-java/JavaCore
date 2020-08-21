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
            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
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
            System.out.println("from " + this.nick + ": " + clientStr);
            if (clientStr.equals("/exit")) {
                return;
            }
            if (clientStr.startsWith("/")) {
                String[] dataArray = clientStr.split("\\s");
                if (messagePrivate(dataArray, clientStr)) {
                    this.sendMsg("Incorected command");
                }
            } else {
                server.broadcastMsg(this.nick + ": " + clientStr);
            }
        }
    }

    /**
     * Отправляет сообщение только конкретному пользователю который в сети
     * @param dataArray массив слов разбитый из строки которая пришла на сервер
     * @param msg целая строка не разбитая на массив dataArray
     * @return возвращает значение false если сообщение отправлено конкретному пользователю с ником из первого слова dataArray
     */
    private boolean messagePrivate(String[] dataArray, String msg) {
        if (dataArray.length > 1) {
            msg = msg.substring(dataArray[0].length());
            if (server.isNickBusy(dataArray[0].substring(1))) {
                server.getClientHandler(dataArray[0].substring(1)).sendMsg(nick + ":" + msg);
                return false;
            }
        }

        return true;
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(this.nick + ": out from chat");

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
}