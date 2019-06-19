package com.story.mipsa.pocketify;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mipsa on 6/11/2019.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.exampleViewHolder >{
    private FragmentActivity mContext;
    private ArrayList<itemExample> ExampleArrayList;



    public static class exampleViewHolder extends RecyclerView.ViewHolder{
        public TextView subjectName,Attendance,Status,Percentage;
        public TextView optionDigit;

        //Initializing each object
        public exampleViewHolder(View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.SubName);
            Attendance = itemView.findViewById(R.id.item_number);
            Status = itemView.findViewById(R.id.item_displayStatus);
            Percentage = itemView.findViewById(R.id.item_displayPercentage);
            optionDigit = itemView.findViewById(R.id.txtOptionDigit);
        }
    }


    public ExampleAdapter(ArrayList<itemExample> exampleList, FragmentActivity context){
        ExampleArrayList = exampleList;
        this.mContext = context;
    }

    @Override
    public exampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example,parent,false);
        exampleViewHolder exampleViewHolder = new exampleViewHolder(v);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(final ExampleAdapter.exampleViewHolder holder, final int position) {
        //Setting the datavalues for the card view
        itemExample currentItem = ExampleArrayList.get(position);
        holder.subjectName.setText(currentItem.getSubjectName());
        holder.Attendance.setText(currentItem.getPresent()+"/"+currentItem.getTotal());
        holder.Percentage.setText(currentItem.getPercentage()+"%");
        holder.Status.setText("You can bunk the next "+currentItem.getPresent()+" classes.");
        holder.optionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Popup menu for options in each card view
                PopupMenu popupMenu = new PopupMenu(mContext,holder.optionDigit);
                popupMenu.inflate(R.menu.optional_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.undo:
                                Toast message = Toast.makeText(mContext,"Undo done",Toast.LENGTH_SHORT);
                                View toastView = message.getView();
                                toastView.setBackgroundResource(R.drawable.toast_color);
                                TextView v =  message.getView().findViewById(android.R.id.message);
                                v.setTextColor(Color.BLACK);
                                message.show();
                                break;
                            case R.id.reset:
                                Toast message1 = Toast.makeText(mContext,"Reset done",Toast.LENGTH_SHORT);
                                View toastView1 = message1.getView();
                                toastView1.setBackgroundResource(R.drawable.toast_color);
                                TextView v1 =  message1.getView().findViewById(android.R.id.message);
                                v1.setTextColor(Color.BLACK);
                                message1.show();
                                break;
                            case R.id.edit:
                                Toast message2 = Toast.makeText(mContext,"Edit done",Toast.LENGTH_SHORT);
                                View toastView2 = message2.getView();
                                toastView2.setBackgroundResource(R.drawable.toast_color);
                                TextView v2 = message2.getView().findViewById(android.R.id.message);
                                v2.setTextColor(Color.BLACK);
                                message2.show();
                                break;
                            case R.id.delete:
                                //Why does it crash if i call remove function in Attendance Fragment here. (NullExceptionPointer)
                                ExampleArrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,ExampleArrayList.size());
                                Toast message3 = Toast.makeText(mContext,"Deleted",Toast.LENGTH_SHORT);
                                View toastView3 = message3.getView();
                                toastView3.setBackgroundResource(R.drawable.toast_color);
                                TextView v3 =  message3.getView().findViewById(android.R.id.message);
                                v3.setTextColor(Color.BLACK);
                                message3.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return ExampleArrayList.size();
    }
}

