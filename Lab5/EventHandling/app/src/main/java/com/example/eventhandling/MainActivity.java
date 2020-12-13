package com.example.eventhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);
        TextView textView = findViewById(R.id.tv_t);
        TextView textView1 = findViewById(R.id.tv_text);
        textView.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        Button button = findViewById(R.id.btn);
        final TextView textView = findViewById(R.id.tv_t);
        TextView textView1 = findViewById(R.id.tv_text);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.click_btn:
                setTitle("ButtonClick");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.GONE);
                button.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        textView.setText("Button clicked");
                    }
                }
                );
                button.setOnLongClickListener(
                        new Button.OnLongClickListener() {
                            public boolean onLongClick(View v) {
                                textView.setText("Long button click");
                                return true;
                            }
                        }
                );
                return true;
            case R.id.m_event:
                setTitle("MotionEvent");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                textView1.setVisibility(View.VISIBLE);
                return true;
            case R.id.cmn_g:
                setTitle("CommonGestures");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
