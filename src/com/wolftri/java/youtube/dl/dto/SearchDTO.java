package com.wolftri.java.youtube.dl.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author dogi_
 */

public class SearchDTO{
    
    public String kind;
    public String etag;
    public String nextPageToken;
    public String regionCode;
    public PageInfoDTO pageInfo;
    @SerializedName("items")
    public List<VideoDTO> videos=new ArrayList<>();
    
    public SearchDTO(){
        
    }
    
}
