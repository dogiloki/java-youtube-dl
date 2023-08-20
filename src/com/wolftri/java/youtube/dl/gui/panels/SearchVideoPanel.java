package com.wolftri.java.youtube.dl.gui.panels;

import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.gui.MainFrame;
import com.wolftri.java.youtube.dl.gui.VideoDialog;
import java.awt.Frame;
import java.text.MessageFormat;
import multitaks.Function;

/**
 *
 * @author dogi_
 */

public class SearchVideoPanel extends javax.swing.JPanel{
    
    private Frame frame;
    private String url;
    
    public SearchVideoPanel(Frame frame, String title, String id, String thumbnails){
        initComponents();
        if(frame==null){
            return;
        }
        this.frame=frame;
        try{
            this.icon_image.setIcon(Function.generateIcon(
                    Function.createImageFromURL(thumbnails),
                    250,
                    250
            ));
        }catch(Exception ex){
            
        }
        this.url=MessageFormat.format(VideoDAO.BASE_VIDEO_URL,id);
        this.text_title.setText(title);
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
        new VideoDialog(this.frame,true,this.url).setVisible(true);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon_image;
    private javax.swing.JLabel text_title;
    // End of variables declaration//GEN-END:variables
}
