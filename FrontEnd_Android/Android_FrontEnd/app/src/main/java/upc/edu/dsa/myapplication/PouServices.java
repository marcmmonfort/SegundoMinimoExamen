package upc.edu.dsa.myapplication;

import java.util.List;

import retrofit2.http.PUT;
import retrofit2.http.Path;
import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Activities.*;
import upc.edu.dsa.myapplication.Entities.*;
import upc.edu.dsa.myapplication.Entities.VO.Credenciales;
import upc.edu.dsa.myapplication.Entities.VO.InfoRegistro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import upc.edu.dsa.myapplication.Entities.VO.InformacionPou;

public interface PouServices {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // SERVICIOS DE NUESTRO JUEGO POU
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // UBICACIÓN: Activity_Pou_Login.
    // DESCRIPCIÓN: Hacer Login en un Pou.
    @POST("/dsaApp/pougame/pou/login")
    Call<Void> login(@Body Credenciales credenciales);

    // UBICACIÓN: Activity_Pou_Register.
    // DESCRIPCIÓN: Registrar un nuevo Pou.
    @POST("/dsaApp/pougame/pou/registro")
    Call<Void> registro(@Body InfoRegistro infoRegistro);

    // UBICACIÓN: Activity_Pou_Login.
    // DESCRIPCIÓN: Se obtienen todos los parámetros del Pou y se pasan al salón cuando el Login se hace correctamente.
    @GET("/dsaApp/pougame/pou/cargarDatos/{gmail}/{password}")
    Call<InformacionPou> getInfoAndroidPou(@Path("gmail") String gmail, @Path("password") String password);

    // UBICACIÓN: Activity_Pou_Salon / Activity_Pou_Info / Activity_Pou_Juego / Activity_Pou_Tienda.
    // DESCRIPCIÓN: Se guardan todos los parámetros del Pou al pulsar el botón de Guardar Partida / al hacer Logout / al iniciar el Juego / cuando arrancas la Web.
    @PUT("/dsaApp/pougame/pou/actualizarDatos")
    Call<Void> updateObjetoArmario(@Body InformacionPou informacionPou);

    // UBICACIÓN: Activity_Pou_Info.
    // DESCRIPCIÓN: Comprobamos los correos de todos los pous. 201 si no existe y 404 si existe.
    @PUT("/dsaApp/pougame/pou/comprobarCorreo/{gmail}")
    Call<Void> comprobarCorreo(@Path("gmail") String gmail);

    // UBICACIÓN: Activity_Pou_Cocina / Activity_Pou_Lavabo.
    // DESCRIPCIÓN: Obtenemos la información de x articulo.
    @GET("/dsaApp/pougame/tienda/obtenerarticulo/{articuloid}")
    Call<ObjetoTienda> obtenerInfoObjeto(@Path("articuloid") String articuloId);

    // UBICACIÓN: -
    // DESCRIPCIÓN: Obtener todos los Pous ordenados.
    @GET("/dsaApp/pougame/pou/ranking/{rankingId}")
    Call<List<Pou>> obtenerPousOrdenadosDescendentemente(@Path("rankingId") String rankingId);

    // UBICACIÓN: Activity_Pou_SplashScreen.
    // DESCRIPCIÓN: Enviamos al BackEnd la información del idioma con el que estamos cargando la App.
    @PUT("/dsaApp/pougame/pou/guardaridioma/{userId}/{idioma}")
    Call<Void> guardarIdioma(@Path("userId") String userId, @Path("idioma") String idioma);
}
