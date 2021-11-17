package com.example.sportsbuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportsbuy.DataBase.usersDAO;
import com.example.sportsbuy.DataBase.usersDB;
import com.example.sportsbuy.DataBase.usersEntity;
import com.example.sportsbuy.DataBase.usersRepository;
import com.example.sportsbuy.DataBase.usersRepositoryImpl;
import com.example.sportsbuy.Fragmentos.FragTerminosyCondiciones;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create_Account extends AppCompatActivity {

    LinearLayout conte;
    Fragment frag;
    CheckBox checkboxFrag;
    EditText et_cedula,et_nombre,et_apellido,et_correo,et_contraseña,et_telefono;
    Button btn_salvar;
    //TextView verinfo;

    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = usersDB.getInstance(this.getApplicationContext());
        dao = db.usersDAO();
        repo = new usersRepositoryImpl(dao);
        user = new usersEntity();

        btn_salvar = findViewById(R.id.btn_CA_salvar);

        et_cedula = findViewById(R.id.et_cedula);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido= findViewById(R.id.et_apellido);
        et_correo = findViewById(R.id.et_correo);
        et_contraseña = findViewById(R.id.et_contraseña);
        et_telefono = findViewById(R.id.et_telefono);
        //verinfo = findViewById(R.id.verinfo);

        checkboxFrag = findViewById(R.id.checkboxfrag);

        conte = findViewById(R.id.cont);
        conte.setVisibility(View.INVISIBLE);

        btn_salvar.setVisibility(View.INVISIBLE);

    }

    public void btn_CA_crear_cuenta(View view) {

        boolean validacion = this.validar(view);

        if (validacion){

            String correo = et_correo.getText().toString();

            List <usersEntity> search_user = repo.buscarPorCorreo(correo);

            if (search_user.size()>0){

                Toast toast= Toast.makeText(Create_Account.this,"",Toast.LENGTH_SHORT);
                toast.setText("El Correo ya esta asociado");
                //toast.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
                toast.show();
                et_correo.setFocusable(true);
                et_correo.setError("Correo invalido");
            }
            else {
                this.guardarinfo();

                Toast toast= Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT);
                toast.setText("Se guardo la informacion");
                //toast.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
                toast.show();

                et_cedula.setText("");
                et_nombre.setText("");
                et_apellido.setText("");
                et_correo.setText("");
                et_contraseña.setText("");
                et_telefono.setText("");

                Intent ActivityLo = new Intent(this, Login.class);
                startActivity(ActivityLo);
                finish();

                Toast inicio= Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT);
                inicio.setText("Inicia Sesion");
                //inicio.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
                inicio.show();
            }

        }
        else{
            Toast toast= Toast.makeText(view.getContext(),"",Toast.LENGTH_LONG);
            toast.setText("No se guardo la informacion");
            //toast.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
            toast.show();
        }


    }

    private void guardarinfo() {

            String cedula, nombre, apellido, correo, contraseña, telefono;

            cedula= et_cedula.getText().toString();
            nombre = et_nombre.getText().toString();
            apellido = et_apellido.getText().toString();
            correo = et_correo.getText().toString();
            contraseña = et_contraseña.getText().toString();
            telefono = et_telefono.getText().toString();

            user.setCedula(cedula);
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setCorreo(correo);
            user.setClave(contraseña);
            user.setTelefono(telefono);

            repo.insertUsers(user);


/*
        try {



            File tarjeta = Environment.getExternalStorageDirectory();
            File file = new File(tarjeta.getAbsolutePath(),"cuenta1");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(nombre);
            osw.write(apellido);
            osw.write(correo);
            osw.write(contraseña);
            osw.write(telefono);
            osw.flush();
            osw.close();


            FileOutputStream fos = openFileOutput("cuenta1.csv", MODE_APPEND);

            fos.write(nombre.getBytes());
            fos.write(apellido.getBytes());
            fos.write(correo.getBytes());
            fos.write(contraseña.getBytes());
            fos.write(telefono.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
             */
    }



    public void btn_CA_salvar(View view) {

       String Vcorreo = et_correo.getText().toString();

       List <usersEntity> user = repo.buscarPorCorreo(Vcorreo);

       String cedula, nombre, apellido, correo ,contraseña, telefono;

           for (usersEntity i:user) {

               cedula = i.getCedula();
               nombre = i.getNombre();
               apellido = i.getApellido();
               correo = i.getCorreo();
               contraseña = i.getClave();
               telefono = i.getTelefono();

               et_cedula.setText(cedula);
               et_nombre.setText(nombre);
               et_apellido.setText(apellido);
               et_correo.setText(correo);
               et_contraseña.setText(contraseña);
               et_telefono.setText(telefono);
           }




        /*
        String nomarchivo = verinfo.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo=new InputStreamReader(fIn);
            BufferedReader br=new BufferedReader(archivo);
            String linea=br.readLine();
            String todo="";
            while (linea!=null)
            {
                todo=todo+linea+"\n";
                linea=br.readLine();
            }
            br.close();
            archivo.close();
            verinfo.setText(todo);

        } catch (IOException e)
        {
        }

        FileInputStream fi = null;
        try {
             fi = openFileInput("cuenta1.csv");
            InputStreamReader inp=new InputStreamReader(fi);
            BufferedReader buffer=new BufferedReader(inp);

            StringBuilder strb=new StringBuilder();

            String linea=null;

            while ((linea=buffer.readLine())!= null){

                strb.append(linea);
            }
            inp.close();
            fi.close();
            verinfo.setText(""+strb.toString());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }



    public boolean validar(View view){
        String cedula, nombre, apellido, correo, contraseña, telefono;

        cedula = et_cedula.getText().toString();
        nombre=et_nombre.getText().toString();
        apellido = et_apellido.getText().toString();
        correo = et_correo.getText().toString();
        contraseña = et_contraseña.getText().toString();
        telefono = et_telefono.getText().toString();

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        boolean validarCorreo = mather.find();

        //[example][@][example][.][com]

        if (cedula.equals("")){

            Toast toast= Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT);
            toast.setText("La Cedula es necesaria");
            //toast.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
            toast.show();
            et_cedula.setError("Cedula");
            et_cedula.setFocusable(true);

        }else{
            if(nombre.equals("")){

                Toast toast= Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT);
                toast.setText("El Nombre es necesario");
                //toast.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
                toast.show();
                et_nombre.setError("Nombre");
                et_nombre.setFocusable(true);

            }else {

                if (apellido.equals("")) {
                    Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                    toast.setText("El Apellido es necesario");
                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                    toast.show();
                    et_apellido.setError("Apellido");
                    et_apellido.setFocusable(true);
                } else {
                    if (correo.equals("")) {
                        Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                        toast.setText("El Correo es necesario");
                        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                        toast.show();
                        et_correo.setError("Correo");
                        et_correo.setFocusable(true);
                    } else {
                        if (validarCorreo!=true){
                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_LONG);
                            toast.setText("El correo debe Seguir este Patron"+"\"[example][@][example][.][com]\"");
                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                            toast.show();
                            et_correo.setError("Correo");
                            et_correo.setFocusable(true);
                        }else{
                            if (contraseña.equals("")) {
                                Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                toast.setText("La Contraseña es necesaria");
                                //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                toast.show();
                                et_contraseña.setError("Contraseña");
                                et_contraseña.setFocusable(true);
                            } else {
                                if(contraseña.length()<8){
                                    Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_LONG);
                                    toast.setText("La Contraseña minimo debe tener 8 caracteres");
                                    //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                    toast.show();
                                    et_contraseña.setError("Contraseña");
                                    et_contraseña.setFocusable(true);
                                }else{
                                    if (telefono.equals("")) {
                                        Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                        toast.setText("El Telefono es necesario");
                                        //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                        toast.show();
                                        et_telefono.setError("Telefono");
                                        et_telefono.setFocusable(true);
                                    } else {
                                        if (checkboxFrag.isChecked()!=true){
                                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                                            toast.setText("Las Politicas de uso Son obligatorias");
                                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                                            toast.show();
                                            conte.setVisibility(View.VISIBLE);
                                        }else{
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void tv_TyC_btn(View view) {

        conte.setVisibility(View.VISIBLE);
        frag = new FragTerminosyCondiciones();
        this.cargarFragmento(frag);

    }
    private void cargarFragmento(Fragment frag) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_TyC,frag);
        transaction.commit();
    }

    public void checkbox(View view) {
        conte.setVisibility(View.INVISIBLE);
        checkboxFrag.setChecked(true);
    }

    public void tv_btn_tengo_cuenta(View view) {
        Intent ActivityLo = new Intent(this, Login.class);
        startActivity(ActivityLo);
        finish();
    }
}

