package com.example.sportsbuy.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface articuloDAO {

    @Query("SELECT * FROM Articulos")
    List<articuloEntity> getAll();

    @Query("SELECT * FROM Articulos where Categoria = :categoria")
    List<articuloEntity> buscarArticuloPorCategoria(String categoria);

    /*
    @Query("SELECT * FROM Usuarios ORDER BY Id DESC LIMIT 1")
    List<usersEntity> getLastEntry();
     */

    @Insert
    void insert (articuloEntity articulo);
    @Update
    void update (articuloEntity articulo);
    @Delete
    void delete (articuloEntity articulo);
}
