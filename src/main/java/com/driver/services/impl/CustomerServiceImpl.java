package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> customer = customerRepository2.findById(customerId);
		if(customer.isEmpty()) return;
		Customer customerd = customer.get();
//		List<TripBooking> trip = tripBookingRepository2.findByCustomerId(customerId);
//		if(trip.size()>0) tripBookingRepository2.deleteAll(trip);
		customerRepository2.delete(customerd);

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		Optional<Customer> customer = customerRepository2.findById(customerId);
		if(customer.isEmpty())
		{
			return null;
		}
		Optional<Driver> driver = driverRepository2.findFirstByAvailabilityOrderByDriverIdAsc(true);
		if(driver.isEmpty())
		{
			throw new Exception("No cab available!");
		}
		Driver driverdtl = driver.get();

		Cab cabdtl = driverdtl.getCab();
		Integer price = cabdtl.getPerKmRate()*distanceInKm;

		Customer customerdtl = customer.get();

		TripBooking booking = new TripBooking();
		booking.setCustomer(customerdtl);
		booking.setFromLocation(fromLocation);
		booking.setToLocation(toLocation);
		booking.setStatus(TripStatus.CONFIRMED);
		booking.setDistanceInKm(distanceInKm);
		booking.setDriver(driverdtl);
		booking.setCustomer(customerdtl);
		booking.setBill(price);

		return tripBookingRepository2.save(booking);


	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking booking = tripBookingRepository2.findById(tripId).get();
		booking.setStatus(TripStatus.CANCELED);
		Driver driver  = booking.getDriver();
		driver.setAvailability(true);
		driverRepository2.save(driver);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking booking = tripBookingRepository2.findById(tripId).get();
		booking.setStatus(TripStatus.COMPLETED);
		Driver driver  = booking.getDriver();
		driver.setAvailability(true);
		driverRepository2.save(driver);
	}
}
