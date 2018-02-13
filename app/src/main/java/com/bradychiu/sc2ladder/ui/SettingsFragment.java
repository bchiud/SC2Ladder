package com.bradychiu.sc2ladder.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;

public class SettingsFragment extends Fragment {

    private Context mContext;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mContext = getActivity();
        mTextView = (TextView) view.findViewById(R.id.tv_settings);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTextView.setText("Settings Frag Started");
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

}
