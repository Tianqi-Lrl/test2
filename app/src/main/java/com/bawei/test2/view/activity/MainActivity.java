package com.bawei.test2.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bawei.test2.R;
import com.bawei.test2.SecondActivity;
import com.bawei.test2.base.BaseActivity;
import com.bawei.test2.contract.IHomeContrat;
import com.bawei.test2.model.bean.Bean;
import com.bawei.test2.presenter.HomePresenter;
import com.bawei.test2.view.adapter.MyAdapter;

import java.util.List;

public  class MainActivity extends BaseActivity<HomePresenter> implements IHomeContrat.IView {

    private RecyclerView rv;

    @Override
    protected void initData() {
        mPresenter.getHomeData();
    }

    @Override
    protected void initView() {
        rv = findViewById(R.id.rv);
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        List<Bean.DataBean> data = bean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(data);
        rv.setAdapter(myAdapter);
        myAdapter.setOnTagClickListener(new MyAdapter.onTagClickListener() {
            @Override
            public void onTagClick(int i) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
    }
}
