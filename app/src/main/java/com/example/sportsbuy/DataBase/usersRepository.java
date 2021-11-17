package com.example.sportsbuy.DataBase;

import java.util.List;

public interface usersRepository {

    List<usersEntity> buscarPorCorreo(String correo);
    List<usersEntity> obtenerTodosLosValores();

    void insertUsers(usersEntity user);
    void updateUsers(usersEntity user);
    void deleteUsers(usersEntity user);
}
