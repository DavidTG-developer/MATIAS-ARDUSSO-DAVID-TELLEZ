package com.backend.integrador.test;

import com.backend.integrador.dao.impl.OdontologoDaoEnMemoria;
import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OdontologoServiceTest {
    //seteamos la implementacion de la IDao que queremos que se use, sea H2, en memoria, MySql, o la que tengamos implementada
    private List<Odontologo> listaOdontologosEnMemoria = new ArrayList<>();

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    OdontologoService odontologoService2 = new OdontologoService(new OdontologoDaoEnMemoria(listaOdontologosEnMemoria));


    @Test
    public void deberiaAgregarUnOdontologo(){
        Odontologo odonto1 = new Odontologo(35465, "Pepito", "Perez");
        Odontologo odonto2 = new Odontologo(35466, "Matias", "Ardusso");
        Odontologo odontologoInsertado = odontologoService.registrarOdontologo(odonto1);
        Odontologo odontologoInsertado2 = odontologoService.registrarOdontologo(odonto2);
        assertNotNull(odontologoInsertado.getId());
    }

    @Test
    public void deberiaAgregarUnOdontologoEnMemoria(){
        Odontologo odonto3 = new Odontologo(35487, "Andres", "Perez");
        Odontologo listaOdontologosEnMemoria1 = odontologoService2.registrarOdontologo(odonto3);
        assertNotNull(listaOdontologosEnMemoria1.getId());
    }




    @Test
    public void deberiaHaberUnaListaNoVacia(){
        List<Odontologo> odontologosTest = odontologoService.listarTodosLosOdontologos();
        assertFalse(odontologosTest.isEmpty());
        assertTrue(odontologosTest.size() >= 1);
    }
    @Test
    public void listaNoVaciaEnMemoria(){
        Odontologo odonto3 = new Odontologo(35487, "Andres", "Perez");
        Odontologo listaOdontologosEnMemoria1 = odontologoService2.registrarOdontologo(odonto3);
        Odontologo odonto4 = new Odontologo(35487, "David", "Tellez");
        Odontologo listaOdontologosEnMemoria2 = odontologoService2.registrarOdontologo(odonto4);
        listaOdontologosEnMemoria = odontologoService2.listarTodosLosOdontologos();
        assertFalse(listaOdontologosEnMemoria.isEmpty());
        assertTrue(listaOdontologosEnMemoria.size() >= 1);
    }
}
