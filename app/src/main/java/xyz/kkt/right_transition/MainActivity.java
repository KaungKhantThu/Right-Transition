package xyz.kkt.right_transition;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.content)
    CoordinatorLayout contentView;

    private Animation animMainStart, animMainEnd;

    private static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);

        animMainStart = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.start);
        animMainEnd = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.end);

        drawerLayout.setScrimColor(Color.TRANSPARENT);

//        drawerLayout.setStatusBarBackground(R.color.primary);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {

                                           }

                                           @Override
                                           public void onDrawerOpened(View drawerView) {
                                               contentView.startAnimation(animMainStart);
                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {
                                               contentView.startAnimation(animMainEnd);
                                           }

                                           @Override
                                           public void onDrawerStateChanged(int newState) {
                                           }
                                       }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_right_side_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.btnMyMenu:
                drawerLayout.openDrawer(GravityCompat.END);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
