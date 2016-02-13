package it.bitesrl.univaq.corso.cityfinal.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import it.bitesrl.univaq.corso.cityfinal.model.Cities;
import it.bitesrl.univaq.corso.cityfinal.model.dao.CitiesDAO;

/**
 * Created by mattia on 04/02/16.
 */
public class UtilsDatabase extends SQLiteOpenHelper {

    private static final String DATABASE = "database.db";
    private static final int VERSION = 1;

    private static UtilsDatabase instance = null;
    private static Context mContext;

    public static UtilsDatabase getInstance(Context context){
        mContext = context;
        if(instance == null) instance = new UtilsDatabase(mContext);
        return instance;
    }



    public UtilsDatabase(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CitiesDAO.CREATE(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) CitiesDAO.UPGRADE(db);
    }

    public List<Cities> getAll(){
        return CitiesDAO.GET(getReadableDatabase());
    }

    public boolean save(Cities cities){
        return CitiesDAO.SAVE(getWritableDatabase(), cities);
    }

    public boolean update(Cities cities) {
        return CitiesDAO.UPDATE(getWritableDatabase(), cities);
    }

    public boolean delete(Cities cities){
        return CitiesDAO.DELETE(getWritableDatabase(), cities);
    }
}
