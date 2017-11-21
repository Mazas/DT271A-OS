/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seminar4e;
import java.io.File;
import java.util.Date;


public class Seminar4e {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //write welcome message and read input from user
        //print welcome message
        printWelcome();
        //read filename from user
        java.util.Scanner sc=new java.util.Scanner(System.in);
        String filename=sc.nextLine();
        //print result
        printResult(filename);
    }
    
    public static void printWelcome(){
        try {
            //start to open the current directory
            File file = new File(".");
            //then list all files in the directory
            //use the printDirectoryInfo of the current directory
            printDirectoryInfo(file);
            //ask the user to pick a file in the list
            System.out.println("Enter filename");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void printResult(String filename){
        //create a file object using the specified filename
        File file = new File(filename);
        //check to make sure that the file exists
        //if not print error message and exit
        if (!file.exists()){
            System.out.println("File does not exist.");
            return;
        }
        //check if file is a regular file or a directory
        //depending on the test call printFileInfo or printDirectoryInfo
        if (file.isDirectory()){
            printDirectoryInfo(file);
        }else if (file.isFile()){
            printFileInfo(file);
        }else {
            System.out.println("Error occurred.");
        }
    }
    
    public static void printFileInfo(File file){
        try {
            //print the following information about the file
            //1. complete filename including path
            System.out.println("\n"+file.getCanonicalPath());
            //2. length in bytes
            System.out.println("Size: "+file.length()+" bytes");
            //3. last time when the file was modified
            System.out.println("Last Modified: "+new Date(file.lastModified()));
            //(You need to convert into a readable form for instance with Date object)
            //4. Show if the file is readable, writeable and if it is a hidden file
            System.out.println("Writable: "+file.canWrite()+
                             "\nReadable: "+file.canRead()+
                             "\nHidden:   "+file.isHidden());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void printDirectoryInfo(File file){
        try {
            //print the following information about a directory
            //1. name of directory including path
            System.out.println(file.getCanonicalPath());
            //2. list of all files and directories located in this directory
            for (File fileName: file.listFiles()){
                System.out.printf("%-20s %10d kB%n",fileName.getName(),file.length()/1024);
            }

            //3. number of files in the directory
            System.out.println("\nNumber of files: "+file.list().length+"\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
