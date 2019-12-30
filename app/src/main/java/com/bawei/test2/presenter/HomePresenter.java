package com.bawei.test2.presenter;

import com.bawei.test2.base.BasePresenter;
import com.bawei.test2.contract.IHomeContrat;
import com.bawei.test2.model.HomeModel;
import com.bawei.test2.model.bean.Bean;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public class HomePresenter extends BasePresenter<IHomeContrat.IView> implements IHomeContrat.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContrat.IModel.IModelCallBack() {
            @Override
            public void onHomeSuccess(Bean bean) {
                view.onHomeSuccess(bean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }
}
