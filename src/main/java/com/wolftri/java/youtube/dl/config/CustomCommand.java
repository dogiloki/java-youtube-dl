package com.wolftri.java.youtube.dl.config;

/**
 *
 * @author dogi_
 */

public enum CustomCommand{
    
    LIST_FORMAT("\"yt/yt-dlp.exe\" --dump-json {0}"),
    DOWNLOAD_FORMAT("\"yt/yt-dlp.exe\" -f {0} --output \"{1}/{2}\" {3}");
    
    private final String text;
    
    private CustomCommand(String text){
        this.text=text;
    }

    public String toCommand(){
        return this.text;
    }
    
}