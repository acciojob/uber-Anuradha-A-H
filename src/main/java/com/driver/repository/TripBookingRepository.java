package com.driver.repository;
import com.driver.model.Customer;
import com.driver.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer>{
    List<TripBooking> findByCustomer(Customer customer);
}
