package com.ucsdextandroid1.snapapp.stories;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.util.ColorUtil;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoryCardViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView titleView;
    private TextView subtitleView;

    private Story currentStory;
    private StoryCardClickListener listener;

    public static StoryCardViewHolder inflate(ViewGroup parent) {
        return new StoryCardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_story_card, parent, false));
    }

    public StoryCardViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.vsc_image_view);
        titleView = itemView.findViewById(R.id.vsc_title);
        subtitleView = itemView.findViewById(R.id.vsc_subtitle);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null && currentStory != null)
                    listener.onStoryCardClicked(currentStory);
            }
        });
    }

    public void bind(Story story) {
        currentStory = story;

        Picasso.get().load(story.getImageUrl())
                .placeholder(new ColorDrawable(ColorUtil.getRandomColor()))
                .into(imageView);

        titleView.setText(story.getTitle());
        subtitleView.setText(story.getSubtitle());
    }

    public void setClickListener(StoryCardClickListener listener) {
        this.listener = listener;
    }

    public interface StoryCardClickListener {
        void onStoryCardClicked(Story story);
    }

}
