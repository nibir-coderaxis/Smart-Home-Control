/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplicationsmartroom;
import java.awt.Color;
import jssc.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tazim
 */
public class Room1 extends javax.swing.JFrame {


     static String light1 = "OFF";
     static String light2 = "OFF";
     static String light3 = "OFF";
     static String pre_light1 = "";
     static String pre_light2 = "";
     static String pre_light3 = "";
     
     static Boolean first_status = true; 
     WebUpdate wp = new WebUpdate();
     
     static InputStream inputStream;
     static SerialPort serialPort = new SerialPort("COM7");
    /**
     * Creates new form Room1
     */
    public Room1() {
        initComponents();
        
        this.setLocationRelativeTo(null); 
        
                    jLabelLight1.setText("  Light 1 is OFF");
                    jLabelLight1.setBackground(Color.RED);
                    jLabelLight1.setOpaque(true);
                    
                    jPanelLight1.setBackground(Color.WHITE);
                    jPanelLight1.setOpaque(true);
                    
                    jLabelLight2.setText("  Light 2 is OFF");
                    jLabelLight2.setBackground(Color.RED);
                    jLabelLight2.setOpaque(true);
                    
                    jPanelLight2.setBackground(Color.WHITE);
                    jPanelLight2.setOpaque(true);
                    
                    jLabelLight3.setText("  Light 3 is OFF");
                    jLabelLight3.setBackground(Color.RED);
                    jLabelLight3.setOpaque(true);
                    
                    jPanelLight3.setBackground(Color.WHITE);
                    jPanelLight3.setOpaque(true);
         
        start_com_port();
        
        Runnable helloRunnable = new Runnable() {
        public void run() {
                       getData();
        }
             };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 2, TimeUnit.SECONDS);
      
    }
  
    
    public String getLight1(){
        return light1;
    }
    public String getLight2(){
        return light2;
    }
    public String getLight3(){
        return light3;
    }
    
    
    public void getData(){
            
        
        String L1 = wp.getLight1();
        String L2 = wp.getLight2();
        String L3 = wp.getLight3();
        
        if(!L1.equals("null"))
        {
            if(light1.equals("ON") && L1.equals("Light1 Off"))
              {
               try{
                   serialPort.writeBytes("Light1Off".getBytes()); 
               }
               catch (SerialPortException ex) {
                 System.out.println(ex);
                }
              }
            else if(light1.equals("OFF") && L1.equals("Light1 On"))
              {
               try{
                  serialPort.writeBytes("Light1On".getBytes());
                }
                catch (SerialPortException ex) {
                 System.out.println(ex);
                 }
               }
         }
        
        if(!L2.equals("null"))
        {
            if(light2.equals("ON") && L2.equals("Light2 Off"))
            {
             try{
                serialPort.writeBytes("Light2Off".getBytes()); 
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
            }
        else if(light2.equals("OFF") && L2.equals("Light2 On"))
           {
             try{
                serialPort.writeBytes("Light2On".getBytes());
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
           }
        }
        

        
         if(!L3.equals("null"))
         {
             if(light3.equals("ON") && L3.equals("Light3 Off"))
             {
             try{
                serialPort.writeBytes("Light3Off".getBytes()); 
               }
            catch (SerialPortException ex) {
                System.out.println(ex);
               }
             }
        else if(light3.equals("OFF") && L3.equals("Light3 On"))
            {
             try{
                serialPort.writeBytes("Light3On".getBytes());
            }
            catch (SerialPortException ex) {
                 System.out.println(ex);
               }
             }
         }
        
        
 
    }
    
    
    public void start_com_port(){
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            //int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            //serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
           
              // serialPort.writeBytes("1".getBytes());//Write data to port
              // serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        } 
    }
    
     class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR()){ 
                try {
                       byte[] buffer = serialPort.readBytes(1);
                       String status = new String(buffer);
                       // System.out.print(str);
                    
                       //String status = serialPort.readString();
                       
                       if(status!=null && status.length()== 1)
                       {
                           System.out.println(status);
                           
                           set_view(status);
                       }
                      
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Room1.class.getName()).log(Level.SEVERE, null, ex);
                      }
            }
        }
}
    
    public  void set_view(String status){
        
        
       /* if(first_status==true)
        {
            System.out.print("#"+status+"#");
            first_status=false;
            if(status=="Light1On"){
                pre_light1="ON";
                    jLabelLight1.setText("Light 1 is ON");
                    jLabelLight1.setBackground(Color.GREEN);
                    jLabelLight1.setOpaque(true);
                    
                    jPanelLight1.setBackground(Color.YELLOW);
                    jPanelLight1.setOpaque(true);
            }
            if(status=="Light2On"){
                pre_light2="ON";
                    jLabelLight2.setText("Light 2 is ON");
                    jLabelLight2.setBackground(Color.GREEN);
                    jLabelLight2.setOpaque(true);
                    
                    jPanelLight2.setBackground(Color.YELLOW);
                    jPanelLight2.setOpaque(true);
            }
            if(status=="Light3On"){
                pre_light3="ON";
                    jLabelLight3.setText("Light 3 is ON");
                    jLabelLight3.setBackground(Color.GREEN);
                    jLabelLight3.setOpaque(true);
                    
                    jPanelLight3.setBackground(Color.YELLOW);
                    jPanelLight3.setOpaque(true);
            }
            if(status.equals("Light1Off")){
                System.out.print("!!!!!Entered!!!!!");
                pre_light1="OFF";
                    jLabelLight1.setText("Light 1 is OFF");
                    jLabelLight1.setBackground(Color.RED);
                    jLabelLight1.setOpaque(true);
                    
                    jPanelLight1.setBackground(Color.WHITE);
                    jPanelLight1.setOpaque(true);
            }
            if(status=="Light2Off"){
                pre_light2="OFF";
                    jLabelLight2.setText("Light 2 is OFF");
                    jLabelLight2.setBackground(Color.RED);
                    jLabelLight2.setOpaque(true);
                    
                    jPanelLight2.setBackground(Color.WHITE);
                    jPanelLight2.setOpaque(true);
            }
            if(status=="Light3Off"){
                pre_light3="OFF";
                    jLabelLight3.setText("Light 3 is OFF");
                    jLabelLight3.setBackground(Color.RED);
                    jLabelLight3.setOpaque(true);
                    
                    jPanelLight3.setBackground(Color.WHITE);
                    jPanelLight3.setOpaque(true);
            }
        }
        
        */
        
           if(status.equals("1")){
              // System.out.print("Light 1 is successfully on!");
                light1="ON";
              //  if(light1!=pre_light1){
                    pre_light1="ON";
                    jLabelLight1.setText("  Light 1 is ON");
                    jLabelLight1.setBackground(Color.GREEN);
                   // jLabelLight1.setOpaque(true);
                    
                    jPanelLight1.setBackground(Color.YELLOW);
                    //jPanelLight1.setOpaque(true);
               // }
            }
           else if(status.equals("2")){
                light2="ON";
               // if(light2!=pre_light2){
                    pre_light2="ON";
                    jLabelLight2.setText("  Light 2 is ON");
                    jLabelLight2.setBackground(Color.GREEN);
                   // jLabelLight2.setOpaque(true);
                    
                    jPanelLight2.setBackground(Color.YELLOW);
                    //jPanelLight2.setOpaque(true);
                //}
            }
           else if(status.equals("3")){
                light3="ON";
                //if(light3!=pre_light3){
                    pre_light3="ON";
                    jLabelLight3.setText("  Light 3 is ON");
                    jLabelLight3.setBackground(Color.GREEN);
                   // jLabelLight3.setOpaque(true);
                    
                    jPanelLight3.setBackground(Color.YELLOW);
                    //jPanelLight3.setOpaque(true);
               // }
            }
           else if(status.equals("4")){
                light1="OFF";
                //if(light1!=pre_light1){
                    pre_light1="OFF";
                    jLabelLight1.setText("  Light 1 is OFF");
                    jLabelLight1.setBackground(Color.RED);
                   // jLabelLight1.setOpaque(true);
                    
                    jPanelLight1.setBackground(Color.WHITE);
                    //jPanelLight1.setOpaque(true);
               // }
            }
           else if(status.equals("5")){
                light2="OFF";
              //  if(light2!=pre_light2){
                    pre_light2="OFF";
                    jLabelLight2.setText("  Light 2 is OFF");
                    jLabelLight2.setBackground(Color.RED);
                   // jLabelLight2.setOpaque(true);
                    
                    jPanelLight2.setBackground(Color.WHITE);
                   // jPanelLight2.setOpaque(true);
              //  }
            }
           else if(status.equals("6")){
                light3="OFF";
              //  if(light3!=pre_light3){
                    pre_light3="OFF";
                    jLabelLight3.setText("  Light 3 is OFF");
                    jLabelLight3.setBackground(Color.RED);
                   // jLabelLight3.setOpaque(true);
                    
                    jPanelLight3.setBackground(Color.WHITE);
                   // jPanelLight3.setOpaque(true);
              //  }
            }
          
        
    }
    

    public void close_com_port(){
     try {
           serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        } 
     }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLight1 = new javax.swing.JPanel();
        jButtonLight1 = new javax.swing.JButton();
        jLabelLight1 = new javax.swing.JLabel();
        jPanelLight2 = new javax.swing.JPanel();
        jButtonLight2 = new javax.swing.JButton();
        jLabelLight2 = new javax.swing.JLabel();
        jPanelLight3 = new javax.swing.JPanel();
        jButtonLight3 = new javax.swing.JButton();
        jLabelLight3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ROOM1");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(702, 366));
        setResizable(false);

        jPanelLight1.setPreferredSize(new java.awt.Dimension(234, 0));

        jButtonLight1.setBackground(new java.awt.Color(102, 255, 102));
        jButtonLight1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLight1.setForeground(new java.awt.Color(255, 0, 153));
        jButtonLight1.setText("Light 1");
        jButtonLight1.setBorder(null);
        jButtonLight1.setBorderPainted(false);
        jButtonLight1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLight1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLight1MouseClicked(evt);
            }
        });

        jLabelLight1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelLight1.setText("     No Status!");

        javax.swing.GroupLayout jPanelLight1Layout = new javax.swing.GroupLayout(jPanelLight1);
        jPanelLight1.setLayout(jPanelLight1Layout);
        jPanelLight1Layout.setHorizontalGroup(
            jPanelLight1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLight1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButtonLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLight1Layout.setVerticalGroup(
            jPanelLight1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight1Layout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jLabelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButtonLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanelLight2.setPreferredSize(new java.awt.Dimension(234, 100));

        jButtonLight2.setBackground(new java.awt.Color(102, 255, 102));
        jButtonLight2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLight2.setForeground(new java.awt.Color(255, 0, 153));
        jButtonLight2.setText("Light 2");
        jButtonLight2.setBorder(null);
        jButtonLight2.setBorderPainted(false);
        jButtonLight2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLight2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLight2MouseClicked(evt);
            }
        });

        jLabelLight2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelLight2.setText("     No Status!");

        javax.swing.GroupLayout jPanelLight2Layout = new javax.swing.GroupLayout(jPanelLight2);
        jPanelLight2.setLayout(jPanelLight2Layout);
        jPanelLight2Layout.setHorizontalGroup(
            jPanelLight2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLight2Layout.createSequentialGroup()
                .addGroup(jPanelLight2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLight2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButtonLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLight2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanelLight2Layout.setVerticalGroup(
            jPanelLight2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButtonLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        jPanelLight3.setPreferredSize(new java.awt.Dimension(234, 0));

        jButtonLight3.setBackground(new java.awt.Color(102, 255, 102));
        jButtonLight3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLight3.setForeground(new java.awt.Color(255, 0, 153));
        jButtonLight3.setText("Light 3");
        jButtonLight3.setBorder(null);
        jButtonLight3.setBorderPainted(false);
        jButtonLight3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLight3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLight3MouseClicked(evt);
            }
        });

        jLabelLight3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelLight3.setText("     No Status!");

        javax.swing.GroupLayout jPanelLight3Layout = new javax.swing.GroupLayout(jPanelLight3);
        jPanelLight3.setLayout(jPanelLight3Layout);
        jPanelLight3Layout.setHorizontalGroup(
            jPanelLight3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLight3Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanelLight3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight3Layout.createSequentialGroup()
                        .addComponent(jButtonLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight3Layout.createSequentialGroup()
                        .addComponent(jLabelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        jPanelLight3Layout.setVerticalGroup(
            jPanelLight3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLight3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButtonLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelLight3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLight1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
            .addComponent(jPanelLight2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
            .addComponent(jPanelLight3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLight1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLight1MouseClicked
        if(light1.equals("ON"))
        {
             try{
                serialPort.writeBytes("Light1Off".getBytes()); 
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
        else if(light1.equals("OFF"))
        {
             try{
                serialPort.writeBytes("Light1On".getBytes());
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
    }//GEN-LAST:event_jButtonLight1MouseClicked

    private void jButtonLight2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLight2MouseClicked
        if(light2.equals("ON"))
        {
             try{
                serialPort.writeBytes("Light2Off".getBytes()); 
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
        else if(light2.equals("OFF"))
        {
             try{
                serialPort.writeBytes("Light2On".getBytes());
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
    }//GEN-LAST:event_jButtonLight2MouseClicked

    private void jButtonLight3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLight3MouseClicked
        if(light3.equals("ON"))
        {
             try{
                serialPort.writeBytes("Light3Off".getBytes()); 
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
        else if(light3.equals("OFF"))
        {
             try{
                serialPort.writeBytes("Light3On".getBytes());
            }
            catch (SerialPortException ex) {
            System.out.println(ex);
             }
        }
    }//GEN-LAST:event_jButtonLight3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Room1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Room1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Room1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Room1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Room1().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLight1;
    private javax.swing.JButton jButtonLight2;
    private javax.swing.JButton jButtonLight3;
    private javax.swing.JLabel jLabelLight1;
    private javax.swing.JLabel jLabelLight2;
    private javax.swing.JLabel jLabelLight3;
    private javax.swing.JPanel jPanelLight1;
    private javax.swing.JPanel jPanelLight2;
    private javax.swing.JPanel jPanelLight3;
    // End of variables declaration//GEN-END:variables
}
