package com.learn.rxjava.operators;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
/**
 * Created by nguyenvanlinh on 5/4/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 * Phu hop voi viec su dung search, no luon tra ve  latest Observable  va emit no
 */
public class SwitchMapOperatorActivity extends AppCompatActivity {

    private static final String TAG = SwitchMapOperatorActivity.class.getSimpleName();

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<String> integerObservable =
                Observable.fromArray(new String[]{"1", "2", "3", "4", "5", "6"});


        // it always emits 6 as it un-subscribes the before observer
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String string) throws Exception {
                        return Observable.just(string).delay(1, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All users emitted!");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
