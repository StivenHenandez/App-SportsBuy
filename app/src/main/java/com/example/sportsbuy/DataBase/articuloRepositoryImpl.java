package com.example.sportsbuy.DataBase;

import java.util.List;

public class articuloRepositoryImpl implements articuloRepository {

    articuloDAO DAO;

    @Override
    public List<articuloEntity> buscarArticuloPorCategoria(String categoria) {
        return DAO.buscarArticuloPorCategoria(categoria);
    }

    public articuloRepositoryImpl(articuloDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public List<articuloEntity> obtenerTodosLosValores() {
        return DAO.getAll();
    }

    @Override
    public void insertArticulo(articuloEntity articulo) {
        DAO.insert(articulo);
    }
    @Override
    public void updateArticulo(articuloEntity articulo) {
        DAO.update(articulo);
    }

    @Override
    public void deleteArticulo(articuloEntity articulo) {
        DAO.delete(articulo);
    }
}
