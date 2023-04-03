package com.Sprint1.Sprint1.repository;

import com.Sprint1.Sprint1.model.HotelObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IHotelRepository extends JpaRepository<HotelObject, Integer> {

    @Query("SELECT h FROM HotelObject h WHERE h.disponibleDesde <= :fechaPartida AND h.disponibleHasta >= :fechaRegreso AND h.lugarCiudad = :destino")
    List<HotelObject> findByFechasYDestino(@Param("fechaPartida") LocalDate fechaPartida, @Param("fechaRegreso") LocalDate fechaRegreso, @Param("destino") String destino);

}
