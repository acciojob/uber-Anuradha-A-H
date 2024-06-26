package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    private String mobile;
    private String password;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<TripBooking> tripBookings = new ArrayList<>();

    public List<TripBooking> getTripBookings() {
        return tripBookings;
    }

    public void setTripBookings(List<TripBooking> tripBookings) {
        this.tripBookings = tripBookings;
    }
}