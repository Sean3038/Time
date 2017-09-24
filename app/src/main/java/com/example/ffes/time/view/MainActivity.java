package com.example.ffes.time.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ffes.time.R;
import com.example.ffes.time.adapter.TimeAdapter;
import com.example.ffes.time.data.Time;
import com.example.ffes.time.presenter.CountPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CountContract.View {

    CountContract.Presenter presenter;

    @BindView(R.id.reset_btn)
    TextView reset_btn;
    @BindView(R.id.start_btn)
    TextView start_btn;
    @BindView(R.id.stop_btn)
    TextView stop_btn;
    @BindView(R.id.single_btn)
    TextView single_btn;
    @BindView(R.id.new_count)
    TextView newCount;
    @BindView(R.id.totalCount)
    TextView totalCount;
    @BindView(R.id.count_information)
    RecyclerView countInformation;
    @BindView(R.id.pressstartnotify)
    TextView pressstartnotify;
    @BindView(R.id.presssingalnotify)
    TextView presssingalnotify;

    TimeAdapter timeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        timeAdapter=new TimeAdapter(getApplicationContext());
        countInformation.setAdapter(timeAdapter);
        countInformation.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true));
        new CountPresenter(this,timeAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bind(this);
    }

    @Override
    public void setPresenter(CountContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setTotalTime(Time time) {
        totalCount.setText(time.toString());
    }

    @Override
    public void setCurrentTime(Time time) {
        newCount.setText(time.toString());
    }

    @Override
    public void showStartPane() {
        start_btn.setVisibility(View.VISIBLE);
        reset_btn.setVisibility(View.GONE);
        stop_btn.setVisibility(View.GONE);
        single_btn.setVisibility(View.GONE);
    }

    @Override
    public void showStopSinglePane() {
        start_btn.setVisibility(View.GONE);
        reset_btn.setVisibility(View.GONE);
        stop_btn.setVisibility(View.VISIBLE);
        single_btn.setVisibility(View.VISIBLE);
    }


    @Override
    public void showStartResetPane() {
        start_btn.setVisibility(View.VISIBLE);
        reset_btn.setVisibility(View.VISIBLE);
        stop_btn.setVisibility(View.GONE);
        single_btn.setVisibility(View.GONE);
    }

    @Override
    public void showStartNotify() {
        pressstartnotify.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideStartNotify() {
        pressstartnotify.setVisibility(View.GONE);
    }

    @Override
    public void showCountNotify() {
        presssingalnotify.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCountNotify() {
        presssingalnotify.setVisibility(View.GONE);
    }

    @OnClick(R.id.single_btn)
    void onSingleCountClick() {
        presenter.singleCount();
    }

    @OnClick(R.id.reset_btn)
    void onResetClick() {
        presenter.reset();
    }

    @OnClick(R.id.stop_btn)
    void onStopClick() {
        presenter.stop();
    }

    @OnClick(R.id.start_btn)
    void onStartClick() {
        presenter.start();
    }
}
