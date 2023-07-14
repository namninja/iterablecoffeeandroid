package com.reiterablecoffee.iterablecoffee;

import android.util.Log;

import com.iterable.iterableapi.IterableApi;
import com.iterable.iterableapi.IterableAuthHandler;
import com.iterable.iterableapi.IterableConfig;
import com.iterable.iterableapi.IterableHelper;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Application extends android.app.Application {

    String email = "testing.iterableapps@gmail.com";
    String myToken = "";
    String token = "";
    public void onCreate(){
        super.onCreate();
        //Initialize API


        IterableConfig.Builder configBuilder = new IterableConfig.Builder()
                .setLogLevel(Log.VERBOSE)
                .setExpiringAuthTokenRefreshPeriod((long) 10)
                .setAuthHandler(new IterableAuthHandler() {
                    @Override
                    public String onAuthTokenRequested() {

                        // Insert your implementation here. Return a token.
                        OkHttpClient client = new OkHttpClient().newBuilder().build();
                        MediaType mediaType = MediaType.parse("application/json");
                        RequestBody body = RequestBody.Companion.create("{\"email\":\"" + email + "\"}", mediaType);
                        Request request = new Request.Builder()
                                .url("https://iterabledatafeed.herokuapp.com/mobilejwt")
                                .method("POST", body)
                                .addHeader("Content-Type", "application/json")
                                .build();

                            try (Response response = client.newCall(request).execute()) {
                                assert response.body() != null;
                                token = response.body().string();
                                token = token.replaceAll("^\"|\"$", "");
                                Log.d("AuthHandler token call", token);
                                myToken = token;
                                //PreferenceManager.getInstance(getApplicationContext()).setMyGlobalVariable(myToken);

                                return token;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                    }

                    @Override
                    public void onTokenRegistrationSuccessful(String authToken) {
                        Log.d("Auth token success", authToken);

                    }

                    @Override
                    public void onTokenRegistrationFailed(Throwable object) {
                        Log.d("Auth token failure", myToken);
                        if (!myToken.equals("")) {
                            IterableApi.getInstance().setEmail(email,token);
                        }
                    }
                }
                );

        IterableConfig config = configBuilder.build();
        IterableApi.initialize(getApplicationContext(),"<apiKey>",config);

    }


}




