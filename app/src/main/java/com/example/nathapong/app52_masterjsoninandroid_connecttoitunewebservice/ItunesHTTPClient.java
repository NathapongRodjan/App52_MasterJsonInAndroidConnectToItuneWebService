package com.example.nathapong.app52_masterjsoninandroid_connecttoitunewebservice;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItunesHTTPClient {

    private static String BASE_URL = "https://itunes.apple.com/search?term=michael+jackson";


    public String getItunesStuffData(){

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection)(new URL(BASE_URL)).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            //httpURLConnection.setDoOutput(true);       // เนื่องจาก App นี้ไม่ได้ output อะไรออกไปจึงไม่ต้องใช้
            httpURLConnection.connect();

            // อ่านผลลัพธ์ที่ได้จากการเชื่อมต่อ

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return  stringBuffer.toString();

        }catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        return null;
    }


    public Bitmap getBitmapFromURL(String stringUrl){

        Bitmap bitmap = null;

        try {
            URL url = new URL(stringUrl);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return bitmap;
    }

}
