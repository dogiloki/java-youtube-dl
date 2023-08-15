/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wolftri.java.youtube.dl.gui.views;

import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author dogi_
 */
public class Browser extends JFXPanel{
 
    private WebEngine engine;
    
    public Browser(){
        Platform.runLater(new Runnable(){
           @Override
           public void run(){
               WebView view=new WebView();
               engine=view.getEngine();
               setScene(new Scene(view));
           }
        });
        setVisible(true);
    }
    
    public void loadURL(final String url){
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                String tmp=toURL(url);
                if(tmp==null){
                    tmp=toURL(url);
                }
                engine.load(tmp);
            }
        });
    }
    
    public String toURL(String str){
        try{
            return new URL(str).toExternalForm();
        }catch(MalformedURLException ex){
            return null;
        }
    }
    
}
