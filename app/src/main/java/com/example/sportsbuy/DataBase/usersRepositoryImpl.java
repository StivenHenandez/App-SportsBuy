package com.example.sportsbuy.DataBase;

import java.util.List;

public class usersRepositoryImpl implements usersRepository{

    usersDAO dao;

    public usersRepositoryImpl(usersDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<usersEntity> buscarPorCorreo(String correo) {
        return dao.buscarUsuarioPorCorreo(correo);
    }

    @Override
    public List<usersEntity> obtenerTodosLosValores() {
        return dao.getAll();
    }

    @Override
    public void insertUsers(usersEntity user) {
        dao.insert(user);
    }

    @Override
    public void updateUsers(usersEntity user) {
        dao.update(user);
    }

    @Override
    public void deleteUsers(usersEntity user) {
        dao.delete(user);
    }
}
