package com.motoacademy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txProx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txProx=findViewById(R.id.proximitySensor);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proximitySensor!=null){
                sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this, "Sensor não encontrado", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
            String sensorValue = "Proximity Value "+sensorEvent.values[0];
            txProx.setText(sensorValue);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audio);

            if(sensorEvent.values[0]>0){
                Toast.makeText(this, "Objeto distante!", Toast.LENGTH_SHORT).show();

                mediaPlayer.stop();
            }else{
                Toast.makeText(this, "Objeto próximo!", Toast.LENGTH_SHORT).show();

                mediaPlayer.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}