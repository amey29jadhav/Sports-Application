package com.amey.sports_android.view.adapter;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.view.ui.SportsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    List<Sports> sportsList;
    SportsFragment.ClickCallback clickCallback;

    public SportsAdapter(SportsFragment.ClickCallback clickCallback){
        this.clickCallback = clickCallback;
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
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list_item, parent, false);
        return new SportsViewHolder(view);
    }

    private Sports getItem(int position) {
        return sportsList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return sportsList == null ? 0 : sportsList.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        AppCompatImageView gamelogo;
        CardView cardView;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.itemContainer);

            nameTextView = itemView.findViewById(R.id.name);
            gamelogo = itemView.findViewById(R.id.gamelogo);
        }
        void bind(int position) {
            final Sports sports = getItem(position);
            nameTextView.setText(sports.strSport);
            Picasso.get().load(sports.strSportThumb).into(gamelogo);
            cardView.setOnClickListener(new View.OnClickListener() {
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
