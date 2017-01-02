package com.example.guillaume.wimsproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;



public class Broadcast extends BroadcastReceiver {
    MainActivity appel = MainActivity.getInstance();


    public void onReceive(Context context, Intent intent) {


        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String numero = currentMessage.getDisplayOriginatingAddress();

                    String Numero_envoyeur = numero;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("Reception", "Numéro: " + Numero_envoyeur + "; message: " + message);


                    if (message.contains("12345")) {

                        appel.txtnumero.setText(Numero_envoyeur);  // on remplit le champs numéro
                        appel.boutonenvoi.performClick();    // on simule le clique sur le bouton

                    }

                    if (message.contains("LAT:")) {
                        String lat = message.substring(4);  // on retire les caracteres qu'il y a en trop dans le sms
                        appel.txtlatitude.setText(lat);     // on met la variable latitude dans le champs de texte
                    }

                    if (message.contains("LON:")) {
                        String lon = message.substring(4);  // on retire les caracteres qu'il y a en trop dans le sms
                        appel.txtlongitude.setText(lon);    // on met la variable longitude dans le champs de texte

                    }
                }


            }


        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }

    }


}


