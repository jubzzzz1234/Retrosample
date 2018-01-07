package com.example.jubin.retrosample;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.BridgeInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";
    private static final String TAG="MainActivity";

    private RecyclerView mRecyclerView;
    private UserAdapter adapter;
    List<User.ItemsBean> Users;
    private RecyclerView.LayoutManager mLayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerview reference
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //okhttp reference
        OkHttpClient okClient=new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request=chain.request().newBuilder()
                                .addHeader("Accept","Application/JSON").build();
                        return chain.proceed(request);
                    }
                }).build();
        //retrofit reference
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //interface endpoint
        MyAPIendpointInterface service=retrofit.create(MyAPIendpointInterface.class);
        //call back
        Call<User> call=service.getUserNameTom("tom");//any value can be passed here we pass tom
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Log.d(TAG,"onResponse : "+response.code());
                if(response.isSuccessful())
                {
                    Users = new ArrayList<User.ItemsBean>();
                    User result=response.body();
                    Users=result.getItems();
                    adapter=new UserAdapter(Users);
                    //attach adapter to recycler view
                    mLayoutmanager =new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(mLayoutmanager);
                    mRecyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
