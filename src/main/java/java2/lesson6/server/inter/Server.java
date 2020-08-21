package java2.lesson6.server.inter;


import java2.lesson6.server.handler.ClientHandler;

public interface Server {
    int PORT = 5151;

    boolean isNickBusy(String nick);

    void broadcastMsg(String msg);

    void subscribe(ClientHandler client);

    void unsubscribe(ClientHandler client);

    AuthService getAuthService();

    ClientHandler getClientHandler(String nick);
}
