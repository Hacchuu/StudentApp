package com.story.mipsa.pocketify;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class AttendanceTarget extends AppCompatActivity{

    public static String minimumAttendance;
    SeekBar seekBar;
    Button button;
    TextView textView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Minimum Attendance",minimumAttendance);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_target);

        if(savedInstanceState!=null) {
            minimumAttendance = savedInstanceState.getString("Minimum Attendance");
        }

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        button = (Button)findViewById(R.id.save);
        textView = (TextView)findViewById(R.id.number);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int x;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText(i+"%");
                x = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                minimumAttendance = Integer.toString(x);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast message = Toast.makeText(getApplicationContext(),"Minimum Attendance set to "+minimumAttendance+"%",Toast.LENGTH_SHORT);
                View toastView = message.getView();
                toastView.setBackgroundResource(R.drawable.toast_color);
                TextView v = (TextView) message.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.BLACK);
                message.show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });



    }
}
