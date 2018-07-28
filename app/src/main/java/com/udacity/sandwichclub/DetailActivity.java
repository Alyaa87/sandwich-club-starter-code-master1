package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //make a new textViews for the data to be added to the UI.
    private TextView origin_tv, description_tv, ingredients_tv, also_known_tv, place_of_origin_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        //create a new  for loop to get items in position inside the sandwich object.
        for (int i = 1; i == sandwiches.length; i++) {
            json += "";
            json += sandwiches[i];
        }
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //close on error.
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //make a new object of arrayList to save the value which come from Sandwich class
        ArrayList<String> ingredientsArray = (ArrayList<String>) sandwich.getIngredients();
        //make  new Object of StringBuilder to save all array inside
        StringBuilder ingredientsString = new StringBuilder();
        if (ingredientsArray != null) {
            for (String items : ingredientsArray) {
                ingredientsString.append(items);
                ingredientsString.append("\n");
                // show the value of  StringBuilder ingredientsString
                ingredients_tv.setText(ingredientsString);

            }
        }

        //make a new object of arrayList to save the value which come from Sandwich class
        ArrayList<String> alsoKnownAs = (ArrayList<String>) sandwich.getAlsoKnownAs();
        //make  new Object of StringBuilder to save all array inside
        StringBuilder alsoKnString = new StringBuilder();
        if (alsoKnownAs != null) {
            for (String items : alsoKnownAs) {
                alsoKnString.append(items);
                alsoKnString.append("\n");
                // show the value of  StringBuilder alsoKnString  inside TextView alsoKnownAs .
                also_known_tv.setText(alsoKnString);
            }
            //show the value of  String  getMainName()  inside TextView originTv
            origin_tv.setText(sandwich.getMainName());
            //show the value of String getDescription inside the descriptionTv
            description_tv.setText(sandwich.getDescription());
            //show the placeOf Origin of String inside the placeOfOriinTv
            place_of_origin_tv.setText(sandwich.getPlaceOfOrigin());
        }

    }
//update the UI with the retrieved data.
    private void populateUI(Sandwich sandwich) {
        origin_tv = findViewById(R.id.origin_tv);
        description_tv = findViewById(R.id.description_tv);
        place_of_origin_tv = findViewById(R.id.place_of_origin_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        also_known_tv = findViewById(R.id.also_known_tv);
    }

    private void closeOnError() {finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
