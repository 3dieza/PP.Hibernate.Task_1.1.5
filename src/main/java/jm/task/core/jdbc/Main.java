package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {



        Util util = new Util();
        Util.getConnection();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Bezruckov",(byte) 22);
        userService.saveUser("Chunga","Changa",(byte) 34);
        userService.saveUser("Ionis","Zhopa",(byte) 68);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }



        // реализуйте алгоритм здесь
        // логика в сервисе , реализация в дао



    }
}
