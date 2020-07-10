package database;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequests;

import java.io.IOException;

public class CounterHelper {


    private static int counter;

    public CounterHelper() throws IOException {
        getCount();
    }

    public static void updateCount(int counter) throws IOException{

        RequestBody formBody = new FormBody.Builder()
                .add("count", counter + "")
                .build();
        String url = APIRequests.INCREMENT_BILL_COUNT;

        if(DBHelper.postData(url, formBody)){
            System.out.println("Incremented successfully");
        }

    }


    public static void getCount() throws IOException{
        String url = APIRequests.GET_BILL_COUNT;
        String response = DBHelper.getData(url);

        JSONObject o = new JSONObject(response.toString());
        JSONArray arr = o.getJSONArray("nums");
        counter = arr.getJSONObject(0).getInt("count");
        System.out.println("Bill Recorded successfully");

    }

    public static int getCounter() {
        return counter;
    }

}