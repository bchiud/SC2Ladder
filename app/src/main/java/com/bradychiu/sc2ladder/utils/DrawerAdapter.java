package com.bradychiu.sc2ladder.utils;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;

import java.util.ArrayList;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private ArrayList<DrawerItemModel> drawerMenuList;
    public DrawerAdapter(ArrayList<DrawerItemModel> drawerMenuList) {
        this.drawerMenuList = drawerMenuList;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        holder.title.setText(drawerMenuList.get(position).getTitle());
        holder.icon.setImageResource(drawerMenuList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return drawerMenuList.size();
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
        }

    }

}