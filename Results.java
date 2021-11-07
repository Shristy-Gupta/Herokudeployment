import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import com.opencsv.CSVWriter;
public class Results {


    public static void main(String args[]) {
        //Please provide the Static Paths in configuration file<<<IMPORTANT>>>
        int[] hashfunction = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] size = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        BloomFilter bl;
        //This block of code creates the Bloom Filter for every value of hash and size of Bloom filter in the Bloomfilter


            for (int i = 0; i < hashfunction.length; i++) {
                for (int j = 0; j < size.length; j++) {
                    bl = new BloomFilter(size[j], hashfunction[i]);
                    bl.Write_payload();
                }
            }

        BloomfilterLookup searchkeyword;

            // This block finds the false positives for the different number of Hash Functions and Bloomfilter size and stores in output.csv file
            /*
            String Keyword = "Cloud";
            Configuration conn = new Configuration();
            File file = new File(conn.output_path_for_results);

            ArrayList<Payload> finalPayload = new Payload().data();
            try {
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file);

                // create CSVWriter object file writer object as parameter
                CSVWriter writer = new CSVWriter(outputfile);

                // adding header to csv
                String[] header = {"Number of Hashes", "Size/n", "False positive"};
                writer.writeNext(header);
                for (int i = 0; i < hashfunction.length; i++) {
                    for (int j = 0; j < size.length; j++) {
                        searchkeyword = new BloomfilterLookup(size[j], hashfunction[i]);
                        ArrayList<Integer> al = searchkeyword.lookup_id(Keyword);
                        String[] data1 = new String[3];
                        data1[0] = String.valueOf(hashfunction[i]);
                        data1[1] = String.valueOf(size[j]);
                        data1[2] = String.valueOf(searchkeyword.Accuracy(Keyword, al, finalPayload));
                        // add data to csv
                        writer.writeNext(data1);
                    }
                }
                // closing writer connection
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            */
        }
    }