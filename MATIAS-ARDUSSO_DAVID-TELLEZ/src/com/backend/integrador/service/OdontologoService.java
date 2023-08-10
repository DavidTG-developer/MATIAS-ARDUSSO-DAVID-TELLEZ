package com.backend.integrador.service;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;

public class OdontologoService {

    private final IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }

    public Odontologo buscarOdontologoPorId(int id){
        return odontologoIDao.buscarPorId(id);
    }
}
