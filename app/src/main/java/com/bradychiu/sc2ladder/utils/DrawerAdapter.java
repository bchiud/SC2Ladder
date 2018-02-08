package com.bradychiu.sc2ladder.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bradychiu.sc2ladder.R;
import com.bradychiu.sc2ladder.model.drawer.DrawerItemModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private ArrayList<DrawerItemModel> drawerMenuList;
    // a
    private Context context;
    // // a
    // private final ClickListener listener;

    public DrawerAdapter(ArrayList<DrawerItemModel> drawerMenuList, Context context) {
        this.drawerMenuList = drawerMenuList;
        this.context = context;
        // // a
        // this.listener = listener;
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
        private WeakReference<ClickListener> listenerWeakReference;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            // // a
            // listenerWeakReference = new WeakReference<>(listener);
            // // a
            // itemView.setOnClickListener(context);
        }

    }

    public interface ClickListener {
        void onPositionClicked(int position);
        void onLongClicked(int position);
    }

}