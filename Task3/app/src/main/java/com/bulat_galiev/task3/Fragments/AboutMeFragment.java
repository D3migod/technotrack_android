package com.bulat_galiev.task3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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

/**
 * Created by BulatGaliev on 26.05.16.
 */
public class AboutMeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_me_fragment, null);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        final Requests requests = new Requests(getActivity());
        String user_id = getArguments().getString("user_id");
        requests.aboutMeRequest(user_id);
        String returnedNameText = "";
        String returnedStatusText = "";
        TextView nameText = (TextView) v.findViewById(R.id.about_me_nickname);
        TextView statusText = (TextView) v.findViewById(R.id.about_me_status);
        nameText.setText(returnedNameText);
        statusText.setText(returnedStatusText);
        return v;
    }
}
