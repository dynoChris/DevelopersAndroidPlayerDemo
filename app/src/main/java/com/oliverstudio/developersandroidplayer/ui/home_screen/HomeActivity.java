package com.oliverstudio.developersandroidplayer.ui.home_screen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.VideosFragment;

public class HomeActivity extends AppCompatActivity {

    //view
    private Toolbar mToolbar;

    //general vars
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFragment = getSupportFragmentManager().findFragmentByTag(VideosFragment.FRAGMENT_TAG);

        if (mFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new VideosFragment(), VideosFragment.FRAGMENT_TAG)
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

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}