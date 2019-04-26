package com.ucsdextandroid1.snapapp.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

        // TODO added during class 3:
        // add our custom click listener to adapter
        adapter.setOnItemClickCallback(new ChatItemViewHolder.ChatClickListener() {
            @Override
            public void onChatItemClick(Chat chat) {
                // since we passed the currentChat to our listener we have reference to it here
                // and we can do whatever we want with it. In a real app we might open up your
                // current conversation with that person.
                onItemClick(chat);
            }

            @Override
            public void onChatItemLongClick(Chat chat) {
                Toast.makeText(getContext(), "Long Click on " + chat.getFromName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        DataSources.getInstance().getChatItems(new DataSources.Callback<List<Chat>>() {
            @Override
            public void onDataFetched(List<Chat> data) {
                adapter.setItems(data);
            }
        });

        return root;
    }

    private void onItemClick(Chat chat) {
        // Toast just creates a little popup message at the bottom of the screen.
        Toast.makeText(getContext(), chat.getFromName(), Toast.LENGTH_SHORT).show();

        // here is an example of why we can't always just add the code to a click listener inside
        // the view holder. Sometimes we want to do something that requires the activity or fragment
        // and we don't have access to those thing inside the viewHolder. Here we are changing the
        // title of the toolbar to the last chat that was clicked on.
        //
        // Note: We could do things the other way, instead of passing the click event all the way
        // out to the adapter, we could pass the Activity or Fragment in to the adapter and the
        // viewHolder. If we passed the activity all the way to our view holder we could call the
        // same code in our normal itemView.setOnClickListener(v -> { ... }) and we wouldn't need
        // to define a custom interface. This is less re-usable though. If we wanted to have a
        // different screen that showed the same list but where something different happened when
        // an item was clicked you could not do it without changing the code.
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(chat.getFromName());
    }

}
