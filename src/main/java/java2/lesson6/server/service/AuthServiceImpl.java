package java2.lesson6.server.service;

import java2.lesson6.server.inter.AuthService;
import java3.lesson2.DBConn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {

    private PreparedStatement ps = null;

    public void addUser(String login, String pass, String nick) throws SQLException {
        ps = DBConn
                .getInstance()
                .getConn()
                .prepareStatement("INSERT INTO users (login, pass, nick) VALUES (?, ?, ?)");
        ps.setString(1, login);
        ps.setString(2, pass);
        ps.setString(3, nick);
        ps.executeUpdate();
    }

    public AuthServiceImpl() {
        try {
            addUser("login1", "pass1", "nick1");
            addUser("login2", "pass2", "nick2");
            addUser("login3", "pass3", "nick3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNick(String login, String password) {

        try {
            ps = DBConn
                    .getInstance()
                    .getConn()
                    .prepareStatement("SELECT * FROM users WHERE login = ?");
            ps.setString(1, login);

            ResultSet set = ps.executeQuery();

            if (set.next() && set.getString("PASS").equals(password)) {
                return set.getString("NICK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * Меняет ник в базе данных
     *
     * @param beforeNick
     * @param afterNick
     */
    @Override
    public void swapNick(String beforeNick, String afterNick) {
        String login = null, pass = null;

        try {
            DBConn.getInstance().getConn().setAutoCommit(false);
            ps = DBConn
                    .getInstance()
                    .getConn()
                    .prepareStatement("SELECT * FROM users WHERE nick = ?");
            ps.setString(1, beforeNick);

            ResultSet set = ps.executeQuery();

            if (set.next()) {
                login = set.getString("LOGIN");
                pass = set.getString("PASS");
                deleteLoginPassNick(beforeNick);
                ps = DBConn
                        .getInstance()
                        .getConn()
                        .prepareStatement("INSERT INTO users (login, pass, nick) VALUES (?, ?, ?)");
                ps.setString(1, login);
                ps.setString(2, pass);
                ps.setString(3, afterNick);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DBConn.getInstance().getConn().rollback();
                DBConn.getInstance().getConn().setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            DBConn.getInstance().getConn().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Удаляет из базы данных строку с логином, паролем и ником по нику nick
     * @param nick
     * @throws SQLException при удалении строки в базе данных
     */
    private void deleteLoginPassNick(String nick) throws SQLException {

        ps = DBConn
                .getInstance()
                .getConn()
                .prepareStatement("DELETE FROM users WHERE nick = ?");
        ps.setString(1, nick);
        ps.executeUpdate();
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");

    }
}
