package com.example.materialdesigndemo.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.materialdesigndemo.R;
import com.example.materialdesigndemo.adapter.RecyclerViewAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BottomAppBarActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private List<String> data;
    private BottomAppBar bottomAppBar;
    private TextView bottomAppBarTitle;
    private Chip positionChip;
    private Chip radiusChip;
    private Chip marginChip;
    private Chip showTitleChip;

    boolean isFabAlignRight = false;
    boolean isCutoutMarginZero = false;
    boolean isCutoutRadiusZero = false;
    boolean showBottomBarTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_app_bar);

        initData();
        initViews();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            data.add("List item row: " + i);
        }
    }

    private void initViews() {
        fab = findViewById(R.id.fab_bottom_appbar);
        mRecyclerView = findViewById(R.id.recycler_view_bottom_appbar);
        bottomAppBar = findViewById(R.id.bottom_App_bar);
        positionChip = findViewById(R.id.position_chip);
        radiusChip = findViewById(R.id.radius_chip);
        marginChip = findViewById(R.id.margin_chip);
        showTitleChip = findViewById(R.id.show_title_chip);

        bottomAppBarTitle = findViewById(R.id.bottom_app_bar_title);
        setSupportActionBar(bottomAppBar);
        // set home icon on bottom app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // show columns based on screen size
        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setItems(data);

        setupUiClicks();
    }

    private void setupUiClicks() {
        positionChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isFabAlignRight = b;
                resetBottomAppBar();
            }
        });
        radiusChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutRadiusZero = b;
                resetBottomAppBar();
            }
        });
        marginChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutMarginZero = b;
                resetBottomAppBar();
            }
        });
        showTitleChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showBottomBarTitle = b;
                resetBottomAppBar();
            }
        });
        // add one item to recyclerview on top on click of fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(0, "1");
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

    }

    // show bottom app bar based on selected choice
    private void resetBottomAppBar() {
        // fab alignment end or center
        bottomAppBar.setFabAlignmentMode(isFabAlignRight ? BottomAppBar.FAB_ALIGNMENT_MODE_END : BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        // fab zero radius (cutout margin)
        bottomAppBar.setFabCradleMargin(isCutoutMarginZero ? 0 : 17f); //initial default value 17f
        // fab zero margin
        bottomAppBar.setFabCradleRoundedCornerRadius(isCutoutRadiusZero ? 0 : 28f); //initial default value 28f
        // app bar title
        bottomAppBarTitle.setVisibility(showBottomBarTitle ? View.VISIBLE : View.GONE); //By Default its not suggested to add title but this is just a method if required.
        if (showBottomBarTitle)
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        bottomAppBar.invalidate();
    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_main, menu);
        return true;
    }

    // on click of home icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
