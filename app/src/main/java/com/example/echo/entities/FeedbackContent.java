package com.example.echo.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FeedbackContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyFeedbackItem> ITEMS = new ArrayList<DummyFeedbackItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyFeedbackItem> ITEM_MAP = new HashMap<String, DummyFeedbackItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyFeedbackItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyFeedbackItem createDummyItem(int position) {
        return new DummyFeedbackItem(String.valueOf(position),"",
                makeDetails(position),
                makeDate());
    }

    private static String makeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyFeedbackItem {
        public final String id;
        public final String imageUrl;
        public final String content;
        public final String date;

        public DummyFeedbackItem(String id, String imageUrl, String content, String date) {
            this.id = id;
            this.imageUrl = imageUrl;
            this.content = content;
            this.date = date;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
