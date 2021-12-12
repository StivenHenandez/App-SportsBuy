package com.example.sportsbuy.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Articulos")
public class articuloEntity {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "Id")
    int itemId;

    @ColumnInfo(name = "Categoria")
    String categoria;


    @ColumnInfo(name = "Idfoto")
    int idfoto;

    @ColumnInfo(name = "Nombre")
    String nombre;

    @ColumnInfo(name = "Descripcion")
    String descripcion;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
