package com.example.unchain.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unchain.Adapter.PostAdapter;
import com.example.unchain.Adapter.StoryAdapter;
import com.example.unchain.Model.PostModel;
import com.example.unchain.Model.StoryModel;
import com.example.unchain.R;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView stories;
    RecyclerView posts;
    ArrayList<StoryModel> storyList;
    ArrayList<PostModel> postList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        stories = view.findViewById(R.id.stories);
        posts = view.findViewById(R.id.posts);

        storyList = new ArrayList<>();
        storyList.add(new StoryModel(R.drawable.storyone, R.drawable.profile, R.drawable.live_icon, "Adam Cole"));
        storyList.add(new StoryModel(R.drawable.profile, R.drawable.profile, R.drawable.live_icon, "Unreal_Studio"));
        storyList.add(new StoryModel(R.drawable.storytwo, R.drawable.profile, R.drawable.live_icon, "Pure Nuts"));
        storyList.add(new StoryModel(R.drawable.storythree, R.drawable.profile, R.drawable.live_icon, "Pure Nuts"));
        storyList.add(new StoryModel(R.drawable.storyfour, R.drawable.profile, R.drawable.live_icon, "Pure Nuts"));
        storyList.add(new StoryModel(R.drawable.storyfive, R.drawable.profile, R.drawable.live_icon, "Pure Nuts"));
        storyList.add(new StoryModel(R.drawable.storysix, R.drawable.profile, R.drawable.live_icon, "Pure Nuts"));


        StoryAdapter storyAdapter = new StoryAdapter(storyList, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        stories.setLayoutManager(linearLayoutManager);
        stories.setNestedScrollingEnabled(false);
        stories.setAdapter(storyAdapter);


        postList = new ArrayList<>();
        postList.add(new PostModel(R.drawable.profile, R.drawable.profile, "Unreal Studio", "1h", "121K", "4k", "131"));
        postList.add(new PostModel(R.drawable.storyfour, R.drawable.lionel_messi, "Messi", "5h", "100M", "100k", "20k"));

        PostAdapter postAdapter = new PostAdapter(postList, getContext());

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        posts.setLayoutManager(linearLayoutManager);
        posts.setNestedScrollingEnabled(false);
        posts.setAdapter(postAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}