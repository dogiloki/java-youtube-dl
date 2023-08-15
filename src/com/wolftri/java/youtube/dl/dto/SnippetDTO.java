package com.wolftri.java.youtube.dl.dto;

import java.util.Map;

/**
 *
 * @author dogi_
 */

public class SnippetDTO{
    
    public String publishedAt;
    public String channelId;
    public String title;
    public String description;
    public Map<String,Map<String,String>> thumbnails;
    public String channelTitle;
    public String liveBroadcastContent;
    public String publishTime;
    
    public SnippetDTO(){
        
    }
    
}
