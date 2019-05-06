/*
 * Created by JFormDesigner on Sun May 05 01:29:07 CEST 2019
 */

package com.comtrade.view;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.threads.Server;
import com.comtrade.threads.ServerTimeThread;
import com.comtrade.threads.backupThreads.BatchThread;
import com.comtrade.threads.backupThreads.DataStorageThread;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Strahinja
 */
public class ServerUi extends JFrame {
    public ServerUi() {
        initComponents();
        setProporties();
    }

    private void setProporties() {
        timeText.setEditable(false);
        serverLogs.setEditable(false);
        backupLogs.setEditable(false);

    }

    private void button1ActionPerformed(ActionEvent e) {
        ControllerBLogic.getInstance();
        Server s = new Server(serverLogs);
        ServerTimeThread stt = new ServerTimeThread(timeText);
        // DATA THREAD AND BATCH THREAD
        BatchThread bt = new BatchThread(backupLogs, progressBar1);
        DataStorageThread dts = new DataStorageThread(serverLogs, backupLogs, progressBar1);
        // DATA THREAD AND BATCH THREAD
        bt.start();
        s.start();
        stt.start();
        dts.start();
        startServer.setEnabled(false);

    }

    private void button2ActionPerformed(ActionEvent e) {
        BatchThread bt = new BatchThread(backupLogs, progressBar1);
        bt.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Strahinja
        startServer = new JButton();
        scrollPane1 = new JScrollPane();
        serverLogs = new JTextArea();
        scrollPane2 = new JScrollPane();
        backupLogs = new JTextArea();
        progressBar1 = new JProgressBar();
        timeText = new JTextArea();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- startServer ----
        startServer.setText("START SERVER");
        startServer.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(startServer);
        startServer.setBounds(30, 35, 125, 55);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(serverLogs);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(185, 30, 305, 60);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(backupLogs);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(115, 135, 305, 60);

        //---- progressBar1 ----
        progressBar1.setStringPainted(true);
        progressBar1.setBackground(Color.white);
        contentPane.add(progressBar1);
        progressBar1.setBounds(35, 205, 475, 40);
        contentPane.add(timeText);
        timeText.setBounds(30, 10, 125, timeText.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(545, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Strahinja
    private JButton startServer;
    private JScrollPane scrollPane1;
    private JTextArea serverLogs;
    private JScrollPane scrollPane2;
    private JTextArea backupLogs;
    private JProgressBar progressBar1;
    private JTextArea timeText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
