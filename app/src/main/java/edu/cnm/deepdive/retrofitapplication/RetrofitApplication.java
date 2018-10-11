package edu.cnm.deepdive.retrofitapplication;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApplication extends Application {

  private static final String BASE_URL = "";//TODO Fill in URL
  private static RetrofitApplication instance;
  private Retrofit retrofit;

  @Override
  public void onCreate() {
    super.onCreate();
    // Setup singleton instance
    instance = this;
    // Setup Retrofit
    retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static RetrofitApplication getInstance() {
    return instance;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }
}
