package com.ishant.mscs.entity;


import javax.persistence.*;

@Entity
@Table(name="Journey")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String source;

    private String destination;

    private long cardNumber;

    private String status;

    private double fair;

    public double getFair() {
        return fair;
    }

    public void setFair(double fair) {
        this.fair = fair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

}
