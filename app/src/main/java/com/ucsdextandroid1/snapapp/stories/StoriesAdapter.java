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
    private Callback callback;

    public void setItems(Context context, List<Story> stories) {
        items.clear();
        items.add(new StoriesListItem(context.getString(R.string.stories)));

        for(Story story : stories) {
            items.add(new StoriesListItem(story));
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType) {
            case StoriesListItem.TYPE_CARD:
                StoryCardViewHolder storyCardViewHolder = StoryCardViewHolder.inflate(parent);
                storyCardViewHolder.setClickListener(defaultCallback);
                return storyCardViewHolder;
            case StoriesListItem.TYPE_TITLE:
                return StoriesSectionTitleViewHolder.inflate(parent);
            default:
                throw new IllegalArgumentException("Unknown viewType " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StoriesSectionTitleViewHolder) {
            ((StoriesSectionTitleViewHolder) holder).bind(items.get(position).getTitle());
        }
        else if(holder instanceof StoryCardViewHolder) {
            ((StoryCardViewHolder) holder).bind(items.get(position).getStory());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public int getSpanForType(int position) {
        switch(getItemViewType(position)) {
            case StoriesListItem.TYPE_CARD:
                return 1;
            case StoriesListItem.TYPE_TITLE:
            default:
                return 2;
        }
    }

    public interface Callback extends StoryCardViewHolder.StoryCardClickListener {

    }

    private Callback defaultCallback = new Callback() {
        @Override
        public void onStoryCardClicked(Story story) {
            if(callback != null)
                callback.onStoryCardClicked(story);
        }
    };

    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_CARD = 2;

        private final int type;
        private final String title;
        private final Story story;

        public StoriesListItem(String title) {
            this.title = title;
            this.story = null;
            this.type = TYPE_TITLE;
        }

        public StoriesListItem(Story story) {
            this.title = null;
            this.story = story;
            this.type = TYPE_CARD;
        }

        public String getTitle() {
            return title;
        }

        public Story getStory() {
            return story;
        }

        public int getType() {
            return type;
        }
    }

}
