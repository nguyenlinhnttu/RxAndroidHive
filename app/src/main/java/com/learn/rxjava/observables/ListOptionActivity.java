package com.learn.rxjava.observables;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.learn.rxjava.R;

public class ListOptionActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_option);
        Button btnObserver = findViewById(R.id.btn_observer);
        Button btnSingle = findViewById(R.id.btn_single);
        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOptionActivity.this,SingleObserverActivity.class));
            }
        });
        btnObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOptionActivity.this,ObserverActivity.class));
            }
        });
        Button btnMaybe = findViewById(R.id.btn_maybe);
        btnMaybe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOptionActivity.this,MaybeObserverActivity.class));
            }
        });

        Button btnCompletable = findViewById(R.id.btn_completable);
        btnCompletable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOptionActivity.this,CompletableObserverActivity.class));
            }
        });

        Button btnFlow = findViewById(R.id.btn_flowable);
        btnFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOptionActivity.this,FlowableObserverActivity.class));
            }
        });
    }
}
