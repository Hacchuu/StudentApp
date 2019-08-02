package com.story.mipsa.pocketify;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CGPAFragment extends Fragment {

    View myInflatedView;
    public static ArrayList<cgpaItem> cgpaArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter = new cgpaAdapter(cgpaArrayList, getActivity() );
    private RecyclerView.LayoutManager mLayoutManager;

    public CGPAFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myInflatedView = inflater.inflate(R.layout.fragment_cgpa, container,false);
        //createExampleList();
        buildRecyclerView();
        return myInflatedView;
    }

    public void getSub(String input){
       insertItem(input,cgpaArrayList.size());
    }

    public void insertItem(String inputSubject,int position){
        cgpaArrayList.add(position,new cgpaItem(inputSubject,"",""));
        mAdapter.notifyDataSetChanged();
    }

    public void buildRecyclerView(){
        mRecyclerView = myInflatedView.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }



}
