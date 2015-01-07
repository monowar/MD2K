package org.md2k.phoneframework.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.md2k.phoneframework.Constant;
import org.md2k.phoneframework.MainActivity;
import org.md2k.phoneframework.services.sensors.datatype.AutoSenseChestDataType;
import org.md2k.phoneframework.services.sensors.datatype.DataType;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class NetworkLogger {
	private static NetworkLogger INSTANCE = null;
	static Context context;
	DataType data;
	int count=0;
	
	public static NetworkLogger getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new NetworkLogger();
			context=MainActivity.context;

		}
		return INSTANCE;
	}
	public void sendDataToNetwork(DataType b)
	{
		if(count>=2) return;
		count++;
		if(isConnected()){
			data=b;
			new HttpAsyncTask().execute(Constant.URL);
		}
		else{
			Log.d("NetworkLogger","sendDataToNetwork: notconnected time="+b.getTimeStamp());
		}
	}
	public static String POST(String url, DataType b){
		AutoSenseChestDataType chest_b=(AutoSenseChestDataType) b;
        InputStream inputStream = null;
        String result = "";
        try {
 
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
 
            String json = "";
 
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("deviceid", chest_b.getDeviceID());
            jsonObject.accumulate("sensorid", chest_b.getSensorID());
            jsonObject.accumulate("timestamp", chest_b.getTimeStamp());
//            jsonObject.accumulate("name", chest_b.getDeviceID());
//            jsonObject.accumulate("country", chest_b.getSensorID());
//            jsonObject.accumulate("twitter", chest_b.getTimeStamp());
            
 
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
 
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person); 
 
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
 
            // 6. set httpPost Entity
            httpPost.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
 
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
 
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        // 11. return result
        return result;
    }	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    } 	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
 
//            person = new Person();
//            person.setName(etName.getText().toString());
//            person.setCountry(etCountry.getText().toString());
//            person.setTwitter(etTwitter.getText().toString());
 
            return POST(urls[0],data);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
 //           Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
       }
    }	
	public boolean isConnected(){
		context=MainActivity.context;
		
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
    }	
}
