package com.ucsdextandroid1.snapapp.chat;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class ChatAdapter extends RecyclerView.Adapter {

    private List<Chat> items = new ArrayList<>();

    // reference to the current click listener
    private ChatItemViewHolder.ChatClickListener listener;

    public void setItems(List<Chat> chats) {
        this.items.clear();
        this.items.addAll(chats);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemViewHolder viewHolder = ChatItemViewHolder.inflate(parent);
        // TODO added during class 3:
        // we add our click listener to our view holder after it is created because it is the same for every item
        viewHolder.setOnChatItemClickCallback(listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ChatItemViewHolder) {
            ((ChatItemViewHolder) holder).bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    // TODO added during class 3:
    // a method to set our custom click listener
    public void setOnItemClickCallback(ChatItemViewHolder.ChatClickListener listener) {
        this.listener = listener;
    }

}
