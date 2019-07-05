package com.story.mipsa.pocketify;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;


import java.lang.reflect.Field;
import java.util.ArrayList;

//import com.marcinmoskala.arcseekbar.ArcSeekBar;
//import com.marcinmoskala.arcseekbar.ProgressListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment implements subjectDialog.onInput{

    FirstPage firstPage;
    AttendanceTarget attendanceTarget;
    //CGPAFragment cgpaFragment;
    public String name;
    public static String target;
    View myInflatedView;
    public TextView textView,textView2;
    private Button insertButton;
    public  static ArrayList<itemExample> exampleArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter = new ExampleAdapter(exampleArrayList,getActivity());
    private RecyclerView.LayoutManager mLayoutManager;

    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_attendance, container,false);


        name = firstPage.name;
        target = attendanceTarget.minimumAttendance;
        textView = myInflatedView.findViewById(R.id.nameFill);
        textView.setText(name);
        textView2 = myInflatedView.findViewById(R.id.TargetFill);
        textView2.setText(target+"%");
        insertButton = myInflatedView.findViewById(R.id.addSubject);
        //createExampleList();
        buildRecyclerView();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Insert new Subjects
                openDialog();
            }
        });


        return myInflatedView;
    }

    public void openDialog(){
        subjectDialog subjectDialog = new subjectDialog();
        subjectDialog.setTargetFragment(AttendanceFragment.this,1);
        subjectDialog.setCancelable(false);
        subjectDialog.show(getFragmentManager(),"SubjectDialog");
    }

    public void insertItem(String inputSubject,int position){
        exampleArrayList.add(position,new itemExample(inputSubject,0,0,0,0,0,0));
        //mAdapter.notifyItemInserted(exampleArrayList.size()-1);
        mAdapter.notifyDataSetChanged();
    }

//    public void createExampleList(){
//        exampleArrayList = new ArrayList<>();
//    }

    public void buildRecyclerView(){
        mRecyclerView = myInflatedView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(exampleArrayList,getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void sendInput(String input) {
        CGPAFragment.getSub(input);
        if(exampleArrayList.size()==0){
            insertItem(input,0);
        }
        else {
            insertItem(input, exampleArrayList.size());

        }
    }
}
