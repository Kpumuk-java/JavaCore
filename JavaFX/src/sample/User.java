package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 5151;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean authorized;

    Controller controller;

    public void openConnection() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);

            while (true) {
                String printServer = dis.readUTF();
                if(printServer.startsWith("/authOk")) {
                    setAuthorized(true);
                    controller.chatField.appendText(printServer + "\n");
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
                    controller.chatField.appendText(printServer + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnected();
        }
    }

    public void sendMsg (String sendServer) {
            try {
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

    public void setController (Controller con) {
        this.controller = con;
    }

    private void setAuthorized (boolean b) {
        authorized = b;
    }

    public boolean isAuthorized() {
        return authorized;
    }
}
