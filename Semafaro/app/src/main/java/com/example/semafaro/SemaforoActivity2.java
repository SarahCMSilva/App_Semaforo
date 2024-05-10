package com.example.semafaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class SemaforoActivity2 extends AppCompatActivity {

    private Button buttonVoltar;
    private Button buttonAmarelo;
    private Button buttonVermelho;
    private Button buttonVerde;
    private Switch switchStatus;
    private Switch switchLedVermelho;
    private Switch switchLedVerde;
    private Switch switchLedAmarelo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonAmarelo = findViewById(R.id.buttonAmarelo);
        buttonVerde = findViewById(R.id.buttonVerde);
        buttonVermelho = findViewById(R.id.buttonVermelho);
        buttonVoltar = findViewById(R.id.buttonVoltar);
        switchLedAmarelo =findViewById(R.id.switchLedAmarelo);
        switchLedVerde =findViewById(R.id.switchLedVerde);
        switchLedVermelho =findViewById(R.id.switchLedVermelho);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        switchStatus = findViewById(R.id.switchStatus); // Substitua pelo ID correto do seu Switch
        eventosSwitch();
    }

    private void eventosSwitch(){
        switchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Context self = getApplicationContext();
                    Status status = new Status();
                    String message = status.ligarSemaforo();
                    Constantes.mqttClient.publish(Constantes.TOPIC_LIGAR_DESCLIGAR_SEMAFARO, message, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            buttonVermelho.setBackgroundTintList(getColorStateList(R.color.red));
                            buttonVerde.setBackgroundTintList(getColorStateList(R.color.green));
                            buttonAmarelo.setBackgroundTintList(getColorStateList(R.color.yellow));
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(self,"Falha ao ligar semafaro :(", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    buttonVermelho.setBackgroundTintList(getColorStateList(R.color.cinza));
                    buttonVerde.setBackgroundTintList(getColorStateList(R.color.cinza));
                    buttonAmarelo.setBackgroundTintList(getColorStateList(R.color.cinza));
                }
            }
        });
    }
}
