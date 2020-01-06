package com.aditya.humbleparking.database;

/**
 * Created by Aditya on 20/12/2019.
 */

public class Config {
    public static final String URL_ADD="http://crud-aditya.000webhostapp.com/API_Smart_Parking/tambahData.php";
    public static final String URL_GET_ALL = "http://crud-aditya.000webhostapp.com/API_Smart_Parking/getSemuaData.php";
    public static final String URL_GET_PARKIR = "http://crud-aditya.000webhostapp.com/API_Smart_Parking//getData.php?id=";
    public static final String URL_UPDATE_PARKIR = "http://crud-aditya.000webhostapp.com/API_Smart_Parking/perbaharuiData.php";
    public static final String URL_DELETE_PARKIR = "http://crud-aditya.000webhostapp.com/API_Smart_Parking/hapusData.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_PARKIR_ID = "id";
    public static final String KEY_PARKIR_NOPLAT = "noPlat";
    public static final String KEY_PARKIR_NAMA = "nama";
    public static final String KEY_PARKIR_LAMA = "lama";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NOPLAT = "noPlat";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_LAMA = "lama";

    //employee id to pass with intent
    public static final String PARKIR_ID = "parkir_id";
}

