/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.jui.main;

import com.greatcattle.business.consts.TableConst;
import com.greatcattle.business.service.TruckService;
import com.greatcattle.controllers.utils.UiUtil;
import com.greatcattle.dataservice.common.dto.PageModel;
import com.greatcattle.framework.IPageAction;
import com.greatcattle.framework.PageReqInfo;
import com.greatcattle.framework.PageService;
import com.greatcattle.framework.utils.SpringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class TruckFrm extends javax.swing.JPanel {

    private TruckService ts = null;
    /**
     * Creates new form truckFrm
     */
    public TruckFrm() {
        initComponents();
        ts = SpringUtils.getBean(TruckService.class);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        qryPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        truckNoJTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        resultPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        truckJTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        firstPage = new javax.swing.JButton();
        lastPage = new javax.swing.JButton();
        nextPage = new javax.swing.JButton();
        endPage = new javax.swing.JButton();
        pageIndexJTextField = new javax.swing.JTextField();

        setPreferredSize(UiUtil.getDefaultPanelSize());
        setLayout(new java.awt.BorderLayout());

        qryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("查询条件"));
        qryPanel.setOpaque(false);
        qryPanel.setPreferredSize(new java.awt.Dimension(602, 100));

        jLabel1.setText("车牌号：");

        truckNoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                truckNoJTextFieldActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/search.png"))); // NOI18N
        jButton1.setText("查询");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/add.png"))); // NOI18N
        jButton2.setText("新增");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout qryPanelLayout = new javax.swing.GroupLayout(qryPanel);
        qryPanel.setLayout(qryPanelLayout);
        qryPanelLayout.setHorizontalGroup(
            qryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qryPanelLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(truckNoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        qryPanelLayout.setVerticalGroup(
            qryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qryPanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(qryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(truckNoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        add(qryPanel, java.awt.BorderLayout.PAGE_START);

        resultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("查询结果"));
        resultPanel.setLayout(new java.awt.BorderLayout());

        truckJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        truckJTable.setToolTipText("");
        truckJTable.setName("TRUCK"); // NOI18N
        jScrollPane1.setViewportView(truckJTable);

        resultPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(788, 25));

        firstPage.setText("首页");
        firstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstPageActionPerformed(evt);
            }
        });

        lastPage.setText("上一页");
        lastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageActionPerformed(evt);
            }
        });

        nextPage.setText("下一页");
        nextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageActionPerformed(evt);
            }
        });

        endPage.setText("末页");
        endPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endPageActionPerformed(evt);
            }
        });

        pageIndexJTextField.setEditable(false);
        pageIndexJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pageIndexJTextField.setText("0/0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(firstPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pageIndexJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endPage)
                .addContainerGap(247, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextPage)
                    .addComponent(endPage)
                    .addComponent(lastPage)
                    .addComponent(firstPage)
                    .addComponent(pageIndexJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        resultPanel.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        add(resultPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.getPageService().queryPageAction();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddTruckFrm addTruckFrm = new AddTruckFrm(this);
        addTruckFrm.setLocationRelativeTo(null);
        addTruckFrm.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void firstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstPageActionPerformed
        this.getPageService().fistPageAction();
    }//GEN-LAST:event_firstPageActionPerformed

    private void truckNoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_truckNoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_truckNoJTextFieldActionPerformed

    private void nextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageActionPerformed
        this.getPageService().nextPageAction();
    }//GEN-LAST:event_nextPageActionPerformed

    private void lastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageActionPerformed
       this.getPageService().lastPageAction();
    }//GEN-LAST:event_lastPageActionPerformed

    private void endPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endPageActionPerformed
        this.getPageService().endPageAction();
    }//GEN-LAST:event_endPageActionPerformed

    public void callBack(){
        this.jButton1ActionPerformed(null);
    }
    
    public PageService getPageService(){
        Map queryParam = new HashMap();
        queryParam.put("truck_no",truckNoJTextField.getText() );
        PageReqInfo pageReqInfo = new PageReqInfo();
        pageReqInfo.setReqParam(queryParam);
        pageReqInfo.setjTable(truckJTable);
        pageReqInfo.setjTextFiled(pageIndexJTextField);
        PageService pageService = new PageService(new IPageAction(){
            @Override
            public PageModel executeAction(Map reqParam) {
                return ts.queryEntity(reqParam);
            }
        },pageReqInfo);
        return pageService;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton endPage;
    private javax.swing.JButton firstPage;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastPage;
    private javax.swing.JButton nextPage;
    private javax.swing.JTextField pageIndexJTextField;
    private javax.swing.JPanel qryPanel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JTable truckJTable;
    private javax.swing.JTextField truckNoJTextField;
    // End of variables declaration//GEN-END:variables
}