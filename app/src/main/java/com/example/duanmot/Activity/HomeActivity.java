package com.example.duanmot.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Fragment.HoaDonFragment;
import com.example.duanmot.Fragment.SanPhamFragment;
import com.example.duanmot.Fragment.TaiKhoanFragment;
import com.example.duanmot.Fragment.ThongKeFragment;
import com.example.duanmot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    int position = 0;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.nav_bottom_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        relativeLayout = findViewById(R.id.drawerLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_big, new SanPhamFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        if (position == 0) {
            position += 1;
            Snackbar.make(relativeLayout, "Nhấn tiếp để thoát khỏi ứng dụng", Snackbar.LENGTH_LONG).show();
            return;
        }
        if (position >= 1) {
            super.onBackPressed();
        }
    }

    //click bottom menu
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.sanPhamFragment:
                        selectedFragment = new SanPhamFragment();
                        break;
                    case R.id.hoaDonFragment:
                        selectedFragment = new HoaDonFragment();
                        break;
                    case R.id.thongKeFragment:
                        selectedFragment = new ThongKeFragment();
                        break;
                    case R.id.taiKhoanFragment:
                        selectedFragment = new TaiKhoanFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.exit_rtl, R.anim.left_to_right, R.anim.exit_ltr).replace(R.id.layout_big, selectedFragment).commit();
                return true;
            };
}
