package com.rrmsense.retrofit2;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestInterface {

    /*@POST("appserver/retrofit/login.php")
    Observable<String> Login(@Body UserT user);*/

    @FormUrlEncoded
    @POST("appserver/retrofit/login.php")
    Observable<String> Login(@Field("username") String username, @Field("password") String password);

}
