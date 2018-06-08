import java.io.*;
import java.util.*;

abstract class Cache {
    abstract public boolean read(String addr);  //simulates read at given address
    abstract public boolean write(String addr); //simulates write at given address
    abstract public void print();        //prints summary of cache contents
}

class DirectMappedCache extends Cache {

    private int logLineSize;	//log_2 of the number of bytes per cache line
    private int logNumLines;    //log_2 of the number of cache lines

    public DirectMappedCache(int logLineSize, int logNumLines) {
	//constructor that takes lengths of different fields
	this.logLineSize = logLineSize;
	this.logNumLines = logNumLines;
    }

    public boolean read(String addr) {
	System.out.print("Read from address " + addr + ": ");
	return false;
    }
	
    public boolean write(String addr) {
	System.out.print("Write to address  " + addr + ": ");
	return false;
    }

    public void print() {
    }
}

public class CacheSim {

    public static void main(String[] args) {
	//cache w/ 2^4=16 lines, each holding 2^3=8 bytes:
	Cache c = new DirectMappedCache(3,4);

	Scanner input = null;

	try {
	    input = new Scanner(new File("input.txt"));
	} catch(FileNotFoundException ex) {
	    System.err.println("Unable to find input file");
	    System.exit(1);
	}

	while(input.hasNextLine()) {
	    String line = input.nextLine();
	    Scanner lineScan = new Scanner(line);
	    try {
		String cmd = lineScan.next();
		if(cmd.equals("quit"))
		    System.exit(0);
		if(cmd.equals("read")) {
		    String addr = lineScan.next();
		    boolean hit = c.read(addr);
		    if(hit)
			System.out.println("Hit!");
		    else
			System.out.println("Miss");
		} else if(cmd.equals("write")) {
		    String addr = lineScan.next();
		    boolean hit = c.write(addr);
		    if(hit)
			System.out.println("Hit!");
		    else
			System.out.println("Miss");
		} else if(cmd.equals("print")) {
		    c.print();
		} else {
		    System.err.println("Unrecognized command: " + cmd);
		}
	    } catch(NoSuchElementException ex) {
		System.err.println("Invalid line: " + line);
	    }
	}
    }
}
