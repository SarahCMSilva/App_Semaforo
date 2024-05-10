package com.example.semafaro;

public class Constantes {
    public static final String USER_MQTT = "";
    public static final String PASSWORD_MQTT = "";
    public static final int QOS_MQTT = 1;
    public static final boolean RETAINED_MQTT = false;

    public static MQTTClient mqttClient;
    public static String TOPIC_LIGAR_DESCLIGAR_SEMAFARO = "status_semaforo/33";

}
