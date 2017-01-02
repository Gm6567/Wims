package com.example.guillaume.wimsproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;




public class MainActivity extends Activity {


      // declaration des boutons
    Button boutonenvoi,boutoncarte,boutonrecup;
      // declaration des zones de texte
    EditText txtnumero,txtnumero2,txtlatitude,txtlongitude,txtcode;

     // declaration des champs numero, latitude, longitude et le code
    String numero,lat,lon,code;

    // declaration d'une instance gps necessaire a l'appel de fonctions dans certains contextes

    GPSTracker gps;

    // declaration de la variable instance necessaire a l'appel de fonctions de la classe MainActivity dans d'autres classes
    static MainActivity instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        boutonenvoi = (Button) findViewById(R.id.boutonenvoi);
        boutoncarte = (Button) findViewById(R.id.boutoncarte);
        boutonrecup = (Button) findViewById(R.id.boutonrecup);
        txtcode = (EditText)findViewById(R.id.champscode);
        txtnumero = (EditText)findViewById(R.id.champsnumero);
        txtnumero2 = (EditText)findViewById(R.id.champsnumero2);
        txtlatitude = (EditText)findViewById(R.id.champslat);
        txtlongitude = (EditText)findViewById(R.id.champslon);

        boutonenvoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                gps = new GPSTracker(MainActivity.this);


                if(gps.canGetLocation()){


                    numero = txtnumero.getText().toString();
                    envoi(numero);
                    Toast.makeText(getApplicationContext(), "Envoi de votre position effectué", Toast.LENGTH_LONG).show();
                }

                else { Toast.makeText(getApplicationContext(), "Il y a un soucis avec la géolocalisation", Toast.LENGTH_LONG).show();  }



            }
        });

        boutoncarte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                lat = txtlatitude.getText().toString();
                lon = txtlatitude.getText().toString();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        boutonrecup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                code = txtcode.getText().toString();
                numero = txtnumero2.getText().toString();

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(numero, null,code, null, null);

            }
        });

    }

    // instance qui va servir à la classe Broadcast
    public static MainActivity getInstance() {return instance; }

     // instance qui va servir à la classe MapsActivity
    public static MainActivity getInstance2() {
        return instance;
    }


    public void  envoi(String numero){
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        SmsManager sms = SmsManager.getDefault();
        // LAT: et LON: permettent d'indiquer que la latitude et la longitude sont présents dans les sms envoyés
        sms.sendTextMessage(numero, null, "LAT:"+ latitude +"", null, null);
        sms.sendTextMessage(numero, null, "LON:"+ longitude +"", null, null);
    }


}


