package edu.cnm.deepdive.retrofitapplication;

import java.util.List;

import edu.cnm.deepdive.retrofitapplication.models.Post;
import edu.cnm.deepdive.retrofitapplication.models.PostRequest;
import edu.cnm.deepdive.retrofitapplication.models.PostResponse;
import edu.cnm.deepdive.retrofitapplication.models.Todo;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Single;

public interface RetrofitService {

  @GET("todos/{id}")
  Single<Response<Todo>> todos(@Path("id") long id);

  @GET("posts")
  Single<Response<List<Post>>> posts();

  @POST("posts")
  Single<Response<PostResponse>> createPost(@Body PostRequest request);
}
