package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Foo", "Ivanov", (byte) 35);
        userService.saveUser("Zhopa", "Tancev", (byte) 25);
        userService.saveUser("Tent", "Seruc", (byte) 55);
        userService.saveUser("Rusia", "Pomnit", (byte) 27);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}