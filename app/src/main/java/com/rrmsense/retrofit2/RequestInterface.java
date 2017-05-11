package com.rrmsense.retrofit2;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("appserver/retrofit/login.php")
    Observable<User> Login(@Body User user);

    /*@FormUrlEncoded
    @POST("appserver/retrofit/login.php")
    Observable<User> Login(@Field("username") String username, @Field("password") String password);*/

}
