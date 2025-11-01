package com.example.backend;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final List<Map<String, Object>> expenses = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllExpenses() {
        return expenses;
    }

    @PostMapping
    public Map<String, Object> addExpense(@RequestBody Map<String, Object> expense) {
        expenses.add(expense);
        return expense;
    }
}
