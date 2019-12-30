package com.bawei.test2.model;

import com.bawei.test2.contract.IHomeContrat;
import com.bawei.test2.model.bean.Bean;
import com.bawei.test2.util.NetUtil;
import com.google.gson.Gson;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public class HomeModel implements IHomeContrat.IModel {
    @Override
    public void getHomeData(IModelCallBack iModelCallBack) {
        String httpUrl="http://blog.zhaoliang5156.cn/api/shop/fulishe1.json";
        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallBack() {
            @Override
            public void onJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                iModelCallBack.onHomeSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallBack.onHomeFailure(throwable);
            }
        });
    }
}
