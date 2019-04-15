package com.oliverstudio.developersandroidplayer.ui.home_screen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.ui.history_screen.view.HistoryFragment;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.VideosFragment;

public class HomeActivity extends AppCompatActivity {

    //view
    private Toolbar mToolbar;

    //general vars
    private VideosFragment mVideosFragment;
    private HistoryFragment mHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mVideosFragment = (VideosFragment) getSupportFragmentManager().findFragmentByTag(VideosFragment.FRAGMENT_TAG);
        mHistoryFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(HistoryFragment.FRAGMENT_TAG);

        if (mVideosFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new VideosFragment(), VideosFragment.FRAGMENT_TAG)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.history:
                if (mHistoryFragment == null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new HistoryFragment())
                            .addToBackStack(HistoryFragment.FRAGMENT_TAG)
                            .commit();
                }

                mToolbar.setTitle(R.string.history);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}