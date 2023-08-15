package com.wolftri.java.youtube.dl.gui.views;

import com.wolftri.java.youtube.dl.dto.VideoDTO;
import com.wolftri.java.youtube.dl.gui.MainFrame;
import com.wolftri.java.youtube.dl.gui.VideoDialog;
import java.awt.Frame;
import multitaks.Function;

/**
 *
 * @author dogi_
 */

public class VideoPanel extends javax.swing.JPanel{
    
    private VideoDTO video;
    private Frame frame;
    
    public VideoPanel(Frame frame, VideoDTO video){
        initComponents();
        if(video==null || frame==null){
            return;
        }
        this.frame=frame;
        this.video=video;
        this.icon_image.setIcon(Function.generateIcon(
                    Function.createImageFromURL(video.snippet.thumbnails.get("default").get("url")),
                    250,
                    250
            ));
        this.text_title.setText(video.snippet.title);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon_image = new javax.swing.JLabel();
        text_title = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        icon_image.setText("jLabel1");

        text_title.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(text_title, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_image, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_title)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        new VideoDialog(this.frame,true,this.video).setVisible(true);
        ((MainFrame)this.frame).getVideos();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon_image;
    private javax.swing.JLabel text_title;
    // End of variables declaration//GEN-END:variables
}
