package edu.cnm.deepdive.retrofitapplication.models;

//{
//        "userId": 1,
//        "id": 2,
//        "title": "quis ut nam facilis et officia qui",
//        "completed": false
//}

import com.google.gson.annotations.SerializedName;

public class Todo {
  @SerializedName("userId")
  public long uId = 0;

  public long id = 0;
  public String title = "";
  public boolean completed = false;
}
