package upc.edu.dsa.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import upc.edu.dsa.myapplication.Entities.ObjetoTienda;
import upc.edu.dsa.myapplication.PouRetrofit;
import upc.edu.dsa.myapplication.PouServices;
import upc.edu.dsa.myapplication.R;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import upc.edu.dsa.myapplication.Entities.VO.*;

public class Activity_Pou_Info extends AppCompatActivity {

    ImageButton btnLeft, btnRight;
    Button botonLogout, botonActualizar;
    TextView id_info;
    TextView diversion_info,titulo_info,hambre_info,dinero_info,sueno_info,salud_info;
    TextInputEditText inputNombrePou, inputNacimientoPou, inputCorreoPou, inputPasswordPou, inputIdPou;

    PouServices pouServices;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLES GLOBALES DEL POU QUE SE PASAN ENTRE LAS ACTIVITIES.
    String data_pouId = "marc";
    String data_nombrePou = "Marc";
    String data_nacimientoPou = "28/10/2001";
    String data_correoPou = "marc@gmail.com";
    String data_passwordPou = "Calella";
    int recordPou = 0;
    int lvlHambre = 28;
    int lvlSalud = 10;
    int lvlDiversion = 200;
    int lvlSueno = 1;
    int amountDinero = 50;
    int amountCandy = 1;
    int amountManzana = 6;
    int amountPizza = 6;
    int amountAgua = 6;
    int amountAquarius = 6;
    int amountRoncola = 6;
    int amountHambre = 1;
    int amountSalud = 1;
    int amountDiversion = 1;
    int amountSueno = 1;
    String pouEstado = "normal";
    String pouCamiseta = "spain";
    String pouBambas = "veja";
    String pouGafas = "nada";
    String pouGorro = "cerveza";
    String posee_pijama = "NO";
    String posee_fcb = "NO";
    String posee_spain = "YES";
    String posee_messi = "NO";
    String posee_rafa = "YES";
    String posee_veja = "NO";
    String posee_fiesta = "NO";
    String posee_rayban = "YES";
    String posee_ciclismo = "NO";
    String posee_cerveza = "YES";
    String posee_boina = "NO";
    String posee_polo = "YES";
    String activityOrigen = "Juego";
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pou_info_screen);

        btnLeft =(ImageButton)findViewById(R.id.btn_izquierda_info);
        btnRight =(ImageButton)findViewById(R.id.btn_derecha_info);
        botonLogout = (Button)findViewById(R.id.botonLogout);
        botonActualizar = (Button)findViewById(R.id.botonActualizar);

        inputNombrePou = (TextInputEditText)findViewById(R.id.inputNombrePou);
        inputNacimientoPou = (TextInputEditText)findViewById(R.id.inputNacimientoPou);
        inputCorreoPou = (TextInputEditText)findViewById(R.id.inputCorreoPou);
        inputPasswordPou = (TextInputEditText)findViewById(R.id.inputPasswordPou);
        inputIdPou = (TextInputEditText)findViewById(R.id.inputIdPou);

        diversion_info = findViewById(R.id.diversion_info);
        titulo_info = findViewById(R.id.titulo_info);
        hambre_info = findViewById(R.id.hambre_info);
        dinero_info = findViewById(R.id.dinero_info);
        sueno_info = findViewById(R.id.sueno_info);
        salud_info = findViewById(R.id.salud_info);

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // RECEPCIÓN DE DATOS DESDE OTRA ACTIVIDAD
        Bundle infoRecibida = getIntent().getExtras();
        if (infoRecibida!=null){
            lvlHambre = Integer.parseInt(infoRecibida.getString("pasarNivelHambre"));
            lvlSalud = Integer.parseInt(infoRecibida.getString("pasarNivelSalud"));
            lvlDiversion = Integer.parseInt(infoRecibida.getString("pasarNivelDiversion"));
            lvlSueno = Integer.parseInt(infoRecibida.getString("pasarNivelSueno"));
            amountDinero = Integer.parseInt(infoRecibida.getString("pasarDinero"));

            amountCandy = Integer.parseInt(infoRecibida.getString("pasarCandy"));
            amountManzana = Integer.parseInt(infoRecibida.getString("pasarManzana"));
            amountPizza = Integer.parseInt(infoRecibida.getString("pasarPizza"));
            amountAgua = Integer.parseInt(infoRecibida.getString("pasarAgua"));
            amountAquarius = Integer.parseInt(infoRecibida.getString("pasarAquarius"));
            amountRoncola = Integer.parseInt(infoRecibida.getString("pasarRoncola"));

            amountHambre = Integer.parseInt(infoRecibida.getString("pasarPocionHambre"));
            amountSalud = Integer.parseInt(infoRecibida.getString("pasarPocionSalud"));
            amountDiversion = Integer.parseInt(infoRecibida.getString("pasarPocionDiversion"));
            amountSueno = Integer.parseInt(infoRecibida.getString("pasarPocionSueno"));

            pouEstado = infoRecibida.getString("pasarPouEstado");
            pouCamiseta = infoRecibida.getString("pasarPouCamiseta");
            pouBambas = infoRecibida.getString("pasarPouBambas");
            pouGafas = infoRecibida.getString("pasarPouGafas");
            pouGorro = infoRecibida.getString("pasarPouGorro");

            data_pouId = infoRecibida.getString("pasarDataPouId");
            data_nombrePou = infoRecibida.getString("pasarDataNombrePou");
            data_nacimientoPou = infoRecibida.getString("pasarDataNacimientoPou");
            data_correoPou = infoRecibida.getString("pasarDataCorreoPou");
            data_passwordPou = infoRecibida.getString("pasarDataPasswordPou");

            posee_pijama = infoRecibida.getString("pasarPoseePijama");
            posee_fcb = infoRecibida.getString("pasarPoseeFcb");
            posee_spain = infoRecibida.getString("pasarPoseeSpain");
            posee_messi = infoRecibida.getString("pasarPoseeMessi");
            posee_rafa = infoRecibida.getString("pasarPoseeRafa");
            posee_veja = infoRecibida.getString("pasarPoseeVeja");
            posee_fiesta = infoRecibida.getString("pasarPoseeFiesta");
            posee_rayban = infoRecibida.getString("pasarPoseeRayban");
            posee_ciclismo = infoRecibida.getString("pasarPoseeCiclismo");
            posee_cerveza = infoRecibida.getString("pasarPoseeCerveza");
            posee_boina = infoRecibida.getString("pasarPoseeBoina");
            posee_polo = infoRecibida.getString("pasarPoseePolo");

            recordPou = Integer.parseInt(infoRecibida.getString("pasarRecordPou"));
            activityOrigen =infoRecibida.getString("pasarActividadOrigen");
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // DECLARACIÓN INICIAL DE LOS DATOS.
        hambre_info.setText(Integer.toString(lvlHambre));
        salud_info.setText(Integer.toString(lvlSalud));
        diversion_info.setText(Integer.toString(lvlDiversion));
        sueno_info.setText(Integer.toString(lvlSueno));
        dinero_info.setText(Integer.toString(amountDinero));

        inputIdPou.setText(data_pouId);
        inputNombrePou.setText(data_nombrePou);
        inputNacimientoPou.setText(data_nacimientoPou);
        inputCorreoPou.setText(data_correoPou);
        inputPasswordPou.setText(data_passwordPou);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent1 = new Intent(Activity_Pou_Info.this, Activity_Pou_Juego.class);

                myIntent1.putExtra("pasarNivelHambre",Integer.toString(lvlHambre));
                myIntent1.putExtra("pasarNivelSalud",Integer.toString(lvlSalud));
                myIntent1.putExtra("pasarNivelDiversion",Integer.toString(lvlDiversion));
                myIntent1.putExtra("pasarNivelSueno",Integer.toString(lvlSueno));
                myIntent1.putExtra("pasarDinero",Integer.toString(amountDinero));

                myIntent1.putExtra("pasarCandy",Integer.toString(amountCandy));
                myIntent1.putExtra("pasarManzana",Integer.toString(amountManzana));
                myIntent1.putExtra("pasarPizza",Integer.toString(amountPizza));
                myIntent1.putExtra("pasarAgua",Integer.toString(amountAgua));
                myIntent1.putExtra("pasarAquarius",Integer.toString(amountAquarius));
                myIntent1.putExtra("pasarRoncola",Integer.toString(amountRoncola));

                myIntent1.putExtra("pasarPocionHambre",Integer.toString(amountHambre));
                myIntent1.putExtra("pasarPocionSalud",Integer.toString(amountSalud));
                myIntent1.putExtra("pasarPocionDiversion",Integer.toString(amountDiversion));
                myIntent1.putExtra("pasarPocionSueno",Integer.toString(amountSueno));

                myIntent1.putExtra("pasarPouEstado",pouEstado);
                myIntent1.putExtra("pasarPouCamiseta",pouCamiseta);
                myIntent1.putExtra("pasarPouBambas",pouBambas);
                myIntent1.putExtra("pasarPouGafas",pouGafas);
                myIntent1.putExtra("pasarPouGorro",pouGorro);

                myIntent1.putExtra("pasarDataPouId",data_pouId);
                myIntent1.putExtra("pasarDataNombrePou",data_nombrePou);
                myIntent1.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
                myIntent1.putExtra("pasarDataCorreoPou",data_correoPou);
                myIntent1.putExtra("pasarDataPasswordPou", data_passwordPou);


                myIntent1.putExtra("pasarPoseePijama",posee_pijama);
                myIntent1.putExtra("pasarPoseeFcb",posee_fcb);
                myIntent1.putExtra("pasarPoseeSpain",posee_spain);
                myIntent1.putExtra("pasarPoseeMessi",posee_messi);
                myIntent1.putExtra("pasarPoseeRafa",posee_rafa);
                myIntent1.putExtra("pasarPoseeVeja",posee_veja);
                myIntent1.putExtra("pasarPoseeFiesta",posee_fiesta);
                myIntent1.putExtra("pasarPoseeRayban",posee_rayban);
                myIntent1.putExtra("pasarPoseeCiclismo",posee_ciclismo);
                myIntent1.putExtra("pasarPoseeCerveza",posee_cerveza);
                myIntent1.putExtra("pasarPoseeBoina",posee_boina);
                myIntent1.putExtra("pasarPoseePolo",posee_polo);

                myIntent1.putExtra("pasarRecordPou",Integer.toString(recordPou));
                myIntent1.putExtra("pasarActividadOrigen",activityOrigen);

                Activity_Pou_Info.this.startActivity(myIntent1);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent myIntent2 = new Intent(Activity_Pou_Info.this, Activity_Pou_Salon.class);

                myIntent2.putExtra("pasarNivelHambre",Integer.toString(lvlHambre));
                myIntent2.putExtra("pasarNivelSalud",Integer.toString(lvlSalud));
                myIntent2.putExtra("pasarNivelDiversion",Integer.toString(lvlDiversion));
                myIntent2.putExtra("pasarNivelSueno",Integer.toString(lvlSueno));
                myIntent2.putExtra("pasarDinero",Integer.toString(amountDinero));

                myIntent2.putExtra("pasarCandy",Integer.toString(amountCandy));
                myIntent2.putExtra("pasarManzana",Integer.toString(amountManzana));
                myIntent2.putExtra("pasarPizza",Integer.toString(amountPizza));
                myIntent2.putExtra("pasarAgua",Integer.toString(amountAgua));
                myIntent2.putExtra("pasarAquarius",Integer.toString(amountAquarius));
                myIntent2.putExtra("pasarRoncola",Integer.toString(amountRoncola));

                myIntent2.putExtra("pasarPocionHambre",Integer.toString(amountHambre));
                myIntent2.putExtra("pasarPocionSalud",Integer.toString(amountSalud));
                myIntent2.putExtra("pasarPocionDiversion",Integer.toString(amountDiversion));
                myIntent2.putExtra("pasarPocionSueno",Integer.toString(amountSueno));

                myIntent2.putExtra("pasarPouEstado",pouEstado);
                myIntent2.putExtra("pasarPouCamiseta",pouCamiseta);
                myIntent2.putExtra("pasarPouBambas",pouBambas);
                myIntent2.putExtra("pasarPouGafas",pouGafas);
                myIntent2.putExtra("pasarPouGorro",pouGorro);

                myIntent2.putExtra("pasarDataPouId",data_pouId);
                myIntent2.putExtra("pasarDataNombrePou",data_nombrePou);
                myIntent2.putExtra("pasarDataNacimientoPou",data_nacimientoPou);
                myIntent2.putExtra("pasarDataCorreoPou",data_correoPou);
                myIntent2.putExtra("pasarDataPasswordPou", data_passwordPou);


                myIntent2.putExtra("pasarPoseePijama",posee_pijama);
                myIntent2.putExtra("pasarPoseeFcb",posee_fcb);
                myIntent2.putExtra("pasarPoseeSpain",posee_spain);
                myIntent2.putExtra("pasarPoseeMessi",posee_messi);
                myIntent2.putExtra("pasarPoseeRafa",posee_rafa);
                myIntent2.putExtra("pasarPoseeVeja",posee_veja);
                myIntent2.putExtra("pasarPoseeFiesta",posee_fiesta);
                myIntent2.putExtra("pasarPoseeRayban",posee_rayban);
                myIntent2.putExtra("pasarPoseeCiclismo",posee_ciclismo);
                myIntent2.putExtra("pasarPoseeCerveza",posee_cerveza);
                myIntent2.putExtra("pasarPoseeBoina",posee_boina);
                myIntent2.putExtra("pasarPoseePolo",posee_polo);

                myIntent2.putExtra("pasarRecordPou",Integer.toString(recordPou));
                myIntent2.putExtra("pasarActividadOrigen",activityOrigen);

                Activity_Pou_Info.this.startActivity(myIntent2);
            }
        });
    }

    public void clickActualizar(View view){


        data_nombrePou = inputNombrePou.getText().toString();
        data_nacimientoPou = inputNacimientoPou.getText().toString();
        data_passwordPou= inputPasswordPou.getText().toString();

        //SI SE HA CAMBIADO EL CORREO
        if(!Objects.equals(data_correoPou, inputCorreoPou.getText().toString())){

            //Hacer la petición
            pouServices = PouRetrofit.getInstance().getPouServices();
            Call<Void> comprobarMail = pouServices.comprobarCorreo(inputCorreoPou.getText().toString());
            comprobarMail.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> comprobarMail, Response<Void> respuestaComprobacion) {
                    switch (respuestaComprobacion.code()) {
                        case 201:
                            // Si el correo está disponible.
                            data_correoPou = inputCorreoPou.getText().toString();
                            pouServices = PouRetrofit.getInstance().getPouServices();
                            Call<Void> cargarDatos1 = pouServices.updateObjetoArmario(new InformacionPou(data_pouId, data_nombrePou, data_nacimientoPou, data_correoPou, data_passwordPou, recordPou, lvlHambre, lvlSalud, lvlDiversion, lvlSueno, amountDinero, amountCandy, amountManzana, amountPizza, amountAgua, amountAquarius, amountRoncola, amountHambre, amountSalud, amountDiversion, amountSueno, pouCamiseta, pouBambas, pouGafas, pouGorro, posee_pijama, posee_fcb, posee_spain, posee_messi, posee_rafa, posee_veja, posee_fiesta, posee_rayban, posee_ciclismo, posee_cerveza, posee_boina, posee_polo));
                            cargarDatos1.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> cargarDatos1, Response<Void> respuestaDatos1) {
                                    switch (respuestaDatos1.code()) {
                                        case 201:
                                            StyleableToast.makeText(Activity_Pou_Info.this, "¡Se ha guardado toda la información.", R.style.exampleToast).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> cargarDatos1, Throwable t) {
                                    Log.d("POU"," onFailure", t);
                                    StyleableToast.makeText(Activity_Pou_Info.this, "¡Error!", R.style.exampleToast).show();
                                }
                            });
                            SharedPreferences preferencias1=getSharedPreferences("datos",Context.MODE_PRIVATE);
                            SharedPreferences.Editor Obj_editor1=preferencias1.edit();
                            Obj_editor1.putString("mail",data_correoPou);
                            Obj_editor1.putString("password",data_passwordPou);
                            Obj_editor1.putBoolean("isLogged",true);
                            Obj_editor1.apply();
                            break;
                        case 405:
                            // Si el correo no está disponible.
                            inputCorreoPou.setText(data_correoPou);
                            StyleableToast.makeText(Activity_Pou_Info.this, "¡El correo introducido no está disponible!", R.style.exampleToast).show();
                            pouServices = PouRetrofit.getInstance().getPouServices();
                            Call<Void> cargarDatos2 = pouServices.updateObjetoArmario(new InformacionPou(data_pouId, data_nombrePou, data_nacimientoPou, data_correoPou, data_passwordPou, recordPou, lvlHambre, lvlSalud, lvlDiversion, lvlSueno, amountDinero, amountCandy, amountManzana, amountPizza, amountAgua, amountAquarius, amountRoncola, amountHambre, amountSalud, amountDiversion, amountSueno, pouCamiseta, pouBambas, pouGafas, pouGorro, posee_pijama, posee_fcb, posee_spain, posee_messi, posee_rafa, posee_veja, posee_fiesta, posee_rayban, posee_ciclismo, posee_cerveza, posee_boina, posee_polo));
                            cargarDatos2.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> cargarDatos2, Response<Void> respuestaDatos2) {
                                    switch (respuestaDatos2.code()) {
                                        case 201:
                                            StyleableToast.makeText(Activity_Pou_Info.this, "¡Se ha guardado toda la información.", R.style.exampleToast).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> cargarDatos2, Throwable t) {
                                    Log.d("POU"," onFailure", t);
                                    StyleableToast.makeText(Activity_Pou_Info.this, "¡Error!", R.style.exampleToast).show();
                                }
                            });
                            SharedPreferences preferencias2=getSharedPreferences("datos",Context.MODE_PRIVATE);
                            SharedPreferences.Editor Obj_editor2=preferencias2.edit();
                            Obj_editor2.putString("mail",data_correoPou);
                            Obj_editor2.putString("password",data_passwordPou);
                            Obj_editor2.putBoolean("isLogged",true);
                            Obj_editor2.apply();
                            break;
                    }
                }
                @Override
                public void onFailure(Call<Void> comprobarMail, Throwable t) {
                    Log.d("POU"," onFailure", t);
                    StyleableToast.makeText(Activity_Pou_Info.this, "¡Error!", R.style.exampleToast).show();
                }
            });
        }
    }

    public void clickLogout(View view) throws IOException {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putBoolean("isLogged",false);
        Obj_editor.apply();

        pouServices = PouRetrofit.getInstance().getPouServices();
        //Petición para rellenar todos los parametros
        Call<Void> cargarDatos = pouServices.updateObjetoArmario(new InformacionPou(data_pouId, data_nombrePou, data_nacimientoPou, data_correoPou, data_passwordPou, recordPou, lvlHambre, lvlSalud, lvlDiversion, lvlSueno, amountDinero, amountCandy, amountManzana, amountPizza, amountAgua, amountAquarius, amountRoncola, amountHambre, amountSalud, amountDiversion, amountSueno, pouCamiseta, pouBambas, pouGafas, pouGorro, posee_pijama, posee_fcb, posee_spain, posee_messi, posee_rafa, posee_veja, posee_fiesta, posee_rayban, posee_ciclismo, posee_cerveza, posee_boina, posee_polo));
        cargarDatos.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> cargarDatos, Response<Void> respuestaDatos) {
                switch (respuestaDatos.code()) {
                    case 201:
                        StyleableToast.makeText(Activity_Pou_Info.this, "¡Se ha guardado toda la información y se ha hecho Logout del Pou!", R.style.exampleToast).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<Void> cargarDatos, Throwable t) {
                Log.d("POU"," onFailure", t);
                StyleableToast.makeText(Activity_Pou_Info.this, "¡Error!", R.style.exampleToast).show();
            }
        });

        Intent i = new Intent(Activity_Pou_Info.this, Activity_Pou_Home.class);
        startActivity(i);
    }
}