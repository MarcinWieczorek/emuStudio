/*
 * ConfigDialog.java
 *
 * Created on Streda, 2008, január 2, 13:32
 */

package gui;

import terminal.*;
import gui.TerminalWindow;

/**
 *
 * @author  vbmacher
 */
@SuppressWarnings("serial")
public class ConfigDialog extends javax.swing.JDialog {
    private TerminalDisplay lblTerminal;
    private TerminalWindow parent;
    
    /** Creates new form ConfigDialog */
    public ConfigDialog(TerminalWindow parent, boolean modal,
            TerminalDisplay lblTerminal) {
        super(parent, modal);
        initComponents();
        this.radioHalf.setSelected(parent.isHalfDuplex());
        this.chkAlwaysOnTop.setSelected(parent.isAlwaysOnTop());
        this.lblTerminal = lblTerminal;
        this.chkAntiAliasing.setSelected(lblTerminal.isAntiAliasing());
        this.parent = parent;
        this.setLocationRelativeTo(parent);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        controlPanel = new javax.swing.JPanel();
        chkAlwaysOnTop = new javax.swing.JCheckBox();
        javax.swing.JButton btnClearScreen = new javax.swing.JButton();
        javax.swing.JButton btnRollLine = new javax.swing.JButton();
        chkAntiAliasing = new javax.swing.JCheckBox();
        radioFull = new javax.swing.JRadioButton();
        radioHalf = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Terminal configuration");

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Display"));

        chkAlwaysOnTop.setText("Always on top");
        chkAlwaysOnTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chkAlwaysOnTop.setFocusable(false);
        chkAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlwaysOnTopActionPerformed(evt);
            }
        });

        btnClearScreen.setText("Clear screen");
        btnClearScreen.setFocusable(false);
        btnClearScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearScreenActionPerformed(evt);
            }
        });

        btnRollLine.setText("Roll line");
        btnRollLine.setFocusable(false);
        btnRollLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRollLineActionPerformed(evt);
            }
        });

        chkAntiAliasing.setText("Use anti-aliasing");
        chkAntiAliasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAntiAliasingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(btnClearScreen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRollLine))
                    .addComponent(chkAlwaysOnTop)
                    .addComponent(chkAntiAliasing))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(chkAlwaysOnTop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAntiAliasing)
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearScreen)
                    .addComponent(btnRollLine))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(radioFull);
        radioFull.setSelected(true);
        radioFull.setText("Full duplex mode");
        radioFull.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radioFull.setFocusable(false);
        radioFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFullActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioHalf);
        radioHalf.setText("Half duplex mode");
        radioHalf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        radioHalf.setFocusable(false);
        radioHalf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHalfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioFull)
                    .addComponent(radioHalf)
                    .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioFull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioHalf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlwaysOnTopActionPerformed
        if (chkAlwaysOnTop.isSelected() == true) parent.setAlwaysOnTop(true);
        else parent.setAlwaysOnTop(false);
}//GEN-LAST:event_chkAlwaysOnTopActionPerformed

    private void btnClearScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearScreenActionPerformed
        lblTerminal.clear_screen();
}//GEN-LAST:event_btnClearScreenActionPerformed

    private void btnRollLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRollLineActionPerformed
        lblTerminal.roll_line();
}//GEN-LAST:event_btnRollLineActionPerformed

    private void radioFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFullActionPerformed
        parent.setHalfDuplex(false);
    }//GEN-LAST:event_radioFullActionPerformed

    private void radioHalfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHalfActionPerformed
        parent.setHalfDuplex(true);
    }//GEN-LAST:event_radioHalfActionPerformed

private void chkAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAntiAliasingActionPerformed
    lblTerminal.setAntiAliasing(chkAntiAliasing.isSelected());
}//GEN-LAST:event_chkAntiAliasingActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JCheckBox chkAlwaysOnTop;
    javax.swing.JCheckBox chkAntiAliasing;
    javax.swing.JPanel controlPanel;
    javax.swing.JRadioButton radioFull;
    javax.swing.JRadioButton radioHalf;
    // End of variables declaration//GEN-END:variables
    
}
