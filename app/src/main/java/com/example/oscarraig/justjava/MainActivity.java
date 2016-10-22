package com.example.oscarraig.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.open_window);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Segunda.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",editText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    public void submitOrders(View view) {
        display();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int number = Integer.parseInt(quantityTextView.getText().toString()) + 1 ;
        quantityTextView.setText("" + number);
    }

    public void openWindow(View view) {

    }
}
