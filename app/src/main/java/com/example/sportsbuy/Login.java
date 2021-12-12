package com.example.sportsbuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportsbuy.DataBase.usersDAO;
import com.example.sportsbuy.DataBase.usersDB;
import com.example.sportsbuy.DataBase.usersEntity;
import com.example.sportsbuy.DataBase.usersRepository;
import com.example.sportsbuy.DataBase.usersRepositoryImpl;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText et_correo,et_contraseña;
    MainActivity activity;

    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

    //Firebase

    private FirebaseFirestore firestoredb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correo = findViewById(R.id.et_correo_login);
        et_contraseña = findViewById(R.id.et_contraseña_login);

        db = usersDB.getInstance(this.getApplicationContext());
        dao = db.usersDAO();
        repo = new usersRepositoryImpl(dao);
        user = new usersEntity();

        activity=new MainActivity();

        firestoredb = FirebaseFirestore.getInstance();
    }

    public void Login(View view) {

        System.out.println("entro01");
        String correo = et_correo.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        System.out.println("entro02");
        List<usersEntity> user = repo.buscarPorCorreo(correo);
        System.out.println("entro03");

        if (correo.equals("")){

            Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            toast.setText("El Correo es obligatorio");
            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
            toast.show();

            et_correo.setError("Correo");
            et_correo.setFocusable(true);
        }else{
            if (contraseña.equals("")){
                Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                toast.setText("La Contraseña es obligatoria");
                //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                toast.show();

                et_contraseña.setError("Cedula");
                et_contraseña.setFocusable(true);
            }else{
                String Vcorreo = et_correo.getText().toString();
                firestoredb.collection("Users").document(Vcorreo).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        try {
                            String vcorreo = documentSnapshot.get("Correo").toString();
                            String vcontraseña = documentSnapshot.get("Contraseña").toString();

                            System.out.println("la variable retorna : " + vcorreo);

                            if( vcorreo != null){
                                if (vcontraseña!=null ){
                                    if (vcorreo.equals(et_correo.getText().toString())) {

                                        if (vcontraseña.equals(et_contraseña.getText().toString())) {

                                            Intent activityMain = new Intent(view.getContext(),MainActivity.class);
                                            activityMain.putExtra("correo",vcorreo);
                                            startActivity(activityMain);
                                            finish();

                                            et_correo.setText("");
                                            et_contraseña.setText("");
                                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                            toast.setText("Bienvenido");
                                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                            toast.show();

                                        }else{

                                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                            toast.setText("La Contraseña es incorreocta");
                                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                            toast.show();

                                        }

                                    }else{

                                        Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                        toast.setText("No Se encontro el correo");
                                        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                        toast.show();

                                    }
                                }else{
                                    Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                    toast.setText("No se encontro la contraseña");
                                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                    toast.show();
                                }
                            }else{
                                Toast.makeText(view.getContext(), "No tienes una cuenta asociada", Toast.LENGTH_SHORT).show();

                                //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);

                            }
                        }catch (Exception  e){
                            Toast.makeText(view.getContext(), "No tienes una cuenta asociada", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }



    public void crearcuenta (View view){

        Intent ActivityCA = new Intent(this, Create_Account.class);
        startActivity(ActivityCA);
        finish();

    }

    public void olvidecontraseña (View view){

    }
}