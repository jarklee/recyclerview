/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. Dotohsoft.com. All right reserved
 *  Author TrinhQuan. Create on 2016/10/31
 * ******************************************************************************
 */

package com.skeleton.faker.views.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skeleton.faker.R;
import com.skeleton.faker.views.adapters.MainAdapter;
import com.skeleton.faker.views.base.BaseActivity;
import com.tq.libs.recyclerview.expandable.ExpandableRecyclerViewModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMVPView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    MainPresenter presenter;

    @Inject
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();
        presenter.attachView(this);
        presenter.loadData();
    }

    private void setupView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }


    private Handler handler = new Handler();

    @Override
    public void updateView(final ExpandableRecyclerViewModule module) {
        adapter.setRecyclerViewModule(module);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                module.expandGroup(1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        module.removeGroup(0);
                    }
                }, 3000);
            }
        }, 3000);
    }
}
