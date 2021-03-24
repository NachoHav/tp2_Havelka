package com.example.tp2_havelka;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.Message;
import android.provider.Telephony;
import android.util.Log;

public class Lectura extends Service implements Runnable{



    public Lectura() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


            try {
                while (true){
                    listarMensajes();
                    Thread.sleep(9000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return super.onStartCommand(intent, flags, startId);



    }



    public void listarMensajes(){
        Uri mensajes = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(mensajes, null, null, null, null);

        String nro = "";
        String id = "";
        String fecha = "";
        String mensaje = "";

        if(cursor.getCount() > 0){

            //while(cursor.moveToNext()){

                for (int f = 0; f < 5; f++){
                cursor.moveToPosition(f);

                nro = cursor.getString(2);
                id = cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID));
                fecha = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));
                mensaje = cursor.getString(12);

                if(nro != null && id != null && fecha != null && mensaje != null) {
                    Log.d("numero", nro);
                    Log.d("id", id);
                    Log.d("fecha", fecha);
                    Log.d("mensaje", mensaje);
                }


            }


        }

    }


    @Override
    public void run() {

    }
}