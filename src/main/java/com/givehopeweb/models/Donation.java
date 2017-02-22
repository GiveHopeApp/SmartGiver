//package com.givehopeweb.models;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
///**
// * Created by David on 2/17/17.
// */
//@Entity
//@Table (name = "donations")
//public class Donation {
//
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column (nullable = false, precision = 4)
//    private BigDecimal amount;
//
//    @ManyToOne
//    @JoinColumn (name = "charity_id")
//    private Charity charity;
//
//    @ManyToOne
//    @JoinColumn (name = "user_id")
//    private User user;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Charity getCharity() {
//        return charity;
//    }
//
//    public void setCharity(Charity charity) {
//        this.charity = charity;
//    }
//}
