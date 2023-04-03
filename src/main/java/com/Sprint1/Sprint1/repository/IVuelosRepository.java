package com.Sprint1.Sprint1.repository;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IVuelosRepository extends JpaRepository<VuelosObject, Integer> {

    @Query("SELECT h FROM VuelosObject h WHERE h.fechaIda <= :fechaPartida AND h.fechaVuelta >= :fechaRegreso AND h.destino = :destino")
    List<VuelosObject> findByFechasYDestino(@Param("fechaPartida") LocalDate fechaPartida, @Param("fechaRegreso") LocalDate fechaRegreso, @Param("destino") String destino);

}
