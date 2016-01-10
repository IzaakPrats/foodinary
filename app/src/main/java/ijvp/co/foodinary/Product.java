package ijvp.co.foodinary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by izaakprats on 1/8/16.
 */
public class Product {
    String name;
    String description;
    ArrayList<String> ingredients;
    ArrayList<String> nutrients;

    public Product(String name, String description, String[] ingredients, String[] nutrients) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<String>(Arrays.asList(ingredients));;
        this.nutrients = new ArrayList<String>(Arrays.asList(nutrients));
    }

    private Product() {
        name = null;
        description = null;
        ingredients = new ArrayList<>();
        nutrients = new ArrayList<>();
    }

    public static Product getProduct() {
        String testName = "Sample";
        String testDescription = "This is a sample food that has been scanned via UPC.";

        String[] i = new String[3];
        i[0] = "Sugar";
        i[1] = "Spice";
        i[2] = "Other sugar";

        String[] n = new String[3];
        n[0] = "High Fructose";
        n[1] = "Higher Furctose";
        n[2] = "HIGHEST FRUCTOSE";

        return new Product(testName, testDescription, i, n);
    }

    public static Product parseProductJson(JSONObject obj) throws JSONException {
        Product product = new Product();

        product.name = obj.getString("item_name");
        product.description = obj.getString("item_description");

        String ingredientJSON = obj.getString("nf_ingredient_statement");
        product.ingredients = new ArrayList<>(Arrays.asList(ingredientJSON.split(", ")));

        String calories = "Calories : ";
        calories = calories + String.valueOf(obj.getInt("nf_calories"));
        product.nutrients.add(calories);

        String sodium = "Sodium : ";
        sodium = sodium + String.valueOf(obj.getInt("nf_sodium"));
        product.nutrients.add(sodium);

        String carbs = "Carbohydrates : ";
        carbs = carbs + String.valueOf(obj.getInt("nf_total_carbohydrate"));
        product.nutrients.add(carbs);

        String sugars = "Sugars : ";
        sugars = sugars + String.valueOf(obj.getInt("nf_sugars"));
        product.nutrients.add(sugars);

        return product;
    }
}
