package com.example.bulatgaliev.task1;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by BulatGaliev on 22.03.16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {
    int itemCount;
    Activity callingActivity;

    public RecyclerViewAdapter(Activity callingActivity, int itemCount) {
        this.callingActivity = callingActivity;
        this.itemCount = itemCount;
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rowLayout;
        TextView rowText;
        ImageView rowImage;

        DataViewHolder(View itemView) {
            super(itemView);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.row_layout);
            rowText = (TextView) itemView.findViewById(R.id.row_text);
            rowImage = (ImageView) itemView.findViewById(R.id.row_image);
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
                .inflate(R.layout.list_row, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder viewHolder, int i) {
        if (i % 2 == 0) {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }
        viewHolder.rowImage.setImageResource(R.mipmap.ic_launcher);
        NumbersToTextClass numbersToTextClass = new NumbersToTextClass(callingActivity);
        viewHolder.rowText.setText(numbersToTextClass.toText(i + 1));
    }
}
