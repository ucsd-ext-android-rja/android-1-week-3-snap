package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    //TODO The first thing your should do is finish the StoriesListItem class at the bottom

    private List<StoriesListItem> items = new ArrayList<>();

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        //TODO add title item to our list of StoriesListItems
        //hint, you can use using context.getString(R.string.stories)) to get a String

        //TODO add all of the story items to the list of StoriesListItems

        //TODO let the adapter know that  the data has changed
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO return the correct view holder for each viewType. We want to return the
        // StoriesSectionTitleViewHolder for our title and the StoryCardViewHolder for our items.
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

    //TODO add a method that returns the correct span for each item type. It should take in the
    // int position and return an int representing either 1 or 2 depending on if the item is a title
    // or a story card item.

    //TODO add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder

    //TODO finish creating a class that holds both the story and the title
    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_STORY = 2;

        // you will need to add 2 constructors, one that takes in a String title, and another that
        // takes in a Story story. We need this data class to represent all the possibilities for
        // our list.

    }

}
