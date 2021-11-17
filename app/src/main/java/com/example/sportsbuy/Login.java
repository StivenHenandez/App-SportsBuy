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

import java.util.List;

public class Login extends AppCompatActivity {

    EditText et_correo,et_contraseña;
    MainActivity activity;

    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

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
                if (user.size() > 0) {

                    String Vcorreo = null;
                    String Vcontraseña = null;
                    for (usersEntity i : user) {
                        System.out.println(i.toString());
                        Vcorreo = i.getCorreo();
                        Vcontraseña = i.getClave();
                    }

                    if (Vcorreo == null) {
                        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                        toast.setText("No Se encontro el correo");
                        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                        toast.show();
                    } else {
                        if (Vcontraseña == null) {
                            Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                            toast.setText("No Se encontro La contraseña");
                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                            toast.show();
                        } else {
                            if (Vcorreo.equals(correo)) {

                                if (Vcontraseña.equals(contraseña)) {
                                    /*
                                    Bundle bundle = new Bundle();
                                    bundle.putString("correo",correo);
                                    */
                                    Intent ActivityMA = new Intent(this, MainActivity.class);
                                    ActivityMA.putExtra("correo",et_correo.getText().toString());
                                    startActivity(ActivityMA);
                                    //finish();

                                    Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                                    toast.setText("Bienvenido");
                                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                    toast.show();

                                    //et_cedula.setError("Cedula");
                                    //et_cedula.setFocusable(true);

                                } else {
                                    System.out.println("entro6");
                                    Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                    toast.setText("La Contraseña no coincide");
                                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                    toast.show();
                                    et_contraseña.setError("Contraseña invalida");
                                    System.out.println("entro7");
                                }

                            } else {
                                System.out.println("entro8");
                                Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                toast.setText("El Correo no coincide");
                                //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                toast.show();
                                et_correo.setError("Correo invalido");
                                et_correo.setFocusable(true);
                                System.out.println("entro9");
                            }
                        }
                        System.out.println("entro1");
                    }
                }else{

                    System.out.println("entro10");
                    Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                    toast.setText("No tienes una cuenta Asociada a este correo");
                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                    toast.show();
                    et_correo.setError("Correo invalido");
                    et_correo.setFocusable(true);
                    System.out.println("entro11");

                }
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