package com.wolftri.java.youtube.dl.dao;

import com.google.gson.annotations.Expose;
import multitaks.database.ModelDB;
import multitaks.database.annotations.Collect;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

@Collect(src="videos")
public class VideoDAO extends ModelDB{
    
    public static String BASE_SEARCH_URL="https://www.youtube.com/results?search_query={0}";
    public static String BASE_VIDEO_URL="https://www.youtube.com/watch?v={0}";
    public static String STORAGE_TEMP=Storage.getDir()+"/temp";
    
    @Expose
    public String url;
    
    @Expose
    public String title;
    
    @Expose
    public String duration;
    
    @Expose
    public String size;
    
    @Expose
    public String channel;
    
    @Expose
    public String format_id;
    
    @Expose
    public String storage;
    
    @Expose
    public String filename;
    
    @Expose
    public String created_at;
    
    @Expose
    public String updated_at;
    
    public VideoDAO(){
        
    }
    
    public String getSrc(){
        return this.storage+"/"+this.filename;
    }
    
}
