package com.example.sportsbuy.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuarios")
public class usersEntity {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "Id")
    int itemId;

    @ColumnInfo(name = "Cedula")
    String cedula;

    @ColumnInfo(name = "Nombre")
    String nombre;

    @ColumnInfo(name = "Apellido")
    String apellido;

    @ColumnInfo(name = "Correo")
    String correo;

    @ColumnInfo(name = "Clave")
    String clave;

    @ColumnInfo(name = "Telefono")
    String telefono;


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
