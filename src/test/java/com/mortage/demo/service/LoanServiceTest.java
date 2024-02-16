package com.mortage.demo.service;

import com.mortage.demo.Entity.Loan;
import com.mortage.demo.Repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    private LoanRepository loanRepo;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateLoanWithValidData() {
        // Arrange
        Loan loan = new Loan();
        loan.setPrincipal(10000);
        loan.setAnnualInterestRate(5.0f);
        loan.setYears((byte) 10);

        // Mock the behavior of loanRepo.save(loan)
        when(loanRepo.save(loan)).thenReturn(loan);

        // Act
        Loan createdLoan = loanService.createLoan(loan);

        // Assert
        assertNotNull(createdLoan);
        verify(loanRepo, times(1)).save(loan); // Verify that save method is called only once

    }



    @Test
    void testCalculateMortgage() {
        // Arrange
        LoanService loanService = new LoanService(null); // Since we don't need LoanRepo for this test

        // Test case 1: Principal = 100000, Annual Interest Rate = 5%, Years = 30
        assertEquals(536.82, loanService.calculateMortgage(100000, 5.0f, (byte) 30), 0.01);

        // Test case 2: Principal = 200000, Annual Interest Rate = 4.5%, Years = 15
        assertEquals(1529.996, loanService.calculateMortgage(200000, 4.5f, (byte) 15), 0.01);

        // Add more test cases for different scenarios as needed
    }
}
