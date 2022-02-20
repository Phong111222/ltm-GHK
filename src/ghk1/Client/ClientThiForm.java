/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghk1.Client;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BoDe;

/**
 *
 * @author PC
 */
public class ClientThiForm extends javax.swing.JFrame {

    /**
     * Creates new form ClientThiForm
     */
    private Socket client;
    DataInputStream din;
    DataOutputStream dout;
    private int time;
    private int maSinhVien;
    private ArrayList<BoDe> listCauHoi = new ArrayList();
    private ArrayList<String> listDapAn = new ArrayList();
    private static Timer timerCountDown;

    public ClientThiForm(Socket client, DataInputStream din, DataOutputStream dout, int maSinhVien) {
        initComponents();
        this.client = client;
        this.din = din;
        this.dout = dout;
        this.maSinhVien = maSinhVien; 
        this.dongHoTextField.setText("10");
        try{
            loadDanhSachCauHoi();
            cauhoi1Radio.setSelected(true);
            System.out.println("So luong cau hoi: " + listCauHoi.size());
            loadCauHoi(0);
            loadMaCauHoi();
            countTime(300);
        } catch(Exception e){
            System.out.println("So luong cau hoi: " + listCauHoi.size());
            e.printStackTrace();
            this.dispose();
        }
    }

    public void countTime(int timeInput) {
        timerCountDown = new Timer();
        this.time = timeInput;
        timerCountDown.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                time = setInterval(timerCountDown);
                System.out.println(time);
                setTimeText(time);
                if(time == 0){
                    nopBaiAction();
                }
                //dongHoTextField.setText(""+time);
            }
        }, 1000, 1000);
    }

    private int setInterval(Timer timer) {
        if (this.time == 1) {
            timer.cancel();
        }
        return --time;
    }
    
    private void setTimeText(int time){
        int phut = time/60;
        int giayConLai = time%60;
        String phutS = "";
        String giayS = "";
        if(phut<10) phutS = "0".concat(String.valueOf(phut));
        else phutS = String.valueOf(phut);
        if(giayConLai<10) giayS = "0".concat(String.valueOf(giayConLai));
        else giayS = String.valueOf(giayConLai);
        dongHoTextField.setText(phutS+":"+giayS);
    }
    
    public void loadDanhSachCauHoi(){
        try {
            dout.writeUTF("LOAD_CAUHOI");
            Vector list = null;
            while(true){
                String dataRead = din.readUTF();
                System.out.println(dataRead);
                String[] data = dataRead.split("·");
                if(!data[0].equalsIgnoreCase("data")){
                    break;
                }
                // BoDe(int maCauhoi, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAn)
                listCauHoi.add(new BoDe(Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], data[7]));
                
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
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

        groupRadiCauHoi = new javax.swing.ButtonGroup();
        groupCauTraLoi = new javax.swing.ButtonGroup();
        dongHoTextField = new javax.swing.JTextField();
        nopBaiBtn = new javax.swing.JButton();
        cauhoi1Radio = new javax.swing.JRadioButton();
        cauhoi2Radio = new javax.swing.JRadioButton();
        cauhoi3Radio = new javax.swing.JRadioButton();
        cauhoi4Radio = new javax.swing.JRadioButton();
        cauhoi5Radio = new javax.swing.JRadioButton();
        cauhoi6Radio = new javax.swing.JRadioButton();
        cauhoi7Radio = new javax.swing.JRadioButton();
        cauhoi8Radio = new javax.swing.JRadioButton();
        cauhoi9Radio = new javax.swing.JRadioButton();
        cauhoi10Radio = new javax.swing.JRadioButton();
        cauHoiLabel = new javax.swing.JLabel();
        aRadio = new javax.swing.JRadioButton();
        bRadio = new javax.swing.JRadioButton();
        cRadio = new javax.swing.JRadioButton();
        dRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dongHoTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dongHoTextField.setText("5:00");
        dongHoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dongHoTextFieldActionPerformed(evt);
            }
        });

        nopBaiBtn.setText("Nộp bài");
        nopBaiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopBaiBtnActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi1Radio);
        cauhoi1Radio.setText("Cau hoi 1");
        cauhoi1Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi1RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi2Radio);
        cauhoi2Radio.setText("Cau hoi 2");
        cauhoi2Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi2RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi3Radio);
        cauhoi3Radio.setText("Cau hoi 3");
        cauhoi3Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi3RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi4Radio);
        cauhoi4Radio.setText("Cau hoi 4");
        cauhoi4Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi4RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi5Radio);
        cauhoi5Radio.setText("Cau hoi 5");
        cauhoi5Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi5RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi6Radio);
        cauhoi6Radio.setText("Cau hoi 6");
        cauhoi6Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi6RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi7Radio);
        cauhoi7Radio.setText("Cau hoi 7");
        cauhoi7Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi7RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi8Radio);
        cauhoi8Radio.setText("Cau hoi 8");
        cauhoi8Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi8RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi9Radio);
        cauhoi9Radio.setText("Cau hoi 9");
        cauhoi9Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi9RadioActionPerformed(evt);
            }
        });

        groupRadiCauHoi.add(cauhoi10Radio);
        cauhoi10Radio.setText("Cau hoi 10");
        cauhoi10Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cauhoi10RadioActionPerformed(evt);
            }
        });

        cauHoiLabel.setText("Cau hoi");

        groupCauTraLoi.add(aRadio);
        aRadio.setText("A");
        aRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aRadioActionPerformed(evt);
            }
        });

        groupCauTraLoi.add(bRadio);
        bRadio.setText("B");
        bRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRadioActionPerformed(evt);
            }
        });

        groupCauTraLoi.add(cRadio);
        cRadio.setText("C");
        cRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cRadioActionPerformed(evt);
            }
        });

        groupCauTraLoi.add(dRadio);
        dRadio.setText("D");
        dRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dongHoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(nopBaiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cauhoi5Radio)
                            .addComponent(cauhoi4Radio)
                            .addComponent(cauhoi7Radio)
                            .addComponent(cauhoi6Radio)
                            .addComponent(cauhoi3Radio)
                            .addComponent(cauhoi8Radio)
                            .addComponent(cauhoi9Radio)
                            .addComponent(cauhoi10Radio)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cauhoi1Radio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cauhoi2Radio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cauHoiLabel)
                            .addComponent(aRadio)
                            .addComponent(bRadio)
                            .addComponent(cRadio)
                            .addComponent(dRadio))))
                .addContainerGap(494, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nopBaiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dongHoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cauHoiLabel)
                        .addGap(54, 54, 54)
                        .addComponent(aRadio)
                        .addGap(33, 33, 33)
                        .addComponent(bRadio)
                        .addGap(35, 35, 35)
                        .addComponent(cRadio)
                        .addGap(33, 33, 33)
                        .addComponent(dRadio))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cauhoi1Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi2Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi3Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi4Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cauhoi5Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi6Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi7Radio)
                        .addComponent(cauhoi8Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi9Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cauhoi10Radio)))
                .addGap(0, 75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dongHoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dongHoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dongHoTextFieldActionPerformed

    private void nopBaiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopBaiBtnActionPerformed

       nopBaiAction();
        
    }//GEN-LAST:event_nopBaiBtnActionPerformed

    private void nopBaiAction(){
        try {
            // TODO add your handling code here:
            timerCountDown.cancel();
            String result = "";
            int diem = 0;
            for(String x: listDapAn){
                result = result+"- " + x +"\n";
                String[] data = x.split("-");
                String cauhoi = data[0];
                String[] maCauHoiArray = cauhoi.split("\\| ID: ");
                String maCauHoi = maCauHoiArray[1];
                String dapap = data[1];
                System.out.println(x);
                System.out.println(cauhoi);
                System.out.println(maCauHoi);
                for(BoDe de: listCauHoi){
                    if(de.getIdCauHoi() == Integer.parseInt(maCauHoi)){
                        if(de.getDapAn().trim().equalsIgnoreCase(dapap.trim())){
                            diem = diem +1;
                        }
                    }
                }
                //EncrypteAES(x);
                
            }
            dout.writeUTF("SAVE_DIEM");
            dout.writeUTF(String.format("%s-%s",this.maSinhVien,diem));            
            JOptionPane.showMessageDialog(rootPane, result+"\n"+"Diem cua ban: "+diem);
            ClientSQLLoginUI loginAgian = new ClientSQLLoginUI(client);
            loginAgian.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cauhoi2RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi2RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi2Radio.getText());
        cauhoi2Radio.setSelected(true);
        loadCauHoi(1); 
    }//GEN-LAST:event_cauhoi2RadioActionPerformed

    private void cauhoi5RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi5RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi5Radio.getText());
        cauhoi5Radio.setSelected(true);
        loadCauHoi(4);
    }//GEN-LAST:event_cauhoi5RadioActionPerformed

    private void cauhoi1RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi1RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi1Radio.getText());
        cauhoi1Radio.setSelected(true);
        loadCauHoi(0);
    }//GEN-LAST:event_cauhoi1RadioActionPerformed

    private void cauhoi3RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi3RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi3Radio.getText());
        cauhoi3Radio.setSelected(true);
        loadCauHoi(2);
    }//GEN-LAST:event_cauhoi3RadioActionPerformed

    private void cauhoi4RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi4RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi4Radio.getText());
        cauhoi4Radio.setSelected(true);
        loadCauHoi(3); 
    }//GEN-LAST:event_cauhoi4RadioActionPerformed

    private void cauhoi6RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi6RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi6Radio.getText());
        cauhoi6Radio.setSelected(true);
        loadCauHoi(5);
    }//GEN-LAST:event_cauhoi6RadioActionPerformed

    private void cauhoi7RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi7RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi7Radio.getText());
        cauhoi7Radio.setSelected(true);
        loadCauHoi(6);
    }//GEN-LAST:event_cauhoi7RadioActionPerformed

    private void cauhoi8RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi8RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi8Radio.getText());
        cauhoi8Radio.setSelected(true);
        loadCauHoi(7); 
    }//GEN-LAST:event_cauhoi8RadioActionPerformed

    private void cauhoi9RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi9RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi9Radio.getText());
        cauhoi9Radio.setSelected(true);
        loadCauHoi(8);
    }//GEN-LAST:event_cauhoi9RadioActionPerformed

    private void cauhoi10RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cauhoi10RadioActionPerformed
        // TODO add your handling code here:
        loadDapAnDaChon(cauhoi10Radio.getText());
        cauhoi10Radio.setSelected(true);
        loadCauHoi(9); 
    }//GEN-LAST:event_cauhoi10RadioActionPerformed

    private void aRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aRadioActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = groupRadiCauHoi.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(!checkExistInListDapAn(button.getText())){
                    listDapAn.add(button.getText()+"-A");
                }
                else{
                    updateDapAn(button.getText(),"A");
                }
            }
        }
    }//GEN-LAST:event_aRadioActionPerformed

    private void bRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRadioActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = groupRadiCauHoi.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(!checkExistInListDapAn(button.getText())){
                    listDapAn.add(button.getText()+"-B");
                }
                else{
                    updateDapAn(button.getText(),"B");
                }
            }
        }
    }//GEN-LAST:event_bRadioActionPerformed

    private void cRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cRadioActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = groupRadiCauHoi.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(!checkExistInListDapAn(button.getText())){
                    listDapAn.add(button.getText()+"-C");
                }
                else{
                    updateDapAn(button.getText(),"C");
                }
            }
        }
    }//GEN-LAST:event_cRadioActionPerformed

    private void dRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dRadioActionPerformed
        // TODO add your handling code here:
        for (Enumeration<AbstractButton> buttons = groupRadiCauHoi.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                if(!checkExistInListDapAn(button.getText())){
                    listDapAn.add(button.getText()+"-D");
                }
                else{
                    updateDapAn(button.getText(),"D");
                }
            }
        }
    }//GEN-LAST:event_dRadioActionPerformed

    
    private void loadCauHoi(int stt){
        cauHoiLabel.setText("Cau hoi: "+listCauHoi.get(stt).getCauHoi());
        aRadio.setText("A: "+listCauHoi.get(stt).getDapAnA());
        bRadio.setText("B: "+listCauHoi.get(stt).getDapAnB());
        cRadio.setText("C: "+listCauHoi.get(stt).getDapAnC());
        dRadio.setText("D: "+listCauHoi.get(stt).getDapAnD());
    }
    
    private void loadMaCauHoi(){
        cauhoi1Radio.setText("Cau hoi 1| ID: "+listCauHoi.get(0).getIdCauHoi());
        cauhoi2Radio.setText("Cau hoi 2| ID: "+listCauHoi.get(1).getIdCauHoi());
        cauhoi3Radio.setText("Cau hoi 3| ID: "+listCauHoi.get(2).getIdCauHoi());
        cauhoi4Radio.setText("Cau hoi 4| ID: "+listCauHoi.get(3).getIdCauHoi());
        cauhoi5Radio.setText("Cau hoi 5| ID: "+listCauHoi.get(4).getIdCauHoi());
        cauhoi6Radio.setText("Cau hoi 6| ID: "+listCauHoi.get(5).getIdCauHoi());
        cauhoi7Radio.setText("Cau hoi 7| ID: "+listCauHoi.get(6).getIdCauHoi());
        cauhoi8Radio.setText("Cau hoi 8| ID: "+listCauHoi.get(7).getIdCauHoi());
        cauhoi9Radio.setText("Cau hoi 9| ID: "+listCauHoi.get(8).getIdCauHoi());
        cauhoi10Radio.setText("Cau hoi 10| ID: "+listCauHoi.get(9).getIdCauHoi());
    }
    
    private void loadDapAnDaChon(String sttCauHoi){
        String dapAnCauHoi = null;
        for(String x : listDapAn){
            String array[] = x.split("-");
            if(sttCauHoi.equals(array[0])){
                dapAnCauHoi = x;
                break;
            }
        }
        System.out.println(dapAnCauHoi);
        if(dapAnCauHoi == null || dapAnCauHoi.trim().equals("")){
            groupCauTraLoi.clearSelection();
            return;
        }
        String array[] = dapAnCauHoi.split("-");
        System.out.println(array[1]);
        switch(array[1]){
            case "A":{
                aRadio.setSelected(true);
                break;
            }
            case "B":{
                bRadio.setSelected(true);
                break;
            }
            case "C":{
                cRadio.setSelected(true);
                break;
            }
            case "D":{
                dRadio.setSelected(true);
                break;
            }
            default:{
                groupCauTraLoi.clearSelection();
                break;
            }
        }
    }
    
    private boolean checkExistInListDapAn(String cauHoi){
        for(String x: listDapAn){
            if(x.contains(cauHoi)){
                return true;
            }
        }
        return false;
    }
    
    private void updateDapAn(String cauHoi, String dapAn){
        for(String x: listDapAn){
            if(x.contains(cauHoi)){
                listDapAn.remove(x);
                break;
            }
        }
        listDapAn.add(String.format("%s-%s",cauHoi, dapAn));
    }
    
    private void EncrypteAES(String key){
        try {
            //byte[] data = Base64.getEncoder().encodeToString(key.getBytes())
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec myKey = new SecretKeySpec("lamhatuananh".getBytes(),"AES");
            IvParameterSpec iv = new IvParameterSpec("THIS IS L#AZH".getBytes());
            try {
                cipher.init(Cipher.ENCRYPT_MODE, myKey, iv);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ClientThiForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
            java.util.logging.Logger.getLogger(ClientThiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientThiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientThiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientThiForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton aRadio;
    private javax.swing.JRadioButton bRadio;
    private javax.swing.JRadioButton cRadio;
    private javax.swing.JLabel cauHoiLabel;
    private javax.swing.JRadioButton cauhoi10Radio;
    private javax.swing.JRadioButton cauhoi1Radio;
    private javax.swing.JRadioButton cauhoi2Radio;
    private javax.swing.JRadioButton cauhoi3Radio;
    private javax.swing.JRadioButton cauhoi4Radio;
    private javax.swing.JRadioButton cauhoi5Radio;
    private javax.swing.JRadioButton cauhoi6Radio;
    private javax.swing.JRadioButton cauhoi7Radio;
    private javax.swing.JRadioButton cauhoi8Radio;
    private javax.swing.JRadioButton cauhoi9Radio;
    private javax.swing.JRadioButton dRadio;
    private javax.swing.JTextField dongHoTextField;
    private javax.swing.ButtonGroup groupCauTraLoi;
    private javax.swing.ButtonGroup groupRadiCauHoi;
    private javax.swing.JButton nopBaiBtn;
    // End of variables declaration//GEN-END:variables
}
