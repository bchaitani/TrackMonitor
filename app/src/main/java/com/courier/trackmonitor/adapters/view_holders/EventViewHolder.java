package com.courier.trackmonitor.adapters.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.courier.trackmonitor.R;
import com.courier.trackmonitor.api.model.Event;
import com.courier.trackmonitor.utils.Logger;

/**
 * Created by basselchaitani on 2019-11-22.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle, tvUpdateDate, tvMinutes;
    private View vSeparator;

    public EventViewHolder(View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.title_text);
        tvUpdateDate = itemView.findViewById(R.id.date_text);
        tvMinutes = itemView.findViewById(R.id.minutes_text);

        vSeparator = itemView.findViewById(R.id.separator);
    }

    public void bindEvent(Event event, boolean showSeparator) {
        tvTitle.setText(event.getCompanyName());
        tvUpdateDate.setText(event.getLastUpdate());

        int lastUpdatedInMinutes = event.getLastUpdatedInMinutes();

        tvMinutes.setText(String.valueOf(lastUpdatedInMinutes));

        if (lastUpdatedInMinutes > 10) {
            tvMinutes.setBackgroundResource(R.drawable.shape_rounded_corners_red);
        } else {
            tvMinutes.setBackgroundResource(R.drawable.shape_rounded_corners_green);
        }

        Logger.e("ShowSeparator - " + event.getGroup(), "" + showSeparator);
        if (showSeparator) {
            vSeparator.setVisibility(View.VISIBLE);
        } else {
            vSeparator.setVisibility(View.GONE);
        }
    }
}
