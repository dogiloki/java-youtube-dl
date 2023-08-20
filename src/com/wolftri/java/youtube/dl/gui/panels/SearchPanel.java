package com.wolftri.java.youtube.dl.gui.panels;

import com.wolftri.java.youtube.dl.config.CustomCommand;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import multitaks.Function;
import multitaks.persistent.ExecutionObserver;

/**
 *
 * @author dogi_
 */

public class SearchPanel extends javax.swing.JPanel {

    private Frame parent;
    private ExecutionObserver exec_search=null;
    private int width, height;
    private int width_total;
    private int columns_total;
    private int x, y;
    private int rows, columns, count=0;
    private String title, id, thumbnails;
    
    public SearchPanel(Frame parent){
        initComponents();
        this.parent=parent;
    }
    
    public void loadVideos(){
        if(this.exec_search!=null){
            this.exec_search.cancel();
        }
        
        this.panel_videos.removeAll();
        this.scroll_panel_videos.updateUI();
        
        this.width=250;
        this.height=400;
        this.x=0;
        this.y=0;
        this.rows=0;
        this.columns=0;
        this.width_total=this.scroll_panel_videos.getWidth()-25;
        this.columns_total=(int)(Math.floorDiv(width_total,width))-1;
        
        try{
            String command=MessageFormat.format(CustomCommand.SEARCH_VIDEOS.toCommand(),"50",this.box_search.getText(),"1");
            this.exec_search=ExecutionObserver.execution(command);
            this.exec_search.start((line,posi)->{
                switch(posi%3){
                    case 0: title=line; break;
                    case 1: id=line; break;
                    case 2:{
                        thumbnails=line;
                        this.loadVideo();
                        break;
                    }
                }
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void loadVideo(){
        SearchVideoPanel panel=new SearchVideoPanel(this.parent,this.title,this.id,this.thumbnails);
        panel.setBounds(x,y,width,height);
        if(x==0){
            rows++;
        }
        if(columns<columns_total){
            x+=width;
            columns++;
        }else{
            y+=height;
            x=0;
            columns=0;
        }
        this.count++;
        
        this.panel_videos.add(panel);
        this.panel_videos.updateUI();
        this.panel_videos.setPreferredSize(Function.createDimencion(this.width_total,this.rows*this.height));
        this.title=null;
        this.id=null;
        this.thumbnails=null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        box_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        scroll_panel_videos = new javax.swing.JScrollPane();
        panel_videos = new javax.swing.JPanel();

        box_search.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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
            .addGap(0, 373, Short.MAX_VALUE)
        );
        panel_videosLayout.setVerticalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        scroll_panel_videos.setViewportView(panel_videos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_panel_videos, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
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
                .addComponent(scroll_panel_videos, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        this.loadVideos();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void box_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_box_searchKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.loadVideos();
        }
    }//GEN-LAST:event_box_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_search;
    private javax.swing.JButton btn_search;
    private javax.swing.JPanel panel_videos;
    private javax.swing.JScrollPane scroll_panel_videos;
    // End of variables declaration//GEN-END:variables
}
