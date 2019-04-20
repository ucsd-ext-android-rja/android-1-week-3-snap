package com.ucsdextandroid1.snapapp.chat;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.DataSources;
import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.util.WindowUtil;

import java.util.List;

/**
 * Created by rjaylward on 4/15/19
 */
public class ChatFragment extends Fragment {

    public static ChatFragment create() {
        return new ChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        View background = root.findViewById(R.id.fc_background);
        RecyclerView recyclerView = root.findViewById(R.id.fc_recycler_view);

        WindowUtil.doOnApplyWindowInsetsToMargins(background, true, false);
        WindowUtil.doOnApplyWindowInsetsToPadding(recyclerView, true, true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setClipToPadding(false);

        ChatAdapter adapter = new ChatAdapter();
        recyclerView.setAdapter(adapter);

        //TODO add click listener to adapter

        DataSources.getInstance().getChatItems(new DataSources.Callback<List<Chat>>() {
            @Override
            public void onDataFetched(List<Chat> data) {
                adapter.setItems(data);
            }
        });

        return root;
    }

    private void onItemClick(Chat chat) {
        Toast.makeText(getContext(), chat.getFromName(), Toast.LENGTH_SHORT).show();
    }

}
