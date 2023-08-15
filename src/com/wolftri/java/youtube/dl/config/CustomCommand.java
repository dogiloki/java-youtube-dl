package com.wolftri.java.youtube.dl.config;

/**
 *
 * @author dogi_
 */

public enum CustomCommand{
    
    LIST_FORMAT("\"yt/yt-dlp.exe\" --dump-json {0}"),
    DOWNLOAD_FORMAT("\"yt/yt-dlp.exe\" -f {0} --output \"{1}/{2}\" {3}"),
    SEARCH_VIDEOS("\"yt/yt-dlp.exe\" \"ytsearch{0}:{1}\" --get-id --get-title --get-thumbnail --skip-download --playlist-start {2}");
    
    private final String text;
    
    private CustomCommand(String text){
        this.text=text;
    }

    public String toCommand(){
        return this.text;
    }
    
}
