package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Mazas on 9/7/2017.
 */
public class ThreadClass implements Runnable {
    private String command;
    private ErrorList errorList;
    ThreadClass(String command,ErrorList errorList){
        this.command=command;
        this.errorList=errorList;
    }

    @Override
    public void run() {


        List<String> input = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(command);
        while (tokenizer.hasMoreTokens()) {
            input.add(tokenizer.nextToken());
        }
        ProcessBuilder pb = new ProcessBuilder(input);
        // ProcessBuilder creates a process corresponding to the input command
        // now start the process
        BufferedReader br = null;

        try  {
            Process proc = pb.start();
            //obtain the input and output streams
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
           // read what the process returned
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (java.io.IOException ioe){
            errorList.addError(command);
            System.err.println("Error");
            System.err.println(ioe);
        }finally{
            try{
                if (br!=null) {
                    br.close();
                }
            }catch (Exception e) {//
            }
        }
    }
}
