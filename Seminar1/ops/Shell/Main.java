package com.company;



//This program is originally from the book Operating System //Concepts in Java,
// (Silberschatz) 7th Edition.
import java.io.*;
import java.util.*;

public class Main {
    static void createProcess(String command, ErrorList errorList) throws java.io.IOException {
        /*
        List<String> input = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(command);
        while (tokenizer.hasMoreTokens()) {
            input.add(tokenizer.nextToken());
        }
        ProcessBuilder pb = new ProcessBuilder(input);
        // ProcessBuilder creates a process corresponding to the input command
        // now start the process
        BufferedReader br = null;
        try {
            Process proc = pb.start();
            // obtain the input and output streams
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            // read what the process returned
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (java.io.IOException ioe) {
            System.err.println("Error");
            System.err.println(ioe);
        } finally {
            if (br != null) {
                br.close();
            }
        }
                */
        Thread thread = new Thread(new ThreadClass(command,errorList));
        thread.start();
    }

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        File wd;
        ErrorList errorList = new ErrorList();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");
        // we break out with ‘END’
        while (true) {
            // show the Java shell prompt and read what command they entered
            System.out.print("jsh>");
            commandLine = console.readLine();
            // if user entered a return, just loop again
            if (commandLine.equals("")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) { //User wants to end shell
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                System.exit(0);
            }else if (commandLine.equalsIgnoreCase("showerrlog")){
                errorList.showErrorLog();
                continue;
            }
            //Process the command entered by the user, by creating process
            //for (int i = 0; i < 5; i++) {
            createProcess(commandLine,errorList);

            //}
        }
    }
}
