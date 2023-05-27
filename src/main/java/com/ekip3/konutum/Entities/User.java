package com.ekip3.konutum.Entities;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "id_of_favorite_houses")
    private String idOfFavoriteHouses;
}
