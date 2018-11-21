package com.example.uyen.demousergithub;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> mUsers;
    private LayoutInflater mInflater;

    public UserAdapter(List<User> users) {
        this.mUsers = users;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextId;
        private TextView mTextName;
        private TextView mFullName;
        private TextView mDes;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextId = itemView.findViewById(R.id.text_id);
            mTextName = itemView.findViewById(R.id.text_name);
            mFullName = itemView.findViewById(R.id.text_full_name);
            mDes = itemView.findViewById(R.id.text_des);
        }

        public void binData(User user){
            if(user == null) return;
            mTextId.setText(user.getId() + "");
            mTextName.setText(user.getName());
            mFullName.setText(user.getFullName());
            mDes.setText(user.getDes());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        View v = mInflater.inflate(R.layout.list_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binData(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }
}
