package com.example.resttemplates;

import com.example.dom.User;
import com.example.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResttemplatesApplication {

    public static void main(String[] args) {

        UserService userService = new UserService();

        User user = new User(3L, "James", "Brown", (byte) 33);
        User user2 = new User(3L, "Thomas", "Shelby", (byte) 45);

        userService.getAllUsers();
        userService.addUser(user);
        userService.editUser(user2);
        userService.deleteUser(3L);
    }

}
