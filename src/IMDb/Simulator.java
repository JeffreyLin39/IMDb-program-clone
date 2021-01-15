package IMDb;

import java.io.*;
import java.util.*;

public class Simulator {
    public static void main(String[] args) throws IOException{

        final long startTime = System.currentTimeMillis();

        database data = new database("src/IMDb/dataset_subset.csv");
        data.printMovieList();
        data.sort(1, 0, data.getSize() - 1, new ArrayList<String>());
        data.printMovieList();

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime)/1000.0);

    }
}
