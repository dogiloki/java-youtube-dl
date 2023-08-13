package com.wolftri.java.youtube.dl.dto;

import java.util.Map;

/**
 *
 * @author dogi_
 */

public class VideoDTO{
    
    public String kind;
    public String etag;
    public Map<String,String> id;
    public SnippetDTO snippet;
    
    public VideoDTO(){
        
    }
    
}
