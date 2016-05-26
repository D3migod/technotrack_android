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
public class AddChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_chat_fragment, null);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        final EditText nameEditText = (EditText) v.findViewById(R.id.add_chat_name_entry);
        final EditText aboutEditText = (EditText) v.findViewById(R.id.add_chat_about_entry);
        final Button commitButton = (Button) v.findViewById(R.id.add_chat_commit_button);
        final Requests requests = new Requests(getActivity());
        commitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (requests.addChatRequest(nameEditText.getText().toString(), aboutEditText.getText().toString())) {
                    FragmentChanger fragmentChanger= new FragmentChanger(getActivity());
                    fragmentChanger.changeFragment(new ChatListFragment());
                }
            }
        });
        return v;
    }
}
