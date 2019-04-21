/*
 * Created by JFormDesigner on Sun Mar 31 19:32:58 CEST 2019
 */

package com.comtrade.view;

import com.comtrade.threads.Server;
import com.comtrade.threads.ServerTimeThread;
import com.comtrade.threads.backupThreads.DataThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Strahinja
 */
public class SwingServerUI extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Strahinja
    private JButton button1;
    private JButton button2;
    private JProgressBar progressBar1;
    private JScrollPane scrollPaneStart;
    private JTextArea textStart;
    private JScrollPane scrollPaneBackup;
    private JTextArea textBackup;
    private JTextArea timeText;

    public SwingServerUI() {
        initComponents();
        setProporties();
    }

    private void setProporties() {
        timeText.setEditable(false);
        textStart.setEditable(false);
    }

    private void button1ActionPerformed(ActionEvent e) {
        DataThread dbbt = new DataThread();
        Server s = new Server(textStart);
        ServerTimeThread stt = new ServerTimeThread(timeText);
        dbbt.start();
        s.start();
        stt.start();
        button1.setEnabled(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Strahinja
        button1 = new JButton();
        button2 = new JButton();
        progressBar1 = new JProgressBar();
        scrollPaneStart = new JScrollPane();
        textStart = new JTextArea();
        scrollPaneBackup = new JScrollPane();
        textBackup = new JTextArea();
        timeText = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("Start  Server");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(40, 35, 125, 50);

        //---- button2 ----
        button2.setText("Start Backup");
        contentPane.add(button2);
        button2.setBounds(40, 95, 125, 50);
        contentPane.add(progressBar1);
        progressBar1.setBounds(40, 165, 440, 20);

        //======== scrollPaneStart ========
        {
            scrollPaneStart.setViewportView(textStart);
        }
        contentPane.add(scrollPaneStart);
        scrollPaneStart.setBounds(195, 40, 285, 45);

        //======== scrollPaneBackup ========
        {
            scrollPaneBackup.setViewportView(textBackup);
        }
        contentPane.add(scrollPaneBackup);
        scrollPaneBackup.setBounds(195, 100, 285, 45);
        contentPane.add(timeText);
        timeText.setBounds(40, 10, 125, timeText.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
        setSize(500, 345);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
