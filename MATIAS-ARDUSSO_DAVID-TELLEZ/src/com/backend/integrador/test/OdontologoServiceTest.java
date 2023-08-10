package com.backend.integrador.test;

import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class OdontologoServiceTest {
    //seteamos la implementacion de la IDao que queremos que se use, sea H2, en memoria, MySql, o la que tengamos implementada

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @Test
    public void deberiaAgregarUnOdontologo(){
        Odontologo odonto1 = new Odontologo(35465, "Pepito", "Perez");
        Odontologo odonto2 = new Odontologo(35466, "Matias", "Ardusso");
        Odontologo odontologoInsertado = odontologoService.registrarOdontologo(odonto1);
        Odontologo odontologoInsertado2 = odontologoService.registrarOdontologo(odonto2);
        assertNotNull(odontologoInsertado.getId());
    }


    @Test
    public void deberiaHaberUnaListaNoVacia(){
        List<Odontologo> odontologosTest = odontologoService.listarTodosLosOdontologos();

        assertFalse(odontologosTest.isEmpty());
        assertTrue(odontologosTest.size() >= 1);
    }
}
