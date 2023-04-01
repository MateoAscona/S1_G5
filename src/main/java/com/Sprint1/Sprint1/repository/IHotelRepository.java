package com.Sprint1.Sprint1.repository;

import com.Sprint1.Sprint1.model.HotelObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelRepository extends JpaRepository<HotelObject, Integer> {
}
