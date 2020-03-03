package com.example.alustudentrecord_db;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageFragment extends Fragment {

    TextView messTxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_message, container, false);
        messTxt=view.findViewById(R.id.txt_submit_success);
        String text = this.getArguments().getString("message");
        updateText(text);
        return view;

    }


    public void updateText(String text){
        Log.d("Message4",text);
        messTxt.setText(text);

    }
}

