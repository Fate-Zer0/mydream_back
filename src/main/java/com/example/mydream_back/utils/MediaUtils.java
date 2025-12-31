package com.example.mydream_back.utils;

import java.util.Set;

public class MediaUtils {
    private static final Set<String> VIDEO_EXTENSIONS = Set.of(
            "mp4", "mkv", "avi", "mov", "wmv", "flv", "webm", "m4v", "ts", "mpg", "mpeg"
    );

    private static final Set<String> AUDIO_EXTENSIONS = Set.of(
            "mp3", "wav", "flac", "aac", "ogg", "m4a", "wma", "opus"
    );

    public static boolean isMediaFile(String filename) {
        if (filename == null || filename.isEmpty()) return false;
        String ext = getFileExtension(filename).toLowerCase();
        return VIDEO_EXTENSIONS.contains(ext) || AUDIO_EXTENSIONS.contains(ext);
    }

    private static String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0 && lastDot < filename.length() - 1) {
            return filename.substring(lastDot + 1);
        }
        return "";
    }
}