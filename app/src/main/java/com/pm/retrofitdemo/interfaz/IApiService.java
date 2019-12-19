package com.pm.retrofitdemo.interfaz;

import com.pm.retrofitdemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


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
