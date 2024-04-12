package com.ynmio.LoanApp.dao;


import com.ynmio.LoanApp.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<MyUser,Integer> {

    Optional<MyUser> findUserByPincode(double pincode);

    Optional<MyUser> findUserByName(String username);
}
