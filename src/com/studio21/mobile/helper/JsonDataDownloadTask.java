package com.studio21.mobile.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class JsonDataDownloadTask extends AsyncTask 
{
	private final String TAG = "Alpha-Maraicher-Log";
	
	private String getJSONData(String url) 
	{	
		try 
		{       
	        URL target = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) target.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        
	        conn.connect();
	        int statusCode = conn.getResponseCode();
	        
	        if (statusCode == 200) 
	        {
	           return readInput( conn.getInputStream());      
	        } 
	        else 
	        {
	        	Log.d(TAG, "Failed to download file at " + url);
	        }
	    } 
		catch(IOException ioe)
		{
			Log.e(TAG, ioe.getLocalizedMessage());
		}
		catch (Exception e) 
		{
	        Log.e(TAG, e.getLocalizedMessage());
	    }        
		
        return "";
    }
	
	private String readInput(InputStream input) throws IOException
	{
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null) 
		{
			stringBuilder.append(line);
		}

        input.close();
        return stringBuilder.toString();
	}
		
	@Override
	protected Object doInBackground(Object... arg0) 
	{
		String url = arg0[0].toString();
		return getJSONData(url);
	}
}
