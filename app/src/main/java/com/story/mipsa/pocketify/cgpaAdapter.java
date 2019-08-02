package com.story.mipsa.pocketify;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mipsa on 7/3/2019.
 */

public class cgpaAdapter extends RecyclerView.Adapter<cgpaAdapter.cgpaViewHolder> {

    private ArrayList<cgpaItem> mCgpaItems;
    private FragmentActivity mContext;

    public cgpaAdapter(ArrayList<cgpaItem> cgpaItems, FragmentActivity context){
        mCgpaItems = cgpaItems;
        mContext = context;
    }

    public static class cgpaViewHolder extends RecyclerView.ViewHolder{

        public TextView cgpaSubName,grade,credit;

        public cgpaViewHolder(View itemView) {
            super(itemView);
            cgpaSubName = itemView.findViewById(R.id.cgpaSubName);
            grade = itemView.findViewById(R.id.grade);
            credit = itemView.findViewById(R.id.credit);
        }
    }


    @Override
    public cgpaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cgpa_card,parent,false);
        cgpaViewHolder cgpaViewHolder = new cgpaViewHolder(v);
        return cgpaViewHolder;
    }

    @Override
    public void onBindViewHolder(final cgpaViewHolder holder, final int position) {

        cgpaItem currentItem = mCgpaItems.get(position);
        holder.cgpaSubName.setText(currentItem.getCgpaSubName());
        holder.credit.setText(currentItem.getSubCredit());
        holder.grade.setText(currentItem.getSubGrade());
    }

    @Override
    public int getItemCount() {
        return mCgpaItems.size();
    }
}
