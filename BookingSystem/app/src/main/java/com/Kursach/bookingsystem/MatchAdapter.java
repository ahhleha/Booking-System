package com.Kursach.bookingsystem;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.Kursach.bookingsystem.models.Matches;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    Context context;
    ArrayList<Matches> matchesArrayList;
    private MatchViewHolder holder;
    interface OnMatchesClickListener{
        void onMatchesClick(Matches matches, int position);
    }

    private final OnMatchesClickListener onClickListener;
    public MatchAdapter(Context context, ArrayList<Matches> matchesArrayList, OnMatchesClickListener onClickListener ) {
        this.context = context;
        this.matchesArrayList = matchesArrayList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MatchAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_item, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.MatchViewHolder holder, int position) {
        Matches match = matchesArrayList.get(position);


        holder.firstTeam.setText(match.getNameFirstTeam());
        holder.secondTeam.setText(match.getNameSecondTeam());
        holder.dateOfGame.setText(match.getDateOfGame());
        holder.timeOfGame.setText(match.getTimeOfGame());
        holder.stadium.setText(match.getStadium());

        // обработка нажатия
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onMatchesClick(match, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchesArrayList.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView firstTeam, secondTeam, dateOfGame, timeOfGame, stadium;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            firstTeam = itemView.findViewById(R.id.firstTeam_name);
            secondTeam = itemView.findViewById(R.id.secondTeam_name);
            dateOfGame = itemView.findViewById(R.id.date_of_game);
            timeOfGame = itemView.findViewById(R.id.time_of_game);
            stadium = itemView.findViewById(R.id.stadium);
        }
    }
}
