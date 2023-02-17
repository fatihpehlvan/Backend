package com.fruitjuice.applejuiceauth.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "users")
@Entity
public class User extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String lastName;

    public static User getInstance() {
        User user = new User();
        user.setUsername("test-username");
        user.setPassword("test-password");
        user.setFirstName("test-firstname");
        user.setSecondName("test-secondname");
        user.setLastName("test-lastname");
        user.setEmail("test-email@gmail.com");
        return user;
    }

}
