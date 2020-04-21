package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.LastEventModel;
import com.amey.sports_android.view.callback.ClickCallback;

import java.util.List;

public class LastGameAdapter extends RecyclerView.Adapter<LastGameAdapter.EventsViewHolder> {
    public Context context;
    List<LastEventModel.Events> eventsList;
    ClickCallback clickCallback;
    public LastGameAdapter(Context context, ClickCallback callback){
        this.context = context;
        this.clickCallback = callback;
    }

    @NonNull
    @Override
    public LastGameAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_event_item_list, parent, false);
        return new LastGameAdapter.EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastGameAdapter.EventsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return eventsList == null ? 0 : eventsList.size();

    }

    private LastEventModel.Events getItem(int position) {
        return eventsList.get(position);
    }


    public void setEventsList(List<LastEventModel.Events> events) {
        this.eventsList = events;
        notifyDataSetChanged();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView eventNameTextview, eventdateTextview;
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            //cardView = itemView.findViewById(R.id.cardview);
            eventNameTextview = itemView.findViewById(R.id.eventNameTextview);
            eventdateTextview = itemView.findViewById(R.id.eventdateTextview);

        }

        void bind(int position){
            LastEventModel.Events events = getItem(position);
            eventNameTextview.setText(events.strEvent);
            eventdateTextview.setText(events.dateEvent);


        }
    }
}
