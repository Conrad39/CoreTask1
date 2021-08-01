package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args)  {
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 35);
        userService.saveUser("Петр", "Петров", (byte) 42);
        userService.saveUser("Сергей", "Сергеев", (byte) 23);
        userService.saveUser("Николай", "Николаев", (byte) 44);

        userService.removeUserById(3);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
