package ijvp.co.foodinary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;


public class ProductDetailActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private final String NUTRIONIX_ID = "bafd9293";
    private final String NUTRIONIX_KEY = "444c2a51502358e5bb79b470c0f4d1e6";

    Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // 49000036756 is a 2 Liter Bottle of Cherry Coke.
        getProductForUpc("49000036756");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadUi() {
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(mProduct.name);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(mProduct.description);

        ArrayAdapter<String> listAdapter;

        LinearLayout ingredients = (LinearLayout) findViewById(R.id.ingredientsListView);
        listAdapter = new ArrayAdapter<>(this, R.layout.row_ingredient, mProduct.ingredients);
        for (int i = 0; i < mProduct.ingredients.size(); i++) {
            View view = listAdapter.getView(i, null, ingredients);
            ingredients.addView(view);
        }

        LinearLayout nutrients = (LinearLayout) findViewById(R.id.nutrientsListView);
        listAdapter = new ArrayAdapter<>(this, R.layout.row_ingredient, mProduct.nutrients);
        for (int i = 0; i < mProduct.nutrients.size(); i++) {
            View view = listAdapter.getView(i, null, nutrients);
            nutrients.addView(view);
        }
    }

    public void getProductForUpc(String UPC) {
        String url = getUrlForUpc(UPC);
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Ion.with(this).load(url).asString().setCallback(new FutureCallback<String>() {
                @Override
                public void onCompleted(Exception e, String result) {
                    if (e == null && result != null) {
                       // DO STUFF WITH result
                        try {
                            JSONObject json = new JSONObject(result);
                            mProduct = Product.parseProductJson(json);
                            loadUi();
                        } catch (JSONException e2) {
                            Log.e(TAG, "JSON Error", e2);
                        }
                    } else {
                        Log.e(TAG, "Network Error", e);
                    }
                }
            });
        }
    }

    public String getUrlForUpc(String upc){
        // base url
        String url = "https://api.nutritionix.com/v1_1/item";

        // add upc
        url = url + "?upc=" + upc;

        // add app id
        url = url + "&appId=" + NUTRIONIX_ID;

        // add app key
        url = url + "&appKey=" + NUTRIONIX_KEY;

        return url;
    }
}
