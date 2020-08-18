import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerMessenger {

    private final static List<String> history = new ArrayList();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ServerMessenger serverMessenger = new ServerMessenger();
        serverMessenger.serverStarted();
    }

    private void serverStarted() {
        Socket socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(5151);
            System.out.println("Server started");
            while (true) {
                System.out.println("waiting connected...");
                socket = serverSocket.accept();
                System.out.println("Connected installed: " + socket.toString());
                openConnection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openConnection(Socket socket) throws IOException {

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        if (!history.isEmpty()) {
            sendHistory(dos);
        }

        Thread sendThread = new Thread(() -> {
            while (true) {
                try {
                    if (socket.isClosed()) {
                        break;
                    }
                    String sendUser = sc.nextLine().trim();
                    if (!sendUser.isEmpty()) {
                        if (sendUser.equals("exit")) {
                            socket.isClosed();
                            break;
                        }
                        dos.writeUTF("Server: " + sendUser);
                        synchronized (history) {
                            history.add("Server: " + sendUser);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        sendThread.start();

        while (sendThread.isAlive() && !socket.isClosed()) {
            try {
                String printServer = dis.readUTF();
                if (!printServer.isEmpty()) {
                    if (printServer.equals("exit")) {
                        System.out.println("Connected lost: " + socket.toString());
                        break;
                    }
                    System.out.println("User: " + printServer);
                    synchronized (history) {
                        history.add("User: " + printServer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dis.close();
        dos.close();
    }

    private static void sendHistory(DataOutputStream dos) throws IOException {
        int count = 0;
        List<String> buffer = new ArrayList<>();

        for (int i = history.size() - 1; i >= 0; i--) {
            count++;
            buffer.add(history.get(i));
            if (count == 10) {
                break;
            }
        }

        for (int i = buffer.size() - 1; i > 0; i--) {
            dos.writeUTF(buffer.get(i));
        }

    }
}
