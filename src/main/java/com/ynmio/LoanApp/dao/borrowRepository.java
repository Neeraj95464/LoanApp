package com.ynmio.LoanApp.dao;

import com.ynmio.LoanApp.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface borrowRepository extends JpaRepository<Borrow,Integer> {
}
