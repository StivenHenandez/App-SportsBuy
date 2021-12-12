package com.example.sportsbuy.DataBase;

import java.util.List;

public interface articuloRepository {

    List<articuloEntity> obtenerTodosLosValores();

    List<articuloEntity> buscarArticuloPorCategoria(String categoria);

    void insertArticulo(articuloEntity articulo);
    void updateArticulo(articuloEntity articulo);
    void deleteArticulo(articuloEntity articulo);
}
