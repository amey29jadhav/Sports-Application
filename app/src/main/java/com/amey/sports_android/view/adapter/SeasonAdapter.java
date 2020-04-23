package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.SeasonModel;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.view.callback.ClickCallback;
import com.amey.sports_android.view.ui.SportsFragment;

import java.security.PublicKey;
import java.util.List;

import javax.security.auth.callback.Callback;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {

    public List<SeasonModel.Season> lstSeason;
    Context context;
    ClickCallback<String> clickCallback;
    Typeface fontAwesomeFont;

    public SeasonAdapter(Context context, ClickCallback clickCallback){
        this.context = context;
        this.clickCallback = clickCallback;
        fontAwesomeFont = ResourcesCompat.getFont(context,R.font.fontawesome);
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_list_item, parent, false);
        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
         return lstSeason == null ? 0 : lstSeason.size();
    }

    public void setSeasonList(List<SeasonModel.Season> seasons) {
        this.lstSeason = seasons;
        notifyDataSetChanged();
    }

    private SeasonModel.Season getItem(int position) {
        return lstSeason.get(position);
    }


    public class SeasonViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView name, arrowTextview;
        RelativeLayout headerContainer;
        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            headerContainer = itemView.findViewById(R.id.headerContainer);
            name = itemView.findViewById(R.id.name);
            arrowTextview = itemView.findViewById(R.id.arrowTextview);
            arrowTextview.setTypeface(fontAwesomeFont);
            arrowTextview.setText(context.getResources().getString(R.string.fa_angle_right));

        }

        void bind(int position){
            final SeasonModel.Season season = getItem(position);
            if(season != null){
                name.setText(season.strSeason);

            }
            headerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickCallback != null){
                        clickCallback.onClick(season.strSeason);
                    }
                }
            });


        }
    }
}
