package com.learn.rxjava.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.learn.rxjava.Person;
import com.learn.rxjava.R;
import com.learn.rxjava.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.MathObservable;
/**
 * Created by nguyenvanlinh on 5/4/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 * // RxJava math
 implementation 'io.reactivex:rxjava-math:1.0.0'
 */
public class MathOperatorListActivity extends AppCompatActivity {
    private static final String TAG = MathOperatorListActivity.class.getSimpleName();
    private Disposable disposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_operator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_max, R.id.btn_min, R.id.btn_sum, R.id.btn_average, R.id.btn_count, R.id.btn_reduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_max:
                maxMethod();
                break;
            case R.id.btn_min:
                minMethod();
                break;
            case R.id.btn_sum:
                sumMethod();
                break;
            case R.id.btn_average:
                averageMethod();
                break;
            case R.id.btn_count:
                countMethod();
                break;
            case R.id.btn_reduce:
                reduceMethod();
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void reduceMethod() {
        io.reactivex.Observable
                .range(1, 10)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer number, Integer sum) throws Exception {
                        return sum + number;
                    }
                })
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(TAG, "Sum of numbers from 1 - 10 is: " + integer);
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

    @SuppressLint("CheckResult")
    private void countMethod() {
        getUsersObservable()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGender().equalsIgnoreCase("male");
                    }
                })
                .count()
                .subscribeWith(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long count) {
                        Log.d(TAG, "Male users count: " + count);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }

    private io.reactivex.Observable<User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};
        String[] femaleUsers = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();

        for (String name : maleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }

        for (String name : femaleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

            users.add(user);
        }
        return io.reactivex.Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    private void averageMethod() {
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};

        Observable<Integer> observable = Observable.from(numbers);

        MathObservable
                .averageInteger(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Sum value: " + integer);
                    }
                });
    }

    private void sumMethod() {
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};

        Observable<Integer> observable = Observable.from(numbers);

        MathObservable
                .sumInteger(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Sum value: " + integer);
                    }
                });
    }

    private void minMethod() {
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};

        Observable<Integer> observable = Observable.from(numbers);

        MathObservable
                .min(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Min value: " + integer);
                    }
                });

    }

    private void maxMethod() {
        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};

        Observable<Integer> observable = Observable.from(numbers);

        MathObservable
                .max(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Max value: " + integer);
                    }
                });


    }
}
