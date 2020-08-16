package com.example.myuiproject;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;

import com.example.myuiproject.utils.DeviceUtils;
import com.example.myuiproject.view.NearbySearchView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity implements NearbySearchView.OnNearbySearchViewClickListener {
    private NearbySearchView mNearbySearcyView;
    private static final String TAG = "BaseActivity";
    private View mBottomSheet;
    private BottomSheetBehavior<View> mBehavior;

    private int mMaxPeekHeight;//最大高的
    private int mMinPeekHeight;//最小高度

    private int mScreenHeight;
    private int mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        mNearbySearcyView = (NearbySearchView) findViewById(R.id.nearby_view);
//        mNearbySearcyView.setOnNearbySearchViewClickListener(this);

        //底部弹出BottomSheet
        mBottomSheet = findViewById(R.id.poi_detail_bottom);
        mBehavior = BottomSheetBehavior.from(mBottomSheet);
//        mBottomSheet.setVisibility(View.GONE);

        setBottomSheet();
    }

    @Override
    public void onNearbySearchClick() {

    }

    /**
     * 设置底部POI详细BottomSheet
     */
    private void setBottomSheet() {
        mMinPeekHeight = mBehavior.getPeekHeight();
        //虚拟键盘高度
        int navigationHeight = DeviceUtils.getNavigationBarHeight(this);
        //加上虚拟键盘高度，避免被遮挡
//        mBehavior.setPeekHeight(mMinPeekHeight + navigationHeight);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (null == wm) {
            Log.e(TAG, "获取WindowManager失败:" + wm);
            return;
        }
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        //屏幕高度3/5
        mScreenHeight = point.y;
        mScreenWidth = point.x;
        //设置bottomsheet高度为屏幕 3/5
        int height = mScreenHeight * 3 / 5;
        this.mMaxPeekHeight = height;
        ViewGroup.LayoutParams params = mBottomSheet.getLayoutParams();
        params.height = height;

    }
}