package com.example.magneticfieldsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


    public class MainActivity extends AppCompatActivity implements SensorEventListener {

        SensorManager sensorManager;
        Sensor sensor =null;
        ConstraintLayout lym;
        TextView tv;
        TextView axissTv;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            lym = findViewById(R.id.lymain);
            tv = findViewById(R.id.infoMagnetic);
            axissTv = findViewById(R.id.AxisID);
            sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

            if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=null ) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            }else {
                Toast.makeText(this, "is not found", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                int x = (int)sensorEvent.values[0];
                int y = (int)sensorEvent.values[1];
                int z = (int)sensorEvent.values[2];
                tv.setText("X :"+x+"Y  :"+y+"Z :"+z+"\n MaxValue: "+sensorEvent.sensor.getMaximumRange());
                if(x>50){
                    axissTv.setText("X");
                    lym.setBackgroundResource(R.drawable.blue);
                  //  axissTv.setTextColor(getResources().getColor(R.color.colorBlau));



                } else if(y>80) {
                    axissTv.setText("Y");
                   // axissTv.setTextColor(getResources().getColor(R.color.colorRead));



                 lym.setBackgroundResource(R.drawable.read);
                }else if(z>100) {
                    axissTv.setText("Z");
                   // axissTv.setTextColor(getResources().getColor(R.color.colorBlack));

                    lym.setBackgroundResource(R.drawable.black);

                }
            }


            }




        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }


        @Override
        protected void onPause() {
            super.onPause();

            sensorManager.unregisterListener(this);
        }

        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }

    }
