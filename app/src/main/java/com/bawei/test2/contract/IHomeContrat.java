package com.bawei.test2.contract;

import com.bawei.test2.model.bean.Bean;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public interface IHomeContrat {

    interface IView{
        void onHomeSuccess(Bean bean);
        void onHomeFailure(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData();
    }

    interface IModel{
        void getHomeData(IModelCallBack iModelCallBack);
        interface IModelCallBack{
            void onHomeSuccess(Bean bean);
            void onHomeFailure(Throwable throwable);
        }
    }

}
