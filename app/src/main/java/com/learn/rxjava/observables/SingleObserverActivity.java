package com.learn.rxjava.observables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.learn.rxjava.Note;
import com.learn.rxjava.R;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleObserverActivity extends AppCompatActivity{

    private static final String TAG = SingleObserverActivity.class.getSimpleName();
    private Disposable disposable;

    /**
     * Single Observable emitting single Note
     * Single Observable is more useful in making network calls
     * where you expect a single response object to be emitted
     * -
     * Single : SingleObserver
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black);
        Single<Note> noteObservable = getNoteObservable();

        SingleObserver<Note> singleObserver = getSingleObserver();

        noteObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);
    }

    private SingleObserver<Note> getSingleObserver() {
        SingleObserver<Note> noteSingleObserver = new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(Note note) {
                Log.e(TAG, "onSuccess: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        };
        return noteSingleObserver;
    }

    private Single<Note> getNoteObservable() {
        Single<Note> noteSingle = Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                Note note = new Note(1, "Thuc Coffee");
                emitter.onSuccess(note);
            }
        });
        return noteSingle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
