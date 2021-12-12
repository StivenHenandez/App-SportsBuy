package com.example.sportsbuy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportsbuy.DataBase.articuloDAO;
import com.example.sportsbuy.DataBase.articuloEntity;
import com.example.sportsbuy.DataBase.articuloRepositoryImpl;
import com.example.sportsbuy.DataBase.usersDAO;
import com.example.sportsbuy.DataBase.usersDB;
import com.example.sportsbuy.DataBase.usersEntity;
import com.example.sportsbuy.DataBase.usersRepository;
import com.example.sportsbuy.DataBase.usersRepositoryImpl;
import com.example.sportsbuy.Fragmentos.FragmentAccesorios;
import com.example.sportsbuy.Fragmentos.FragmentRopa;
import com.example.sportsbuy.Fragmentos.FragmentZapatos;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navView;
    TextView tv_correo, tv_username;
    String Vcorreo;

    Menu menu;

    //Variables Fragmento
    LinearLayout conte;
    FragmentManager fragment;
    FragmentTransaction fragmentTransaction;

    //FireBase
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //Firestore database
    private FirebaseFirestore firestoredb;
    //vista

    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

    //ariticulo
    private articuloEntity ClsArticulo;

    private articuloDAO daoArticulo;

    private articuloRepositoryImpl repoArticulo;

    String cedula, nombre, apellido,correo,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = usersDB.getInstance(this.getApplicationContext());
        dao = db.usersDAO();
        repo = new usersRepositoryImpl(dao);
        user = new usersEntity();

        daoArticulo = db.articuloDAO();
        repoArticulo = new articuloRepositoryImpl(daoArticulo);
        //this.CargarArticulosBaseDAtos();

        toolbar = findViewById(R.id.CMA_toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.MAdrawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.apertura, R.string.cierre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navView = findViewById(R.id.AMnav_view);
        navView.setNavigationItemSelectedListener(this);

        Vcorreo = this.getIntent().getStringExtra("correo");

        this.Datosuser(Vcorreo);
        //this.setHeaderView(Vcorreo);

        //tv_username = findViewById(R.id.MA_tv_username);

        //insertar fragmento

        fragment = getSupportFragmentManager();
        fragmentTransaction = fragment.beginTransaction();

        FragmentRopa fragmentRopa = new FragmentRopa();
        fragmentTransaction.add(R.id.viewGroup_conte, fragmentRopa);
        fragmentTransaction.commit();

        //FireBase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        //RecyclerView conte = findViewById(R.id.layout);

        //View conte = this.findViewById(R.id.cordinatorConte);
        //LinearLayout linear = conte.findViewById(R.id.viewGroup_conte);

        //fragmentTransaction.add(conte.findViewById(R.id.viewGroup_conte),fragmentRopa);

        //viewGroup_conte
        //llenar datos de view,Appbar

        firestoredb = FirebaseFirestore.getInstance();

        View headerView = navView.getHeaderView(0);
        tv_correo = headerView.findViewById(R.id.MA_tv_correo);
        tv_username = headerView.findViewById(R.id.MA_tv_username);


        firestoredb.collection("Users").document(Vcorreo).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    String Vcorreo = documentSnapshot.get("Correo").toString();
                    String vnombre = documentSnapshot.get("Nombre").toString();
                    String apellido = documentSnapshot.get("Apellido").toString();

                    String Nombre = vnombre + " " + apellido;

                    tv_correo.setText(Vcorreo);
                    tv_username.setText(Nombre);
                } catch (Exception e) {
                    System.out.println("Error3 : " + e.getLocalizedMessage());
                }
            }
        });

        //Cargar Articulos base de datos
        //Leer Datos txt
        List<String> Nombres = new ArrayList<>();
        List<String> Categorias = new ArrayList<>();
        List<String> Descripciones = new ArrayList<>();
        List<Integer> Idimagen = new ArrayList<>();

        String linea;

        InputStream is = this.getResources().openRawResource(R.raw.descripcion_ropa_img1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {
            if(is!=null){
                while ((linea =reader.readLine())!=null){
                    Nombres.add(linea.split(";")[1]);
                    Categorias.add(linea.split(";")[0]);
                    Descripciones.add(linea.split(";")[2]);
                }
            }
            is.close();

            //String nombres[] = datostxt.toArray(new String[datostxt.size()]);
            //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>();

        }catch (IOException e){
            System.out.println("Error :"+e.getLocalizedMessage());
        }
        //cargar imagenes
        //imagenes Ropa
        int Ropaimg1 = R.drawable.ropa_img_1;
        int Ropaimg2 = R.drawable.ropa_img_2;
        int Ropaimg3 = R.drawable.ropa_img_3;
        int Ropaimg4 = R.drawable.ropa_img_4;
        int Ropaimg5 = R.drawable.ropa_img_5;
        int Ropaimg6 = R.drawable.ropa_img_6;
        int Ropaimg7 = R.drawable.ropa_img_7;
        int Ropaimg8 = R.drawable.ropa_img_8;
        int Ropaimg9 = R.drawable.ropa_img_9;
        int Ropaimg10 = R.drawable.ropa_img_10;

        Idimagen.add(Ropaimg1);
        Idimagen.add(Ropaimg2);
        Idimagen.add(Ropaimg3);
        Idimagen.add(Ropaimg4);
        Idimagen.add(Ropaimg5);
        Idimagen.add(Ropaimg6);
        Idimagen.add(Ropaimg7);
        Idimagen.add(Ropaimg8);
        Idimagen.add(Ropaimg9);
        Idimagen.add(Ropaimg10);

        //Imagenes Accesorios

        int Accesoriosimg1 = R.drawable.accesorios_img_1;
        int Accesoriosimg2 =R.drawable.accesorios_img_2;
        int Accesoriosimg3 = R.drawable.accesorios_img_3;
        int Accesoriosimg4 = R.drawable.accesorios_img_4;
        int Accesoriosimg5 = R.drawable.accesorios_img_5;
        int Accesoriosimg6 = R.drawable.accesorios_img_6;
        int Accesoriosimg7 = R.drawable.accesorios_img_7;
        int Accesoriosimg8 = R.drawable.accesorios_img_8;
        int Accesoriosimg9 = R.drawable.accesorios_img_9;
        int Accesoriosimg10 = R.drawable.accesorios_img_10;

        Idimagen.add(Accesoriosimg1);
        Idimagen.add(Accesoriosimg2);
        Idimagen.add(Accesoriosimg3);
        Idimagen.add(Accesoriosimg4);
        Idimagen.add(Accesoriosimg5);
        Idimagen.add(Accesoriosimg6);
        Idimagen.add(Accesoriosimg7);
        Idimagen.add(Accesoriosimg8);
        Idimagen.add(Accesoriosimg9);
        Idimagen.add(Accesoriosimg10);

        //Imagenes Zapatos
        int Zapatosimg1 = R.drawable.zapatos_img_1;
        int Zapatosimg2 = R.drawable.zapatos_img_2;
        int Zapatosimg3 = R.drawable.zapatos_img_3;
        int Zapatosimg4 = R.drawable.zapatos_img_4;
        int Zapatosimg5 = R.drawable.zapatos_img_5;
        int Zapatosimg6 = R.drawable.zapatos_img_6;
        int Zapatosimg7 = R.drawable.zapatos_img_7;
        int Zapatosimg8 = R.drawable.zapatos_img_8;
        int Zapatosimg9 = R.drawable.zapatos_img_9;
        int Zapatosimg10 = R.drawable.zapatos_img_10;

        Idimagen.add(Zapatosimg1);
        Idimagen.add(Zapatosimg2);
        Idimagen.add(Zapatosimg3);
        Idimagen.add(Zapatosimg4);
        Idimagen.add(Zapatosimg5);
        Idimagen.add(Zapatosimg6);
        Idimagen.add(Zapatosimg7);
        Idimagen.add(Zapatosimg8);
        Idimagen.add(Zapatosimg9);
        Idimagen.add(Zapatosimg10);

        int h = 0;
        for (int i = 0;i<Nombres.size();i++){

            System.out.println(Categorias.size());
            System.out.println(Nombres.size());
            String nombreImg2 = Nombres.get(i);
            String Categoria2 = Categorias.get(i);
            String descripcionImg2 = Descripciones.get(i);
            int imagen = Idimagen.get(i);

            articuloEntity vector [] = new articuloEntity[Nombres.size()];
            vector [i] = new articuloEntity();
            vector [i].setCategoria(Categoria2);
            vector [i].setIdfoto(imagen);
            vector [i].setNombre(nombreImg2);
            vector [i].setDescripcion(descripcionImg2);

            databaseReference.child("Articulos").child(vector[i].getCategoria()).child(vector[i].getNombre()).setValue(vector[i]);
/*
            if(vector[i].getCategoria().equals("Ropa")){
                databaseReference.child("Articulos").child("Ropa").child(vector[i].getNombre()).setValue(vector[i]);
            }else{
                h++;
                System.out.println("No es categoria Ropa" + h);
            }*/
            /* public articuloEntity(String categoria, int idfoto, String nombre, String descripcion) {
        this.categoria = categoria;
        this.idfoto = idfoto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }*/
        }
    }
/*
    public void setHeaderView(String Vcorreo){

        firestoredb.collection("Users").document(Vcorreo).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    String Vcorreo = documentSnapshot.get("Correo").toString();
                    String vnombre  = documentSnapshot.get("Nombre").toString();
                    String apellido = documentSnapshot.get("Apellido").toString();

                    String Nombre = vnombre+" "+apellido;


                    tv_correo.setText(Vcorreo);
                    tv_username.setText(Nombre);
                }catch (Exception  e){
                    System.out.println("Error3 : "+e.getLocalizedMessage());
                }
            }
        });

    }*/

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
            case R.id.salir:

                finish();

                break;
        }
        drawerLayout = findViewById(R.id.MAdrawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    public void btn_ropa(View view){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentRopa fragmentRopa = new FragmentRopa();
        fragmentTransaction.replace(R.id.viewGroup_conte, fragmentRopa);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void btn_accesorios(View view) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        FragmentAccesorios fragmentAccesorios=new FragmentAccesorios();
        fragmentTransaction.replace(R.id.viewGroup_conte,fragmentAccesorios);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void btn_tenis(View view) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        FragmentZapatos fragmentZapatos = new FragmentZapatos();
        fragmentTransaction.replace(R.id.viewGroup_conte,fragmentZapatos);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void CargarArticulosBaseDAtos(){
        //Leer Datos Artchivo txt
        List<String> Nombres = new ArrayList<>();
        List<String> Categorias = new ArrayList<>();
        List<String> Descripciones = new ArrayList<>();
        List<Integer> Idimagen = new ArrayList<>();

        String linea;

        InputStream is = this.getResources().openRawResource(R.raw.descripcion_ropa_img1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {
            if(is!=null){
                while ((linea =reader.readLine())!=null){
                    Nombres.add(linea.split(";")[1]);
                    Categorias.add(linea.split(";")[0]);
                    Descripciones.add(linea.split(";")[2]);
                }
            }
            is.close();

            //String nombres[] = datostxt.toArray(new String[datostxt.size()]);
            //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>();

        }catch (IOException e){
            System.out.println("Error :"+e.getLocalizedMessage());
        }

        //List<articuloEntity> list ;

        //imagenes Ropa
        int Ropaimg1 = R.drawable.ropa_img_1;
        int Ropaimg2 = R.drawable.ropa_img_2;
        int Ropaimg3 = R.drawable.ropa_img_3;
        int Ropaimg4 = R.drawable.ropa_img_4;
        int Ropaimg5 = R.drawable.ropa_img_5;
        int Ropaimg6 = R.drawable.ropa_img_6;
        int Ropaimg7 = R.drawable.ropa_img_7;
        int Ropaimg8 = R.drawable.ropa_img_8;
        int Ropaimg9 = R.drawable.ropa_img_9;
        int Ropaimg10 = R.drawable.ropa_img_10;

        Idimagen.add(Ropaimg1);
        Idimagen.add(Ropaimg2);
        Idimagen.add(Ropaimg3);
        Idimagen.add(Ropaimg4);
        Idimagen.add(Ropaimg5);
        Idimagen.add(Ropaimg6);
        Idimagen.add(Ropaimg7);
        Idimagen.add(Ropaimg8);
        Idimagen.add(Ropaimg9);
        Idimagen.add(Ropaimg10);

        //Imagenes Accesorios

        int Accesoriosimg1 = R.drawable.accesorios_img_1;
        int Accesoriosimg2 =R.drawable.accesorios_img_2;
        int Accesoriosimg3 = R.drawable.accesorios_img_3;
        int Accesoriosimg4 = R.drawable.accesorios_img_4;
        int Accesoriosimg5 = R.drawable.accesorios_img_5;
        int Accesoriosimg6 = R.drawable.accesorios_img_6;
        int Accesoriosimg7 = R.drawable.accesorios_img_7;
        int Accesoriosimg8 = R.drawable.accesorios_img_8;
        int Accesoriosimg9 = R.drawable.accesorios_img_9;
        int Accesoriosimg10 = R.drawable.accesorios_img_10;

        Idimagen.add(Accesoriosimg1);
        Idimagen.add(Accesoriosimg2);
        Idimagen.add(Accesoriosimg3);
        Idimagen.add(Accesoriosimg4);
        Idimagen.add(Accesoriosimg5);
        Idimagen.add(Accesoriosimg6);
        Idimagen.add(Accesoriosimg7);
        Idimagen.add(Accesoriosimg8);
        Idimagen.add(Accesoriosimg9);
        Idimagen.add(Accesoriosimg10);

        //Imagenes Zapatos
        int Zapatosimg1 = R.drawable.zapatos_img_1;
        int Zapatosimg2 = R.drawable.zapatos_img_2;
        int Zapatosimg3 = R.drawable.zapatos_img_3;
        int Zapatosimg4 = R.drawable.zapatos_img_4;
        int Zapatosimg5 = R.drawable.zapatos_img_5;
        int Zapatosimg6 = R.drawable.zapatos_img_6;
        int Zapatosimg7 = R.drawable.zapatos_img_7;
        int Zapatosimg8 = R.drawable.zapatos_img_8;
        int Zapatosimg9 = R.drawable.zapatos_img_9;
        int Zapatosimg10 = R.drawable.zapatos_img_10;

        Idimagen.add(Zapatosimg1);
        Idimagen.add(Zapatosimg2);
        Idimagen.add(Zapatosimg3);
        Idimagen.add(Zapatosimg4);
        Idimagen.add(Zapatosimg5);
        Idimagen.add(Zapatosimg6);
        Idimagen.add(Zapatosimg7);
        Idimagen.add(Zapatosimg8);
        Idimagen.add(Zapatosimg9);
        Idimagen.add(Zapatosimg10);



/*
        for (int i = 0;i<Nombres.size();i++){

            System.out.println(Categorias.size());
            System.out.println(Nombres.size());
            String nombreImg2 = Nombres.get(i);
            String Categoria2 = Categorias.get(i);
            String descripcionImg2 = Descripciones.get(i);
            int imagen = Idimagen.get(i);

            articuloEntity vector [] = new articuloEntity[Nombres.size()];

            vector [i] = new articuloEntity(Categoria2,imagen,nombreImg2,descripcionImg2);
            String dbnombre  = vector[i].getNombre();

            databaseReference.child("Usuarios").child(dbnombre).setValue(vector[i]);
        }

/*
        if (repoArticulo.obtenerTodosLosValores().size()>0){
            System.out.println("Ya hay valores");
        }else{
            for (int i = 0;i<Nombres.size();i++){

                System.out.println(Categorias.size());
                System.out.println(Nombres.size());
                String nombreImg2 = Nombres.get(i);
                String Categoria2 = Categorias.get(i);
                String descripcionImg2 = Descripciones.get(i);
                int imagen = Idimagen.get(i);

                articuloEntity vector [] = new articuloEntity[Nombres.size()];

                vector [i] = new articuloEntity(Categoria2,imagen,nombreImg2,descripcionImg2);

                databaseReference.child("Usuarios").child(vector[i].getNombre().toString()).setValue(vector[i]);
            }
        }
        /*
        databaseReference.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

/*
    public void FABmain(View view) {

        Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_LONG);
        toast.setText("Clic Floating Action Button");
        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
        toast.show();

    }*/

}