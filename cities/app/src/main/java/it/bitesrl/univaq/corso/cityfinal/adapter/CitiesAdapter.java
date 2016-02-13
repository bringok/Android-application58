package it.bitesrl.univaq.corso.cityfinal.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.bitesrl.univaq.corso.cityfinal.MainActivity;
import it.bitesrl.univaq.corso.cityfinal.MapsActivity;
import it.bitesrl.univaq.corso.cityfinal.R;
import it.bitesrl.univaq.corso.cityfinal.model.Cities;
import it.bitesrl.univaq.corso.cityfinal.utils.UtilsImage;
import it.bitesrl.univaq.corso.cityfinal.utils.UtilsPreference;

/**
 * Created by mattia on 04/02/16.
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private List<Cities> mData;
    private static Context context;

    public CitiesAdapter(Context context, List<Cities> cities) {
        mData = cities;
        this.context = context;

        UtilsImage.init(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_citta, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Cities cities = mData.get(position);

        holder.city= cities;
        holder.name.setText(cities.getName());
        holder.lat.setText(String.valueOf(cities.getLat()));
        holder.lon.setText(String.valueOf(cities.getLon()));
        holder.country.setText(cities.getCountry());

        UtilsImage.load(holder.image, cities.getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView name, country, lat, lon;
        ImageView image;

        Cities city;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.adapter_citta_text_name);
            lat = (TextView) itemView.findViewById(R.id.adapter_citta_text_lat);
            lon = (TextView) itemView.findViewById(R.id.adapter_citta_text_lon);
            country = (TextView) itemView.findViewById(R.id.adapter_citta_text_country);
            image = (ImageView) itemView.findViewById(R.id.adapter_citta_image);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, MapsActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("nome", city.getName());
            bundle.putDouble("lat", city.getLat());
            bundle.putDouble("lon", city.getLon());
            intent.putExtras(bundle);

            context.startActivity(intent);

        }

        @Override
        public boolean onLongClick(View v) {

            UtilsPreference.savecity(context, "nome", city.getName());
            Toast.makeText(context, "Salvato come preferito "+UtilsPreference.loadcity(context,"nome",city.getName())+"",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}






