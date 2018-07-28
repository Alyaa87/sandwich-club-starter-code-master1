package com.udacity.sandwichclub.utils;

import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        try {
            //create JSON Object main Root.

            //{
            //   "name":{
            //      "mainName":"Bosna",
            //      "alsoKnownAs":[
            //         "Bosner"
            //      ]
            //   },
            //   "placeOfOrigin":"Austria",
            //   "description":"Bosna
            //            is a spicy Austrian fast food dish, said to have originated in either Salzburg or Linz.
            //            It is now popular all over western Austria and southern
            //            Bavaria.",
            //   "image":"https://upload.wikimedia.org\wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg",
            //   "ingredients":[
            //      "White
            //            bread",
            //      "Bratwurst",
            //      "Onions",
            //      "Tomato ketchup",
            //      "Mustard",
            //      "Curry powder"
            //   ]
            //}
              JSONObject main = new JSONObject(json);
                JSONObject sandwichName = main.getJSONObject("name");
                String mainName = sandwichName.getString("mainName");
                //create Json array Also Known as in the object sandwichName.
                JSONArray alsoKnownAs = sandwichName.getJSONArray("alsoKnownAs");
                //create arrayList
                ArrayList<String> alsoKnownAsList = new ArrayList<>();{
                    if (alsoKnownAsList!= null) {
                        for (int i = 0; i < alsoKnownAs.length(); i++) {
                            alsoKnownAsList.add(alsoKnownAs.getString(i));
                        sandwich.setAlsoKnownAs(alsoKnownAsList);}
                    }
                //add the details.
                String placeOfOrigin = main.getString("placeOfOrigin");
                String description = main.getString("description");
                String sandwichImage = main.getString("image");
                //create array list Ingredients.
                JSONArray ingredients = main.getJSONArray("ingredients");
                ArrayList<String> ingredientsList = new ArrayList<>();{
                    if (ingredientsList != null) {
                        //creating a for loop .
                        for (int i = 0; i < ingredients.length(); i++) {
                            ingredientsList.add(ingredients.getString(i));
                            sandwich.setIngredients(ingredientsList);

                        }
                    }
                      //set the value of the data inside the sandwich class
                    sandwich.setMainName(mainName);
                    sandwich.setAlsoKnownAs(alsoKnownAsList);
                    sandwich.setImage(sandwichImage);
                    sandwich.setIngredients(ingredientsList);
                    sandwich.setPlaceOfOrigin(placeOfOrigin);
                    sandwich.setDescription(description);
                    }
                //return method
                    return sandwich;

            }


        } catch(JSONException e) {
            e.printStackTrace();
        }
        
        return null;

    }
}


