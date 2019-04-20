package com.ucsdextandroid1.snapapp.stories;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoryCardViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView titleView;
    private TextView subtitleView;

    private Story currentStory;
    private StoryCardClickListener listener;

    //TODO add a static method called inflate() that inflates the layout view_story_card

    public StoryCardViewHolder(@NonNull View itemView) {
        super(itemView);
        //TODO find all of the views

        //TODO add a click listener to the itemView that calls the custom click listener
    }

    public void bind(Story story) {
        //TODO set the currentStory

        //TODO load the imageUrl into the imageView using Picasso

        //TODO set the title and the subtitle
    }

    //TODO add a method to set a StoryCardClickListener to this class

    public interface StoryCardClickListener {
        // TODO add a method to be called when the user clicks the card view
    }

}
