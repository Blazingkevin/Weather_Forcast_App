package com.nivek.kevoweather;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherDataFetcher {

    private static final String  TAG ="WeatherDataFetcher" ;
    private static final String APPID = "5380558eb6f894fc9c34b06227195c3f";
    /*
    * Given the url string , returns content of the url inform of byte
    * @pa
    *
    */
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec) ;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;


        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream() ;
            InputStream in = connection.getInputStream() ;

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw  new IOException(connection.getResponseMessage() +": with" + urlSpec) ;
            }

            int byteRead  = 0 ;
            byte[] buffer = new byte[1024] ;

            while((byteRead=in.read(buffer))>0){// while ther is data to read, continue writing to the to the outputsream
                out.write(buffer,0,byteRead);
            }
            out.close();

            return out.toByteArray();
        }
        finally {
            connection.disconnect();
        }
    }

    //converts the byte array to string
    public String getUrlString(String urlSpec) throws IOException{
        return  new String(getUrlBytes(urlSpec)) ;
    }

    //given the location it fetches the weather data in json format
    public void getWeatherData(String city){

        try{
            String url = Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon()
                    .appendQueryParameter("id",city).appendQueryParameter("appid",APPID).build().toString() ;
            String json = getUrlString(url) ;
            Log.i(TAG,"Received json: "+ json);
        }
        catch (IOException ioe){
            Log.e(TAG, "Failed to fetch weather data",ioe) ;
        }


    }
}
