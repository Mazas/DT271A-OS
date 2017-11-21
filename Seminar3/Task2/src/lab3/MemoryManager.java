//description of a memory manager
//in this simple case we assume only one process
//otherwise the we need a list of Page Tables instead of only one
//It is also assumed that frame size is equal to page size
package lab3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jof
 */
public class MemoryManager {
    private int NbrOfPages; //number of pages in virtual memory 
    private int PageSize; //the number of bytes in one page
    private int NbrOfFrames; //number of frames in physical memory
    private int[] pageTable; //pageTable[n] gives the physical address for page n
    //-1 if page is not in physical memory
    private byte[] RAM; //physical memory RAM
    private RandomAccessFile pageFile;
    private int freePos; //points to the frame where we should insert page
    private int pageFaults=0;

    private int cycles=0;

    public MemoryManager(int pages,int pageSize,int frames,String pFile){
        try {
            //initate the virtual memory
            NbrOfPages=pages;
            PageSize=pageSize;
            NbrOfFrames=frames;
            freePos=0;
            //create pageTable
            //initialy no pages loaded into physical memory
            pageTable=new int[NbrOfPages];
            for(int n=0;n<NbrOfPages;n++){
                pageTable[n]=-1;    
            }
            //allocate space for physical memory
            RAM=new byte[NbrOfFrames*PageSize];
            //initiate page file
            pageFile=new RandomAccessFile(pFile,"r");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MemoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public byte read(int logicalAddress){
        //called by a process to read memory from its logical address
        byte data=0;
        //calculate pageNumber and index from the logical address
        int pageNumber=logicalAddress/PageSize;
        int index = logicalAddress%PageSize;
        //check if we get a pageFault
        if(pageTable[pageNumber]==-1){
            //call method to solve page fault
            //pageFault(pageNumber);
            //the following two should be used in step 2 and 3 of the lab
            pageFaultFIFO(pageNumber);
            //pageFaultLRU(pageNumber);
        }
        //read data from RAM
        int frame=pageTable[pageNumber];
        int physicalAddress=frame*PageSize+index;
        data=RAM[physicalAddress];
        //print result
        System.out.print("Virtual address: "+logicalAddress);
        System.out.print(" Physical address: "+physicalAddress);
        System.out.println(" Value: "+data);
        return data;
    }
    
    //solve a page fault for page number pageNumber
    private void pageFault(int pageNumber){
        //this is the simple solution where we assume same size of physical and logical number
        pageFaults++;
        //load page into frame number freePos
        try {
            //read data from pageFile into RAM
            pageFile.seek(pageNumber*PageSize);
            for(int b=0;b<PageSize;b++) {
                RAM[freePos * PageSize + b] = pageFile.readByte();
            }
            if (cycles>0){
                for (int i = 0;i<pageTable.length;i++){
                    if (pageTable[i]==freePos){
                        pageTable[i]=-1;
                        break;
                    }
                }
            }
            pageTable[pageNumber]=freePos;
            freePos++;
        } catch (IOException ex) {
            Logger.getLogger(MemoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        //update position to store next page

    }
    
    //solve a page fault for page number pageNumber
    private void pageFaultFIFO(int pageNumber){
        //this solution allows different size of physical and logical number
        //page replacement using FIFO
        //freePos is used to point to next position
        if (freePos==(RAM.length/PageSize)){
            freePos=0;
            cycles++;
        }
        pageFault(pageNumber);
        
    }
    
    //solve a page fault for page number pageNumber
    private void pageFaultLRU(int pageNumber){
        //this solution allows different size of physical and logical number
        //victim is chosen by least recently used algorithm
        
        
    }
    
    public int getNbrOfPagefaults(){
        return pageFaults;
    }
}

