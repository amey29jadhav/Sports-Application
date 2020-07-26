package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.utilities.PicassoCircleTransformation;
import com.amey.sports_android.utilities.Prefs;
import com.amey.sports_android.utilities.TypeFaceHelper;
import com.amey.sports_android.view.callback.ClickCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder> implements Filterable {

    List<Leagues> lstLeagues;
    List<Leagues> filtertLeagues;
    ClickCallback clickCallback;
    Context context;
    private Typeface fontAwesomeFont;
    Typeface robotoRegular;


    public LeagueAdapter(Context context, ClickCallback callback){
        this.context = context;
        this.clickCallback = callback;
        //fontAwesomeFont = Typeface.createFromAsset(context.getAssets(), "FontAwesome.otf");
        fontAwesomeFont = ResourcesCompat.getFont(context,R.font.fontawesome);
        robotoRegular = TypeFaceHelper.getInstance(context).getStyleTypeFace(TypeFaceHelper.MEDIUM);

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
        return filtertLeagues == null ? 0 : filtertLeagues.size();

    }

    private Leagues getItem(int position) {
        return filtertLeagues.get(position);
    }

    public void setLeaguesList(List<Leagues> leagues) {
        this.lstLeagues = leagues;
        this.filtertLeagues = leagues;
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filtertLeagues = lstLeagues;
                } else {
                    List<Leagues> filteredList = new ArrayList<>();
                    for (Leagues row : lstLeagues) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.strLeague.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filtertLeagues = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtertLeagues;
                filterResults.count = filtertLeagues.size();
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtertLeagues = (ArrayList<Leagues>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView nameTextView;
        AppCompatImageView gamelogo;
        AppCompatTextView arrowTextview;
        RelativeLayout headerContainer;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            headerContainer = itemView.findViewById(R.id.headerContainer);
            headerContainer.setBackgroundColor(context.getResources().getColor( R.color.white));

            nameTextView = itemView.findViewById(R.id.name);
            nameTextView.setTypeface(robotoRegular);
            arrowTextview = itemView.findViewById(R.id.arrowTextview);
            arrowTextview.setTypeface(fontAwesomeFont);
            gamelogo = itemView.findViewById(R.id.gamelogo);
        }

        public void bind(int position){
            final Leagues leagues = getItem(position);
            nameTextView.setText(leagues.strLeague);
            arrowTextview.setText(context.getResources().getString(R.string.fa_angle_right));
            Picasso.get().load("abc").transform(new PicassoCircleTransformation()).placeholder(context.getResources().getDrawable(R.drawable.leagues)).into(gamelogo);
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
