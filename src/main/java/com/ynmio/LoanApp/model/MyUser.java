package com.ynmio.LoanApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String role;

    // Remove mappedBy and joinColumn, as MyUser is the owning side of the relationship
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Borrow borrow;

    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private double pinCode;
}

