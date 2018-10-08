package com.example.djazuli.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/*display app coffe order*/

public class MainActivity extends AppCompatActivity {

    int number=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //dipanggill saat button di klick
    public void submitOrder(View view) {
        CheckBox addWhispedCream = (CheckBox)findViewById(R.id.checkbox_WC);
        CheckBox addCC = (CheckBox)findViewById(R.id.checkbox_CC);
        boolean hasWhispedcream = addWhispedCream.isChecked();
        boolean hasCC = addCC.isChecked();


        EditText namapemesan = (EditText)findViewById(R.id.namaPemesan);
        String nama = namapemesan.getText().toString();

        int price = calculatePrice(hasWhispedcream,hasCC);
        String Message = orderSummary(nama,price,hasWhispedcream,hasCC);
        //displayMessage(Message);
        //displayPrice(number*5);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6 , -122.3"));
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
//
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order by "+nama);
        intent.putExtra(Intent.EXTRA_TEXT, Message);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
    //method memberi kuantitas nilai pada layar
    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }
    //method display harga
    /*private void  displayPrice(int number){
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    public void increment(View view) {
        if(number == 100){

            Toast.makeText(this, "tidak boleh lebih dari 100 ",Toast.LENGTH_SHORT).show();

            return;
        }
        this.number++;
        display(number);
    }

    public void decrement(View view) {
        if(number == 1){

            Toast.makeText(this, "tidak boleh kurang dari 1 ",Toast.LENGTH_SHORT).show();

            return;
        }
        this.number--;
        display(number);
    }

    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public String orderSummary(String nama,int price, boolean hascream,boolean hasCC){
        String gabunganPesanan = "nama "+nama;
        gabunganPesanan+="\nAdd whiped cream "+hascream;
        gabunganPesanan+="\nAdd Chocolate "+hasCC;
        gabunganPesanan+="\nQuantity : "+number;
        gabunganPesanan+="\nTotal : $"+price;
        gabunganPesanan+="\nthank";
        return gabunganPesanan;
    }

    public int calculatePrice(boolean hascream, boolean hasCC){
            int base=5;
            if(hascream){
                base+=1;
            }
            if(hasCC){
                base+=2;
            }
            return base*number;
    }

}
