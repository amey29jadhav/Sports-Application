package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.utilities.PicassoCircleTransformation;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.view.callback.ClickCallback;
import com.amey.sports_android.view.ui.SportsFragment;
import com.amey.sports_android.view.ui.TeamFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> implements Filterable {

    List<TeamModel.Team> teamList;
    List<TeamModel.Team> filtertTeamList;
    ClickCallback<String> clickCallback;
    Context context;

    public TeamAdapter(Context context, ClickCallback<String> clickCallback){
        this.context = context;
        this.clickCallback = clickCallback;
    }
    public void setTeamList(List<TeamModel.Team> teamList){
        this.teamList = teamList;
        this.filtertTeamList = teamList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
         return filtertTeamList == null ? 0 : filtertTeamList.size();
    }

    private TeamModel.Team getItem(int position) {
        return filtertTeamList.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filtertTeamList = teamList;
                } else {
                    List<TeamModel.Team> filteredList = new ArrayList<>();
                    for (TeamModel.Team row : teamList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.strTeam.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filtertTeamList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtertTeamList;
                filterResults.count = filtertTeamList.size();
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtertTeamList = (ArrayList<TeamModel.Team>) filterResults.values;
                notifyDataSetChanged();
            }
        };    }

    public class TeamViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        AppCompatImageView gamelogo;
        RelativeLayout headerContainer;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            headerContainer = itemView.findViewById(R.id.headerContainer);
            headerContainer.setBackgroundColor(context.getResources().getColor( R.color.white));


            nameTextView = itemView.findViewById(R.id.name);
            gamelogo = itemView.findViewById(R.id.gamelogo);
        }

        private void bind(int position){
            final TeamModel.Team team = getItem(position);
            nameTextView.setText(team.strTeam);
            Picasso.get().load(team.strTeamLogo).transform(new PicassoCircleTransformation()).into(gamelogo);
            headerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Prefs.saveTeamId(context,team.idTeam);
                    if(clickCallback != null){
                        clickCallback.onClick(team.idTeam);
                    }
                }
            });

        }

    }
}
