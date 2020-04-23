package com.amey.sports_android.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amey.sports_android.R;
import com.amey.sports_android.service.model.Sports;
import com.amey.sports_android.service.model.Standing;
import com.amey.sports_android.service.model.StandingModel;
import com.amey.sports_android.utilities.TypeFaceHelper;

import java.util.HashMap;
import java.util.List;

public class StandingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Standing> standingList;
    private HashMap<String, List<Standing>> expandableListDetail;
    private static int TYPE_HEADER = 1;
    private static int TYPE_SPORTS = 2;
    Typeface robotoRegular;


    public StandingAdapter(Context context ){
        this.context = context;


    }

    public void setStandingList(final List<Standing> lstStanding){
     this.standingList = lstStanding;
     notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_SPORTS){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standing_item_list, parent, false);
            return new StandingViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standing_item_list, parent, false);
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }else{
            return TYPE_SPORTS;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_SPORTS) {
            ((StandingViewHolder)holder).bind(position);
        }else {

        }

    }

    private Standing getItem(int position) {
        return standingList.get(position);
    }

    @Override
    public int getItemCount() {
        return standingList == null ? 0 : standingList.size();

    }

    public class StandingViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView header, played, win, draw, loss, total;
        RelativeLayout parentcontainer;
        public StandingViewHolder(@NonNull View itemView) {
            super(itemView);
            header= itemView.findViewById(R.id.header);
            header.setTypeface(robotoRegular);

            played = itemView.findViewById(R.id.played);
            played.setTypeface(robotoRegular);
            win = itemView.findViewById(R.id.win);
            win.setTypeface(robotoRegular);
            draw = itemView.findViewById(R.id.draw);
            draw.setTypeface(robotoRegular);
            loss = itemView.findViewById(R.id.loss);
            loss.setTypeface(robotoRegular);
            total = itemView.findViewById(R.id.total);
            total.setTypeface(robotoRegular);
        }

        void bind(int position){
            Standing currentStanding = getItem(position);
            if(currentStanding != null)
                if(!currentStanding.name.isEmpty()) {
                    header.setText(currentStanding.name);
                }else{
                    header.setText("-");
                }
            played.setText(Integer.toString(currentStanding.played));
            win.setText(Integer.toString(currentStanding.win));
            draw.setText(Integer.toString(currentStanding.draw));
            loss.setText(Integer.toString(currentStanding.loss));
            total.setText(Integer.toString(currentStanding.total));


        }

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView header, played, win, draw, loss, total;
        RelativeLayout parentcontainer;

        public HeaderViewHolder(@NonNull View itemView) {

            super(itemView);

            parentcontainer = itemView.findViewById(R.id.parentcontainer);
            parentcontainer.setBackgroundColor(context.getResources().getColor(R.color.gray));
            header= itemView.findViewById(R.id.header);
            played = itemView.findViewById(R.id.played);
            win = itemView.findViewById(R.id.win);
            draw = itemView.findViewById(R.id.draw);
            loss = itemView.findViewById(R.id.loss);
            total = itemView.findViewById(R.id.total);

            header.setTypeface(robotoRegular);
            played.setTypeface(robotoRegular);
            win.setTypeface(robotoRegular);
            draw.setTypeface(robotoRegular);
            loss.setTypeface(robotoRegular);
            total.setTypeface(robotoRegular);

            header.setText("Name");
            played.setText("Played");
            win.setText("Win");
            draw.setText("Draw");
            loss.setText("Loss");
            total.setText("Total");

            header.setTextColor(context.getResources().getColor(R.color.dark_grey));
            played.setTextColor(context.getResources().getColor(R.color.dark_grey));
            win.setTextColor(context.getResources().getColor(R.color.dark_grey));
            draw.setTextColor(context.getResources().getColor(R.color.dark_grey));
            loss.setTextColor(context.getResources().getColor(R.color.dark_grey));
            total.setTextColor(context.getResources().getColor(R.color.dark_grey));

        }

    }
}
