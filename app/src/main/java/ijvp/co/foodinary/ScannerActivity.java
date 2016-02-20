package ijvp.co.foodinary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Button button = new Button(getApplicationContext());
        button.setText("Click me to scan");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");

                startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
            }
        });

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        viewGroup.addView(button);

        Button second = new Button(getApplicationContext());
        second.setText("Click me to see test product");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ProductDetailActivity.class);

                // 49000036756 is a 2 Liter Bottle of Cherry Coke.
                i.putExtra("upc", "49000036756");
                startActivity(i);
            }
        });

        viewGroup.addView(second);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.tvResult);

            if (resultCode == RESULT_OK) {
                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }
}
