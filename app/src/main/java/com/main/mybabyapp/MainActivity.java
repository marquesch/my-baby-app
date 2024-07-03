package com.main.mybabyapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.main.mybabyapp.data.dao.BottleFeedDao;
import com.main.mybabyapp.data.dao.BreastFeedDao;
import com.main.mybabyapp.data.dao.DiaperDao;
import com.main.mybabyapp.data.database.DatabaseHelper;
import com.main.mybabyapp.data.model.ActivityItem;
import com.main.mybabyapp.ui.adapters.ActivityItemAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper = new DatabaseHelper(this);
    private BottleFeedDao bottleFeedDao;
    private BreastFeedDao breastFeedDao;
    private DiaperDao diaperDao;
    private FloatingActionButton fabAdd;
    private RecyclerView itemRecyclerView;
    private ActivityItemAdapter adapter;
    private List<ActivityItem> activityItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        itemRecyclerView = findViewById(R.id.itemsRecyclerView);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityItemList = getActivityItemList();

        adapter = new ActivityItemAdapter(activityItemList);
        itemRecyclerView.setAdapter(adapter);

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

    private List<ActivityItem> getActivityItemList() {
        bottleFeedDao = new BottleFeedDao(dbHelper.getWritableDatabase());
        breastFeedDao = new BreastFeedDao(dbHelper.getWritableDatabase());
        diaperDao = new DiaperDao(dbHelper.getWritableDatabase());

        List<ActivityItem> list = new ArrayList<>();
        list.addAll(bottleFeedDao.getDayBottleFeeds());
        list.addAll(breastFeedDao.getDayBreastFeeds());
        list.addAll(diaperDao.getDayDiapers());
        Collections.sort(list, Comparator.comparingLong(o -> o.getTime().getTime()));

        return list;
    }

}