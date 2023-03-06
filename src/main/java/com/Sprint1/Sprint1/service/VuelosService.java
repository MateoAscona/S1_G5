package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.repository.VuelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VuelosService {
    @Autowired
    VuelosRepository vuelosRepository;

    public List<VuelosObject> listarVuelos() {
        return vuelosRepository.listaDeVuelos();
    }

}
