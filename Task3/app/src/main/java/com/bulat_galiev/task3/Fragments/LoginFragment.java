package com.bulat_galiev.task3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bulat_galiev.task3.Models.FragmentChanger;
import com.bulat_galiev.task3.Models.Requests;
import com.bulat_galiev.task3.R;

import org.json.JSONObject;

/**
 * Created by BulatGaliev on 12.05.16.
 */
public class LoginFragment extends Fragment {

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final EditText loginEditText = (EditText) v.findViewById(R.id.login_email_entry);
        final EditText passwordEditText = (EditText) v.findViewById(R.id.login_password_entry);
        final Button loginButton = (Button) v.findViewById(R.id.login_login_button);
        final Requests requests = new Requests(getActivity());
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (requests.loginRequest(loginEditText.getText().toString(), passwordEditText.getText().toString())) {
                    FragmentChanger fragmentChanger= new FragmentChanger(getActivity());
                    fragmentChanger.changeFragment(new ChatListFragment());
                }
            }
        });
        final Button registerButton = (Button) v.findViewById(R.id.login_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentChanger fragmentChanger= new FragmentChanger(getActivity());
                fragmentChanger.changeFragment(new RegisterFragment());
            }
        });
        return v;
    }
}
