package com.example.jubin.retrosample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jubin on 2/10/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User.ItemsBean> Itembean;

    public UserAdapter(List<User.ItemsBean> itembean) {
        Itembean = itembean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_row,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(Itembean.get(position).getAvatar_url());
        holder.score.setText(Itembean.get(position).getScore()+"");//value is double so to convert to string

    }

    @Override
    public int getItemCount() {
        return Itembean.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title,score;

        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.texxtView1);
            score=(TextView)itemView.findViewById(R.id.texxtView2);
        }
    }
}
