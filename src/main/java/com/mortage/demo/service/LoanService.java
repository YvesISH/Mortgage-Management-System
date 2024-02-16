package com.mortage.demo.service;

import com.mortage.demo.Entity.Loan;
import com.mortage.demo.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepo;

    @Autowired
    public LoanService(LoanRepository loanRepo) {
        this.loanRepo = loanRepo;
    }

    public Loan createLoan(Loan loan) {
        if (!isNumeric(String.valueOf(loan.getPrincipal())) || !isNumeric(String.valueOf(loan.getAnnualInterestRate())) || !isNumeric(String.valueOf(loan.getYears()))) {
            throw new IllegalArgumentException("Principal, annual interest rate, and years must be numeric values");
        }

        if (loan.getPrincipal() <= 0 || loan.getAnnualInterestRate() <= 0 || loan.getYears() <= 0) {
            throw new IllegalArgumentException("Principal, annual interest rate, and years must be positive numbers");
        }

        // Calculate mortgage and save loan
        double mortgage = calculateMortgage(loan.getPrincipal(), loan.getAnnualInterestRate(), loan.getYears());
        loan.setMortgage(mortgage);
        return loanRepo.save(loan);
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // This regex matches positive and negative decimal numbers
    }

    // Method to calculate mortgage
    double calculateMortgage(int principal, float annualInterestRate, byte years) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        float monthlyInterest = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = years * MONTHS_IN_YEAR;

        return principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)) /
                (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }
}
