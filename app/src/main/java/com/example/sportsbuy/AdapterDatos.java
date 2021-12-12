package com.example.sportsbuy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsbuy.DataBase.articuloEntity;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    List<articuloEntity> listdatos;
    //Icon image;

    public AdapterDatos(List<articuloEntity> listdatos) {
        this.listdatos = listdatos;
        //this.image = image;
    }


    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ariticulo,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDatos.ViewHolderDatos holder, int position) {

        articuloEntity articulos = listdatos.get(position);

        int Limagen = articulos.getIdfoto();
        String Lnombre = articulos.getNombre();
        String Ldescription = articulos.getDescripcion();

        holder.LLenarCamposRopa(Limagen,Lnombre ,Ldescription);


    }

    @Override
    public int getItemCount() {
        return listdatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre_A;
        TextView descripcion_A;
        ImageView imageView_A;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre_A = itemView.findViewById(R.id.tv_articulo_nombre);
            descripcion_A = itemView.findViewById(R.id.tv_articulo_descripcion);
            imageView_A= itemView.findViewById(R.id.imageView_articulo);
        }

        public void LLenarCamposRopa(int imagen ,String nombre , String Description) {
            nombre_A.setText(nombre);
            descripcion_A.setText(Description);
            imageView_A.setImageResource(imagen);
        }
        public void LLenarCamposAccesorios(int imagen ,String nombre , String Description) {
            nombre_A.setText(nombre);
            descripcion_A.setText(Description);
            imageView_A.setImageResource(imagen);
        }

    }
}
