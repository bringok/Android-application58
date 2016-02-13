package it.bitesrl.univaq.corso.cityfinal.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.bitesrl.univaq.corso.cityfinal.model.Cities;

/**
 * Created by root on 04/02/16.
 */
public class UtilsHttp {

    private static final String MyURL =
            "http://www.bitesrl.it/test/bite/univaq/script.php?action=getAll";


    public static List<Cities> requestGet(){
        List<Cities> list = new ArrayList<>();
        HttpURLConnection connection = null;
        try{
            URL url = new URL(MyURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);

                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();

                JSONObject json = new JSONObject(sb.toString());
                JSONArray jCities = json.getJSONArray("city");
                if(jCities != null){
                    for(int i = 0; i < jCities.length(); ++i){
                        JSONObject jCity = jCities.getJSONObject(i);

                        Cities cities = new Cities();
                        cities.setName(jCity.getString("name"));
                        cities.setCountry(jCity.getString("country"));
                        cities.setPhoto(jCity.getString("photo"));
                        cities.setLon(jCity.getDouble("lon"));
                        cities.setLat(jCity.getDouble("lat"));
                        list.add(cities);
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally {
            if(connection != null) connection.disconnect();
        }
        return list;
    }
}
