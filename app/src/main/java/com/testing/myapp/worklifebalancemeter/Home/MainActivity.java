package com.testing.myapp.worklifebalancemeter.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.testing.myapp.worklifebalancemeter.R;
import com.testing.myapp.worklifebalancemeter.videoPlayer.VideoActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String TAG = "MainActivity:";
    final Context context = this;

    private static final String TAG_HOME = "home";
    private static final String TAG_CHARTS = "charts";
    private static final String TAG_ANALYZE = "analyze";
    public static String CURRENT_TAG = TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final ImageView imageWrokingButton = (ImageView) findViewById(R.id.working_status_on);
//        final ImageView imageWrokingButtonOff = (ImageView) findViewById(R.id.working_status_off);
        final TextView textWorking = (TextView) findViewById(R.id.working_status_text);


//        imageWrokingButton.setOnClickListener(new View.OnClickListener() {
//            boolean working = false ;
//            @Override
//            public void onClick(View v) {
//                if(working){
//                    textWorking.setText("Not working");
//                    imageWrokingButton.setImageResource(R.drawable.ic_working);
//
//                }else{
//                    imageWrokingButton.setImageResource(R.drawable.ic_working_off);
//                    textWorking.setText("Working");
//                }
//                working = !working ;
//            }
//        });

        Log.d(TAG, "onCreate: starting main activity");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Started working in your workplace", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        CircleImageView image = findViewById(R.id.image);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        toolbar.setNavigationIcon(R.drawable.ic_home);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupViewPager(navigationView);
        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);

        if (id == R.id.nav_home) {
            // Handle the camera action
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_charts) {
            viewPager.setCurrentItem(1);

        } else if (id == R.id.nav_analyze) {
            viewPager.setCurrentItem(2);

        }else if (id == R.id.nav_wish) {
            viewPager.setCurrentItem(3);

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(context, VideoActivity.class);
            startActivity(intent);
//            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_contact_us) {

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setupViewPager(final NavigationView navigationView){

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new ReminderFragment());
        adapter.addFragment(new WishFragment());
        adapter.addFragment(new LocationFragment());
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        final TextView titleText = findViewById(R.id.working_status_text);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_analyze);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_help);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_wishlist);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                MenuItem menuItem = navigationView.getMenu().getItem(position);
                menuItem.setChecked(true);

                tab.getIcon().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                switch (position)
                {
                    case 0:titleText.setText("Home"); break;
                    case 1:titleText.setText("Reminders"); break;
                    case 2:titleText.setText("Location"); break;
                    case 3:titleText.setText("Wish List"); break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
