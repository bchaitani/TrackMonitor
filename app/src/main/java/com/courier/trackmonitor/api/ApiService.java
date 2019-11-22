package com.courier.trackmonitor.api;

import com.courier.trackmonitor.api.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Urls.GET_EVENTS_LAST_UPDATED)
    Call<List<Event>> getEventsLastUpdated();
}
