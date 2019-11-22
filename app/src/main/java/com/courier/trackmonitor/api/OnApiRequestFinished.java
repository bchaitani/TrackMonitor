package com.courier.trackmonitor.api;

public interface OnApiRequestFinished<T> {

    void onResultReady(T result);

    void onConnectionError(String error);

    void onApiError(int statusCode, String errorMessage);
}
