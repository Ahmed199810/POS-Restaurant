package database;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DBHelper {

    private static OkHttpClient client = new OkHttpClient();

    public static boolean checkInternetConnection(){
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Connection Successful");
            return true;
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
            return false;
        }
    }


    public static String searchItem(String url, RequestBody formBody) throws MalformedURLException, IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String str = response.body().string();
        System.out.println(str);
        JSONObject o = new JSONObject(str);
        int success = (int) o.get("success");
        if(success == 1){
            return str;
        }else{
            return null;
        }
    }


    public static boolean postData(String url, RequestBody formBody) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String str = response.body().string();
        System.out.println(str);
        JSONObject o = new JSONObject(str);
        int success = (int) o.get("success");
        if(success == 1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean postQuery(String url, RequestBody formBody) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String str = response.body().string();
        System.out.println(str);
        JSONObject o = new JSONObject(str);
        int success = (int) o.get("success");
        if(success == 1){
            return true;
        }else{
            return false;
        }
    }

    public static String checkDataExist(String url, String word, String param) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("word", word)
                .add("param", param)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static String getData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String getData(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String loginUser(String url, RequestBody formBody) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        String str = response.body().string();
        System.out.println(str);
        JSONObject o = new JSONObject(str);
        int success = (int) o.get("success");
        if(success == 1){
            return str;
        }else{
            return null;
        }
    }

}