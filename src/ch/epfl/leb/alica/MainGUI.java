/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.epfl.leb.alica;

import ch.epfl.leb.alica.analyzers.AnalyzerSetupPanel;
import ch.epfl.leb.alica.analyzers.autolase.AutoLaseSetupPanel;
import ch.epfl.leb.alica.analyzers.spotcounter.SpotCounterSetupPanel;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.ListDataListener;

/**
 *
 * @author stefko
 */
public final class MainGUI extends JFrame {
    private static MainGUI instance = null;
    
    private final AlicaCore alica_core;
    private final HashMap<String, AnalyzerSetupPanel> analyzer_setup_panels;
    
    
    /**
     * Creates new form MainGUI
     */
    private MainGUI(AlicaCore core) {
        if (core == null) {
            throw new NullPointerException("ALICA core must be initialized before instantiating the GUI!");
        } else {
            this.alica_core = core;
        }
        
        analyzer_setup_panels = new HashMap<String, AnalyzerSetupPanel>();
        analyzer_setup_panels.put("SpotCounter", new SpotCounterSetupPanel());
        analyzer_setup_panels.put("AutoLase", new AutoLaseSetupPanel());
        initComponents();
        updateAnalyzerSetupPanel("SpotCounter");
    }
    
    public static MainGUI initialize(AlicaCore core) {
        if (instance != null) {
            throw new AlreadyInitializedException("Main ALICA GUI was already initialized.");
        } else {
            instance = new MainGUI(core);
        }
        return instance;
    }
    
    public static MainGUI getInstance() {
        if (instance == null) {
            throw new NullPointerException("Main ALICA GUI was not yet initialized.");
        }
        return instance;
    }
    
    
    private void updateAnalyzerSetupPanel(String newPanelName) {
        analyzer_panel.removeAll();
        analyzer_panel.add(analyzer_setup_panels.get(newPanelName));
        analyzer_setup_panels.get(newPanelName).setBounds(5, 5, 200, 150);
        analyzer_setup_panels.get(newPanelName).revalidate();
        analyzer_setup_panels.get(newPanelName).repaint();
        analyzer_panel.revalidate();
        analyzer_panel.repaint();
        this.validate();
        this.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_source = new javax.swing.ButtonGroup();
        l_title = new javax.swing.JLabel();
        b_exit_plugin = new javax.swing.JButton();
        l_titletext = new javax.swing.JLabel();
        b_magic = new javax.swing.JButton();
        b_worker_start = new javax.swing.JButton();
        b_worker_stop = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rb_source_mmcore = new javax.swing.JRadioButton();
        rb_source_pipeline = new javax.swing.JRadioButton();
        analyzer_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_analyzer_setup = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        l_title.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        l_title.setText("ALICA");

        b_exit_plugin.setText("Exit");
        b_exit_plugin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_exit_pluginMouseClicked(evt);
            }
        });

        l_titletext.setText("Automated Laser Illumination Control Algorithm");

        b_magic.setText("Print Loaded Devices");
        b_magic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_magicMouseClicked(evt);
            }
        });

        b_worker_start.setText("Start");
        b_worker_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_worker_startMouseClicked(evt);
            }
        });

        b_worker_stop.setText("Stop");
        b_worker_stop.setEnabled(false);
        b_worker_stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_worker_stopMouseClicked(evt);
            }
        });

        jLabel1.setText("Image source");

        buttonGroup_source.add(rb_source_mmcore);
        rb_source_mmcore.setText("MM Core");

        buttonGroup_source.add(rb_source_pipeline);
        rb_source_pipeline.setText("Pipeline");

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

        cb_analyzer_setup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SpotCounter", "AutoLase" }));
        cb_analyzer_setup.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cb_analyzer_setupPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_magic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_exit_plugin))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(l_titletext)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cb_analyzer_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rb_source_pipeline)
                                    .addComponent(rb_source_mmcore)
                                    .addComponent(analyzer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_title)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(b_worker_start)
                        .addGap(18, 18, 18)
                        .addComponent(b_worker_stop)
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(l_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_titletext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_source_mmcore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_source_pipeline)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_analyzer_setup, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyzer_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_worker_stop)
                    .addComponent(b_worker_start))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_exit_plugin)
                    .addComponent(b_magic))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_exit_pluginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_exit_pluginMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_b_exit_pluginMouseClicked

    private void b_magicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_magicMouseClicked
        alica_core.printLoadedDevices();
    }//GEN-LAST:event_b_magicMouseClicked

    private void b_worker_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_worker_startMouseClicked
        b_worker_start.setEnabled(false);
        b_worker_stop.setEnabled(true);
        
        alica_core.setAnalyzer(analyzer_setup_panels.get(cb_analyzer_setup.getSelectedItem()).initAnalyzer());
        alica_core.startWorker();
    }//GEN-LAST:event_b_worker_startMouseClicked

    private void b_worker_stopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_worker_stopMouseClicked
        b_worker_start.setEnabled(true);
        b_worker_stop.setEnabled(false);
        alica_core.stopWorker();
    }//GEN-LAST:event_b_worker_stopMouseClicked

    private void cb_analyzer_setupPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cb_analyzer_setupPopupMenuWillBecomeInvisible
        updateAnalyzerSetupPanel((String) cb_analyzer_setup.getSelectedItem());
    }//GEN-LAST:event_cb_analyzer_setupPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel analyzer_panel;
    private javax.swing.JButton b_exit_plugin;
    private javax.swing.JButton b_magic;
    private javax.swing.JButton b_worker_start;
    private javax.swing.JButton b_worker_stop;
    private javax.swing.ButtonGroup buttonGroup_source;
    private javax.swing.JComboBox cb_analyzer_setup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel l_title;
    private javax.swing.JLabel l_titletext;
    private javax.swing.JRadioButton rb_source_mmcore;
    private javax.swing.JRadioButton rb_source_pipeline;
    // End of variables declaration//GEN-END:variables

    public static class AlreadyInitializedException extends RuntimeException {
        public AlreadyInitializedException(String message) {
            super(message);
        }
    }
}