package com.ynmio.LoanApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private String note;
    private double pinCode;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id") // This specifies the foreign key column in the Borrow table
    private MyUser user;
}


