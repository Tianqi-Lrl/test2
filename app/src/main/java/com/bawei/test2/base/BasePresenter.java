package com.bawei.test2.base;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public abstract class BasePresenter<V> {
    protected V view;
    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void attach(V view) {
        this.view = view;
    }
    public void dttach() {
        view = null;
    }
}
