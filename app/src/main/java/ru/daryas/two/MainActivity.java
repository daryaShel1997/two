package ru.daryas.two;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import ru.daryas.two.ActivityFragment.AboutFragment;
import ru.daryas.two.ActivityFragment.GifFragment;
import ru.daryas.two.ActivityFragment.RecyclervFragment;

public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;

    final static String TAG_1 = "fragment1";
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.my_drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
//        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            GifFragment frag3 = new GifFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, frag3, TAG_1)
                 //   .addToBackStack(null)
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    Intent intent;

                    switch (menuItem.getItemId()) {
                        case R.id.idnews:
                            GifFragment frag1 = new GifFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag1, TAG_1)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case R.id.idgif:
                            RecyclervFragment frag2 = new RecyclervFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag2, TAG_1)
                                    .addToBackStack(null)
                                    .commit();
                            break;


                        case R.id.idabout:
                            AboutFragment frag3 = new AboutFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag3, TAG_1)
                                    .addToBackStack(null)
                                    .commit();

                            break;

                    }
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                });
    }


}