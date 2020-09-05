package java3.lesson2;


import java3.lesson2.dao.UsersDAO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UsersDAO usersDAO = new UsersDAO();
        usersDAO.addUser(new User("sdds", "sdsd", "dsdsd"));
        System.out.println(usersDAO.getAllUser());

    }
}
