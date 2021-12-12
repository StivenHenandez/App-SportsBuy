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


public class FragmentAccesorios extends Fragment {

    AdapterDatos adapter;
    private usersDB db;
    private articuloDAO dao;
    private articuloRepositoryImpl repo;
    //database
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

/*
        Bundle bundle = new Bundle();
        bundle.putString("name","teni Azul");
        bundle.putString("Description","Es un teni azul");

        List <Bundle> baseDatos= new ArrayList<>();
        baseDatos.add(bundle);*/

        View fragment = inflater.inflate(R.layout.fragment_accesorios, container, false);

        RecyclerView recyclerView=fragment.findViewById(R.id.recyclerViewAccesorios);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Traer base de datos
        List<articuloEntity> list = new ArrayList<>();
        //list = repo.buscarArticuloPorCategoria("Accesorios");

        databaseReference.child("Articulos").child("Accesorios").addValueEventListener(new ValueEventListener() {
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

        //Icon imagen = new
        //File foto=

        //adapter = new AdapterDatos(baseDatos);
        //recyclerView.setAdapter(adapter);

        return fragment;
    }
}