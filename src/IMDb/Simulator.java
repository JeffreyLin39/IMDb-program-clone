package IMDb;

import java.io.*;

public class Simulator {
    public static void main(String[] args) throws IOException{

        final long startTime = System.currentTimeMillis();

        database data = new database("C:/Users/jeffr/Documents/ICS4U1/CPT/ics4u-cpt---data-visualization-jeffrey-lin/src/IMDb/dataset_subset.csv");
        data.sort(1, 0, data.getSize() - 1);
        
        final long endTime = System.currentTimeMillis();
        System.out.println("Total memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576 + " MB");
        System.out.println("Total execution time: " + (endTime - startTime)/1000.0 + "s");

    }
}
