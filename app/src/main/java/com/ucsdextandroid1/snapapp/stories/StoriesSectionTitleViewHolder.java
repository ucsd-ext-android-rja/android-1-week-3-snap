package com.ucsdextandroid1.snapapp.stories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesSectionTitleViewHolder extends RecyclerView.ViewHolder {

    public static StoriesSectionTitleViewHolder inflate(ViewGroup parent) {
        return new StoriesSectionTitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_stories_title, parent, false));
    }

    private TextView titleView;

    private StoriesSectionTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.vst_title);
    }

    public void bind(String title) {
        if(getAdapterPosition() == 0)
            itemView.setBackgroundResource(R.drawable.card_background);
        else
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.white));

        titleView.setText(title);
    }

}
