package edu.northeastern.stickers.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserStickerHistory {
    private String userId;
    private String time;
    private String stickerId;
    private String stickerPath;

    public UserStickerHistory(String userId, String time, String stickerId, String stickerPath) {
        this.userId = userId;
        this.time = time;
        this.stickerId = stickerId;
        this.stickerPath = stickerPath;
    }


    public String getUserId() {
        return userId;
    }
    public String getTime() {
        return time;
    }
    public String getStickerId() {
        return stickerId;
    }
    public String getStickerPath(){return stickerPath;}


//    public List<StickerSentCount> getStickerSentCountList() {
//        return stickerSentCountList;
//    }


//    /**
//     * Class to fetch data in this user's sending sticker activity
//     */
//    public static class StickerSentCount {
//        private String stickerId;
//        private int sentCountNumber;
//
//        public StickerSentCount(java.lang.String stickerId, int sentCountNumber) {
//            this.stickerId = stickerId;
//            this.sentCountNumber = sentCountNumber;
//        }
//
//        public java.lang.String getStickerId() {
//            return stickerId;
//        }
//
//        public int getSentCountNumber() {
//            return sentCountNumber;
//        }
//    }
//
//    /**
//     * Class to fetch data in this user's receiving sticker activity
//     */
//    public static class StickerReceivedCount {
//        private String userIdSentSticker;
//        private String stickerId;
//        private String time;
//
//        public StickerReceivedCount(String userIdSentSticker, java.lang.String stickerId, java.lang.String time) {
//            this.userIdSentSticker = userIdSentSticker;
//            this.stickerId = stickerId;
//            this.time = time;
//        }
//
//        public String getUserIdSentSticker() {
//            return userIdSentSticker;
//        }
//
//        public String getStickerId() {
//            return stickerId;
//        }
//
//        public String getTime() {
//            return time;
//        }
//    }
}

