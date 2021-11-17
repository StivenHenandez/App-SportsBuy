package com.example.sportsbuy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sportsbuy.DataBase.usersDAO;
import com.example.sportsbuy.DataBase.usersDB;
import com.example.sportsbuy.DataBase.usersEntity;
import com.example.sportsbuy.DataBase.usersRepository;
import com.example.sportsbuy.DataBase.usersRepositoryImpl;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navView;
    TextView tv_correo, tv_username;
    String Vcorreo;
    Menu menu;


    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

    String cedula, nombre, apellido,correo,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = usersDB.getInstance(this.getApplicationContext());
        dao = db.usersDAO();
        repo = new usersRepositoryImpl(dao);
        user = new usersEntity();

        toolbar = findViewById(R.id.CMA_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.MAdrawer_layout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.apertura,R.string.cierre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navView = findViewById(R.id.AMnav_view);
        navView.setNavigationItemSelectedListener(this);

        Vcorreo= this.getIntent().getStringExtra("correo");

        this.Datosuser(Vcorreo);
        this.setheaderView(correo,nombre,apellido);

        //tv_username = findViewById(R.id.MA_tv_username);

    }

    public void setheaderView(String Vcorreo, String nombre, String apellido){
        View headerView = navView.getHeaderView(0);
        tv_correo = headerView.findViewById(R.id.MA_tv_correo);
        tv_username = headerView.findViewById(R.id.MA_tv_username);

        String Nonbre = nombre +" "+apellido;

        tv_correo.setText(Vcorreo);
        tv_username.setText(Nonbre);
    }

    public void Datosuser(String Bcorreo){

        List<usersEntity> user = repo.buscarPorCorreo(Vcorreo);

        for (usersEntity i:user) {

            cedula = i.getCedula();
            nombre = i.getNombre();
            apellido = i.getApellido();
            correo = i.getCorreo();
            telefono = i.getTelefono();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Perfil:
                Toast.makeText(MainActivity.this, "Perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configuracion:
                Toast.makeText(MainActivity.this, "Configuracion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editarperfil:
                Toast.makeText(MainActivity.this, "Editar Perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.infoapp:
                Toast.makeText(MainActivity.this, "Info App", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout = findViewById(R.id.MAdrawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
/*
    public void FABmain(View view) {

        Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_LONG);
        toast.setText("Clic Floating Action Button");
        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
        toast.show();

    }*/

}