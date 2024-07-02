package com.main.mybabyapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fab_plus);

        PopupMenu popupMenu = new PopupMenu(this, fabAdd);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.plus_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.mama) {
                    openFeedActivity();
                    return true;
                } else if (itemId == R.id.fralda) {
                    openDiaperActivity();
                    return true;
                }
                return false;
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }

    private void openFeedActivity() {
        Intent intent = new Intent(MainActivity.this, FeedActivity.class);
        startActivity(intent);
    }

    private void openDiaperActivity() {
        Intent intent = new Intent(MainActivity.this, DiaperActivity.class);
        startActivity(intent);
    }

}