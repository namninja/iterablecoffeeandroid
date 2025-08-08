package com.reiterablecoffee.iterablecoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.iterable.iterableapi.IterableApi;
import com.iterable.iterableapi.IterableAttributionInfo;
import com.reiterablecoffee.iterablecoffee.PreferenceManager;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String email = BuildConfig.EMAIL;
    String phoneNumber = BuildConfig.PHONE;
    String userId = BuildConfig.UUID;
    String firstName = BuildConfig.FIRSTNAME;
    String lastName = BuildConfig.LASTNAME;
    private TextView greeting;
    //String token = IterableApi.getInstance().getAuthToken();
    //String token = PreferenceManager.getInstance(getApplicationContext()).getMyGlobalVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greeting = findViewById(R.id.textGreeting);



    }

    public void logInUser(View view) throws JSONException {
        Log.d("MainActivity", "User Logged In");
//        token = IterableApi.getInstance().getAuthToken();
//        Log.d("MA token", "token" + token);
//        if (token == null) {
//            IterableApi.getInstance().setEmail(email);
//        } else {
//            IterableApi.getInstance().setEmail(email, token);
//        }
        IterableApi.getInstance().setEmail(email);

        //UUID
//        JSONObject dataFields = new JSONObject();
//        dataFields.put("firstName", firstName);
//        dataFields.put("lastName", lastName);
//        dataFields.put("email", email);
//        dataFields.put("userId", userId);
//        dataFields.put("phoneNumber", phoneNumber);
//        IterableApi.getInstance().updateUser(dataFields);
//
//        IterableApi.getInstance().setUserId("knownAndroid-4444");

        //UUID End
        IterableApi.getInstance().track(
                "login successful"
        );
        
        Snackbar.make(greeting, "login successful", Snackbar.LENGTH_LONG)
                .setAction("More", v -> {
                            Toast.makeText(MainActivity.this, email, Toast.LENGTH_LONG).show();

                        }
                )
                .show();
//        setContentView(R.layout.order_page);
        Intent intentOrderForm = new Intent(this,OrderCoffee.class);
        //intentOrderForm.putExtra(Extra_Message,fullName);
        startActivity(intentOrderForm);
    }

    public void createNewUser(View view) throws JSONException {
        Log.d("MainActivity", "New User Created");
//        if (token == null) {
//            IterableApi.getInstance().setEmail(email);
//        } else {
//            IterableApi.getInstance().setEmail(email, token);
//        }
        IterableApi.getInstance().setEmail(email);
        JSONObject dataFields = new JSONObject();

        String favoriteCafeBeverage = "mocha";

        dataFields.put("firstName", firstName);
        dataFields.put("lastName", lastName);
        dataFields.put("userId", userId);
        dataFields.put("favoriteCafeBeverage", favoriteCafeBeverage);
        dataFields.put("phoneNumber", phoneNumber);

        IterableApi.getInstance().updateUser(dataFields);

        IterableApi.getInstance().track(
                "signup complete",
                dataFields
        );

        String msg = firstName + " " + lastName + " , " + favoriteCafeBeverage + " , " + phoneNumber;

        Snackbar.make(greeting, email, Snackbar.LENGTH_LONG)
                .setAction("More", v -> {
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

                        }
                )
                .show();
        //setContentView(R.layout.order_page);
        Intent intentOrderForm = new Intent(this,OrderCoffee.class);
        //intentOrderForm.putExtra(Extra_Message,fullName);
        startActivity(intentOrderForm);
    }
}