package com.example.unchain.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unchain.Model.StoryModel;
import com.example.unchain.R;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.viewHolder> {

    ArrayList<StoryModel> list;
    Context context;

    public StoryAdapter(ArrayList<StoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_rv_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        StoryModel model = list.get(position);
        holder.storyImg.setImageResource(model.getStoryPic());
        holder.profileImg.setImageResource(model.getProfilePic());
        holder.storyType.setImageResource(model.getStoryType());
        holder.profileName.setText(model.getProfileName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView storyImg, profileImg, storyType;
        TextView profileName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            storyImg = itemView.findViewById(R.id.storyImg);
            profileImg = itemView.findViewById(R.id.profile_image_rv);
            storyType = itemView.findViewById(R.id.story_type);
            profileName = itemView.findViewById(R.id.profile_name_story);
        }
    }
}
