package com.amey.sports_android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder> {

    List<Leagues> lstLeagues;

    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_list_item, parent, false);
        return new LeagueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return lstLeagues == null ? 0 : lstLeagues.size();

    }

    private Leagues getItem(int position) {
        return lstLeagues.get(position);
    }

    public void setLeaguesList(List<Leagues> leagues) {
        this.lstLeagues = leagues;
        notifyDataSetChanged();
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        AppCompatImageView gamelogo;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            gamelogo = itemView.findViewById(R.id.gamelogo);
        }

        public void bind(int position){
            Leagues leagues = getItem(position);
            nameTextView.setText(leagues.strLeague);
            //Picasso.get().load(leagues.).into(gamelogo);
        }

    }
}
