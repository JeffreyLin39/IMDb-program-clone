package IMDb;

import java.io.*;

public class Simulator {
    public static void main(String[] args) throws IOException{

        database data = new database("src/IMDb/dataset_full.csv");
        System.out.println("done");
    }
}
