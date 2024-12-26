package com.finBase.project.Controller;

import com.finBase.project.Model.Expense;
import com.finBase.project.Model.User;
import com.finBase.project.repository.ExpenseRepository;
import com.finBase.project.repository.UserRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<?> saveExpense(@RequestBody Expense expense) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(expense.getEmail());

            if (userOptional.isPresent()  ) {
                User user = userOptional.get();
                System.out.println("User ID: " + user.getId());
                Optional<Expense> existingExpenseOpt = expenseRepository.findById(user.getId());
                if (existingExpenseOpt.isPresent()) {

                    Expense existingExpense = existingExpenseOpt.get();

                    if (expense.getSpend() != null) {
                        existingExpense.setSpend(existingExpense.getSpend() + expense.getSpend());
                    }
                    if (expense.getBudget() != null) {
                        existingExpense.setBudget(existingExpense.getBudget() + expense.getBudget());
                    }
                    if (expense.getMargin() != null) {
                        existingExpense.setMargin(existingExpense.getMargin() + expense.getMargin());
                    }

                    expenseRepository.save(existingExpense);
                    expenseRepository.flush();

                    return ResponseEntity.ok(new ApiResponse("Expense updated successfully.", true));
                } else {
                    expense.setId(user.getId());

                    expenseRepository.save(expense);
                      expenseRepository.flush();

                    return ResponseEntity.ok(new ApiResponse("New expense created successfully.", true));
                }
            } else {
                System.out.println("User not found. Please Signup.");
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/signup.html");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            }
        } catch (OptimisticLockException ole) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Optimistic lock failure: " + ole.getMessage(), false));
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Error occurred while saving expense: " + e.getMessage(), false));
        }
    }

    @Transactional
    @PostMapping("/initialize-expenses")
    public ResponseEntity<?> initializeExpenses() {
        try {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                Optional<Expense> existingExpenseOpt = expenseRepository.findById(user.getId());
                if (!existingExpenseOpt.isPresent()) {
                    Expense newExpense = new Expense();
                    newExpense.setId(user.getId());
                    newExpense.setEmail(user.getEmail());
                    newExpense.setSpend(0);
                    newExpense.setBudget(0);
                    newExpense.setMargin(0);

                    expenseRepository.save(newExpense);
                    expenseRepository.flush();
                }
            }
            return ResponseEntity.ok(new ApiResponse("Expenses initialized successfully.", true));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Error initializing expenses: " + e.getMessage(), false));
        }
    }
}





















