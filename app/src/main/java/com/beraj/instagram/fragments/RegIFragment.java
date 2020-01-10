package com.beraj.instagram.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beraj.instagram.LoginApi;
import com.beraj.instagram.R;
import com.beraj.instagram.Url;
import com.beraj.instagram.activities.RegisterIIActivity;
import com.beraj.instagram.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegIFragment extends Fragment {

    Button btnNext;
    EditText phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_i, container, false);

        btnNext = view.findViewById(R.id.btnRegPhone);
        phone = view.findViewById(R.id.etRegPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithPhone(phone.getText().toString());
            }
        });

        return view;
    }

    private void registerWithPhone(String phone) {

        Intent finalRegWithPhone = new Intent(getContext(), RegisterIIActivity.class);
        finalRegWithPhone.putExtra("regWithPhone", phone);
        RegisterIIActivity.phoneOrEmail = 1;
        startActivity(finalRegWithPhone);
    }

}
