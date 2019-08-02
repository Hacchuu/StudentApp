package com.story.mipsa.pocketify;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
        TextView optionDigit;
        public Button present,absent;
        AttendanceFragment attendanceFragment = new AttendanceFragment();
        public int presentS,presentTemp=0;
        public int absentS,total,totalS,attend=0,bunk=0, min,per;
        public float avg=0,temp;


        //Initializing each object
        public exampleViewHolder(View itemView) {
            super(itemView);
            present = itemView.findViewById(R.id.item_present);
            absent = itemView.findViewById(R.id.item_absent);
            subjectName = itemView.findViewById(R.id.SubName);
            Attendance = itemView.findViewById(R.id.item_number);
            Status = itemView.findViewById(R.id.item_displayStatus);
            Percentage = itemView.findViewById(R.id.item_displayPercentage);
            optionDigit = itemView.findViewById(R.id.txtOptionDigit);
            String target = attendanceFragment.target;
            min = Integer.parseInt(target);
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
        final itemExample currentItem = ExampleArrayList.get(position);

        //Setting the datavalues for the card view
        holder.subjectName.setText(currentItem.getSubjectName());
        holder.Attendance.setText(currentItem.getPresent()+"/"+currentItem.getTotal());
        holder.Percentage.setText(String.format("%.1f%%",currentItem.getPercentage()));
        holder.Status.setText("Situation");
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

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Present( currentItem, holder);
            }
        });

        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Absent( currentItem, holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ExampleArrayList.size();
    }

    public void Absent(itemExample currentItem, ExampleAdapter.exampleViewHolder holder){
        holder.absentS = currentItem.getAbsent();
        holder.total = currentItem.getTotal();
        holder.absentS++;
        holder.total++;
        float avg = ((float)holder.presentS/(float)holder.total)*100;
        currentItem.setPercentage(avg);
        currentItem.setTotal(holder.total);
        currentItem.setAbsent(holder.absentS);
        holder.Attendance.setText(currentItem.getPresent()+"/"+currentItem.getTotal());
        holder.Percentage.setText(String.format("%.1f%%",currentItem.getPercentage()));
        Calculate(currentItem,holder);
        if(holder.attend>0){
            if(holder.attend>1)
                holder.Status.setText("You can't bunk the next "+holder.attend+" classes ⚆_⚆");
            else
                holder.Status.setText("You can't bunk the next class ◉_◉");
        }
        else if(holder.bunk>0){
            if(holder.bunk>1)
                holder.Status.setText("You can bunk "+holder.bunk+" classes ♥‿♥");
            else
                holder.Status.setText("You can bunk your next class (ᵔᴥᵔ)");
        }

    }

    public void Present(itemExample currentItem, ExampleAdapter.exampleViewHolder holder){
        holder.presentS = currentItem.getPresent();
        holder.total = currentItem.getTotal();
        holder.presentS++;
        holder.total++;
        float avg = ((float)holder.presentS/(float)holder.total)*100;
        currentItem.setPercentage(avg);
        currentItem.setTotal(holder.total);
        currentItem.setPresent(holder.presentS);
        holder.Attendance.setText(currentItem.getPresent()+"/"+currentItem.getTotal());
        holder.Percentage.setText(String.format("%.1f%%",currentItem.getPercentage()));
        Calculate(currentItem,holder);
        if(holder.attend>0){
            if(holder.attend>1)
                holder.Status.setText("You can't bunk the next "+holder.attend+" classes ⚆_⚆");
            else
                holder.Status.setText("You can't bunk the next class ◉_◉");
        }
        else if(holder.bunk>0){
            if(holder.bunk>1)
                holder.Status.setText("You can bunk "+holder.bunk+" classes ♥‿♥");
            else
                holder.Status.setText("You can bunk 1 class (ᵔᴥᵔ)");
            }
        }

    public void Calculate(itemExample currentItem, ExampleAdapter.exampleViewHolder holder){
        holder.absentS = currentItem.getAbsent();
        holder.presentS = currentItem.getPresent();
        holder.total = currentItem.getTotal();
        holder.totalS = holder.total;
        holder.attend = 0;
        holder.bunk = 0;
        if(holder.totalS != 0){
            holder.avg = ((float)holder.presentS/(float)holder.totalS)*100;
        }
        holder.temp = holder.avg;
        if(holder.temp >= holder.min) {
            do {
                holder.totalS += 1;
                holder.temp = ((float) holder.presentS / (float) holder.totalS) * 100;
                if (holder.temp < holder.min && holder.bunk == 0) {
                    holder.attend++;
                } else if (holder.temp >= holder.min && holder.attend == 0)
                    holder.bunk++;
            } while (holder.temp > holder.min);
        }
        else{
            holder.presentTemp = holder.presentS;
            do {
                holder.totalS += 1;
                holder.presentTemp+=1;
                holder.temp = ((float) holder.presentTemp / (float) holder.totalS) * 100;
                if (holder.temp <= holder.min && holder.bunk == 0) {
                    holder.attend++;
                } else if (holder.temp > holder.min && holder.attend == 0)
                    holder.bunk++;
            } while (holder.temp <= holder.min);
        }
        currentItem.setAttend(holder.attend);
        currentItem.setBunk(holder.bunk);
    }

}

