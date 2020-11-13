package com.aziz;

import android.app.Application;

import com.aziz.data.network.QuizApiClient;

public class QuizApp extends Application {
    public static QuizApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
    }
}
