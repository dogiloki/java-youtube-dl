package com.wolftri.java.youtube.dl.gui;

import com.wolftri.java.youtube.dl.gui.panels.DownloadPanel;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.gui.panels.SearchPanel;
import com.wolftri.java.youtube.dl.gui.panels.VideosPanel;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.awt.Desktop;
import java.net.URI;
import java.net.URLDecoder;

/**
 *
 * @author dogi_
 */

public class MainFrame extends javax.swing.JFrame{
    
    public interface Callback{
        public void selectedVideos(VideoDAO video, int row);
    }
    
    private SearchPanel search;
    private DownloadPanel download;
    private VideosPanel videos;
    
    public MainFrame(){
        initComponents();
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Storage s_videos=new Storage(VideoDAO.STORAGE_VIDEOS,DirectoryType.FOLDER);
        s_videos.exists(true);
        new Storage(VideoDAO.STORAGE_VIDEOS,DirectoryType.FOLDER).exists(true);
        this.download=new DownloadPanel(this);
        this.search=new SearchPanel(this);
        this.videos=new VideosPanel();
        Function.setPanel(this.panel_download,this.download);
        Function.setPanel(this.panel_search,this.search);
        Function.setPanel(this.panel_videos,this.videos);
    }
    
    public DownloadPanel getDownload(){
        return this.download;
    }
    
    public VideosPanel getVideosPanel(){
        return this.videos;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_search = new javax.swing.JPanel();
        panel_download = new javax.swing.JPanel();
        panel_videos = new javax.swing.JPanel();
        btn_login_spotify = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panel_searchLayout = new javax.swing.GroupLayout(panel_search);
        panel_search.setLayout(panel_searchLayout);
        panel_searchLayout.setHorizontalGroup(
            panel_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        panel_searchLayout.setVerticalGroup(
            panel_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Busqueda", panel_search);

        javax.swing.GroupLayout panel_downloadLayout = new javax.swing.GroupLayout(panel_download);
        panel_download.setLayout(panel_downloadLayout);
        panel_downloadLayout.setHorizontalGroup(
            panel_downloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        panel_downloadLayout.setVerticalGroup(
            panel_downloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Descargas", panel_download);

        javax.swing.GroupLayout panel_videosLayout = new javax.swing.GroupLayout(panel_videos);
        panel_videos.setLayout(panel_videosLayout);
        panel_videosLayout.setHorizontalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        panel_videosLayout.setVerticalGroup(
            panel_videosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Videos", panel_videos);

        btn_login_spotify.setText("Iniciar sesión en Spotify");
        btn_login_spotify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login_spotifyActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_login_spotify)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btn_login_spotify)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_login_spotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login_spotifyActionPerformed
        Desktop desktop=Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.OPEN)){
            try{
                desktop.browse(new URI("https://accounts.spotify.com/authorize?"+
                        new ListFields()
                                .append("code","code")
                                .append("client_id","e5ca74826c4c47379138eac8bd3363f4")
                                .append("scope","user-read-email")
                                .append("redirect_uri","http://localhost:8000")
                                .append("response_type","code")
                                .append("state","state").toQuery()
                    ));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_login_spotifyActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login_spotify;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_download;
    private javax.swing.JPanel panel_search;
    private javax.swing.JPanel panel_videos;
    // End of variables declaration//GEN-END:variables
}
