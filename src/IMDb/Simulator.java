package IMDb;

import java.io.*;

public class Simulator {
    public static void main(String[] args) throws IOException{

        final long startTime = System.currentTimeMillis();

        database data = new database("src/IMDb/dataset_full.csv");
        System.out.println("done");

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime)/1000.0);

    }
}
