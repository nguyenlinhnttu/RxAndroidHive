package com.learn.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nguyenvanlinh on 4/18/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 */
public class BufferOperatorActivity extends AppCompatActivity {
    private static final String TAG = BufferOperatorActivity.class.getSimpleName();
    private Disposable disposable;
    private int maxTaps = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_operator);
        final TextView txtTapResult = findViewById(R.id.tap_result);
        final TextView txtTapResultMax = findViewById(R.id.tap_result_max_count);
        Button btnTapArea = findViewById(R.id.layout_tap_area);
        RxView.clicks(btnTapArea)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) throws Exception {
                        return 1;
                    }
                })
                .buffer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.e(TAG, "onNext: " + integers.size() + " taps received!");
                        if (integers.size() > 0) {
                            maxTaps = integers.size() > maxTaps ? integers.size() : maxTaps;
                            txtTapResult.setText(String.format("Received %d taps in 3 secs", integers.size()));
                            txtTapResultMax.setText(String.format("Maximum of %d taps received in this session", maxTaps));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
