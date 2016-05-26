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

import com.bulat_galiev.task3.Models.FragmentChanger;
import com.bulat_galiev.task3.Models.Requests;
import com.bulat_galiev.task3.R;

/**
 * Created by BulatGaliev on 26.05.16.
 */
public class ChangeAboutMeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.change_about_me_fragment, null);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        final EditText nicknameEditText = (EditText) v.findViewById(R.id.change_about_me_nickname);
        final EditText statusEditText = (EditText) v.findViewById(R.id.change_about_me_status);
        final Button commitButton = (Button) v.findViewById(R.id.change_about_me_confirm_button);
        final Requests requests = new Requests(getActivity());
        commitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (requests.changeAboutMeRequest(statusEditText.getText().toString())) {
                    FragmentChanger fragmentChanger= new FragmentChanger(getActivity());
                    ChatListFragment chatListFragment = new ChatListFragment();
                    Bundle args = new Bundle();
                    args.putString("user_id", requests.getCid());
                    chatListFragment.setArguments(args);
                    fragmentChanger.changeFragment(chatListFragment);
                }
            }
        });
        return v;
    }
}

