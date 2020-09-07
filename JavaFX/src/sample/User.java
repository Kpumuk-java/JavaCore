package sample;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 5151;
    private Integer COUNT_HISTORY = 100;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean authorized = false;
    private boolean connected = false;

    private File history = new File("history.txt");


    Controller controller;

    public void openConnection() {
        connected = true;
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            authorized = false;

            while (true) {
                String printServer = dis.readUTF();
                if (printServer.startsWith("/authOk")) {
                    authorized = true;
                    controller.chatField.appendText(printServer + "\n");
                    if (history != null) {
                        sendHistory();
                    }
                    break;
                }

                controller.chatField.appendText(printServer + "\n");
            }


            while (!socket.isClosed()) {
                String printServer = dis.readUTF();
                if (!printServer.isEmpty()) {
                    if (printServer.equals("/exit")) {
                        System.out.println("Server down");
                        socket.close();
                        break;
                    }
                    writeHistory(printServer);
                    controller.chatField.appendText(printServer + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnected();
            connected = false;
        }
    }

    private void sendHistory() {
        List<String> fullHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(history))) {
            String s = reader.readLine();
            while (s != null) {
                fullHistory.add(s);
                s = reader.readLine();
            }

            if (fullHistory.size() >= COUNT_HISTORY) {
                for (int i = fullHistory.size() - COUNT_HISTORY; i < fullHistory.size(); i++) {
                    controller.chatField.appendText(fullHistory.get(i) + "\n");
                }
            } else {
                for (int i = 0; i < fullHistory.size(); i++) {
                    controller.chatField.appendText(fullHistory.get(i) + "\n");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * (У меня 3 полувопроса:
     * 1. меня очень сильно смущает что приходится при каждой записи строки
     * в историю открывать поток и закрывать поток записи в файл, это же сильно "тормозит программу", потери во времени
     * 2. Сначало хотел сделать запись сразу в цикле while (!socket.isClosed()) где ждем данных с сервера,
     * но (как я понимаю из-за того что запись в файл это фоновый поток) у меня банально строка не успевала записаться
     * в файл txt, и обнулялась до следующей порции данных с сервера(((
     * 3. Была ли необходимость хранить историю в байтах в файле data? (кроме того, что в таком ввиде вес файла минимален)
     * )
     * @param str строка которую нужно записать в историю
     */
    public void writeHistory (String str) {
        try (FileWriter file = new FileWriter(history, true);) {
            file.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String sendServer) {
        try  {
            dos.writeUTF(sendServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sendServer.equals("/exit")) {
            closeConnected();
        }

    }


    private void closeConnected() {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setController(Controller con) {
        this.controller = con;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public boolean isConnected() {
        return connected;
    }
}
