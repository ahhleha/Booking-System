package com.Kursach.bookingsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder> {

    Context context;
    ArrayList<Stadium> stadiumsArrayList;
    private StadiumViewHolder holder;

    public StadiumAdapter(Context context, ArrayList<Stadium> stadiumsArrayList) {
        this.context = context;
        this.stadiumsArrayList = stadiumsArrayList;
    }


    @NonNull
    @Override
    public StadiumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stadium_item, parent, false);
        return new StadiumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StadiumViewHolder holder, int position) {

        Stadium stadium = stadiumsArrayList.get(position);
        int url_image = context.getResources().getIdentifier(stadium.getUrl_image(), "drawable", context.getPackageName());
        holder.stadiumImage.setImageResource(url_image);
        holder.name.setText(stadium.getName());
        holder.info.setText(String.valueOf(stadium.getInfo()));
    }

    @Override
    public int getItemCount() {
        return stadiumsArrayList.size();
    }

    public static class StadiumViewHolder extends RecyclerView.ViewHolder{

        ImageView stadiumImage;
        TextView name, info;

        public StadiumViewHolder(@NonNull View itemView) {
            super(itemView);
            stadiumImage = itemView.findViewById(R.id.stadium_image);
            name = itemView.findViewById(R.id.title_stadium);
            info = itemView.findViewById(R.id.stadium_info);
        }
    }
}
