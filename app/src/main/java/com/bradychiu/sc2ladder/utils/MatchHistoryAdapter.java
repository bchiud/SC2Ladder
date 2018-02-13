package com.bradychiu.sc2ladder.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.matchHistory.MatchModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder> {

    private List<MatchModel> mMatches;

    public MatchHistoryAdapter(List<MatchModel> matches) {
        mMatches = matches;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_history_item, viewGroup, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder viewHolder, int i) {
        Date date = new Date(mMatches.get(i).date() * 1000);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        String dateFormatted = dateFormat.format(date);

        viewHolder.matchMap.setText(mMatches.get(i).map());
        viewHolder.matchType.setText(mMatches.get(i).type());
        viewHolder.matchDecision.setText(mMatches.get(i).decision());
        viewHolder.matchDate.setText(dateFormatted);
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView matchMap;
        TextView matchType;
        TextView matchDecision;
        TextView matchDate;

        MatchViewHolder(View itemView) {
            super(itemView);
            matchMap = (TextView) itemView.findViewById(R.id.tv_match_map);
            matchType = (TextView) itemView.findViewById(R.id.tv_match_type);
            matchDecision = (TextView) itemView.findViewById(R.id.tv_match_decision);
            matchDate = (TextView) itemView.findViewById(R.id.tv_match_date);
        }

    }

    public void updateMatches(List<MatchModel> newMatches) {
        mMatches = newMatches;
        notifyDataSetChanged();
    }
}
