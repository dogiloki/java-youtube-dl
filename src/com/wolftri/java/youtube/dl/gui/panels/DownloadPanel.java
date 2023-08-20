package com.wolftri.java.youtube.dl.gui.panels;

import com.wolftri.java.youtube.dl.config.CustomCommand;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.gui.MainFrame;
import com.wolftri.java.youtube.dl.gui.VideoDialog;
import java.awt.Font;
import java.awt.Frame;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import multitaks.database.Cursor;
import multitaks.database.ModelDB;
import multitaks.directory.Storage;
import multitaks.persistent.ExecutionObserver;

/**
 *
 * @author dogi_
 */

public class DownloadPanel extends javax.swing.JPanel{
    
    private Frame parent;
    private Map<Integer,VideoDAO> videos=new HashMap<>();
    private Map<Integer,ExecutionObserver> downloads=new HashMap<>();
    
    public DownloadPanel(Frame parent){
        initComponents();
        this.parent=parent;
        this.table_videos.setRowHeight(160);
        this.table_videos.setFont(new Font("Arial",Font.PLAIN,18));
        this.loadVideos();
    }
    
    public void loadVideos(){
        boolean show_downloaded=this.check_videos_download.isSelected();
        this.videos.clear();
        Cursor<VideoDAO> cursor=ModelDB.all(VideoDAO.class);
        VideoDAO video;
        DefaultTableModel model_videos=new DefaultTableModel();
        model_videos.addColumn("VIDEOS");
        model_videos.addColumn("DESCARGANDO");
        int index=0;
        while((video=cursor.next())!=null){
            boolean downloaded=false;
            if(Storage.exists(video.getSrc())){
                if(show_downloaded){
                    downloaded=true;
                }else{
                    continue;
                }
            }
            String str="<html>";
            str+=video.format_id+"<br>";
            str+=video.channel+"<br>";
            str+=video.title+"<br>";
            str+=video.duration+"<br>";
            str+=video.created_at+"<br>";
            str+=video.storage+"</html>";
            this.videos.put(index,video);
            model_videos.addRow(new Object[]{str,downloaded?"Descargado":""});
            index++;
        }
        this.table_videos.setModel(model_videos);
    }
    
    public int[] getRowsSelected(){
        return this.table_videos.getSelectedRows();
    }
    
    public void selectionVideos(MainFrame.Callback action){
        for(int row:this.getRowsSelected()){
            action.selectedVideos(this.videos.get(row),row);
        }
        this.loadVideos();
    }
    public void selectionVideo(MainFrame.Callback action){
        int row=this.table_videos.getSelectedRow();
        if(row==-1){
            return;
        }
        action.selectedVideos(this.videos.get(row),row);
        this.loadVideos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_delete = new javax.swing.JButton();
        btn_open_folder = new javax.swing.JButton();
        btn_add_video = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_download = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_videos = new javax.swing.JTable();
        check_videos_download = new javax.swing.JCheckBox();
        btn_delete_all = new javax.swing.JButton();

        btn_delete.setText("Eliminar");
        btn_delete.setToolTipText("No elimina  el archivo, únicamente el registro en esta ventana");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_open_folder.setText("Abrir carpeta");
        btn_open_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_open_folderActionPerformed(evt);
            }
        });

        btn_add_video.setText("Añadir video");
        btn_add_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_videoActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cacelar");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_download.setText("Descargar en este dispositivo");
        btn_download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_downloadActionPerformed(evt);
            }
        });

        table_videos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(table_videos);

        check_videos_download.setText("Mostrar videos descargados");
        check_videos_download.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                check_videos_downloadStateChanged(evt);
            }
        });

        btn_delete_all.setText("Eliminar todo");
        btn_delete_all.setToolTipText("No elimina  el archivo, únicamente el registro en esta ventana");
        btn_delete_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_allActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(check_videos_download)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_delete_all)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_open_folder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_video)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_download)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(check_videos_download)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_download)
                    .addComponent(btn_cancel)
                    .addComponent(btn_add_video)
                    .addComponent(btn_delete)
                    .addComponent(btn_open_folder)
                    .addComponent(btn_delete_all))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        this.selectionVideos((video,row)->{
            video.delete();
        });
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_open_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_open_folderActionPerformed
        this.selectionVideo((video,row)->{
            Storage.execute(video.storage);
        });
    }//GEN-LAST:event_btn_open_folderActionPerformed

    private void btn_add_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_videoActionPerformed
        String url=JOptionPane.showInputDialog(null,"Ingresa URL del video");
        if(url==null || url.replaceAll(" ","").equals(" ")){
            return;
        }
        new VideoDialog(this.parent,true,url).setVisible(true);
        this.loadVideos();
    }//GEN-LAST:event_btn_add_videoActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.selectionVideo((video,row)->{
            this.downloads.get(row).cancel();
        });
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_downloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_downloadActionPerformed
        this.selectionVideos((video,row)->{
            try{
                if(!Storage.exists(video.getSrc())){
                    ExecutionObserver download=ExecutionObserver.execution(MessageFormat.format(CustomCommand.DOWNLOAD_FORMAT.toCommand(),video.format_id,VideoDAO.STORAGE_TEMP,video.filename,video.url));
                    this.downloads.put(row,download);
                    download.start((line,posi)->{
                        this.table_videos.setValueAt(line.replaceAll("\\[download\\] ",""),row,1);
                    });
                    download.onCanceled=(text,code)->{

                    };
                    download.onFinalized=(text,code)->{
                        try{
                            Storage.copyFile(VideoDAO.STORAGE_TEMP+"/"+video.filename,video.getSrc());
                            Storage.deleteFile(VideoDAO.STORAGE_TEMP+"/"+video.filename);
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    };
                }
            }catch(Exception ex){
                this.table_videos.setValueAt(ex.getMessage(),row,1);
            }
        });
    }//GEN-LAST:event_btn_downloadActionPerformed

    private void check_videos_downloadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_check_videos_downloadStateChanged
        this.loadVideos();
    }//GEN-LAST:event_check_videos_downloadStateChanged

    private void btn_delete_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_allActionPerformed
        new VideoDAO().getCollection().delete();
    }//GEN-LAST:event_btn_delete_allActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_video;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_delete_all;
    private javax.swing.JButton btn_download;
    private javax.swing.JButton btn_open_folder;
    private javax.swing.JCheckBox check_videos_download;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table_videos;
    // End of variables declaration//GEN-END:variables
}
