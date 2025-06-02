package com.example.unchain.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unchain.Model.FriendModel;
import com.example.unchain.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.viewholder> {

    ArrayList<FriendModel> list;
    Context context;

    public FriendAdapter(ArrayList<FriendModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friends_rv_sample, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        FriendModel model = list.get(position);
        holder.profile_pic.setImageResource(model.getFriend_profilePic());
        holder.friend_name.setText(model.getFriendName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CircleImageView profile_pic;
        TextView friend_name;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            friend_name = itemView.findViewById(R.id.friend_name);
        }
    }
}
