package it.bitesrl.univaq.corso.cityfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.bitesrl.univaq.corso.cityfinal.adapter.CitiesAdapter;
import it.bitesrl.univaq.corso.cityfinal.model.Cities;
import it.bitesrl.univaq.corso.cityfinal.utils.UtilsDatabase;
import it.bitesrl.univaq.corso.cityfinal.utils.UtilsHttp;
import it.bitesrl.univaq.corso.cityfinal.utils.UtilsPreference;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.MENU_1:
                AsyncLaoder async = new AsyncLaoder();
                async.execute();
                break;
        }
        return false;
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /*TextView text= (TextView) findViewById(R.id.preferita);
       text.setText(UtilsPreference.load(MainActivity.this,"nome");*/
        mList = (RecyclerView) findViewById(R.id.main_recycler_citta);

        mList.setLayoutManager(new LinearLayoutManager(this));

        AsyncLaoder async = new AsyncLaoder();
        async.execute();
    }

    private class AsyncLaoder extends AsyncTask<Void, Void, List<Cities>>{

        @Override
        protected List<Cities> doInBackground(Void... params) {

            boolean firstTime =
                    UtilsPreference.load(MainActivity.this, UtilsPreference.KEY_FIRSTTIME, true);
            UtilsPreference.save(MainActivity.this, UtilsPreference.KEY_FIRSTTIME, false);
            if(firstTime) {
                List<Cities> cities = UtilsHttp.requestGet();
                if(cities != null){
                    for(Cities city : cities){
                        UtilsDatabase.getInstance(MainActivity.this).save(city);
                    }
                }
                return cities;
            } else {
                return UtilsDatabase.getInstance(MainActivity.this).getAll();
            }
        }

        @Override
        protected void onPostExecute(List<Cities> cities) {

            mList.setAdapter(new CitiesAdapter(MainActivity.this, cities));
        }
    }
}
