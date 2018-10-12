package edu.cnm.deepdive.retrofitapplication.models;

import com.google.gson.annotations.SerializedName;

public class Post {
  @SerializedName("userId")
  public long uId = 0;

  public long id = 0;
  public String title = "";
  public String body = "";
}
