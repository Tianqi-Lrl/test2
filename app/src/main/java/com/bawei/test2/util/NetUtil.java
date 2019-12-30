package com.bawei.test2.util;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.test2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil == null) {
            synchronized (NetUtil.class){
                if (netUtil == null) {
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }

    public void getJsonGet(String httpUrl,MyCallBack myCallBack){
        Request build = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();

        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null&&response.isSuccessful()) {
                    String s = response.body().string().trim();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            myCallBack.onJson(s);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("错误"));
                        }
                    });
                }
            }
        });
    }
    public void getJsonPost(String httpUrl, Map<String,String> map,MyCallBack myCallBack){
        FormBody.Builder builder = new FormBody.Builder();

        for (String key:map.keySet()) {
            String value = map.get(key);
            builder.add(key,value);
        }

        FormBody build1 = builder.build();

        Request build = new Request.Builder()
                .post(build1)
                .url(httpUrl)
                .build();

        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null&&response.isSuccessful()) {
                    String s = response.body().string().trim();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            myCallBack.onJson(s);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("错误"));
                        }
                    });
                }
            }
        });
    }

    public void getPhoto(String photoUrl, ImageView imageView){

        Glide.with(imageView).load(photoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(imageView);

    }




    public interface MyCallBack{
        void onJson(String json);
        void onError(Throwable throwable);
    }
}
