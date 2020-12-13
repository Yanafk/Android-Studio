package com.example.eventhandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
                ConstraintLayout myLayout = findViewById(R.id.main_activity);
                myLayout.setOnTouchListener(new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {
                        handleTouch(m);
                        return true;
                    }
                }
                );
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

    void handleTouch(MotionEvent m) {
        TextView textView1 = findViewById(R.id.tv_t);
        TextView textView2 = findViewById(R.id.tv_text);
        int pointerCount = m.getPointerCount();
        for (int i = 0; i < pointerCount; i++)
        {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }
            String touchStatus = "Action: " + actionString + " Index: " +
                    actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if (id == 0)
                textView1.setText(touchStatus);
            else
                textView2.setText(touchStatus);
        }
    }
}
