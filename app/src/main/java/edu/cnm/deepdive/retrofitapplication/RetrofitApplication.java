package edu.cnm.deepdive.retrofitapplication;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApplication extends Application {

  private static RetrofitApplication instance;
  private Retrofit retrofit;
  private RetrofitService service;

  @Override
  public void onCreate() {
    super.onCreate();
    // Setup singleton instance
    instance = this;
    // Setup GSON
    Gson gson = new GsonBuilder()
        // .excludeFieldsWithoutExposeAnnotation() // Uncomment this to require @Expose
        .create();
    // Setup Retrofit
    retrofit = new Retrofit.Builder()
        .baseUrl(getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    service = retrofit.create(RetrofitService.class);
  }

  public static RetrofitApplication getInstance() {
    return instance;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

  public RetrofitService getService() { return service; }
}
