package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    private List<StoriesListItem> items = new ArrayList<>();

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        //TODO add title item, using context.getString(R.string.stories)) to get the title

        //TODO add all of the story items to the list

        //TODO let the adapter know that  the data has changed
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO return the correct view holder for each viewType
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO bind the title or the story to the correct view holder
    }

    @Override
    public int getItemCount() {
        // TODO return the correct item count
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO return the correct view type
        return 0;
    }

    //TODO add a method that returns the correct span for each item type.

    //TODO add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder

    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_CARD = 2;

    }

}
