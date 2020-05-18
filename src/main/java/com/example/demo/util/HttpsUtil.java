package com.example.demo.util;

import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class HttpsUtil {

    public static String doPost(String spec, String json) {
        try {
            URL url = new URL(spec);
            if (url.getProtocol().toUpperCase().equals("HTTPS")) {
                jumpSSLVerify();
            }
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            if (!StringUtils.isEmpty(json)) {
                OutputStream outputStream = connection.getOutputStream();
                for (byte code : json.getBytes()) {
                    outputStream.write(code);
                }
                outputStream.flush();
                outputStream.close();
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                StringBuilder result = new StringBuilder();
                int code;
                while ((code = inputStreamReader.read()) >= 0) {
                    result.append((char) code);
                }
                inputStreamReader.close();
                return result.toString();
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    private static void jumpSSLVerify() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> true);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
