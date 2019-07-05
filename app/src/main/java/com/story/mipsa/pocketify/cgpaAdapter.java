package com.story.mipsa.pocketify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mipsa on 7/3/2019.
 */

public class cgpaAdapter extends RecyclerView.Adapter<cgpaAdapter.cgpaViewHolder> {

    private ArrayList<cgpaItem> mCgpaItems;

    public static class cgpaViewHolder extends RecyclerView.ViewHolder{

        public TextView cgpaSubName,grade,credit;

        public cgpaViewHolder(View itemView) {
            super(itemView);
            cgpaSubName = itemView.findViewById(R.id.cgpaSubName);
            grade = itemView.findViewById(R.id.grade);
            credit = itemView.findViewById(R.id.credit);
        }
    }

    public cgpaAdapter(ArrayList<cgpaItem> cgpaItems){
        mCgpaItems = cgpaItems;

    }



    @Override
    public cgpaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cgpa_card,parent,false);
        cgpaViewHolder cgpaViewHolder = new cgpaViewHolder(v);
        return cgpaViewHolder;
    }

    @Override
    public void onBindViewHolder(cgpaViewHolder holder, int position) {

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
