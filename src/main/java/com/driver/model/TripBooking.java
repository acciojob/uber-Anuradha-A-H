package com.driver.model;


import com.driver.model.TripStatus;

import javax.persistence.*;

@Entity
public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripBookingId;
    private Integer distanceInKm;
    private String fromLocation;
    private String toLocation;
    private Integer bill;
    private TripStatus status;
    public TripBooking(){

    }
    public TripBooking(Integer tripBookingId, Integer distance, String fromLocation,String toLocation,Integer bill, TripStatus status)
    {
        this.tripBookingId = tripBookingId;
        this.distanceInKm = distance;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.bill = bill;
        this.status = status;
    }

    public Integer getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(Integer tripBookingId) {
        this.tripBookingId = tripBookingId;
    }


    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @ManyToOne
    @JoinColumn(name = "driverId")
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}