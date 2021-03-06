/* 
 * Copyright (C) 2017 Laboratory of Experimental Biophysics
 * Ecole Polytechnique Federale de Lausanne
 * 
 * Author: Marcel Stefko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.epfl.leb.alica;

import ij.IJ;
import ij.gui.YesNoCancelDialog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.micromanager.internal.MMStudio;

/**
 * Main controlling GUI for the ALICA plugin. This is a singleton which
 * is shown every time the plugin is invoked from MM menu.
 * @author Marcel Stefko
 */
public final class MainGUI extends JFrame {
    private static MainGUI instance = null;
    
    private boolean log_is_saved = true;
    
    private final AlicaCore alica_core;
    
    
    /**
     * Creates new form MainGUI, private to prevent outside instantiation
     */
    private MainGUI(AlicaCore core) {
        super();
        if (core == null) {
            // force initialization by using internal method
            this.alica_core = AlicaCore.initialize(MMStudio.getInstance());
            throw new NullPointerException("ALICA core must be initialized before instantiating the GUI!");
        } else {
            this.alica_core = core;
        }
        
        initComponents();

        // Populate the dropdown menus of analyzer, controller and laser
        cb_analyzer_setup.removeAllItems();
        for (String key: alica_core.getAnalyzerFactory().getProductNameList()) {
            cb_analyzer_setup.addItem(key);
        }
        cb_analyzer_setup.setSelectedItem(alica_core.getAnalyzerFactory().getSelectedProductName());
        
        cb_controller_setup.removeAllItems();
        for (String key: alica_core.getControllerFactory().getProductNameList()) {
            cb_controller_setup.addItem(key);
        }
        cb_controller_setup.setSelectedItem(alica_core.getControllerFactory().getSelectedProductName());
        
        cb_laser_setup.removeAllItems();
        for (String key: alica_core.getLaserFactory().getPossibleLasers()) {
            cb_laser_setup.addItem(key);
        }
        cb_laser_setup.setSelectedItem(alica_core.getLaserFactory().getSelectedDeviceName());
        
        // update the setup panels
        updateAnalyzerSetupPanel();
        updateControllerSetupPanel();
    }
    
    /**
     * Singleton initializer
     * @param core AlicaCore singleton
     * @return the GUI instance
     */
    public static MainGUI initialize(AlicaCore core) {
        if (instance != null) {
            throw new AlreadyInitializedException("Main ALICA GUI was already initialized.");
        } else {
            instance = new MainGUI(core);
        }
        return instance;
    }
    
    /**
     *
     * @return the GUI singleton instance
     */
    public static MainGUI getInstance() {
        if (instance == null) {
            instance = initialize(AlicaCore.getInstance());
        }
        return instance;
    }
    
    
    private void updateAnalyzerSetupPanel() {
        analyzer_panel.removeAll();
        javax.swing.JPanel panel = alica_core.getAnalyzerFactory().getSelectedSetupPanel();
        if (panel != null) {
            analyzer_panel.add(panel);
            panel.setBounds(5,5,200,150);
            panel.revalidate();
            panel.repaint();
        }
    }
    
    private void updateControllerSetupPanel() {
        controller_panel.removeAll();
        javax.swing.JPanel panel = alica_core.getControllerFactory().getSelectedSetupPanel();
        if (panel != null) {
            controller_panel.add(panel);
            panel.setBounds(5,5,200,150);
            panel.revalidate();
            panel.repaint();
        }
    }
    
    /**
     *  Query the MM core for possible properties of the selected laser device,
     *  and populate the dropdown menu.
     */
    private void updateLaserPropertyList() {
        cb_laser_properties.removeAllItems();
        try {
            for (String s: alica_core.getLaserFactory().getSelectedDeviceProperties()) {
                cb_laser_properties.addItem(s);
            }
        } catch (Exception ex) {
            AlicaLogger.getInstance().logError(ex, "Error in getting selected device properties.");
            return;
        }
        try {
            cb_laser_properties.setSelectedIndex(0);
        } catch (IllegalArgumentException ex) {
            return;
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

        buttonGroup_imaging_mode = new javax.swing.ButtonGroup();
        l_title = new javax.swing.JLabel();
        b_exit_plugin = new javax.swing.JButton();
        l_titletext = new javax.swing.JLabel();
        b_print_loaded_devices = new javax.swing.JButton();
        b_worker_start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rb_source_mmcore = new javax.swing.JRadioButton();
        rb_source_live_pipeline = new javax.swing.JRadioButton();
        analyzer_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_analyzer_setup = new javax.swing.JComboBox();
        controller_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cb_controller_setup = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cb_laser_setup = new javax.swing.JComboBox();
        cb_laser_properties = new javax.swing.JComboBox();
        chkb_laser_is_virtual = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        e_laser_max_power = new javax.swing.JTextField();
        rb_source_acquisition = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        e_controller_tickrate = new javax.swing.JTextField();
        b_save_last_run_log = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        e_laser_deadzone_pct = new javax.swing.JTextField();
        l_roi_is_set = new javax.swing.JLabel();
        b_set_roi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ALICA Setup");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        l_title.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        l_title.setText("ALICA");

        b_exit_plugin.setText("Exit");
        b_exit_plugin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_exit_pluginMouseClicked(evt);
            }
        });

        l_titletext.setText("Automated Laser Illumination Control Algorithm");

        b_print_loaded_devices.setText("Print Loaded Devices");
        b_print_loaded_devices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_print_loaded_devicesMouseClicked(evt);
            }
        });

        b_worker_start.setText("Start");
        b_worker_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_worker_startActionPerformed(evt);
            }
        });

        jLabel1.setText("Image source:");

        buttonGroup_imaging_mode.add(rb_source_mmcore);
        rb_source_mmcore.setSelected(true);
        rb_source_mmcore.setText("MM Core");

        buttonGroup_imaging_mode.add(rb_source_live_pipeline);
        rb_source_live_pipeline.setText("Live mode");

        analyzer_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        analyzer_panel.setPreferredSize(new java.awt.Dimension(210, 160));

        javax.swing.GroupLayout analyzer_panelLayout = new javax.swing.GroupLayout(analyzer_panel);
        analyzer_panel.setLayout(analyzer_panelLayout);
        analyzer_panelLayout.setHorizontalGroup(
            analyzer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        analyzer_panelLayout.setVerticalGroup(
            analyzer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        jLabel2.setText("Analyzer:");

        cb_analyzer_setup.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cb_analyzer_setupPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        controller_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout controller_panelLayout = new javax.swing.GroupLayout(controller_panel);
        controller_panel.setLayout(controller_panelLayout);
        controller_panelLayout.setHorizontalGroup(
            controller_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        controller_panelLayout.setVerticalGroup(
            controller_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setText("Controller:");

        cb_controller_setup.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cb_controller_setupPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setText("Laser:");

        cb_laser_setup.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cb_laser_setupPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cb_laser_properties.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cb_laser_propertiesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        chkb_laser_is_virtual.setText("Virtual");

        jLabel5.setText("Max power:");

        e_laser_max_power.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        e_laser_max_power.setText("50");

        buttonGroup_imaging_mode.add(rb_source_acquisition);
        rb_source_acquisition.setText("Next acquisition");

        jLabel6.setText("Controller tick rate [ms]:");

        e_controller_tickrate.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        e_controller_tickrate.setText("500");

        b_save_last_run_log.setText("Save last run log");
        b_save_last_run_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_save_last_run_logActionPerformed(evt);
            }
        });

        jLabel7.setText("Deadzone [%]:");

        e_laser_deadzone_pct.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        e_laser_deadzone_pct.setText("10");

        l_roi_is_set.setText("ROI: Not set");

        b_set_roi.setText("Set  ROI");
        b_set_roi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_set_roiActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/leb_logo_small.png"))); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(analyzer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(controller_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cb_laser_properties, 0, 137, Short.MAX_VALUE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(e_laser_max_power, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(e_laser_deadzone_pct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(chkb_laser_is_virtual))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rb_source_live_pipeline)
                                                    .addComponent(rb_source_acquisition)
                                                    .addComponent(rb_source_mmcore))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb_analyzer_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(cb_controller_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(cb_laser_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(e_controller_tickrate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(l_roi_is_set)
                                                .addGap(65, 65, 65)
                                                .addComponent(b_set_roi))))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(l_titletext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(b_save_last_run_log)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_worker_start)
                                .addGap(93, 93, 93)
                                .addComponent(b_print_loaded_devices)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_exit_plugin))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_title)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_titletext))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(rb_source_mmcore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_source_live_pipeline)
                    .addComponent(b_set_roi)
                    .addComponent(l_roi_is_set))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_source_acquisition)
                    .addComponent(jLabel6)
                    .addComponent(e_controller_tickrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_analyzer_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cb_controller_setup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cb_laser_setup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(controller_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(analyzer_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_exit_plugin)
                            .addComponent(b_print_loaded_devices)
                            .addComponent(b_worker_start)
                            .addComponent(b_save_last_run_log))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_laser_properties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(e_laser_max_power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(e_laser_deadzone_pct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkb_laser_is_virtual)
                        .addContainerGap(93, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_exit_pluginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_exit_pluginMouseClicked
        // do not dispose of the singleton, only hide
        this.setVisible(false);
    }//GEN-LAST:event_b_exit_pluginMouseClicked

    private void b_print_loaded_devicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_print_loaded_devicesMouseClicked
        alica_core.printLoadedDevices();
    }//GEN-LAST:event_b_print_loaded_devicesMouseClicked

    private void cb_analyzer_setupPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cb_analyzer_setupPopupMenuWillBecomeInvisible
        alica_core.getAnalyzerFactory().selectProduct((String) cb_analyzer_setup.getSelectedItem());
        updateAnalyzerSetupPanel();
    }//GEN-LAST:event_cb_analyzer_setupPopupMenuWillBecomeInvisible

    private void cb_controller_setupPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cb_controller_setupPopupMenuWillBecomeInvisible
        alica_core.getControllerFactory().selectProduct((String) cb_controller_setup.getSelectedItem());
        updateControllerSetupPanel();
    }//GEN-LAST:event_cb_controller_setupPopupMenuWillBecomeInvisible

    private void cb_laser_setupPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cb_laser_setupPopupMenuWillBecomeInvisible
        alica_core.getLaserFactory().selectDevice((String) cb_laser_setup.getSelectedItem());
        updateLaserPropertyList();
    }//GEN-LAST:event_cb_laser_setupPopupMenuWillBecomeInvisible

    private void cb_laser_propertiesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cb_laser_propertiesPopupMenuWillBecomeInvisible
        alica_core.getLaserFactory().selectProperty((String) cb_laser_properties.getSelectedItem());
    }//GEN-LAST:event_cb_laser_propertiesPopupMenuWillBecomeInvisible

    private void b_worker_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_worker_startActionPerformed
        if (this.log_is_saved == false) {
            YesNoCancelDialog dlg = new YesNoCancelDialog(this, "Save log?", "Log from previous run is unsaved and will be discarded. Save now?");
            if (dlg.cancelPressed()) {
                return;
            } else if (dlg.yesPressed()) {
                if (!AlicaLogger.getInstance().saveLog())
                    return;
                else 
                    this.log_is_saved = true;
            } else {
                this.log_is_saved = true;
            }
        }
        // parse laser power entry
        double max_laser_power;
        try {
            max_laser_power = Double.parseDouble(e_laser_max_power.getText());
        } catch (NumberFormatException ex) {
            AlicaLogger.getInstance().showMessage("Error in parsing max laser power value.");
            return;
        }
        if (max_laser_power<=0.0) {
            AlicaLogger.getInstance().showMessage("Max laser power must be positive.");
            return;
        }
        alica_core.setMaxLaserPower(max_laser_power);
        
        // parse laser power change deadzone
        double laser_power_deadzone_pct;
        try {
            laser_power_deadzone_pct = Double.parseDouble(e_laser_deadzone_pct.getText());
        } catch (NumberFormatException ex) {
            AlicaLogger.getInstance().showMessage("Error in parsing laser power value.");
            return;
        }
        // value sanity check
        if (laser_power_deadzone_pct>50 || laser_power_deadzone_pct<0) {
            AlicaLogger.getInstance().showMessage("Laser power deadzone must be between 0% and 50%.");
            return;
        }
        // convert from percentage
        alica_core.setLaserPowerDeadzone(laser_power_deadzone_pct/100);
        
        // parse laser virtual
        alica_core.setLaserVirtual(chkb_laser_is_virtual.isSelected());
        
        // parse imaging mode
        ImagingMode imaging_mode;
        if (rb_source_mmcore.isSelected())
            imaging_mode = ImagingMode.GRAB_FROM_CORE;
        else if (rb_source_live_pipeline.isSelected())
            imaging_mode = ImagingMode.LIVE;
        else
            imaging_mode = ImagingMode.NEXT_ACQUISITION;
        
        // parse controller tick rate
        int controller_tick_rate;
        try {
            controller_tick_rate = Integer.parseInt(e_controller_tickrate.getText());
        } catch (NumberFormatException ex) {
            AlicaLogger.getInstance().showMessage("Error in parsing controller tick rate.");
            return;
        }
        if (controller_tick_rate<50) {
            AlicaLogger.getInstance().showMessage("Controller tick rate must be at least 50ms.");
            return;
        }
        alica_core.setControlWorkerTickRate(controller_tick_rate);
        

        
        // launch the worker
        try {
            alica_core.startWorkers(imaging_mode);
        } catch (RuntimeException ex) {
            AlicaLogger.getInstance().showError(ex, "Error in worker initialization.");
            return;
        }
        this.log_is_saved = false;
    }//GEN-LAST:event_b_worker_startActionPerformed

    private void b_save_last_run_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_save_last_run_logActionPerformed
        AlicaLogger.getInstance().saveLog();
        this.log_is_saved = true;
    }//GEN-LAST:event_b_save_last_run_logActionPerformed

    private void b_set_roiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_set_roiActionPerformed
        boolean is_set = alica_core.setCurrentROI();
        if (is_set)
            l_roi_is_set.setText("ROI: Set");
        else
            l_roi_is_set.setText("ROI: Not set");
    }//GEN-LAST:event_b_set_roiActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel analyzer_panel;
    private javax.swing.JButton b_exit_plugin;
    private javax.swing.JButton b_print_loaded_devices;
    private javax.swing.JButton b_save_last_run_log;
    private javax.swing.JButton b_set_roi;
    private javax.swing.JButton b_worker_start;
    private javax.swing.ButtonGroup buttonGroup_imaging_mode;
    private javax.swing.JComboBox cb_analyzer_setup;
    private javax.swing.JComboBox cb_controller_setup;
    private javax.swing.JComboBox cb_laser_properties;
    private javax.swing.JComboBox cb_laser_setup;
    private javax.swing.JCheckBox chkb_laser_is_virtual;
    private javax.swing.JPanel controller_panel;
    private javax.swing.JTextField e_controller_tickrate;
    private javax.swing.JTextField e_laser_deadzone_pct;
    private javax.swing.JTextField e_laser_max_power;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel l_roi_is_set;
    private javax.swing.JLabel l_title;
    private javax.swing.JLabel l_titletext;
    private javax.swing.JRadioButton rb_source_acquisition;
    private javax.swing.JRadioButton rb_source_live_pipeline;
    private javax.swing.JRadioButton rb_source_mmcore;
    // End of variables declaration//GEN-END:variables

    /**
     * Thrown if the GUI singleton is attempted to be initialized for a second
     * time.
     */
    public static class AlreadyInitializedException extends RuntimeException {

        /**
         * 
         * @param message message of the exception.
         */
        public AlreadyInitializedException(String message) {
            super(message);
        }
    }
}