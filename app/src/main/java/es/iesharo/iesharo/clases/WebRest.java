package es.iesharo.iesharo.clases;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebRest {
    private static int peticiones=0;
    private final Context contexto;

    public WebRest(Context contexto) {
        this.contexto = contexto;
    }
    public static void addPeticion(){
         peticiones++;
    }
    public static void removePeticion(){
        if( peticiones>0) peticiones--;
    }
    public static int getPeticiones(){
        return peticiones;
    }
    public static void setPeticiones(int i){peticiones=i; }
    public void get(String uri, Response.Listener<JSONObject> jsonListener,
                    Response.ErrorListener errorListener,
                    final HashMap<String, String> cabeceras) {

        // Crear petición GET
        JsonObjectRequest peticion = new JsonObjectRequest(
                uri,
                null,
                jsonListener,
                errorListener
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return cabeceras;
            }
        };

        // Añadir petición a la pila
        peticion.setTag("riojaparty");
        Volley_Singleton.getInstance(contexto).addToRequestQueue(peticion);
    }
    public void post(String uri, HashMap<String, String> datos, Response.Listener<JSONObject> jsonListener,
                     Response.ErrorListener errorListener, final HashMap<String, String> cabeceras) {

        // Crear petición POST
        JsonObjectRequest peticion = new JsonObjectRequest(
                Request.Method.POST,
                uri,
                new JSONObject(datos),
                jsonListener,
                errorListener
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return cabeceras;
            }
        };

        // Añadir petición a la pila
        peticion.setTag("riojaparty");
        Volley_Singleton.getInstance(contexto).addToRequestQueue(peticion);
    }
    public void simple(String uri, Response.Listener<String> stringListener, Response.ErrorListener errorListener){
        StringRequest peticion = new StringRequest(
                Request.Method.GET,
                uri,
                stringListener,
                errorListener
        );
        // Añadir petición a la pila
        Volley_Singleton.getInstance(contexto).addToRequestQueue(peticion);
    }

    public void image(String uri, Response.Listener<Bitmap> bitmapListener,
                      Response.ErrorListener errorListener) {
        ImageRequest peticion = new ImageRequest(
                uri,
                bitmapListener, 0, 0, null,
                errorListener
        );
        // Añadir petición a la pila
        peticion.setTag("riojaparty");
        Volley_Singleton.getInstance(contexto).addToRequestQueue(peticion);
        };



}
