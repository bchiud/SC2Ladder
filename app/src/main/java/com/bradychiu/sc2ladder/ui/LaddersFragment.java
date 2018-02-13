package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;

public class LaddersFragment extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ladders, container, false);
        mTextView = (TextView) view.findViewById(R.id.tv_ladders);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTextView.setText("Ladders Frag Started");
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

}
