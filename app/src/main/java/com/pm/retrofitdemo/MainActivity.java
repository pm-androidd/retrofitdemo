package com.pm.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pm.retrofitdemo.adapter.IApiService;
import com.pm.retrofitdemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    Retrofit retrofit = null;

    IApiService service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.jsonText);

        // instantiate the Retrofit Library
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Initialize the Interface Service
        service = retrofit.create(IApiService.class);
        GetUsers();
    }


    /***
     * Get the users from jsonplaceholder endpoint
     * https://jsonplaceholder.typicode.com/users
     */
    private void GetUsers(){

         Call<List<User>> usersList = service.usersList();
         usersList.enqueue(new Callback<List<User>>() {
             @Override
             public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                 if(!response.isSuccessful()){
                     textView.setText("Codigo: " + response.code());
                     return;
                 }

                 String content = "";
                 for(User user : response.body()){
                     content = "";
                     content += "Id: " + user.getId() + "\n";
                     content += "Name: " +  user.getName() + "\n";
                     content += "City: " +  user.getAddress().getCity() + "\n\n";
                     textView.append(content);
                 }
             }

             @Override
             public void onFailure(Call<List<User>> call, Throwable t) {
                 textView.setText(t.getMessage());
             }
         });
    }

}
