package com.learn.rxjava.operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.learn.rxjava.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nguyenvanlinh on 4/27/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 */
public class ListOperatorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_operators);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_map, R.id.btn_flat_map, R.id.btn_switch_map, R.id.btn_concat_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_map:
                startActivity(new Intent(this,MapOperatorActivity.class));
                break;
            case R.id.btn_flat_map:
                startActivity(new Intent(this,FlatMapActivity.class));
                break;
            case R.id.btn_switch_map:
                startActivity(new Intent(this,SwitchMapOperatorActivity.class));
                break;
            case R.id.btn_concat_map:
                startActivity(new Intent(this,ConcatMapOperatorActivity.class));
                break;
        }
    }
}
