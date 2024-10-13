package com.wolftri.java.youtube.dl.gui.panels;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.wolftri.java.youtube.dl.dao.VideoDAO;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.directory.DirectoryList;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.server.socket.SocketServer;

/**
 *
 * @author dogi_
 */

public class VideosPanel extends javax.swing.JPanel{

    private SocketServer server=new SocketServer();
    private Map<Integer,Storage> videos=new HashMap<>();
    private Map<Storage,Integer> videos_transfer=new HashMap<>();
    private Iterator<Storage> videos_transfer_iterator;
    
    public VideosPanel(){
        initComponents();
        this.loadVideos();
    }
    
    public void loadVideos(){
        this.list_videos.removeAll();
        DefaultListModel model_videos=new DefaultListModel();
        Storage s_videos=new Storage(VideoDAO.STORAGE_VIDEOS);
        DirectoryList files=s_videos.listDirectory();
        s_videos.close();
        int index=0;
        String search=this.box_search.getText();
        while(files.hasNext()){
            Path path=files.next();
            String src=path.toString();
            String name=path.getFileName().toString();
            if(!search.replaceAll(" ","").equals(" ") && !name.contains(search)){
                continue;
            }
            model_videos.add(index,"["+index+"] "+name);
            this.videos.put(index,new Storage(src));
            index++;
        }
        this.list_videos.setModel(model_videos);
    }
    
    public void generateQR(String text){
        try{
            int width=150;
            int height=150;
            BitMatrix matrix=new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE,width,height);
            BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for(int x=0; x<width; x++){
                for(int y=0; y<height; y++){
                    image.setRGB(x,y,matrix.get(x,y)?0xFF000000:0xFFFFFFFF);
                }
            }
            this.text_code_qr.setIcon(Function.generateIcon(new ImageIcon(image),width,height));
            this.text_qr.setText(text);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addLog(String text){
        this.box_log.setText(text+"\n"+this.box_log.getText());
    }
    
    public void resetTransfer(){
        this.videos_transfer.clear();
        this.videos_transfer_iterator=null;
        this.btn_transfer.setEnabled(true);
    }
    
    public void transferFile(Storage s){
        if(s==null){
            return;
        }
        this.btn_transfer.setEnabled(false);
        int index=this.videos_transfer.get(s);
        try{
            this.addLog("["+index+"] Esperando confirmación");
            FileBlock f=s.fileBlock(8192);
            this.server.emit("file",s.getName());
            this.server.on("file_name_success",(message)->{
                if(Boolean.parseBoolean(message)){
                    this.addLog("["+index+"] Enviando el archivo...");
                    try{
                        byte[] b;
                        while((b=f.read())!=null){
                            Map<String,Object> json=new HashMap<>();
                            json.put("size",f.getBlockSize());
                            json.put("byte",b);
                            this.server.emit("file_byte",new Gson().toJson(json));
                        }
                        this.server.emit("file_byte_success","true");
                        this.addLog("["+index+"] Archivo enviado");
                    }catch(Exception ex){
                        this.server.emit("file_byte_success","false");
                        this.addLog("["+index+"] Archivo no enviado");
                        this.addLog("["+index+"] "+ex.getMessage());
                        ex.printStackTrace();
                    }finally{
                        if(this.videos_transfer_iterator.hasNext()){
                            s.close();
                            this.transferFile(this.videos_transfer_iterator.next());
                        }else{
                            this.resetTransfer();
                        }
                    }
                }else{
                    this.addLog("["+index+"] Transferencia denegada");
                    this.resetTransfer();
                }
            });
        }catch(Exception ex){
            this.addLog("["+index+"] "+ex.getMessage());
            this.resetTransfer();
            ex.printStackTrace();
        }
    }
    
    public SocketServer getServer(){
        return this.server;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_videos = new javax.swing.JList<>();
        box_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        box_port = new javax.swing.JTextField();
        btn_start = new javax.swing.JButton();
        text_code_qr = new javax.swing.JLabel();
        text_qr = new javax.swing.JLabel();
        btn_transfer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        box_log = new javax.swing.JTextArea();
        download_app = new javax.swing.JButton();
        btn_open_folder = new javax.swing.JButton();

        jScrollPane1.setViewportView(list_videos);

        box_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                box_searchKeyReleased(evt);
            }
        });

        jLabel1.setText("Puerto");

        box_port.setText("1234");

        btn_start.setText("Iniciar servidor");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_transfer.setText("Transferir");
        btn_transfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferActionPerformed(evt);
            }
        });

        box_log.setColumns(20);
        box_log.setRows(5);
        jScrollPane2.setViewportView(box_log);

        download_app.setText("Descargar app");
        download_app.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download_appActionPerformed(evt);
            }
        });

        btn_open_folder.setText("Abrir carpeta");
        btn_open_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_open_folderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text_code_qr, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(box_port)
                            .addComponent(jLabel1)
                            .addComponent(btn_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(download_app, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_open_folder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(text_qr)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(box_search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_transfer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(box_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_start)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(download_app)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_open_folder))
                            .addComponent(text_code_qr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_qr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(box_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_transfer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        this.resetTransfer();
        if(this.server.isStart()){
            try{
                this.server.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            this.btn_start.setText("Iniciar servidor");
            this.addLog("Servidor cerrado");
            return;
        }
        try{
            this.server.start(Integer.parseInt(this.box_port.getText()));
            this.generateQR(this.server.getAddress());
            this.btn_start.setText("Parar servidor");
            this.server.onConnect=(client)->{
                this.addLog("Conectado: "+client.getAddress());
            };
            this.server.onDisconnect=(client)->{
                this.addLog("Desconectado: "+client.getAddress());
            };
            this.addLog("Servidor iniciado");
        }catch(Exception ex){
            this.addLog(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_startActionPerformed

    private void box_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_box_searchKeyReleased
        this.loadVideos();
    }//GEN-LAST:event_box_searchKeyReleased

    private void btn_transferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferActionPerformed
        if(!this.server.isStart()){
            JOptionPane.showMessageDialog(null,"El servidor no esta iniciado","Advertencia",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Map<Integer,Storage> videos=new HashMap<>();
        for(int index:this.list_videos.getSelectedIndices()){
            Storage s=this.videos.get(index);
            this.videos_transfer.put(s,index);
            videos.put(index,s);
        }
        this.videos_transfer_iterator=videos.values().iterator();
        this.transferFile(this.videos_transfer_iterator.next());
    }//GEN-LAST:event_btn_transferActionPerformed

    private void download_appActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_download_appActionPerformed
        this.generateQR("https://github.com/dogiloki/java-youtube-dl/raw/main/app/app-yt-dl.apk");
    }//GEN-LAST:event_download_appActionPerformed

    private void btn_open_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_open_folderActionPerformed
        new Storage(VideoDAO.STORAGE_VIDEOS).execute();
    }//GEN-LAST:event_btn_open_folderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea box_log;
    private javax.swing.JTextField box_port;
    private javax.swing.JTextField box_search;
    private javax.swing.JButton btn_open_folder;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_transfer;
    private javax.swing.JButton download_app;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> list_videos;
    private javax.swing.JLabel text_code_qr;
    private javax.swing.JLabel text_qr;
    // End of variables declaration//GEN-END:variables
}
