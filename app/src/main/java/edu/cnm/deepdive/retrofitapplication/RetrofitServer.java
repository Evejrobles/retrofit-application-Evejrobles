package edu.cnm.deepdive.retrofitapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.util.List;

import edu.cnm.deepdive.retrofitapplication.models.Post;
import edu.cnm.deepdive.retrofitapplication.models.PostRequest;
import edu.cnm.deepdive.retrofitapplication.models.PostResponse;
import edu.cnm.deepdive.retrofitapplication.models.Todo;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RetrofitServer {
  public static List<Post> posts = null;

  public static void todo(final Context context, long id, final GetValueCallback<Todo> callback) {
    RetrofitService service = RetrofitApplication.getInstance().getService();
    service.todos(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Response<Todo>>() {
          @Override
          public void call(Response<Todo> todoResponse) {
            if (todoResponse.isSuccessful()) {
              Todo todo = todoResponse.body();
              if (callback != null) {
                callback.setResult(todo);
              }
            }
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        });
  }

  public static void posts() {
    RetrofitService service = RetrofitApplication.getInstance().getService();
    service.posts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Response<List<Post>>>() {
          @Override
          public void call(Response<List<Post>> listResponse) {
            if (listResponse.isSuccessful()) {
              posts = listResponse.body();
            }
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        });
  }

  public static void createPost(final Context context, String title, String body, long userId) {
    RetrofitService service = RetrofitApplication.getInstance().getService();
    PostRequest request = new PostRequest();
    request.title = title;
    request.body = body;
    request.userId = userId;
    service.createPost(request)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Response<PostResponse>>() {
          @Override
          public void call(Response<PostResponse> postResponseResponse) {
            if (postResponseResponse.isSuccessful()) {
              Intent intent = new Intent();
              intent.setAction("POST_SENT");
              LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {
            throwable.printStackTrace();
          }
        });
  }
}
