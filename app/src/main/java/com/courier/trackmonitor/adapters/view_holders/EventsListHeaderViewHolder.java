package com.courier.trackmonitor.adapters.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.courier.trackmonitor.R;
import com.courier.trackmonitor.api.model.Event;

/**
 * Created by basselchaitani on 2019-11-22.
 */
public class EventsListHeaderViewHolder  extends RecyclerView.ViewHolder {

    TextView tvTitle;

    public EventsListHeaderViewHolder(View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.title_text);
    }

    public void bindHeader(String title) {
        tvTitle.setText(title);
    }
}