package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = null;
        HttpURLConnection connection = null ;
        int responseCode = 0;
        String urlString = "https://api.zippopotam.us/us/33162";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("problem in url");
        }
        //connection

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("connection problem");
        }

        //extract information from the connection object:

        if(responseCode == 200){
            BufferedReader in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();

            String readLine = null;


            while((readLine = in.readLine())!=null){
                apiData.append(readLine);
            }
            //
            in.close();

            JSONObject jsonApIResponse = new JSONObject(apiData.toString());

            System.out.println(jsonApIResponse.get("post code"));
            System.out.println(jsonApIResponse.get("country"));
            System.out.println(jsonApIResponse.get( "country abbreviation"));




        }else{
            System.out.println("API call could not be made!!!");
        }
    }
}