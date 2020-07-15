package com.redhat.examples.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserService {

    private final Map<String, UserProvider> providers = new TreeMap<String, UserProvider>();
    
    private static final Log LOG = LogFactory.getLog(UserService.class);

    public UserService() throws IOException, JSONException {
    	URL urlForGetRequest = new URL("https://ion-qas.iff.com/api/v1/users/");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            String str = response.toString();
	    JSONObject obj = new JSONObject(str);
 	    JSONArray arr = obj.getJSONArray("users");
	    for (int i = 0; i < arr.length(); i++) {
   		 String userid = arr.getJSONObject(i).getString("globaluserid");
	         String fname = arr.getJSONObject(i).getString("firstname");
		 String lname = arr.getJSONObject(i).getString("lastname");
		 providers.put(userid, new UserProvider(userid, fname, lname));
	    }
        } else {
            System.out.println("GET NOT WORKED");
        }

    }

    public Collection<UserProvider> listProviders() {
        return providers.values();
    }
}
