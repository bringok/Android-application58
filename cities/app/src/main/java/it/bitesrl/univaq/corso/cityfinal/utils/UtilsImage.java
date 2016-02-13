package it.bitesrl.univaq.corso.cityfinal.utils;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by mattia on 04/02/16.
 */
public class UtilsImage {

    public static void init(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
    }


    public static void load(ImageView image, String urlImage){

        ImageLoader.getInstance().displayImage(urlImage, image);
    }
}
