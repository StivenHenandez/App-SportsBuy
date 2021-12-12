package com.example.sportsbuy.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

@Database(entities = {usersEntity.class,articuloEntity.class}, version=1)

public abstract class usersDB extends RoomDatabase {

    public static usersDB INSTANCE;
    //public static articuloDB INSTANCE2;

    public abstract articuloDAO articuloDAO();

    public abstract usersDAO usersDAO();

    public static usersDB getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,usersDB.class,"Usuarios.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
