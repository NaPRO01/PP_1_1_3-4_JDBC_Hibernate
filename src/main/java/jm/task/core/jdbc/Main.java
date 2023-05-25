package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Кирилл", "Васильев", (byte) 21);
        userService.saveUser("Владислав", "Книга", (byte) 22);
        userService.saveUser("Сергей", "Соколов", (byte) 28);
        userService.saveUser("Светлана", "Пацукевич", (byte) 34);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
