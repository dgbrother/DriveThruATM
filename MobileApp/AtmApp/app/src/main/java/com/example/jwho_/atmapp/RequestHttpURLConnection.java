package com.example.jwho_.atmapp;

import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RequestHttpURLConnection {
    public String request(String _url, ContentValues params) {
        HttpURLConnection urlConn = null;
        StringBuffer sbParams = new StringBuffer();     // URL에 함께 보낼 parameter

        // StringBuffer에 파라미터 연결
        if(params == null) {
            sbParams.append("");
        }
        else {
            boolean isAnd = false;
            String key;
            String value;

            for(Map.Entry<String, Object> parameter : params.valueSet()) {
                key = parameter.getKey();
                value = parameter.getValue().toString();

                if(isAnd)
                    sbParams.append("&");

                sbParams.append(key).append("=").append(value);

                if(!isAnd)
                    if(params.size() >= 2)
                        isAnd = true;
            }
        }

        // HttpURLConnection 을 통해 Web의 데이터 가져오기
        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // url 설정
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Accept_Charset", "UTF-8");
            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // parameter 전달 및 데이터 읽어오기
            String strParams = sbParams.toString();
            OutputStream os = urlConn.getOutputStream();
            os.write(strParams.getBytes("UTF-8"));
            os.flush();
            os.close();

            Log.d("http", "responseCode: "+urlConn.getResponseCode());
            // 연결 요청 확인
            if(urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;
            // 읽어온 데이터 리턴
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            String line;
            String page = "";

            while((line = reader.readLine()) != null) {
                page += line;
            }
            Log.d("http", "URL: "+url.toString());
            Log.d("http", "Param: "+strParams);
            Log.d("http", "Response: "+page);

            return page;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConn != null)
                urlConn.disconnect();
        }

        return null;
    }
}
