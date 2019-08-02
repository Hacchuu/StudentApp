package com.story.mipsa.pocketify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences sharedPreferences;
    public static String minimumAttendance;
    public static final String savedTarget = "targetKey";
    public static final String mypreference = "mypref";
    SeekBar seekBar;
    Button button;
    TextView textView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Minimum Attendance",minimumAttendance);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        minimumAttendance = (String) savedInstanceState.getSerializable( "Minimum Attendance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_target);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

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

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(savedTarget, minimumAttendance);
                editor.commit();

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

        if(sharedPreferences.contains(savedTarget)){
            minimumAttendance = sharedPreferences.getString(savedTarget,"");
            textView.setText(minimumAttendance+"%");
//            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//            startActivity(intent);
        }

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();
    }
}
}
