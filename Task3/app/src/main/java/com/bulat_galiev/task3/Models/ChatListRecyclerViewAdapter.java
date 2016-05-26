package com.bulat_galiev.task3.Models;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bulat_galiev.task3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BulatGaliev on 26.05.16.
 */
public class ChatListRecyclerViewAdapter extends RecyclerView.Adapter<ChatListRecyclerViewAdapter.DataViewHolder> {
    private static final String ROW_DATA = "data";
    private static final String ROW_CHANNELS = "channels";
    JSONArray jsonObject;
    Activity callingActivity;
    int itemCount;

    public ChatListRecyclerViewAdapter(Activity callingActivity, JSONObject jsonObject) {
        this.callingActivity = callingActivity;
        this.jsonObject = new JSONArray();
        try {
            this.jsonObject = jsonObject.getJSONObject(ROW_DATA).getJSONArray(ROW_CHANNELS);
        } catch (JSONException e) {
            Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
        }
        itemCount = this.jsonObject.length();
    }

    public ChatListRecyclerViewAdapter(JSONObject jsonObject) {
        this.jsonObject = new JSONArray();
        try {
            this.jsonObject = jsonObject.getJSONObject(ROW_DATA).getJSONArray(ROW_CHANNELS);
        } catch (JSONException e) {
            Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
        }
        itemCount = this.jsonObject.length();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public interface OnItemClickListener {
            void onItemClick(View view);
        }

        private OnItemClickListener listener;
        RelativeLayout rowLayout;
        TextView rowName;
        TextView rowAbout;
        ImageView rowImage;

        DataViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.row_layout);
            rowName = (TextView) itemView.findViewById(R.id.row_name);
            rowAbout = (TextView) itemView.findViewById(R.id.row_about);
            rowImage = (ImageView) itemView.findViewById(R.id.row_image);
            rowImage.setOnClickListener(this);
            rowName.setOnClickListener(this);
            rowAbout.setOnClickListener(this);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        DataViewHolder(View itemView) {
            super(itemView);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.row_layout);
            rowName = (TextView) itemView.findViewById(R.id.row_name);
            rowAbout = (TextView) itemView.findViewById(R.id.row_about);
            rowImage = (ImageView) itemView.findViewById(R.id.row_image);
            rowImage.setOnClickListener(this);
            rowName.setOnClickListener(this);
            rowAbout.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);
        final DataViewHolder dataViewHolder = new DataViewHolder(view);
        dataViewHolder.setOnItemClickListener(new ChatListRecyclerViewAdapter.DataViewHolder.OnItemClickListener() {
            public void onItemClick(View view) {
                /*Intent intent = new Intent(callingActivity.getApplicationContext(), SliderFragmentActivity.class);
                intent.putExtra("JSON_OBJECT", jsonObject.toString());
                intent.putExtra("CURRENT_ITEM", dataViewHolder.getAdapterPosition());
                callingActivity.startActivity(intent);
                callingActivity.finish();*/
                //implement onclick here
            }
        });
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder viewHolder, int position) {
        if (position % 2 == 0) {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }

        try {
            JSONObject jsonObjectElement = jsonObject.getJSONObject(position);
            //new ImageLoadingTask(viewHolder.rowImage, callingActivity).execute(IMAGE_URL_BEGIN + jsonObjectElement.getString("picture"));
            String title =jsonObjectElement.getString("name") + " (" + jsonObjectElement.getString("online") + ")";
            viewHolder.rowName.setText(title);
            viewHolder.rowAbout.setText(jsonObjectElement.getString("descr"));
            // Set text and image here
        } catch (JSONException e) {
            Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
        }
    }
}
