package config;

/**
 *
 * @author dogi_
 */

public enum CustomFile{
    
    LIST_FORMAT("meta/list_format.bat");
    
    private final String src;
    
    private CustomFile(String src){
        this.src=src;
    }
    
    public String getSrc(){
        return src;
    }
    
}
