package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class User {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 5151;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;


    public void openConnection() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            Thread sendThread = new Thread(() -> {
                Scanner sc = new Scanner(System.in);
                while (!socket.isClosed()) {
                    String sendServer = sc.nextLine().trim();
                    if (!sendServer.isEmpty()) {
                        try {
                            dos.writeUTF(sendServer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (sendServer.equals("exit")) {
                            closeConnected();
                            System.exit(0);
                            break;
                        }
                    }
                }
                sc.close();
            });

            sendThread.start();

            while (sendThread.isAlive()) {
                String printServer = dis.readUTF();
                if (!printServer.isEmpty()) {
                    if (printServer.equals("exit")) {
                        System.out.println("Server down");
                        break;
                    }
                    System.out.println(printServer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

}
