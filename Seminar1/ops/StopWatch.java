package com.company;


import java.awt.EventQueue;
import javax.swing.JFrame;


//the actual stopwatch
public class StopWatch {
    private WatchGUI myGui;
    private int hours;
    private int min;
    private int sec;
    private int dsec; //1/10 second

    public StopWatch(){
        //initiate time
        hours=0;
        min=0;
        sec=0;
        dsec=0;
        //create GUI
        myGui = new WatchGUI(this);
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                myGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myGui.setVisible(true);
            }
        });
    }

    public void start(){
        System.out.println("start");
        tick();
    }

    public void stop(){
        System.out.println("stop");
    }

    public void reset(){
        System.out.println("reset");
        dsec=0;
        sec=0;
        min=0;
        hours=0;
        myGui.writeTime(String.format("%02d:%02d:%02d.%d",hours,min,sec,dsec));
    }

    public void tick(){
        dsec+=1;
        if(dsec>9){
            dsec=0;
            sec+=1;
        }
        if (sec==60){
            sec=0;
            min+=1;
        }
        if(min==60){
            min=0;
            hours+=1;
        }
        myGui.writeTime(String.format("%02d:%02d:%02d.%d",hours,min,sec,dsec));
    }
}
