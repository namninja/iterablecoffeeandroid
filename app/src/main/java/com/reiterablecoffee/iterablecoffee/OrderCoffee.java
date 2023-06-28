package com.reiterablecoffee.iterablecoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iterable.iterableapi.CommerceItem;
import com.iterable.iterableapi.IterableApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderCoffee extends AppCompatActivity {
    TextView menuText;
    String token = IterableApi.getInstance().getAuthToken();
    String email = "nam.ngo+droid4@gmail.com";

    // Create an array of CommerceItem objects
    CommerceItem item1 = new CommerceItem(
            "IC001",
            "Coffee",
            5.00,
            1
//                "COF01",
//                "Plain Coffee",
//                "https://reiterablecoffee.com/publicmenu?drink=coffee",
//                "https://reiterablecoffee.com/publicmenu?drink=coffee",
//                new String[] {"hot", "coffee", "plain" }
    );

    CommerceItem item2 = new CommerceItem(
            "IC002",
            "Coffee",
            8.50,
            1
//                "CAP01",
//                "Cappuccino",
//                "https://reiterablecoffee.com/publicmenu?drink=cappucino",
//                "https://reiterablecoffee.com/publicmenu?drink=cappucino",
//                new String[] {"hot","espresso","foam","milk" }
    );

    CommerceItem item3 = new CommerceItem(
            "IC003",
            "Latte",
            8.50,
            1
//                "LAT01",
//                "Cafe Latte",
//                "https://reiterablecoffee.com/publicmenu?drink=latte",
//                "https://reiterablecoffee.com/publicmenu?drink=latte",
//                new String[] {"hot","espresso","milk" }
    );

    CommerceItem item4 = new CommerceItem(
            "IC004",
            "Mocha",
            9.00,
            1
//                "MOC01",
//                "Mocha Latte",
//                "https://reiterablecoffee.com/publicmenu?drink=mocha",
//                "https://reiterablecoffee.com/publicmenu?drink=mocha",
//                new String[] {"hot","coffee","milk","mocha" }
    );

    double total = item1.price + item2.price + item3.price + item4.price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);
        //Intent intent = getIntent();
        menuText = findViewById(R.id.textMenu);
        IterableApi.getInstance().setEmail(email, token);
        Button makePurchase = findViewById(R.id.buttonPurchase);
        Button addToCart = findViewById(R.id.buttonAddCart);
        //IterableApi.getInstance().setEmail(email);

    }

    public void addToCart(View view) {
        menuText.setText(String.valueOf("BOOM"));
        Log.d("Order", "Add to Cart");
        List<CommerceItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        IterableApi.getInstance().updateCart(items);
    }

    public void completePurchase(View view) {
        Log.d("Order", "Purchase");
        JSONObject store_address = new JSONObject();
        final JSONObject dataFields = new JSONObject();

        try {
            store_address.put("Street1", "123 Main St");
            store_address.put("Street2", "Apt 1");
            store_address.put("City", "San Diego");
            store_address.put("State", "CA");
            store_address.put("Zip", "90210");
            dataFields.put("dataFields", store_address);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        List<CommerceItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

// Make the call to Iterable's API
        IterableApi.getInstance().trackPurchase(total, items, dataFields);
    }
}

