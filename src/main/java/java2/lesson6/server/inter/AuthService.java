package java2.lesson6.server.inter;

public interface AuthService {

    void start();
    String getNick(String login, String password);
    void stop();
    void swapNick(String beforeNick, String afterNick);
}