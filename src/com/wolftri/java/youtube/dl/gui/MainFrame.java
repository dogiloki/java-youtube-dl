package com.wolftri.java.youtube.dl.gui;

import com.google.gson.Gson;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.dto.SearchDTO;
import com.wolftri.java.youtube.dl.dto.VideoDTO;
import com.wolftri.java.youtube.dl.gui.views.VideoPanel;
import com.wolftri.java.youtube.dl.config.CustomCommand;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import multitaks.Function;
import multitaks.Http;
import multitaks.Persistent.ExecutionObserver;
import multitaks.database.Cursor;
import multitaks.database.ModelDB;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class MainFrame extends javax.swing.JFrame{
    
    public interface Callback{
        public void selectedVideos(VideoDAO video, int row);
    }
    
    private Http http=new Http("https://www.googleapis.com/youtube/v3");
    private Map<Integer,VideoDAO> videos=new HashMap<>();
    private Map<Integer,ExecutionObserver> downloads=new HashMap<>();
    
    public MainFrame(){
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.scroll_panel_videos.getVerticalScrollBar().setUnitIncrement(15);
        this.http.getParams().put("key","AIzaSyC5tQRf8HmtkvdqNiQ7piaIZFJ_Nmw6ufY");
        this.http.getParams().put("part","snippet");
        this.http.getParams().put("maxResults",30);
        this.table_videos.setRowHeight(160);
        this.table_videos.setFont(new Font("Arial",Font.PLAIN,18));
        this.getVideos();
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
            
            VideoPanel panel=new VideoPanel(this,video);
            
            panel.setBounds(x,y,width,height);
            
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
    
    public void getVideos(){
        this.videos.clear();
        Cursor<VideoDAO> cursor=ModelDB.all(VideoDAO.class);
        VideoDAO video;
        DefaultTableModel model_videos=new DefaultTableModel();
        model_videos.addColumn("VIDEOS");
        model_videos.addColumn("DESCARGANDO");
        int index=0;
        while((video=cursor.next())!=null){
            String str="<html>";
            str+=video.format_id+"<br>";
            str+=video.channel+"<br>";
            str+=video.title+"<br>";
            str+=video.duration+"<br>";
            str+=video.created_at+"<br>";
            str+=video.storage+"</html>";
            this.videos.put(index,video);
            model_videos.addRow(new Object[]{str,Storage.exists(video.getSrc())?"Ya esta descargado":""});
            index++;
        }
        this.table_videos.setModel(model_videos);
    }
    
    public int[] getRowsSelected(){
        return this.table_videos.getSelectedRows();
    }
    
    public void selectionVideos(Callback action){
        for(int row:this.getRowsSelected()){
            action.selectedVideos(this.videos.get(row),row);
        }
        this.getVideos();
    }
    public void selectionVideo(Callback action){
        int row=this.table_videos.getSelectedRow();
        if(row==-1){
            return;
        }
        action.selectedVideos(this.videos.get(row),row);
        this.getVideos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        scroll_panel_videos = new javax.swing.JScrollPane();
        panel_videos = new javax.swing.JPanel();
        box_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_download_canceled = new javax.swing.JButton();
        btn_pause_resumen = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_open_folder = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_videos = new javax.swing.JTable();
        btn_add_video = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panel_videosLayout = new javax.swing.GroupLayout(panel_videos);
        panel_videos.setLayout(panel_videosLayout);
        panel_videosLayout.setHorizontalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        panel_videosLayout.setVerticalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        scroll_panel_videos.setViewportView(panel_videos);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_panel_videos)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(box_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_panel_videos, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("YouTube", jPanel1);

        btn_download_canceled.setText("Descargar");
        btn_download_canceled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_download_canceledActionPerformed(evt);
            }
        });

        btn_pause_resumen.setText("Cacelar");
        btn_pause_resumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pause_resumenActionPerformed(evt);
            }
        });

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

        btn_add_video.setText("Añadir video");
        btn_add_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_videoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_open_folder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_video)
                        .addGap(386, 386, 386)
                        .addComponent(btn_pause_resumen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_download_canceled)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_download_canceled)
                    .addComponent(btn_pause_resumen)
                    .addComponent(btn_delete)
                    .addComponent(btn_open_folder)
                    .addComponent(btn_add_video))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Videos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
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

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        this.selectionVideos((video,row)->{
            video.delete();
        });
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_add_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_videoActionPerformed
        String url=JOptionPane.showInputDialog(null,"Ingresa URL del video");
        new VideoDialog(this,true,url).setVisible(true);
        this.getVideos();
    }//GEN-LAST:event_btn_add_videoActionPerformed

    private void btn_download_canceledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_download_canceledActionPerformed
        this.selectionVideos((video,row)->{
            try{
                if(!Storage.exists(video.getSrc())){
                    ExecutionObserver download=ExecutionObserver.execution(MessageFormat.format(CustomCommand.DOWNLOAD_FORMAT.toCommand(),video.format_id,VideoDAO.STORAGE_TEMP,video.filename,video.url));
                    this.downloads.put(row,download);
                    download.start((line)->{
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
    }//GEN-LAST:event_btn_download_canceledActionPerformed

    private void btn_open_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_open_folderActionPerformed
        this.selectionVideo((video,row)->{
            Storage.execute(video.storage);
        });
    }//GEN-LAST:event_btn_open_folderActionPerformed

    private void btn_pause_resumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pause_resumenActionPerformed
        this.selectionVideo((video,row)->{
            this.downloads.get(row).cancel();
        });
    }//GEN-LAST:event_btn_pause_resumenActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_search;
    private javax.swing.JButton btn_add_video;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_download_canceled;
    private javax.swing.JButton btn_open_folder;
    private javax.swing.JButton btn_pause_resumen;
    private javax.swing.JButton btn_search;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_videos;
    private javax.swing.JScrollPane scroll_panel_videos;
    private javax.swing.JTable table_videos;
    // End of variables declaration//GEN-END:variables
}
