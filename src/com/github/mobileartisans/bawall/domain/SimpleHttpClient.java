package com.github.mobileartisans.bawall.domain;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpClient {
    private final String username;
    private final String password;
    private final String url;

    public SimpleHttpClient(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    protected String get() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Authorization", "basic " + new String(Base64.encode((username + ":" + password).getBytes(), Base64.NO_WRAP)));
            InputStream inputStream = con.getInputStream();
            ByteArrayOutputStream content = new ByteArrayOutputStream();

            int readBytes = 0;
            byte[] sBuffer = new byte[1000];
            while ((readBytes = inputStream.read(sBuffer)) != -1) {
                content.write(sBuffer, 0, readBytes);
            }
            return new String(content.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void post(String body) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.addRequestProperty("Authorization", "basic " + new String(Base64.encode((username + ":" + password).getBytes(), Base64.NO_WRAP)));
            con.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            ByteArrayOutputStream content = new ByteArrayOutputStream();

            InputStream inputStream = con.getInputStream();
            int readBytes = 0;
            byte[] sBuffer = new byte[1000];
            while ((readBytes = inputStream.read(sBuffer)) != -1) {
                content.write(sBuffer, 0, readBytes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
