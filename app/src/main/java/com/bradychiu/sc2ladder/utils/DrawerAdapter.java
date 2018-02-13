package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private ArrayList<DrawerItemModel> mDrawerMenuList;
    private DrawerItemClickListener mListener;
    private Toast mToast;

    public DrawerAdapter(ArrayList<DrawerItemModel> drawerMenuList, DrawerItemClickListener listener) {
        mDrawerMenuList = drawerMenuList;
        mListener = listener;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        return new DrawerViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        holder.title.setText(mDrawerMenuList.get(position).getTitle());
        holder.icon.setImageResource(mDrawerMenuList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mDrawerMenuList.size();
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        TextView title;
        ImageView icon;
        private DrawerItemClickListener aListener;

        public DrawerViewHolder(View itemView, DrawerItemClickListener bListener) {
            super(itemView);
            aListener = bListener;
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mListener.onDrawerItemClick(mDrawerMenuList.get(clickedPosition).getTitle());
        }
    }

    public interface DrawerItemClickListener {
        void onDrawerItemClick(String buttonTitle);
    }

}