package com.example.unchain.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unchain.Model.PostModel;
import com.example.unchain.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    ArrayList<PostModel> list;
    Context context;

    public PostAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        PostModel model = list.get(position);

        holder.profilePic.setImageResource(model.getProfilePic());
        holder.postImg.setImageResource(model.getPostImg());
        holder.profileName.setText(model.getProfileName());
        holder.time.setText(model.getTime());
        holder.like.setText(model.getLike());
        holder.comment.setText(model.getComment());
        holder.share.setText(model.getShare());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic, postImg;
        TextView profileName, time, like, comment, share;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = itemView.findViewById(R.id.profile_image_rv);
            postImg = itemView.findViewById(R.id.postImage);
            profileName = itemView.findViewById(R.id.profile_name_notf);
            time = itemView.findViewById(R.id.upload_time);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            share = itemView.findViewById(R.id.share);
        }
    }
}
