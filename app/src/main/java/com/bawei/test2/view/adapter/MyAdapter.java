package com.bawei.test2.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.test2.R;
import com.bawei.test2.model.bean.Bean;
import com.bawei.test2.util.NetUtil;

import java.util.List;

/**
 * Time:2019/12/29
 * Author:天祈Aa
 * Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Bean.DataBean> data;

    public MyAdapter(List<Bean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.show, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Bean.DataBean dataBean = data.get(position);
        holder.tv.setText(dataBean.getGoods_name());
        NetUtil.getInstance().getPhoto(dataBean.getGoods_thumb(),holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(position+1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    onTagClickListener onTagClickListener;

    public void setOnTagClickListener(MyAdapter.onTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface onTagClickListener{
        void onTagClick(int i);
    }
}
