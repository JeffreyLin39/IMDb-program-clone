package IMDb;

import java.util.*;
import java.io.*;

public class database {
    
    private BufferedReader dataset;
    private ArrayList<String> movieList;
    private HashSet<String> filters;
    private HashMap<String, movie> allMovies;
    //private HashMap<String, ArrayList<String>> moviesByGenre;


    public database(String fileName) throws IOException{
        
        try {
            dataset = new BufferedReader(new FileReader(fileName));
            allMovies = new HashMap<>();
            //moviesByGenre = new HashMap<>();
            movieList = new ArrayList<>();
            filters = new HashSet<String>();
            this.fillDatabase();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    private void fillDatabase() throws IOException {
        String line;
        int marker;
        
        Boolean shouldIgnore;
        Boolean ignoreLast;
        int lineNum;
        //String genre;
        lineNum = 1;
        ignoreLast = false;
        
        line = dataset.readLine();

        while ((line = dataset.readLine()) != null) {
            lineNum++;
            marker = 0;
            shouldIgnore = false;
            ArrayList<String> info = new ArrayList<>();

            for(int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    if (shouldIgnore) {
                        ignoreLast = true;
                        shouldIgnore = false;
                    }
                    else {
                        shouldIgnore = true;
                        marker++;
                    }
                }
                else if (!shouldIgnore && line.charAt(i) == ',') {
                    if(ignoreLast) {
                        info.add(line.substring(marker, i - 1));
                        ignoreLast = false;
                    }
                    else {
                        info.add(line.substring(marker, i));
                    }
                    marker = i + 1;
                }
            }

            try {
                movieList.add(info.get(0));
                allMovies.put(info.get(0), new movie(info.get(1), info.get(5), info.get(7), info.get(8), info.get(9), info.get(13), Integer.parseInt(info.get(3)), Integer.parseInt(info.get(6)), Double.parseDouble(info.get(14))));
                
                //marker = 1;
                //for(int i = 1; i < info.get(5).length(); i++) {
                //    if (info.get(5).charAt(i) == ',' || info.get(5).charAt(i) == '"') {
                //        genre = info.get(5).substring(marker, i);
                //        if (!moviesByGenre.containsKey(genre)) {
                //            moviesByGenre.put(genre, new ArrayList<String>());
                //        }
                //        moviesByGenre.get(genre).add(info.get(0));
                //        marker = i + 2;
                //    }
                //}
            }
            catch (Exception e) {
                System.out.println("Error in dataset on line: " + lineNum);
                System.out.println(e);
            }   
        }
    }

    //public Set<String> getGenreList(){
    //    return this.moviesByGenre.keySet();
    //}

    public int getSize(){
        return this.allMovies.size();
    }

    public void sort(int value, int start, int end) {
        int middle;

        if (start < end) {
            middle = (start + end) / 2;

            sort(value, start, middle);
            sort(value, middle + 1, end);
            merge(value, start, middle, end);
            
        }
    }

    public void merge(int value, int start, int middle, int end) {
        int pos1;
        int pos2;
        int count;
        ArrayList<String> temp = new ArrayList<>();

        pos1 = start;
        pos2 = middle + 1;
        count = start;

        while(pos1 <= middle && pos2 <= end) {
            if(value > 4){
                if (Integer.parseInt(this.allMovies.get(this.movieList.get(pos1)).getParameter(value)) < Integer.parseInt(this.allMovies.get(this.movieList.get(pos2)).getParameter(value))) {
                    temp.add(this.movieList.get(pos1));
                    pos1++;
                }
                else {
                    temp.add(this.movieList.get(pos2));
                    pos2++;
                }
            } 
            else {
                if (this.allMovies.get(this.movieList.get(pos1)).getParameter(value).compareTo(this.allMovies.get(this.movieList.get(pos2)).getParameter(value)) < 0) {
                    temp.add(this.movieList.get(pos1));
                    pos1++;
                }
                else {
                    temp.add(this.movieList.get(pos2));
                    pos2++;
                }

            }
        }

       while (pos1 <= middle) {
           temp.add(this.movieList.get(pos1));

           pos1++;
       }
      
       while (pos2 <= end) {
            temp.add(this.movieList.get(pos2));
            pos2++;
        }
        
        for (String item: temp) {
            this.movieList.set(count, item);
            count++;
        }

    }

    public movie getMovie(String id){
        return this.allMovies.get(id);
    }

    public void addFilter(String filter) {
        this.filters.add(filter);
    }

    public void printMovieList() {
        int size;
        int avgYear;
        int avgDuration;
        size = 0;
        avgYear = 0;
        avgDuration = 0;

        for (String item: movieList) {

            if(!filters.contains(this.allMovies.get(item).getParameter(2)) && !filters.contains(this.allMovies.get(item).getParameter(3)) && !filters.contains(this.allMovies.get(item).getParameter(4)) && !filters.contains(this.allMovies.get(item).getParameter(5)) && !filters.contains(this.allMovies.get(item).getParameter(6))){
                System.out.println(this.allMovies.get(item).getParameter(1));
                size++;
                avgYear += Integer.parseInt(this.allMovies.get(item).getParameter(5));
                avgDuration += Integer.parseInt(this.allMovies.get(item).getParameter(6));
            }

        }
        System.out.println(size + " results");
        System.out.println("Average year of release: " + avgYear/size);
        System.out.println("Average year of release: " + avgDuration/size);
        System.out.println();
    }

    public void searchMovies(String search) {
        for (String key: this.allMovies.keySet()) {
            if (key.contains(search)) {
                System.out.println(this.allMovies.get(key).getParameter(1));
            }
        }
    }

    public ArrayList<String> getMovieList() {
        return this.movieList;
    }



}
