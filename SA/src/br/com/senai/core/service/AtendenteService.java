package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoAtendente;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Atendente;

public class AtendenteService {
    private DaoAtendente dao;

    public AtendenteService() {
        this.dao = FactoryDao.getInstance().getDaoPostgresqlAtendente();
    }

    public List<Atendente> listarAtendente() {
        return dao.listarAtendente();
    }
}
