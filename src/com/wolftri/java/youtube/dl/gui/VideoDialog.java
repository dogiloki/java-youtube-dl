package com.wolftri.java.youtube.dl.gui;

import com.google.gson.Gson;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import com.wolftri.java.youtube.dl.dto.VideoDTO;
import com.wolftri.java.youtube.dl.config.CustomCommand;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import multitaks.directory.Storage;
import multitaks.persistent.ExecutionObserver;

/**
 *
 * @author dogi_
 */

public class VideoDialog extends javax.swing.JDialog{
    
    private VideoDAO video;
    private Map<String,List<Map<String,String>>> json_formats=new HashMap<>();
    
    public VideoDialog(java.awt.Frame parent, boolean modal, VideoDTO video){
        super(parent, modal);
        this.init(parent,modal,MessageFormat.format(VideoDAO.BASE_VIDEO_URL,video.id.get("videoId")));
    }
    
    public VideoDialog(java.awt.Frame parent, boolean modal, String url){
        super(parent, modal);
        this.init(parent,modal,url);
    }
    
    private void init(java.awt.Frame parent, boolean modal, String url){
        initComponents();
        this.setLocationRelativeTo(null);
        this.video=new VideoDAO();
        this.video.url=url;
        try{
            Gson gson=new Gson();
            String text_json=ExecutionObserver.execution(MessageFormat.format(CustomCommand.LIST_FORMAT.toCommand(),this.video.url)).start();
            Map<String,String> json=gson.fromJson(text_json,Map.class);
            this.json_formats=gson.fromJson(text_json,Map.class);
            this.video.duration=String.valueOf(json.get("duration"));
            this.video.title=json.get("title");
            this.video.size=json.get("filesize");
            this.video.channel=json.get("channel");
            this.video.format_id=json.get("format_id");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        this.text_title.setText(this.video.title);
        this.box_storage.setText(VideoDAO.STORAGE_VIDEOS);
        this.loadTable();
    }
    
    public void loadTable(){
        DefaultTableModel model_formats=new DefaultTableModel();
        model_formats.addColumn("ID");
        model_formats.addColumn("EXT");
        model_formats.addColumn("RESOLUCIÓN");
        model_formats.addColumn("FPS");
        model_formats.addColumn("TAMAÑO");
        model_formats.addColumn("TBR");
        model_formats.addColumn("AUDIO");
        model_formats.addColumn("VIDEO");
        model_formats.addColumn("CALIDAD");
        for(Map<String,String> format:this.json_formats.get("formats")){
            model_formats.addRow(new Object[]{
                format.get("format_id"),
                format.get("ext"),
                format.get("resolution"),
                format.get("fps"),
                format.get("filesize"),
                format.get("tbr"),
                format.get("acodec"),
                format.get("vcodec"),
                format.get("format")
            });
        }
        this.table_formats.setModel(model_formats);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text_title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_formats = new javax.swing.JTable();
        btn_add_list_downloads = new javax.swing.JButton();
        box_storage = new javax.swing.JTextField();
        btn_change_storage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        text_title.setText("Título");

        table_formats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_formats);

        btn_add_list_downloads.setText("Añadir a descargas");
        btn_add_list_downloads.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_list_downloadsActionPerformed(evt);
            }
        });

        box_storage.setEnabled(false);

        btn_change_storage.setText("Examinar");
        btn_change_storage.setEnabled(false);
        btn_change_storage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_change_storageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_title)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(box_storage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_change_storage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_list_downloads)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_list_downloads)
                    .addComponent(box_storage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_change_storage))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_add_list_downloadsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_list_downloadsActionPerformed
        this.video.storage=this.box_storage.getText();
        this.video.format_id=this.table_formats.getValueAt(this.table_formats.getSelectedRow(),0).toString();
        this.video.filename=this.video.title+"_"+this.video.format_id;
        this.video.filename=this.video.filename.replaceAll(" ","-").replaceAll("[\\\\\\\\/:*?\\\"<>|+]", "")+"."+this.table_formats.getValueAt(this.table_formats.getSelectedRow(),1).toString();
        this.video.save();
        dispose();
    }//GEN-LAST:event_btn_add_list_downloadsActionPerformed

    private void btn_change_storageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_change_storageActionPerformed
        String storage=Storage.selectFolder(this);
        if(storage!=null){
            this.box_storage.setText(storage);
        }
    }//GEN-LAST:event_btn_change_storageActionPerformed

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VideoDialog dialog = new VideoDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField box_storage;
    private javax.swing.JButton btn_add_list_downloads;
    private javax.swing.JButton btn_change_storage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_formats;
    private javax.swing.JLabel text_title;
    // End of variables declaration//GEN-END:variables
}
