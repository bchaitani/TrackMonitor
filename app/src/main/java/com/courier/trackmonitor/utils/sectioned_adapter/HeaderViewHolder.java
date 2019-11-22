package com.courier.trackmonitor.utils.sectioned_adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by basselchaitani on 2019-11-22.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    protected TextView titleText = null;

    public HeaderViewHolder(View itemView, @IdRes int titleID) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(titleID);
    }

    public void render(String title) {
        titleText.setText(title);
    }

}
