package com.courier.trackmonitor.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.courier.trackmonitor.App;
import com.courier.trackmonitor.api.model.Event;
import com.courier.trackmonitor.utils.Logger;
import com.courier.trackmonitor.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by basselchaitani on 2/17/19.
 */
public class ApiManager {

    private static ApiManager mInstance = new ApiManager();

    public static ApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new ApiManager();
        }

        return mInstance;
    }

    private ApiManager() {
    }

    public void getEventsLastUpdated(OnApiRequestFinished<List<Event>> listener) {
        Call<List<Event>> call = ApiClient.getInstance().getApiService().getEventsLastUpdated();
        requestCall(call, listener);
    }

    private <T> void requestCall(@NonNull Call<T> call, final OnApiRequestFinished<T> listener) {
        Context context = App.getContext();
        if (context != null && listener != null) {
            if (!Utils.isNetworkAvailable(context)) {
                listener.onConnectionError("no Internet Connection");
            } else {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                        try {
                            if (response.code() == 401) {
                                // TODO unauthorized call handling
                            } else if (response.isSuccessful()) {
                                T result = response.body();
                                listener.onResultReady(result);
                            } else {
                                listener.onApiError(response.code(), response.message());
                            }
                        } catch (ClassCastException e) {
                            Logger.e("requestCall", e.getMessage());
                            listener.onApiError(-1, e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        listener.onApiError(-1, t.getMessage());
                    }
                });
            }
        } else {
            throw new NullPointerException("OnApiRequestFinished must not be null");
        }
    }
}