package com.wolftri.java.youtube.dl.gui;

import com.google.gson.Gson;
import com.wolftri.java.youtube.dl.dto.SearchDTO;
import com.wolftri.java.youtube.dl.dto.VideoDTO;
import com.wolftri.java.youtube.dl.gui.views.VideoPanel;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import multitaks.Function;
import multitaks.Http;

/**
 *
 * @author dogi_
 */

public class MainFrame extends javax.swing.JFrame{
    
    private Http http=new Http("https://www.googleapis.com/youtube/v3");
    
    public MainFrame(){
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.scroll_panel_videos.getVerticalScrollBar().setUnitIncrement(15);
        this.http.getParams().put("key","AIzaSyC5tQRf8HmtkvdqNiQ7piaIZFJ_Nmw6ufY");
        this.http.getParams().put("part","snippet");
        this.http.getParams().put("maxResults",30);
    }
    
    public void loadVideos(){
        SearchDTO search=new SearchDTO();
        try{
            this.http.getParams().put("q",this.box_search.getText());
            String json=this.http.get("search").toString();
            search=new Gson().fromJson(json,SearchDTO.class);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        this.panel_videos.removeAll();
        this.scroll_panel_videos.updateUI();
        int width=250, height=400;
        int width_total=this.scroll_panel_videos.getWidth()-25;
        int columns_total=(int)(Math.floorDiv(width_total,width))-1;
        int x=0, y=0;
        int rows=0, count=0;
        for(VideoDTO video:search.videos){
            
            VideoPanel panel=new VideoPanel();
            
            panel.setBounds(x,y,width,height);
            
            panel.icon_image.setIcon(Function.generateIcon(
                    Function.createImageFromURL(video.snippet.thumbnails.get("default").get("url")),
                    250,
                    250
            ));
            panel.text_title.setText(video.snippet.title);
            
            if(x==0){
                rows++;
            }
            if(count<columns_total){
                x+=width;
                count++;
            }else{
                y+=height;
                x=0;
                count=0;
            }
            
            this.panel_videos.add(panel);
        }
        this.panel_videos.updateUI();
        this.panel_videos.setPreferredSize(Function.createDimencion(width_total,rows*height));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        box_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        scroll_panel_videos = new javax.swing.JScrollPane();
        panel_videos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        box_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                box_searchKeyReleased(evt);
            }
        });

        btn_search.setText("Buscar");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_videosLayout = new javax.swing.GroupLayout(panel_videos);
        panel_videos.setLayout(panel_videosLayout);
        panel_videosLayout.setHorizontalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        panel_videosLayout.setVerticalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        scroll_panel_videos.setViewportView(panel_videos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_panel_videos)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(box_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_panel_videos)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        this.loadVideos();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void box_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_box_searchKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.loadVideos();
        }
    }//GEN-LAST:event_box_searchKeyReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_search;
    private javax.swing.JButton btn_search;
    private javax.swing.JPanel panel_videos;
    private javax.swing.JScrollPane scroll_panel_videos;
    // End of variables declaration//GEN-END:variables
}
