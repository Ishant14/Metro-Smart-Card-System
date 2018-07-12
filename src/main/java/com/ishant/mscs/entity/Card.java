package com.ishant.mscs.entity;


import javax.persistence.*;

@Entity
@Table(name="Card")
public class Card  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardNumber;

    private double balance;


    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
