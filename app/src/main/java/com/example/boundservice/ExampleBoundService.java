package com.example.boundservice;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class ExampleBoundService extends Service {

    private final IBinder iBinder = new MyLocalBinder();
   
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss ", Locale.ENGLISH );
        return (dateFormat.format( new Date() ));
    }

    public class MyLocalBinder extends Binder {

        ExampleBoundService getExampleBound() {
            return ExampleBoundService.this;
        }
    }
}
