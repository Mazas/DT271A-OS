/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class WatchGUI extends JFrame {
    private StopWatch myWatch; //need a reference to signal input
    private JTextArea textArea; //write status text
    private JLabel timeLabel; //the time
    private boolean running = false;

    public WatchGUI(StopWatch sWatch) {
        myWatch = sWatch;

        setTitle("StopWatch");
        setSize(500, 300);

        //ctreate buttons to control stopwatch
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.NORTH);
        //add listener to each button
        GUIListener startAction = new GUIListener("Start");
        startButton.addActionListener(startAction);
        GUIListener stopAction = new GUIListener("Stop");
        stopButton.addActionListener(stopAction);
        GUIListener resetAction = new GUIListener("Reset");
        resetButton.addActionListener(resetAction);

        //add a textArea to print status
        JPanel tPanel = new JPanel();
        textArea = new JTextArea(10, 44);
        tPanel.add(textArea);
        add(tPanel, BorderLayout.SOUTH);
        timeLabel = new JLabel("00:00:00.0");
        timeLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        JPanel timePanel = new JPanel();
        timePanel.add(timeLabel);
        add(timePanel, BorderLayout.CENTER);

    }


    public void writeStatus(String text) {
        textArea.append(text);
    }

    public void writeTime(String time) {
        timeLabel.setText(time);
    }

    private class GUIListener implements ActionListener {
        private String text;
        Thread thread ;


        public GUIListener(String message) {
            text = message;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            initThread();
            //notify in status that a button is clicked
            writeStatus(getCurrentTimeStamp());
            writeStatus(text);
            writeStatus(" button pressed\n");
            //notify my stopwatch that a button ha been clocked
            if (text == "Start") {
                if (!running) {
                    running = true;
                    try {
                        initThread();
                        myWatch.start();
                        this.thread.start();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            if (text == "Stop") {
                if (running) {
                    running = false;
                    myWatch.stop();
                    try {
                        this.thread.join();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            if (text == "Reset") {
                myWatch.reset();
            }
        }
        private void initThread(){
            this.thread = new Thread(() -> {
                System.out.print(Thread.currentThread().getId()+": ");
                while(running) {
                    try {
                        myWatch.tick();
                        Thread.sleep(100);
                    } catch (InterruptedException ex){
                        //
                    }
                }
            });
        }

        public String getCurrentTimeStamp() {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            strDate += ": ";
            return strDate;
        }
    }
}




