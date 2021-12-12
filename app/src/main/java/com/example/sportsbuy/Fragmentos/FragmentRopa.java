package com.example.sportsbuy.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsbuy.AdapterDatos;
import com.example.sportsbuy.DataBase.articuloDAO;
import com.example.sportsbuy.DataBase.articuloEntity;
import com.example.sportsbuy.DataBase.articuloRepositoryImpl;
import com.example.sportsbuy.DataBase.usersDB;
import com.example.sportsbuy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentRopa extends Fragment {

    private usersDB db;
    private articuloDAO dao;
    private articuloRepositoryImpl repo;
    //private articuloEntity articulo;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private articuloEntity articuloEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = usersDB.getInstance(this.getContext());
        dao = db.articuloDAO();
        repo = new articuloRepositoryImpl(dao);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<articuloEntity> list = new ArrayList<>();

        View fragment = inflater.inflate(R.layout.fragment_ropa, container, false);

        RecyclerView recyclerView = fragment.findViewById(R.id.recyclerViewRopa);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //list = new ArrayList<>();




        //list.add(articulo);




        //list.add(articulo2);


        //user.setCedula(cedula);
        //user.setNombre(nombre);
        //user.setApellido(apellido);
        //user.setCorreo(correo);
        //user.setClave(contraseña);
        //user.setTelefono(telefono);



        //list = repo.buscarArticuloPorCategoria("Ropa");


        databaseReference.child("Articulos").child("Ropa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int h = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println("El contador va en : "+h);
                    articuloEntity persona = dataSnapshot.getValue(articuloEntity.class);
                    h = h+1;
                }

                articuloEntity vector[] = new articuloEntity[h];
                int i = 0;
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    articuloEntity usuario = dataSnapshot.getValue(articuloEntity.class);
                    //cedula.setText(persona.cedula);

                    vector[i] = usuario;
                    list.add(vector[i]);
                    i = i + 1;
                    System.out.println("En contador va en : "+i);
                }
                int vlue = vector.length;
                System.out.println("tamaño vector : "+vlue);
                int value = list.size();
                System.out.println("tamaño lista :"+value);

                AdapterDatos adapter = new AdapterDatos(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
    databaseReference.child("Usuarios").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            int h = 0;
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                usersEntity persona = dataSnapshot.getValue(usersEntity.class);
                h = h+1;
            }

            usersEntity vector[] = new usersEntity[h];
            int i = 0;
            listusers.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                usersEntity usuario = dataSnapshot.getValue(usersEntity.class);
                //cedula.setText(persona.cedula);

                vector[i] = usuario;
                listusers.add(vector[i]);
                i = i + 1;
                System.out.println("En contador va en : "+i);
            }
            int vlue = vector.length;
            System.out.println("tamaño vector : "+vlue);
            int value = listusers.size();
            System.out.println("tamaño lista :"+value);

            if (vector[0]!=null) {

                usersEntity usuario = null;

                for (usersEntity user:listusers) {
                    if (user.getCedula().equals(Vcedula)){
                        usuario = user;
                        break;
                    }
                }

                //et_cedula,et_nombre,et_apellido,et_correo,et_contraseña,et_telefono;
                if (usuario != null){
                    et_cedula.setText(usuario.getCedula());
                    et_nombre.setText(usuario.getNombre());
                    et_apellido.setText(usuario.getApellido());
                    et_correo.setText(usuario.getCorreo());
                    et_contraseña.setText(usuario.getClave());
                    et_telefono.setText(usuario.getTelefono());
                }else{
                    Toast.makeText(Create_Account.this, "No se Encontro el usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Create_Account.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });*/

        /*
        List <String> nombre = new ArrayList();
        String nombreImg1 = "Nombre imagen 1";
        String nombreImg2 = "Nombre imagen 2";
        nombre.add(nombreImg1);
        nombre.add(nombreImg2);

        List <String> descripcion = new ArrayList();
        String descripcionImg1 = "descripcion imagen 1";
        String descripcionImg2 = "descripcion imagen 2";
        descripcion.add(descripcionImg1);
        descripcion.add(descripcionImg2);

        List <Integer> imagenes = new ArrayList();
        int img = R.drawable.account_icon;
        int img2 = R.drawable.img;
        imagenes.add(img);
        imagenes.add(img2);

        Bundle bundle= new Bundle();
        System.out.println(nombre.get(0));
        bundle.putString("name", nombre.get(0));
        bundle.putString("Description",descripcion.get(0));
        bundle.putInt("imagen", imagenes.get(0));

        list.add(bundle);

        Bundle bundle2= new Bundle();
        System.out.println(nombre.get(1));
        bundle.putString("name", nombre.get(1));
        bundle.putString("Description",descripcion.get(1));
        bundle.putInt("imagen", imagenes.get(1));

        list.add(bundle2);

        /*
        for (int i = 0;i<=imagenes.size();i++){

            Bundle bundle= new Bundle();
            System.out.println(nombre.get(i));
            bundle.putString("name", nombre.get(i));
            bundle.putString("Description",descripcion.get(i));
            bundle.putInt("imagen", imagenes.get(i));

            list.add(bundle);
        }
        /*
        int img = R.drawable.account_icon;
        int img2 = R.drawable.img;

        List <Integer> imagenes = new ArrayList();
        List <String> nombre = new ArrayList();
        List <String> descripcion = new ArrayList();

        for (int i = 0;i<=10;i++){

            Bundle bundle= new Bundle();
            bundle.putString("name","este es el primer Productos");
            bundle.putString("Description","esta debe ser la primera describcion del producto");
            bundle.putInt("imagen",img);

            list.add(bundle);
        }*/
        return fragment;
    }
}