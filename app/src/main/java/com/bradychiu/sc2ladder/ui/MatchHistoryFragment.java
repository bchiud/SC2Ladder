package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;

public class MatchHistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_history, container, false);
    }

    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.tv_match_history);
        view.setText(text);
    }

}
