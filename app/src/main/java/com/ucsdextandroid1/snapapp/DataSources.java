package com.ucsdextandroid1.snapapp;

import com.ucsdextandroid1.snapapp.chat.Chat;
import com.ucsdextandroid1.snapapp.stories.Story;
import com.ucsdextandroid1.snapapp.util.ColorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class DataSources {

    private static DataSources instance;

    public static DataSources getInstance() {
        if(instance == null)
            instance = new DataSources();

        return instance;
    }

    public void getChatItems(Callback<List<Chat>> callback) {
        List<Chat> chats = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            Chat.State state;

            switch(i % 3) {
                case 0:
                    state = Chat.State.RECEIVED;
                    break;
                case 1:
                    state = Chat.State.OPENED;
                    break;
                case 2:
                default:
                    state = Chat.State.SENT;
                    break;
            }

            chats.add(new Chat("User " + i, state, null, ColorUtil.getRandomColor()));
        }

        callback.onDataFetched(chats);
    }

    public void getStoryCards(Callback<List<Story>> callback) {
        callback.onDataFetched(Arrays.asList(
                new Story(
                        "Space",
                        "space is neat",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_1.jpg",
                        null
                ),new Story(
                        "More Space",
                        "space is cold",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_3.jpg",
                        null
                ),
                new Story(
                        "Some Space",
                        "space is fun",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_10.jpg",
                        null
                ),
                new Story(
                        "Other Space",
                        "space is awesome",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_5.jpg",
                        null
                ),
                new Story(
                        "Spaceish",
                        "space is cool",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_6.jpg",
                        null
                ),
                new Story(
                        "Space Space",
                        "space is great",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_7.jpg",
                        null
                ),
                new Story(
                        "Big Space",
                        "space is neat",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_8.jpg",
                        null
                ),
                new Story(
                        "Far Space",
                        "space is sweet",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_9.jpg",
                        null
                )
        ));
    }

    public interface Callback<T> {
        void onDataFetched(T data);
    }
}
