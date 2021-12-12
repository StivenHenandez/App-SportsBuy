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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create_Account extends AppCompatActivity {

    LinearLayout conte;
    Fragment frag;
    CheckBox checkboxFrag;
    EditText et_cedula, et_nombre, et_apellido, et_correo, et_contraseña, et_telefono;
    Button btn_salvar;
    //TextView verinfo;

    private usersDB db;
    private usersDAO dao;
    private usersRepository repo;
    private usersEntity user;

    /*
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;*/

    private FirebaseFirestore firestoredb;

    String TAG = "";

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

        btn_salvar.setVisibility(View.VISIBLE);

        /*FireBase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();*/
        firestoredb = FirebaseFirestore.getInstance();
    }

    public void crearcuenta2(String nombre) {

    }
    public void btn_CA_crear_cuenta(View view) {

        boolean validacion = this.validar(view);

        if (validacion){

            String Vcorreo = et_correo.getText().toString();

            firestoredb.collection("Users").document(Vcorreo).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    try {
                        if (documentSnapshot.get("Correo")!= null){
                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                            toast.setText("El Correo ya existe");
                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                            toast.show();
                            et_correo.setError("Correo invalido");
                            et_correo.setFocusable(true);
                        }
                        else{
                            Map<String, Object> Usuario = new HashMap<>();

                            Usuario.put("Cedula", et_cedula.getText().toString());
                            Usuario.put("Nombre", et_nombre.getText().toString());
                            Usuario.put("Apellido", et_apellido.getText().toString());
                            Usuario.put("Correo", et_correo.getText().toString());
                            Usuario.put("Contraseña", et_contraseña.getText().toString());
                            Usuario.put("Telefono", et_telefono.getText().toString());

                            firestoredb.collection("Users").document(Vcorreo).set(Usuario);
                            Toast toast = Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT);
                            toast.setText("Se creo la informacion");
                            //toast.setGravity(Gravity.AXIS_SPECIFIED, 100, 1000);
                            toast.show();

                            limpiar_campos();

                            Login instacia= new Login();

                            Intent activityLogin = new Intent(view.getContext(), Login.class);
                            startActivity(activityLogin);
                            finish();

                            Toast inicio= Toast.makeText(view.getContext(),"Inicia Sesion",Toast.LENGTH_SHORT);
                            //inicio.setText("Inicia Sesion");
                            //inicio.setGravity(Gravity.AXIS_SPECIFIED,100,1000);
                            //inicio.show();
                        }
                    }catch (Exception  e){
                        System.out.println("Error3 : "+e.getLocalizedMessage());
                    }
                }
            });
            //Intent activityLogin = new Intent(this, Login.class);
            //startActivity(activityLogin);
            //finish();
        }else{
            System.out.println("No se valido");
        }


/*
        if (validacion == true){
            if (){
                try {


                    Map<String, Object> Usuario = new HashMap<>();

                    Usuario.put("Cedula", et_cedula.getText().toString());
                    Usuario.put("Nombre", et_nombre.getText().toString());
                    Usuario.put("Apellido", et_apellido.getText().toString());
                    Usuario.put("Correo", et_correo.getText().toString());
                    Usuario.put("Contraseña", et_contraseña.getText().toString());
                    Usuario.put("Telefono", et_telefono.getText().toString());

                    firestoredb.collection("Users").document(Vcorreo).set(Usuario);

                    et_cedula.setText("");
                    et_nombre.setText("");
                    et_apellido.setText("");
                    et_correo.setText("");
                    et_contraseña.setText("");
                    et_telefono.setText("");

                    Toast.makeText(this, "Registro Correctamente", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    System.out.println("Error : " + e.getLocalizedMessage());
                    Toast.makeText(this, "No se pudo Registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }


/*
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
*/
    }

    private void limpiar_campos(){
        et_cedula.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
        et_correo.setText("");
        et_contraseña.setText("");
        et_telefono.setText("");
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

        //databaseReference.child("Usuarios").child(cedula).setValue(user);d
        Toast.makeText(this, "Registro Correctamente", Toast.LENGTH_SHORT).show();

    }

    //List<usersEntity> listusers = new ArrayList<>();

    public void btn_CA_salvar(View view) {

        String Vcorreo = et_correo.getText().toString();



    firestoredb.collection("Users").document(Vcorreo).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
            try {
                et_cedula.setText(documentSnapshot.get("Cedula").toString());
                et_nombre.setText(documentSnapshot.get("Nombre").toString());
                et_apellido.setText(documentSnapshot.get("Apellido").toString());
                et_correo.setText(documentSnapshot.get("Correo").toString());
                et_contraseña.setText(documentSnapshot.get("Contraseña").toString());
                et_telefono.setText(documentSnapshot.get("Telefono").toString());
            }catch (Exception  e){
                    System.out.println("Error3 : "+e.getLocalizedMessage());
            }
        }
    });



        //String Vcedula = et_cedula.getText().toString();

        //List <usersEntity> usuarios = this.DatosFirebase();

        //if (usuarios != null){
          //  System.out.println("El tamaño de la lista es"+listusers.size());
        //}



        /*

        this.DatosFirebase();

        usersEntity usuario = null;

        if(listusers.size()>0){
            for (usersEntity user:listusers) {
                if (user.getCedula().equals(Vcedula)){
                    usuario = user;
                    break;
                }
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

        //List <usersEntity> user = repo.buscarPorCorreo(Vcorreo);

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


/*
    public List<usersEntity> DatosFirebase(){

        List<usersEntity> datos = new ArrayList<>();

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
                    datos.add(vector[i]);
                    i = i + 1;
                    System.out.println("En contador va en : "+i);
                    System.out.println("Tamaño lista : "+datos.size());
                    //System.out.println(""+vector[0]);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        System.out.println("Tamaño lista 2 : "+datos.size());
        if (datos.size()>0){
            System.out.println("retorno Lista");
            return datos;
        }
        else {
            return null;
        }
    }*/

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
                                            return false;
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

