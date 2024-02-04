package com.wolftri.java.youtube.dl.gui;

import com.wolftri.java.youtube.dl.gui.panels.DownloadPanel;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.gui.panels.SearchPanel;
import com.wolftri.java.youtube.dl.gui.panels.VideosPanel;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;

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
            .addGap(0, 447, Short.MAX_VALUE)
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
            .addGap(0, 447, Short.MAX_VALUE)
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
            .addGap(0, 447, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Videos", panel_videos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_download;
    private javax.swing.JPanel panel_search;
    private javax.swing.JPanel panel_videos;
    // End of variables declaration//GEN-END:variables
}
