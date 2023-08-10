package com.backend.integrador.test;

import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class OdontologoServiceTest {
    //seteamos la implementacion de la IDao que queremos que se use, sea H2, en memoria, MySql, o la que tengamos implementada

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @Test
    public void deberiaAgregarUnOdontologo(){
        Odontologo odonto1 = new Odontologo(35465, "Pepito", "Perez");
        Odontologo odontologoInsertado = odontologoService.registrarOdontologo(odonto1);

        assertNotNull(odontologoInsertado.getId());
    }
}
