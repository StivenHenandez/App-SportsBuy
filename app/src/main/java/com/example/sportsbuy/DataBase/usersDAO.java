package com.example.sportsbuy.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface usersDAO {

    @Query("SELECT * FROM Usuarios")
    List<usersEntity> getAll();

    @Query("SELECT * FROM Usuarios where Correo = :correo")
    List<usersEntity> buscarUsuarioPorCorreo(String correo);

    /*
    @Query("SELECT * FROM Usuarios ORDER BY Id DESC LIMIT 1")
    List<usersEntity> getLastEntry();
     */
    @Insert
    void insert (usersEntity user);
    @Update
    void update (usersEntity user);
    @Delete
    void delete (usersEntity user);


}
