package com.example.alustudentrecord_db;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class EntryFragment extends Fragment {

    AddClicked mCallback;
    Button mButton;
    EditText sIdEdit; EditText sNameEdit; EditText sPswdEdit; EditText sEmailEdit;
    Context context;

    public interface AddClicked{
        void sendText(String text);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry,
                container, false);
        sNameEdit=view.findViewById(R.id.s_name);
        sIdEdit=view.findViewById(R.id.student_id);
        sEmailEdit=view.findViewById(R.id.s_email);
        sPswdEdit=view.findViewById(R.id.s_pswd);

        mButton = view.findViewById(R.id.s_add_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addRecord();
            }
        });
        context = getActivity();
        return view;


    }

    public void addRecord(){
        if(validateForm()) {
            mCallback.sendText("Record has been successfully addded");
        }
    }
    public Boolean validateForm(){

        return validateEditText(sIdEdit, "Enter id") & validateEditText(sNameEdit, "Enter Name") &
                validateEditText(sPswdEdit, "Enter Password") &validateEditText(sEmailEdit, "Enter Email");
    }

    public boolean validateEditText(EditText view, String message){
        if (view.getText().toString().isEmpty()){
            Drawable drawable=getResources().getDrawable(R.drawable.ic_error_black_24dp);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            view.setError(message,drawable );
            view.requestFocus();
            return false;
        }
        return true;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddClicked) {
            mCallback = (AddClicked) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences sh
                = context.getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

        String name = sh.getString("name", "");
        String pswd=sh.getString("password","");

        sNameEdit.setText(name);
        sPswdEdit.setText(pswd);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        SharedPreferences sharedPreferences
                = context.getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();
        myEdit.putString("name",
                sNameEdit.getText().toString());
        myEdit.putString("password",
                sPswdEdit.getText().toString());
        myEdit.commit();
    }
}