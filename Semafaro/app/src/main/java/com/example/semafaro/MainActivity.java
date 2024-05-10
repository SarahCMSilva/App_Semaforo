package com.example.semafaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextBroker;
    private EditText editTextPorta;
    private Button buttonConectar;
    private Button buttonDesconectar;
    private Button buttonInteragir;
    private MQTTService mqttService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBroker = findViewById(R.id.editTextBroker);
        editTextPorta = findViewById(R.id.editTextPorta);
        buttonConectar = findViewById(R.id.buttonConectar);
        buttonDesconectar = findViewById(R.id.buttonDesconectar);
        buttonInteragir = findViewById(R.id.buttonInteragir);

        buttonInteragir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaSemaforo =new Intent(MainActivity.this,SemaforoActivity2.class);
                startActivity(telaSemaforo);
                finish();
            }
        });
        buttonConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // conectar no broker
                // Se a conexao for bem sucedida habilita o botao
//                buttonInteragir.setEnabled(true);
//                buttonConectar.setEnabled(false);
//                buttonDesconectar.setEnabled(true);
                String hostname = "broker.hivemq.com";
                String port = "1883";
                mqttService = new MQTTService(getApplicationContext(),hostname,port);
                mqttService.connect(buttonConectar,buttonDesconectar,buttonInteragir);


            }
        });
        buttonDesconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttService.disconnect(buttonConectar,buttonDesconectar,buttonInteragir);
//                buttonInteragir.setEnabled(false);
//                buttonDesconectar.setEnabled(false);
//                buttonConectar.setEnabled(true);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}