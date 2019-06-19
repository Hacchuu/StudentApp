package com.story.mipsa.pocketify;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mipsa on 6/11/2019.
 */

public class subjectDialog extends AppCompatDialogFragment{

    public interface onInput{
        void sendInput(String input);
    }
    public onInput onInput;



    private EditText editText;
    private Button cancel,Add;
    public String SubjectName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.subject_dialog, container,false);
        editText = view.findViewById(R.id.enterSubject);
        cancel = view.findViewById(R.id.cancel);
        Add = view.findViewById(R.id.add);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = editText.getText().toString().trim();
                if(!subject.equals("")){
                    onInput.sendInput(subject);
                }
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    try {
            onInput = (onInput)getTargetFragment();
    }catch (ClassCastException e){

    }
    }
}
