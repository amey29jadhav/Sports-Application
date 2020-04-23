package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.graphics.Movie;
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
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.utilities.PicassoCircleTransformation;
import com.amey.sports_android.utilities.TypeFaceHelper;
import com.amey.sports_android.view.ui.SportsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> implements Filterable {

    List<Sports> sportsList;
    List<Sports> filterSportsList;
    SportsFragment.ClickCallback clickCallback;
    Context context;
    private Typeface fontAwesomeFont;
    Typeface robotoRegular;


    public SportsAdapter(Context context, SportsFragment.ClickCallback clickCallback){
        this.context = context;
        this.clickCallback = clickCallback;
        fontAwesomeFont = Typeface.createFromAsset(context.getAssets(), "FontAwesome.otf");
        robotoRegular = TypeFaceHelper.getInstance(context).getStyleTypeFace(TypeFaceHelper.MEDIUM);

    }
    public void setSportList(final List<Sports> sports) {
       /* if (this.sportsList == null) {
            this.sportsList = sports;
            notifyItemRangeInserted(0, sports.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SportsAdapter.this.sportsList.size();
                }

                @Override
                public int getNewListSize() {
                    return sports.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return SportsAdapter.this.sportsList.get(oldItemPosition).idSport ==
                            sports.get(newItemPosition).idSport;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Sports project = sports.get(newItemPosition);
                    Sports old = sports.get(oldItemPosition);
                    return project.idSport == old.idSport;
                    //&& Objects.equals(project.git_url, old.git_url);
                }
            });
            this.sportsList = sports;
            result.dispatchUpdatesTo(this);

        }*/
        this.sportsList = sports;
        this.filterSportsList = sports;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SportsAdapter.SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list_item, parent, false);
        return new SportsViewHolder(view);

    }



    private Sports getItem(int position) {
        return filterSportsList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return filterSportsList == null ? 0 : filterSportsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterSportsList = sportsList;
                } else {
                    List<Sports> filteredList = new ArrayList<>();
                    for (Sports row : sportsList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.strSport.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filterSportsList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterSportsList;
                filterResults.count = filterSportsList.size();
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterSportsList = (ArrayList<Sports>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class SportsViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView nameTextView, arrowTextview;
        AppCompatImageView gamelogo;
        RelativeLayout headerContainer;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            headerContainer = itemView.findViewById(R.id.headerContainer);
            headerContainer.setBackgroundColor(context.getResources().getColor( R.color.white));
            nameTextView = itemView.findViewById(R.id.name);
            nameTextView.setTypeface(robotoRegular);
            arrowTextview = itemView.findViewById(R.id.arrowTextview);
            arrowTextview.setTypeface(fontAwesomeFont);
            gamelogo = itemView.findViewById(R.id.gamelogo);
        }
        void bind(int position) {
            final Sports sports = getItem(position);
            nameTextView.setText(sports.strSport);
            arrowTextview.setText(context.getResources().getString(R.string.fa_angle_right));
            Picasso.get().load(sports.strSportThumb).transform(new PicassoCircleTransformation()).into(gamelogo);
            headerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickCallback != null){
                        clickCallback.onClick(sports.strSport);
                    }
                }
            });



        }
    }



}
