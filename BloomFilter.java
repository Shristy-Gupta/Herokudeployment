
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.io.*;
import java.util.Scanner;
import com.opencsv.CSVWriter;

public class BloomFilter {
    Utility ul=new Utility();
    ArrayList<Payload> finalPayload;
    int size;
    int no_of_hashes;
    BloomFilter(int size, int no_of_hashes){
        this.size=size;
        this.no_of_hashes=no_of_hashes;
        add_bloomfilter_to_payload();
    }
    int [] bloomfilter(ArrayList<String> keywords){
        int N= size;
        int bitmap[] = new int[N];
        int _size=keywords.size();
        for(int i=0;i<_size;i++){
            int auxans[]=ul.BitMap(keywords.get(i),size,no_of_hashes);
            bitmap=ul.OR_two_array(bitmap,auxans,size);
        }
        return  bitmap;
    }
    //This function adds bitmap to the existing payload data
    //Call this function once to attach bloom filter to all the payloads
    //Implicit call will happen when bloom filter class is initialized
    void add_bloomfilter_to_payload(){
        //ArrayList<Payload> finalPayload;
        Payload pl=new Payload();
        finalPayload= pl.data();
        int n=finalPayload.size();
        for(int i=0;i<n;i++){
            Payload p=finalPayload.get(i);
            p.bloomfilter=bloomfilter(p.keywords);
        }

    }

    /*Below functions are add-ons to the bloom filter to analyse the data these data dont modify the bloom
     * filter functionality */

    void print_payload(){
        for(int i=0;i<finalPayload.size();i++){
            Payload p=finalPayload.get(i);
            System.out.println();
            System.out.println(p.document_id);
            System.out.println(p.document_Name);
            System.out.println(p.keywords);
            for (int j=0;j<size;j++) {
                System.out.print(p.bloomfilter[j]);}

        }
    }
    /*Write Payload is written to add the files in the for respective bloom filters for 1600 files
     * */
    void Write_payload(){
        Configuration conn=new Configuration();

        try {
            String con=conn.Output_path_for_bloomfilter;
            con+=size+"_"+no_of_hashes+"//";
            Path path = Paths.get(con);
            if(!Files.exists(path))
                Files.createDirectory(path);
            for(int i=0;i<finalPayload.size();i++){
                Payload p=finalPayload.get(i);
                String _con=con + p.document_Name;
                File file = new File(_con);
                // create FileWriter object with file as parameter
                FileWriter _outputfile = new FileWriter(file);
                // create CSVWriter object file writer object as parameter
                CSVWriter writer = new CSVWriter(_outputfile);
                String BLOOMFILTER="";
                for (int j=0;j<size;j++) {
                    BLOOMFILTER+=p.bloomfilter[j];
                }
                String[] data1={BLOOMFILTER};
                writer.writeNext(data1);
                // closing writer connection
                writer.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
