package com.courier.trackmonitor.adapters;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.courier.trackmonitor.R;
import com.courier.trackmonitor.adapters.view_holders.EventViewHolder;
import com.courier.trackmonitor.adapters.view_holders.EventsListHeaderViewHolder;
import com.courier.trackmonitor.api.model.Event;
import com.courier.trackmonitor.utils.Logger;
import com.courier.trackmonitor.utils.sectioned_adapter.SectionedRecyclerViewAdapter;

import java.util.List;

/**
 * Created by basselchaitani on 2019-11-22.
 */
public class EventsListAdapter extends SectionedRecyclerViewAdapter<EventsListHeaderViewHolder, EventViewHolder, EventsListHeaderViewHolder> {

    private Context context = null;
    private List<EventsListAdapter.SectionItem> mSectionItems;

    public EventsListAdapter(Context context, List<EventsListAdapter.SectionItem> sectionItems) {
        this.context = context;
        this.mSectionItems = sectionItems;
    }

    @Override
    protected int getItemCountForSection(int section) {
        return mSectionItems.get(section).getEvents().size();
    }

    @Override
    protected int getSectionCount() {
        return mSectionItems.size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    protected LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(context);
    }

    @Override
    protected EventsListHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_list_header, parent, false);
        return new EventsListHeaderViewHolder(view);
    }

    @Override
    protected EventsListHeaderViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_list_header, parent, false);
        return new EventsListHeaderViewHolder(view);
    }

    @Override
    protected EventViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.view_event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(EventsListHeaderViewHolder holder, int section) {
        holder.bindHeader(mSectionItems.get(section).getSectionTitle());
    }

    @Override
    protected void onBindSectionFooterViewHolder(EventsListHeaderViewHolder holder, int section) {
        holder.bindHeader(mSectionItems.get(section).getSectionTitle());
    }

    @Override
    protected void onBindItemViewHolder(EventViewHolder holder, int section, int position) {

        boolean showSeparator = true;

        if (position == mSectionItems.get(section).getEvents().size() - 1) {
            showSeparator = false;
        }

        holder.bindEvent(mSectionItems.get(section).getEvents().get(position), showSeparator);
    }

    public static class SectionItem implements Parcelable {

        private String sectionTitle;
        private List<Event> events;

        public String getSectionTitle() {
            return sectionTitle;
        }

        public void setSectionTitle(String sectionTitle) {
            this.sectionTitle = sectionTitle;
        }

        public List<Event> getEvents() {
            return events;
        }

        public void setEvents(List<Event> events) {
            this.events = events;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sectionTitle);
            dest.writeTypedList(this.events);
        }

        public SectionItem() {
        }

        protected SectionItem(Parcel in) {
            this.sectionTitle = in.readString();
            this.events = in.createTypedArrayList(Event.CREATOR);
        }

        public static final Parcelable.Creator<SectionItem> CREATOR = new Parcelable.Creator<SectionItem>() {
            @Override
            public SectionItem createFromParcel(Parcel source) {
                return new SectionItem(source);
            }

            @Override
            public SectionItem[] newArray(int size) {
                return new SectionItem[size];
            }
        };
    }
}