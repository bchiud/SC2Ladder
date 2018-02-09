package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;

public class MatchHistoryFragment extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_history, container, false);
        System.out.println(R.id.tv_match_history);
        mTextView = (TextView) view.findViewById(R.id.tv_match_history);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTextView.setText("Match History Frag Started");
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

}
