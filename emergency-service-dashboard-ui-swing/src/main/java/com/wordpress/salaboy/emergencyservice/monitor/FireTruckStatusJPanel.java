/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.wordpress.salaboy.emergencyservice.monitor;

import com.wordpress.salaboy.model.FireTruck;
import com.wordpress.salaboy.model.serviceclient.PersistenceServiceProvider;
import java.awt.Color;
import java.util.Date;

/**
 *
 * @author salaboy
 */
public class FireTruckStatusJPanel extends javax.swing.JPanel {

    private String vehicleId;
    private final EmergencyMonitorPanel emergencyMonitorPanel;

    /**
     * Creates new form FireTruckStatusJPanel
     */
    public FireTruckStatusJPanel(EmergencyMonitorPanel emergencyMonitorPanel, String vehicleId) {
        this.vehicleId = vehicleId;
        this.setName(vehicleId);
        this.emergencyMonitorPanel = emergencyMonitorPanel;
        
        initComponents();
        vehicleIDLabel.setText(" => Vehicle: "+vehicleId);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jTruckStatusLabel = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        vehicleIDLabel = new javax.swing.JLabel();

        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 50));
        jProgressBar1.setSize(new java.awt.Dimension(146, 50));

        jTruckStatusLabel.setBackground(new java.awt.Color(51, 255, 0));
        jTruckStatusLabel.setForeground(new java.awt.Color(0, 255, 51));
        jTruckStatusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/png/fire-on.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/png/watertanl.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/png/overheat.png"))); // NOI18N

        vehicleIDLabel.setText("VehicleId: ");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel5))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jProgressBar2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(jTruckStatusLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(vehicleIDLabel)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTruckStatusLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addContainerGap(21, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(vehicleIDLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(48, 48, 48)
                .add(jProgressBar2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JLabel jTruckStatusLabel;
    private javax.swing.JLabel vehicleIDLabel;
    // End of variables declaration//GEN-END:variables

    public void decreaseWaterLevel() {
        FireTruck truck = (FireTruck) PersistenceServiceProvider.getPersistenceService().loadVehicle(vehicleId);
        jProgressBar1.setMaximum(truck.getTankSize());
        jProgressBar1.setValue(truck.getTankLevel());
    }

    public void fireTruckOutOfWater() {
        jProgressBar1.setValue(0);
        jProgressBar1.setBackground(Color.RED);
        jTruckStatusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/png/fire-off.png")));
        this.emergencyMonitorPanel.addAlert(vehicleId, new Date(), "Ran out of Water");
    }

    public void waterPumpOverHeat() {
        FireTruck truck = (FireTruck) PersistenceServiceProvider.getPersistenceService().loadVehicle(vehicleId);
        jProgressBar2.setMaximum(truck.getWaterPumpPower());
        jProgressBar2.setValue(truck.getTiltStatus());
        this.emergencyMonitorPanel.addAlert(vehicleId, new Date(), "Pump Overheated");
    }
    public void waterRefilled(){
        FireTruck truck = (FireTruck) PersistenceServiceProvider.getPersistenceService().loadVehicle(vehicleId);
        jProgressBar1.setValue(truck.getTankLevel());
        this.emergencyMonitorPanel.addAlert(vehicleId, new Date(), "Refilled");
    }
}
