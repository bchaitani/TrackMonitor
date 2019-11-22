package com.courier.trackmonitor.api;

import com.courier.trackmonitor.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();

    private static final int TIMEOUT_IN_MIN = 30;

    private static ApiClient apiClient;
    private ApiService apiService;

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return apiClient;
    }

    public ApiClient() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.readTimeout(TIMEOUT_IN_MIN, TimeUnit.MINUTES);
        okHttpClientBuilder.connectTimeout(TIMEOUT_IN_MIN, TimeUnit.MINUTES);
        okHttpClientBuilder.writeTimeout(TIMEOUT_IN_MIN, TimeUnit.MINUTES);

        OkHttpClient httpClient = okHttpClientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}