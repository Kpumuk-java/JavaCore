package java2.lesson6.server.service;



import java2.lesson6.server.handler.ClientHandler;
import java2.lesson6.server.inter.AuthService;
import java2.lesson6.server.inter.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class ServerImpl implements Server {

    private List<ClientHandler> clients;
    private AuthService authService;

    public ServerImpl() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            authService = new AuthServiceImpl();
            authService.start();
            clients = new LinkedList<>();
            while (true) {
                System.out.println("Wait join clients");
                Socket socket = serverSocket.accept();
                System.out.println("Client join");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Problem in server");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    @Override
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getNick() != null && c.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }

    @Override
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    @Override
    public AuthService getAuthService() {
        return authService;
    }

    /**
     *
     * @param nick ник который ищет метод
     * @return возвращает при совпадении конкретный ClientHandler
     */
    @Override
    public synchronized ClientHandler getClientHandler(String nick) {
        for (ClientHandler c : clients) {
            if (c.getNick().equals(nick)) {
                return c;
            }
        }
        return null;
    }

    /**
     *
     * @return Строку содержащую ники ClientHandler кто в сети
     */
    @Override
    public synchronized String broadcastClientList() {
        StringBuilder builder = new StringBuilder("Онлайн: ");
        for (ClientHandler c : clients) {
            builder.append(c.getNick() + " ");
        }
        return builder.toString();
    }
}