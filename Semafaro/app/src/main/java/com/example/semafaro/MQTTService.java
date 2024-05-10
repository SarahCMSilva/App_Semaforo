package com.example.semafaro;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.*;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MQTTService {
    private Context context;
    public MQTTService(Context context, String hostname,String port){
        String serverURI = "tcp://".concat(hostname).concat(":").concat(port);
        this.context = context;
        Constantes.mqttClient = new MQTTClient(context, serverURI);
    }


    public void connect(Button buttonConectar, Button buttonDesconectar, Button buttonInteragirSemaforo){
        Context self = this.context;

        Constantes.mqttClient.connect(Constantes.USER_MQTT, Constantes.PASSWORD_MQTT, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                buttonConectar.setEnabled(false);
                buttonDesconectar.setEnabled(true);
                buttonInteragirSemaforo.setEnabled(true);
                Toast.makeText(self, "Conexão OK :)", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                buttonConectar.setEnabled(true);
                buttonDesconectar.setEnabled(false);
                buttonInteragirSemaforo.setEnabled(false);
                Toast.makeText(self, " Falha na conexão", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void disconnect(Button buttonConectar, Button buttonDesconectar, Button buttonInteragirSemaforo){
        Context self = this.context;
        Constantes.mqttClient.disconnect(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                buttonConectar.setEnabled(true);
                buttonDesconectar.setEnabled(false);
                buttonInteragirSemaforo.setEnabled(false);

                Toast.makeText(self, "Desconectado com sucesso :)", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                buttonConectar.setEnabled(false);
                buttonDesconectar.setEnabled(true);
                buttonInteragirSemaforo.setEnabled(true);

                Toast.makeText(self, "Falha ao Desconectar :(", Toast.LENGTH_LONG).show();
            }


        });

    }
    public void publish(String topic){
        Context self = this.context;
        Constantes.mqttClient.publish(topic, "", new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}