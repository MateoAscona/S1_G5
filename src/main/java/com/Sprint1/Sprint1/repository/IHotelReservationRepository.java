package com.Sprint1.Sprint1.repository;

import com.Sprint1.Sprint1.model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelReservationRepository extends JpaRepository<HotelReservation, Integer> {
}
