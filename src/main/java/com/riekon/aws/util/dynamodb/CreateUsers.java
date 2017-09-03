package com.riekon.aws.util.dynamodb;

import com.riekon.aws.model.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class CreateUsers {

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = new Connection();

        UserModel newUser = new UserModel();
        newUser.setName("moray.grieve");
        newUser.setRoles(new HashSet(Arrays.asList("user")));
        newUser.setPassword(new BCryptPasswordEncoder().encode("archie123"));
        connection.getMapper().save(newUser);
    }
}
