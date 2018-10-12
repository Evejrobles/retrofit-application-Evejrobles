package edu.cnm.deepdive.retrofitapplication;

import android.os.Handler;
import android.os.Looper;

public abstract class GetValueCallback<T> implements Runnable {

  private T mResult = null;
  private String mError = null;

  protected abstract void onResult(T result);

  protected abstract void onError(String error);

  @Override
  public void run() {
    if (mError != null) {
      onError(mError);
    }
    else {
      onResult(mResult);
    }
  }

  public void setResult(T result) {
    mResult = result;
    mError = null;
    new Handler(Looper.getMainLooper()).post(this);
  }

  public void setError(String error) {
    mError = error;
    mResult = null;
    new Handler(Looper.getMainLooper()).post(this);
  }
}
