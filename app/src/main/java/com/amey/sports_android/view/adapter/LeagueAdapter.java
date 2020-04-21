package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.view.callback.ClickCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder> {

    List<Leagues> lstLeagues;
    ClickCallback clickCallback;
    Context context;

    public LeagueAdapter(Context context, ClickCallback callback){
        this.context = context;
        this.clickCallback = callback;
    }

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
        RelativeLayout headerContainer;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            headerContainer = itemView.findViewById(R.id.headerContainer);
            headerContainer.setBackgroundColor(context.getResources().getColor( R.color.white));

            nameTextView = itemView.findViewById(R.id.name);
            gamelogo = itemView.findViewById(R.id.gamelogo);
            gamelogo.setVisibility(View.INVISIBLE);
        }

        public void bind(int position){
            final Leagues leagues = getItem(position);
            nameTextView.setText(leagues.strLeague);
            //Picasso.get().load(leagues.).into(gamelogo);
            headerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Prefs.saveLeagueId(context,leagues.idLeague);
                    if(clickCallback != null){
                        clickCallback.onClick(leagues.strLeague);
                    }
                }
            });

        }

    }
}
