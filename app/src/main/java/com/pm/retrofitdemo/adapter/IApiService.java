package com.pm.retrofitdemo.adapter;

import com.pm.retrofitdemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/***
 * Api Interface for endpoint jsonplaceholder
 */
public interface IApiService {

    /***
     * Get Users Data
     * @return Users list
     */
    @GET("users")
    Call<List<User>> usersList();
}
