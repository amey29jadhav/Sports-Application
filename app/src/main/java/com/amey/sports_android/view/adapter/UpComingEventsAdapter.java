package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.EventsModel;
import com.amey.sports_android.service.model.Leagues;
import com.amey.sports_android.service.model.TeamModel;
import com.amey.sports_android.utilities.TypeFaceHelper;
import com.amey.sports_android.view.callback.ClickCallback;

import org.w3c.dom.Text;

import java.util.ConcurrentModificationException;
import java.util.List;

public class UpComingEventsAdapter extends RecyclerView.Adapter<UpComingEventsAdapter.EventsViewHolder> {


    public Context context;
    List<EventsModel.Events> eventsList;
    ClickCallback clickCallback;
    Typeface robotoRegular;

    public UpComingEventsAdapter(Context context, ClickCallback callback){
        this.context = context;
        this.clickCallback = callback;
        robotoRegular = TypeFaceHelper.getInstance(context).getStyleTypeFace(TypeFaceHelper.MEDIUM);

    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_list_item, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return eventsList == null ? 0 : eventsList.size();

    }

    private EventsModel.Events getItem(int position) {
        return eventsList.get(position);
    }


    public void setEventsList(List<EventsModel.Events> events) {
        this.eventsList = events;
        notifyDataSetChanged();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView eventNameTextview, eventdateTextview;
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            eventNameTextview = itemView.findViewById(R.id.eventNameTextview);
            eventNameTextview.setTypeface(robotoRegular);
            eventdateTextview = itemView.findViewById(R.id.eventdateTextview);
            eventdateTextview.setTypeface(robotoRegular);

        }

        void bind(int position){
            EventsModel.Events events = getItem(position);
            eventNameTextview.setText(events.strEvent);
            eventdateTextview.setText(events.dateEvent);


        }
    }
}
