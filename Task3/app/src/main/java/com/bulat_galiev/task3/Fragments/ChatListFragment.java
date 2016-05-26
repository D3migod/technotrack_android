package com.bulat_galiev.task3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bulat_galiev.task3.Models.ChatListRecyclerViewAdapter;
import com.bulat_galiev.task3.Models.FragmentChanger;
import com.bulat_galiev.task3.Models.Requests;
import com.bulat_galiev.task3.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BulatGaliev on 26.05.16.
 */
public class ChatListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.hide();
        actionBar.setDisplayUseLogoEnabled(false);
        /*JSONObject jsonObject = new JSONObject();
        super.onCreate(savedInstanceState);
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("JSON_OBJECT"));

        } catch (JSONException e) {
            Log.e("Exception", "SecondActivity: " + e.toString());
        }*/
        View v = inflater.inflate(R.layout.chat_list_fragment, null);
        //setContentView(R.layout.chat_list_fragment);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final Requests requests = new Requests(getActivity());
        JSONObject jsonObject = requests.chatListRequest();
        ChatListRecyclerViewAdapter adapter = new ChatListRecyclerViewAdapter(jsonObject);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
