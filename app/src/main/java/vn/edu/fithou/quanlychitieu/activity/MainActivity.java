package vn.edu.fithou.quanlychitieu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.fithou.quanlychitieu.R;
import vn.edu.fithou.quanlychitieu.fragment.MoreFragment;
import vn.edu.fithou.quanlychitieu.fragment.NotificationFragment;
import vn.edu.fithou.quanlychitieu.fragment.ReportFragment;
import vn.edu.fithou.quanlychitieu.fragment.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    final Fragment moreFragment = new MoreFragment();
    final Fragment notificationFragment = new NotificationFragment();
    final Fragment reportFragment = new ReportFragment();
    final Fragment transactionFragment = new TransactionFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment activeFragment = transactionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mapView();

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        fm.beginTransaction().add(R.id.frag_container, moreFragment, "4").hide(moreFragment).commit();
        fm.beginTransaction().add(R.id.frag_container, notificationFragment, "3").hide(notificationFragment).commit();
        fm.beginTransaction().add(R.id.frag_container,reportFragment, "2").hide(reportFragment).commit();
        fm.beginTransaction().add(R.id.frag_container,transactionFragment, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_action_more:
                    fm.beginTransaction().hide(activeFragment).show(moreFragment).commit();
                    activeFragment = moreFragment;
                    return true;
                case R.id.nav_action_notification:
                    fm.beginTransaction().hide(activeFragment).show(notificationFragment).commit();
                    activeFragment = notificationFragment;
                    return true;
                case R.id.nav_action_report:
                    fm.beginTransaction().hide(activeFragment).show(reportFragment).commit();
                    activeFragment = reportFragment;
                    return true;
                case R.id.nav_action_transaction:
                    fm.beginTransaction().hide(activeFragment).show(transactionFragment).commit();
                    activeFragment = transactionFragment;
                    return true;
            }
            return false;
        }
    };

    private void mapView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }
}
