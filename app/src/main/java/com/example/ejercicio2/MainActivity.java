package com.example.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ejercicio2.Class.Bank;
import com.example.ejercicio2.Class.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Funciona: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Bank>> reqData = service.getListBank("fccb5e2bf1874697ae4af772ed29d56c");

        reqData.enqueue(new Callback<List<Bank>>() {
            @Override
            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {

                if (!response.isSuccessful()) {
                    Log.i("TAG", "Error" + response.code());
                } else {
                    ArrayList<String> lstBancos = new ArrayList<String>();
                    List<Bank> bankL = response.body();
                    TextView textB = (TextView) findViewById(R.id.txtBanc);
                    for (Bank c : bankL) {
                        lstBancos.add(c.name + "\n");
                    }
                    textB.setText("Respuesta Retrofit: \n" + lstBancos);
                }
            }
            @Override
            public void onFailure(Call<List<Bank>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
        }
    }
