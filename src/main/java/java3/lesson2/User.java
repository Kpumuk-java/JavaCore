package java3.lesson2;

public class User {

    private String login;
    private String pass;
    private String nick;

    public User () {

    }

    public User(String login, String pass, String nick) {
        this.login = login;
        this.pass = pass;
        this.nick = nick;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getNick() {
        return nick;
    }

    public User setNick(String nick) {
        this.nick = nick;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
