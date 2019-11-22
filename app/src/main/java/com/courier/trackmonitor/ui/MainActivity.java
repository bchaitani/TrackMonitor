package com.courier.trackmonitor.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.courier.trackmonitor.R;
import com.courier.trackmonitor.adapters.EventsListAdapter;
import com.courier.trackmonitor.api.ApiManager;
import com.courier.trackmonitor.api.OnApiRequestFinished;
import com.courier.trackmonitor.api.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements OnApiRequestFinished<List<Event>> {

    private Map<String, List<Event>> mEventsMap;

    private RecyclerView mRecyclerView;
    private ProgressBar mProressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProressBar = findViewById(R.id.progress_bar);

        getEventsLastUpdated();
    }

    private void getEventsLastUpdated() {
        mEventsMap = new HashMap<>();
        mProressBar.setVisibility(View.VISIBLE);

        ApiManager.getInstance().getEventsLastUpdated(this);
    }

    @Override
    public void onResultReady(List<Event> result) {
        for (int i = 0; i < result.size(); i++) {

            Event event = result.get(i);

            if (mEventsMap.containsKey(event.getGroup())) {
                List<Event> events = mEventsMap.get(event.getGroup());
                if (events != null) {
                    events.add(event);
                } else {
                    events = new ArrayList<>();
                    events.add(event);
                }

                mEventsMap.put(event.getGroup(), events);
            } else {
                List<Event> events = new ArrayList<>();
                events.add(event);

                mEventsMap.put(event.getGroup(), events);
            }
        }

        List<EventsListAdapter.SectionItem> sectionItems = new ArrayList<>();

        // To sort the events map key alphabetically
        Map<String, List<Event>> treeMap = new TreeMap<>(mEventsMap);

        for (Map.Entry<String, List<Event>> entry : treeMap.entrySet()) {
            EventsListAdapter.SectionItem item = new EventsListAdapter.SectionItem();
            item.setSectionTitle(entry.getKey());
            item.setEvents(entry.getValue());

            sectionItems.add(item);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new EventsListAdapter(this, sectionItems));
        mProressBar.setVisibility(View.GONE);
    }

    @Override
    public void onConnectionError(String error) {

    }

    @Override
    public void onApiError(int statusCode, String errorMessage) {

    }
}
