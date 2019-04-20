package com.ucsdextandroid1.snapapp.chat;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * Created by rjaylward on 2019-04-20
 */
public class Chat {

    private String fromName;
    private Chat.State state;
    private String emoji;
    @ColorInt private int color;

    public Chat(String fromName, Chat.State state, @Nullable String emoji, @ColorInt int color) {
        if(emoji != null && emoji.length() != 1)
            throw new IllegalArgumentException("Emoji must be a single character");

        this.fromName = fromName;
        this.state = state;
        this.emoji = emoji;
        this.color = color;
    }

    public enum State {
        SENT, RECEIVED, OPENED
    }

    public String getFromName() {
        return fromName;
    }

    public State getState() {
        return state;
    }

    public String getEmoji() {
        return emoji;
    }

    @ColorInt public int getColor() {
        return color;
    }

}
