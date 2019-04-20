package com.ucsdextandroid1.snapapp.stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.DataSources;
import com.ucsdextandroid1.snapapp.R;
import com.ucsdextandroid1.snapapp.util.WindowUtil;

import java.util.List;

/**
 * Created by rjaylward on 4/15/19
 */
public class StoriesFragment extends Fragment {

    public static StoriesFragment create() {
        return new StoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_story, container, false);

        View background = root.findViewById(R.id.fs_background);
        RecyclerView recyclerView = root.findViewById(R.id.fs_recycler_view);
        recyclerView.setClipToPadding(false);

        // this just adds padding to top of the views so they are not drawn under the status bar
        WindowUtil.doOnApplyWindowInsetsToMargins(background, true, false);
        WindowUtil.doOnApplyWindowInsetsToPadding(recyclerView, true, true);

        StoriesAdapter adapter = new StoriesAdapter();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getSpanForType(position);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setCallback(new StoriesAdapter.Callback() {
            @Override
            public void onStoryCardClicked(Story story) {
                Toast.makeText(getContext(), story.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        DataSources.getInstance().getStoryCards(new DataSources.Callback<List<Story>>() {
            @Override
            public void onDataFetched(List<Story> data) {
                adapter.setItems(getContext(), data);
            }
        });

        return root;
    }

}
