package com.example.myapplication.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends Activity {

    private final int[] images = {
            R.drawable.logo,
            R.drawable.martinique_tartane,
            R.drawable.p1050056
    };
    private final AtomicInteger index = new AtomicInteger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        Button button = (Button) findViewById(R.id.applyButton);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView applyText = (TextView) findViewById(R.id.applyText);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(applyText.getText());

                imageView.setImageResource(getNextImage());
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                System.out.println("MV = " + motionEvent.getAction());
                imageView.setImageResource(getNextImage());
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
                System.out.println("MV = " + motionEvent.getAction());
                System.out.println("MV2 = " + motionEvent2.getAction());
                System.out.println("v = " + v);
                System.out.println("v2 = " + v2);
                imageView.setImageResource(getNextImage());
                return true;
            }
        });


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                System.out.println("Action = " + motionEvent.getAction());
                System.out.println("Action index = " + motionEvent.getActionIndex());
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

    }

    private int getNextImage() {
        return images[index.getAndIncrement() % images.length];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
