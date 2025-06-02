package com.example.unchain.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unchain.Adapter.NotificationAdapter;
import com.example.unchain.Model.NotificationModel;
import com.example.unchain.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    RecyclerView notifications;
    ArrayList<NotificationModel> list;
    NotificationAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        notifications = view.findViewById(R.id.notifications);

        list = new ArrayList<>();
        list.add(new NotificationModel(R.drawable.storyone, "<b>Sudeep Sengupta</b> <i>sent you a friend request.</i>", "just now"));
        list.add(new NotificationModel(R.drawable.storyone, "<b>Brian Shaw</b> <i>sent you a friend request.</i>", "1m"));
        list.add(new NotificationModel(R.drawable.storythree, "<b>Sneha Smali</b> <i> mentioned you in a comment.</i>", "just now"));

        adapter = new NotificationAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        notifications.setLayoutManager(linearLayoutManager);
        notifications.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}