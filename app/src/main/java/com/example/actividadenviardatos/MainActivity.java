package com.example.actividadenviardatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectStreamClass;

public class MainActivity extends AppCompatActivity {
    EditText para,objeto,mensaje;
    Button enviar,limpiar;
    TextView titu;
    ImageView back;
    int count;

    @Override
    public void onBackPressed() {
        if(count==0)
        {
            Toast.makeText(this, "Precione de nuevo para salir...", Toast.LENGTH_SHORT).show();
            count++;
        }
        else {
            super.onBackPressed();
        }
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                count=0;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        para = (EditText) findViewById(R.id.txtPara);
        objeto = (EditText) findViewById(R.id.txtasunto);
        mensaje = (EditText) findViewById(R.id.txtMensaje);
        enviar = (Button) findViewById(R.id.btnEnviar);
        limpiar = (Button) findViewById(R.id.btnLimpiar);
        back = (ImageView) findViewById(R.id.imgBack);
        titu = (TextView) findViewById(R.id.txtTitle);
        titu.setText("Enviar Correo");
        back.setVisibility(View.INVISIBLE);

        Toolbar toolbar = findViewById(R.id.Inclutoolbar);
        setSupportActionBar(toolbar);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                para.getText().clear();
                objeto.getText().clear();
                mensaje.getText().clear();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(para.getText().toString().equals("")||objeto.getText().toString().equals("")||mensaje.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Rellene todos los valores", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (validarEmail(para)==true)
                    {
                        Intent i = new Intent(Intent.ACTION_SENDTO);
                        i.setData(Uri.parse("mailto:"));
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{para.getText().toString().trim()});
                        i.putExtra(Intent.EXTRA_SUBJECT,objeto.getText().toString().trim());
                        i.putExtra(Intent.EXTRA_TEXT,mensaje.getText().toString().trim());
                        //i.setType("message/rfc822");

                        startActivity(Intent.createChooser(i,"Elije un cliente de correo electronico..."));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Direccion de correo invalida", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private boolean validarEmail(EditText para)
    {
        String email = para.getText().toString().trim();
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnAbout:
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                return true;

            case R.id.mnSalir:
                super.onBackPressed();
                return true;

            default:
                return onOptionsItemSelected(item);
        }

    }

}