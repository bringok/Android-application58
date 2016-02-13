package it.bitesrl.univaq.corso.cityfinal.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import it.bitesrl.univaq.corso.cityfinal.model.Cities;

/**
 * Created by mattia on 04/02/16.
 */
public class CitiesDAO {

    public static final String TABLE = "cities";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COUNTRY = "country";
    public static final String PHOTO = "photo";
    public static final String LAT = "lat";
    public static final String LON = "lon";


    public static void CREATE(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE + " (" +
                ID      + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME    + " TEXT," +
                COUNTRY + " TEXT," +
                PHOTO   + " TEXT," +
                LAT   + " DOUBLE," +
                LON   + " DOUBLE" +
                ");";
        db.execSQL(query);
    }

    public static void UPGRADE(SQLiteDatabase db){
        DROP(db);
        CREATE(db);
    }

    public static void DROP(SQLiteDatabase db){
        String query = "DROP TABLE IF EXISTS " + TABLE + ";";
        db.execSQL(query);
    }


    public static boolean SAVE(SQLiteDatabase db, Cities cities){

        ContentValues values = new ContentValues();
        values.put(NAME, cities.getName());
        values.put(COUNTRY, cities.getCountry());
        values.put(PHOTO, cities.getPhoto());
        values.put(LAT, cities.getLat());
        values.put(LON, cities.getLon());
        long id = db.insert(TABLE, null, values);

        if(id != -1) cities.setId(id);
        return id != -1;
    }

    public static boolean UPDATE(SQLiteDatabase db, Cities cities){
        ContentValues values = new ContentValues();
        values.put(NAME, cities.getName());
        values.put(COUNTRY, cities.getCountry());
        values.put(PHOTO, cities.getPhoto());
        values.put(LAT, cities.getLat());
        values.put(LON, cities.getLon());
        return db.update(TABLE, values, ID + "=" + cities.getId(), null) == 1;
    }

    public static boolean DELETE(SQLiteDatabase db, Cities cities){
        return db.delete(TABLE, ID + "=" + cities.getId(), null) == 1;
    }

    public static List<Cities> GET(SQLiteDatabase db){
        List<Cities> cities = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE + ";";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
            while(cursor.moveToNext()) {

                Cities city = new Cities();
                city.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                city.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                city.setCountry(cursor.getString(cursor.getColumnIndex(COUNTRY)));
                city.setLat(cursor.getDouble(cursor.getColumnIndex(LAT)));
                city.setLon(cursor.getDouble(cursor.getColumnIndex(LON)));
                city.setPhoto(cursor.getString(cursor.getColumnIndex(PHOTO)));
                cities.add(city);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally {
            if(cursor != null) cursor.close();
        }

        return cities;
    }
}