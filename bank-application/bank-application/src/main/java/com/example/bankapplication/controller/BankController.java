package com.example.bankapplication.controller;

import com.example.bankapplication.dao.CustomerCRUD;
import com.example.bankapplication.dao.TransactionCRUD;
import com.example.bankapplication.pojo.Customer;
import com.example.bankapplication.pojo.CustomerRes;
import com.example.bankapplication.pojo.Transaction;
import com.example.bankapplication.pojo.TransactionRes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@OpenAPIDefinition(info = @Info(title = "Bank Accounts",
        description = "API for Bank transactions",
        version = "2.0",
        contact = @Contact(
                name = "Nandhini"
        )
))
@Controller
@RequestMapping(path = "/bankcustomer")
public class BankController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final int MIN_BALANCE = 1000;
    @Autowired
    private CustomerCRUD customerCRUD;
    @Autowired
    private TransactionCRUD transactionCRUD;

    @PostMapping(path = "/addCustomer")
    public @ResponseBody
    String addCustomer(@RequestBody Customer customer) {
        System.out.println("Request received to add Activity " + customer.getFirstName());
        if (customer.getuID() == null) return "Customer UID is required";
        com.example.bankapplication.dao.Customer customerDAO = new com.example.bankapplication.dao.Customer();
        customerDAO.copyFromPojo(customer);
        customerDAO.setAccountNumber(Math.abs(generateRandom()));
        customerDAO.setCreationDate(new Date());
        try {
            customerCRUD.save(customerDAO);
        } catch (Exception e) {
            System.out.println("Exception raised " + e.getMessage());
        }
        return "Customer account persisted successfully";
    }

    @PostMapping(path = "/transfer")
    public @ResponseBody
    String initiateTransfer(@RequestBody Transaction transaction) {
        System.out.println("Request Received to initiate transaction");
        if (transaction.getFromAccountNumber() == null || transaction.getToAccountNumber() == null)
            return "Please provide required details.";
        if(transaction.getFromAccountNumber().equals(transaction.getToAccountNumber())) return "Same account transfer is not possible.";
        if(transaction.getAmount() <= 50) return "Amount transfer should be great than or equal 500";
        com.example.bankapplication.dao.Customer senderDAO = customerCRUD.findByAccountNumber(transaction.getFromAccountNumber());
        com.example.bankapplication.dao.Customer receiverDAO = customerCRUD.findByAccountNumber(transaction.getToAccountNumber());
        if (senderDAO == null) return "No Account existing with provided sender details";
        if (receiverDAO == null) return "No Account existing with provided receiver details";
        if (senderDAO.getOpeningBal() < transaction.getAmount()) return "Insufficient funds";
        if ((senderDAO.getOpeningBal() - transaction.getAmount()) < 1000)
            return "Minimum balance is required to continue transaction, current balance - " + senderDAO.getOpeningBal();
        try {
            com.example.bankapplication.dao.Transaction transactionDAO = new com.example.bankapplication.dao.Transaction();
            transactionDAO.copyFromPojo(transaction);
            transactionDAO.setTransactionNumber(Math.abs(generateRandom()));
            transactionDAO.setTransactionDate(new Date());
            senderDAO.setOpeningBal(senderDAO.getOpeningBal() - transaction.getAmount());
            receiverDAO.setOpeningBal(receiverDAO.getOpeningBal() + transaction.getAmount());
            customerCRUD.save(senderDAO);
            customerCRUD.save(receiverDAO);
            transactionCRUD.save(transactionDAO);
        } catch (Exception e) {
            System.out.println("Exception raised during transaction");
            return "Transaction failure";
        }
        return "Transaction successful";
    }

    @GetMapping(path = "/customers")
    public @ResponseBody
    Iterable<CustomerRes> getCustomers() {
        List<CustomerRes> customers = new ArrayList<>();
        try {
            Iterable<com.example.bankapplication.dao.Customer> daos = customerCRUD.findAll();
            for (com.example.bankapplication.dao.Customer customer : daos) {
                customers.add(customer.getPojo());
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch employee info");
        }
        return customers;
    }

    @GetMapping(path = "/transactions")
    @ResponseBody
    Iterable<TransactionRes> getTransactions(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year, @RequestParam(required = false) Long accountNumber, @RequestParam(required = false) String transactionType) throws Exception {
        Iterable<com.example.bankapplication.dao.Transaction> tDAOs = null;
        if (accountNumber == null && month == null && year == null) {
            tDAOs = transactionCRUD.findAll();
        } else if (accountNumber != null && month == null && year == null) {
            if (transactionType == null || transactionType.toLowerCase().equals("debit"))
                tDAOs = transactionCRUD.findByFromAccountNumber(accountNumber);
            else
                tDAOs = transactionCRUD.findByToAccountNumber(accountNumber);
        } else {
            if (month == null) month = LocalDate.now().getMonthValue();
            if (year == null) year = LocalDate.now().getYear();
            Date startDate = simpleDateFormat.parse(String.format("%s-%s-01 00:00:00", year, month));
            Date endDate = simpleDateFormat.parse(String.format("%s-%s-31 00:00:00", year, month));
            tDAOs = transactionCRUD.findByTransactionDateBetween(startDate, endDate);
        }
        List<TransactionRes> transactionList = new ArrayList<>();
        for (com.example.bankapplication.dao.Transaction t : tDAOs) {
            transactionList.add(t.getPojo());
        }
        return transactionList;
    }

    private long generateRandom() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }
        return Long.valueOf(sb.toString()).longValue();
    }
}
