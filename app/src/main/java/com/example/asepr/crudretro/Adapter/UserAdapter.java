package com.example.asepr.crudretro.Adapter;

/**
 * Created by ASEPR on 3/22/2018.
 */
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asepr.crudretro.EditActivity;
import com.example.asepr.crudretro.Model.User;
import com.example.asepr.crudretro.R;

import java.util.List;

/**
 * Created by root on 2/3/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    List<User> mUserList;

    public UserAdapter(List<User> UserList) {
        mUserList = UserList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewId.setText("Id = " + mUserList.get(position).getId());
        holder.mTextViewName.setText("Name = " + mUserList.get(position).getName());
        holder.mTextViewBirthday.setText("Birthday = " + mUserList.get(position).getBirth_date());
        holder.mTextViewGender.setText("Gender = " + mUserList.get(position).getGender());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id", mUserList.get(position).getId());
                mIntent.putExtra("Name", mUserList.get(position).getName());
                mIntent.putExtra("Birthday", mUserList.get(position).getBirth_date());
                mIntent.putExtra("Gender", mUserList.get(position).getGender());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewName, mTextViewBirthday, mTextViewGender;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = (TextView) itemView.findViewById(R.id.tvId);
            mTextViewName = (TextView) itemView.findViewById(R.id.tvName);
            mTextViewBirthday = (TextView) itemView.findViewById(R.id.tvBirthday);
            mTextViewGender = (TextView) itemView.findViewById(R.id.tvGender);
        }
    }
}
