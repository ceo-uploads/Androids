package com.example.unchain.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.unchain.Adapter.FriendAdapter;
import com.example.unchain.Model.FriendModel;
import com.example.unchain.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView my_friends;
    ImageView upload_cover_page, cover_photo;
    ArrayList<FriendModel> friendList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        my_friends = view.findViewById(R.id.friend_list);
        upload_cover_page = view.findViewById(R.id.upload_cp);
        cover_photo = view.findViewById(R.id.cover_photo);

        friendList = new ArrayList<>();
        friendList.add(new FriendModel(R.drawable.storytwo, "Adam Cole"));
        friendList.add(new FriendModel(R.drawable.storythree, "Andrew Adams"));
        friendList.add(new FriendModel(R.drawable.storyfour, "Chris Anderson Browler"));
        friendList.add(new FriendModel(R.drawable.storyfive, "Hygen Berg Hudson"));
        friendList.add(new FriendModel(R.drawable.storysix, "Einstein Fortran"));
        friendList.add(new FriendModel(R.drawable.storyone, "Elina Brion"));
        friendList.add(new FriendModel(R.drawable.profile, "The UnrealX"));
        friendList.add(new FriendModel(R.drawable.lionel_messi, "Lionel Messi"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        my_friends.setLayoutManager(linearLayoutManager);
        my_friends.setNestedScrollingEnabled(false);
        my_friends.setAdapter(new FriendAdapter(friendList, getContext()));

        upload_cover_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 11);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((data != null ? data.getData() : null) != null) {
            Uri uri = data.getData();
            cover_photo.setImageURI(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
