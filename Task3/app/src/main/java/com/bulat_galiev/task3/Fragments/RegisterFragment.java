package com.bulat_galiev.task3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bulat_galiev.task3.Activities.MainActivity;
import com.bulat_galiev.task3.Models.FragmentChanger;
import com.bulat_galiev.task3.Models.Requests;
import com.bulat_galiev.task3.R;

/**
 * Created by BulatGaliev on 13.05.16.
 */
public class RegisterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final EditText loginEditText = (EditText) v.findViewById(R.id.register_email_entry);
        final EditText passwordEditText = (EditText) v.findViewById(R.id.register_password_entry);
        final EditText nickEditText = (EditText) v.findViewById(R.id.register_nick_entry);
        final Button registerButton = (Button) v.findViewById(R.id.register_register_button);
        final Requests requests = new Requests(getActivity());
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (requests.registerRequest(loginEditText.getText().toString(), passwordEditText.getText().toString(), nickEditText.getText().toString())) {
                    if (requests.loginRequest(loginEditText.getText().toString(), passwordEditText.getText().toString())) {
                        FragmentChanger fragmentChanger = new FragmentChanger(getActivity());
                        fragmentChanger.changeFragment(new ChatListFragment());
                    }
                }
            }
        });
        return v;
    }
}
