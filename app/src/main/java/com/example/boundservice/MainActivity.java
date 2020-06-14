package com.example.boundservice;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    ExampleBoundService exampleBoundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = findViewById( R.id.text );
        button = findViewById( R.id.buttonPanel );
        
        Intent intent = new Intent( MainActivity.this, ExampleBoundService.class );
        bindService( intent, serviceConnection, BIND_AUTO_CREATE );
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentTime = exampleBoundService.getCurrentTime();
                textView.setText( currentTime );
            }
        } );
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ExampleBoundService.MyLocalBinder myLocalBinder = (ExampleBoundService.MyLocalBinder) iBinder;
            exampleBoundService = myLocalBinder.getExampleBound();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
