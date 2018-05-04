package com.learn.rxjava.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.learn.rxjava.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nguyenvanlinh on 4/27/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 */
public class MapOperatorActivity extends AppCompatActivity {
    private static final String TAG = MapOperatorActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<User, User>() {
            @Override
            public User apply(User user) throws Exception {
                // modifying user object by adding email address
                // turning user name to uppercase
                user.setEmail(String.format("%s@rxjava.wtf", user.getName()));
                user.setName(user.getName().toUpperCase());
                return user;
            }
        }).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(User user) {
                Log.e(TAG, "onNext: " + user.getName() + ", " + user.getGender() + ", " + user.getEmail());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "All users emitted!");
            }
        });
    }

    private Observable<User> getUsersObservable() {
        String[] names = new String[]{"mark", "john", "trump", "obama"};

        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
