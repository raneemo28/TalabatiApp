package com.alissar.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    final private List<Product> products = new ArrayList<>();
    private int counter = 0;
    private EditText productNameET, productPriceET, productQuantityET;

    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            productNameET = findViewById(R.id.ProductName);
            productPriceET = findViewById(R.id.ProductPrice);
            productQuantityET = findViewById(R.id.ProductQuantity);
            Button addButton =  findViewById(R.id.AddButton);
            Button DisplayButton =findViewById(R.id.DisplayButton);
            addButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                    addProduct();
                }
            });
             DisplayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    startActivity(intent);
                }
            });
            return insets;
        });
    }
    public void addProduct() {
        try {
            String productName = productNameET.getText().toString().trim();
            String price = productPriceET.getText().toString().trim();
            String quantity = productQuantityET.getText().toString().trim();
            
            if (productName.isEmpty()) {
                Toast.makeText(this, "Please enter product name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (price.isEmpty() ) {
                Toast.makeText(this, "Please enter price ", Toast.LENGTH_SHORT).show();
                return;
            }
            if((quantity.isEmpty()))
            {
                Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
                return;
            }
            
            double productPrice = Double.parseDouble(price);
            int productQuantity = Integer.parseInt(quantity);

             if (productPrice <= 0 ) {
                Toast.makeText(this, "Price must be positive", Toast.LENGTH_SHORT).show();
                return;
            }
            if( productQuantity <= 0)
            {
                Toast.makeText(this, "Quantity must be positive", Toast.LENGTH_SHORT).show();
                return;
            }
            
            Product product = new Product(counter, productName, productPrice, productQuantity);
           // products.add(product);
            counter++;

            
            productNameET.setText("");
            productPriceET.setText("");
            productQuantityET.setText("");
            DB.addProductDetails(product);
            
            Toast.makeText(this, "Product added successfully to DB!", Toast.LENGTH_SHORT).show();


        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            System.out.print(e.getMessage());

        } catch (Exception e) {
            Toast.makeText(this, "Error adding product", Toast.LENGTH_SHORT).show();
            System.out.print(e.getMessage());
        }
    }


}

