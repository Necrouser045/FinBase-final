package com.finBase.project.repository;

import com.finBase.project.Model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findById(Long id);
}


