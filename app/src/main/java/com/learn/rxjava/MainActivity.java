package com.learn.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.learn.rxjava.observables.ListOptionActivity;
import com.learn.rxjava.observables.ObserverActivity;
import com.learn.rxjava.operators.ListOperatorsActivity;

/**
 * Created by nguyenvanlinh on 4/16/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 * http://reactivex.io/documentation/operators.html#categorized
 * https://github.com/ReactiveX/RxJava/wiki/Alphabetical-List-of-Observable-Operators
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnExample1 = findViewById(R.id.btn_example_1);
        Button btnExample2 = findViewById(R.id.btn_example_2);
        Button btnExample3 = findViewById(R.id.btn_example_3);
        Button btnExample4 = findViewById(R.id.btn_example_4);
        Button btnExample5 = findViewById(R.id.btn_example_5);
        Button btnExample6 = findViewById(R.id.btn_example_6);
        Button btnOperator = findViewById(R.id.btn_operators);
        Button btnBuffer = findViewById(R.id.btn_buffer);
        Button btnObservable = findViewById(R.id.btn_observables);
        Button btnListOper = findViewById(R.id.btn_list_operator);
        btnExample1.setOnClickListener(this);
        btnExample2.setOnClickListener(this);
        btnExample3.setOnClickListener(this);
        btnExample4.setOnClickListener(this);
        btnExample5.setOnClickListener(this);
        btnExample6.setOnClickListener(this);
        btnOperator.setOnClickListener(this);
        btnBuffer.setOnClickListener(this);
        btnObservable.setOnClickListener(this);
        btnListOper.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_example_1:
                startActivity(new Intent(this,Example1Activity.class));
                break;
            case R.id.btn_example_2:
                startActivity(new Intent(this,Example2Activity.class));
                break;
            case R.id.btn_example_3:
                startActivity(new Intent(this,Example3Activity.class));
                break;
            case R.id.btn_example_4:
                startActivity(new Intent(this,Example4Activity.class));
                break;
            case R.id.btn_example_5:
                startActivity(new Intent(this,Example5Activity.class));
                break;
            case R.id.btn_example_6:
                startActivity(new Intent(this,Example6Activity.class));
                break;
            case R.id.btn_operators:
                startActivity(new Intent(this,OperatorsActivity.class));
                break;
            case R.id.btn_buffer:
                startActivity(new Intent(this,BufferOperatorActivity.class));
                break;
            case R.id.btn_observables:
                startActivity(new Intent(this,ListOptionActivity.class));
                break;
            case R.id.btn_list_operator:
                startActivity(new Intent(this,ListOperatorsActivity.class));
                break;
        }
    }
}
