package mob.s4.cine.series.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mob.s4.cine.series.R;
import mob.s4.cine.series.views.fragments.FavoritesFragment;
import mob.s4.cine.series.views.fragments.MoviesFragment;

public class BottomNavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        setTitle(R.string.title_movies);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        setFragmentTransaction(MoviesFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                setFragmentTransaction(MoviesFragment.newInstance());
                setTitle(R.string.title_movies);
                return true;
            case R.id.navigation_dashboard:
                setFragmentTransaction(FavoritesFragment.newInstance());
                setTitle(R.string.title_saved);
                return true;
        }
        return false;
    }

    @NonNull
    private void setFragmentTransaction(Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment).commitAllowingStateLoss();
    }

}