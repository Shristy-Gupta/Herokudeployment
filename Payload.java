import java.util.ArrayList;
import java.util.*;
import java.util.Collection;
import java.io.*;
import java.util.Scanner;

public class Payload {
    //This class has the Payload against which the algorithm will be tested
    //There will be four attributes
    //Document ID
    //Keywords
    //Document Name
    // BoomFilter
    int document_id;
    ArrayList<String> keywords=new ArrayList<>();
    String document_Name;
    int [] bloomfilter;
    static int NumofData=1700;
    @Override
    public String toString() {
        return "";
    }
    ArrayList<Payload> data(){

        ArrayList<Payload> data_val=new ArrayList<>();
        try{
            Configuration conn=new Configuration();
            File folder = new File(conn.relative_path_of_keyword_file);
            File filesList[] = folder.listFiles();
            Scanner sc = null;
            for(File file: filesList){
                Payload p1=new Payload();
                //Instantiating the Scanner class
                sc= new Scanner(file);
                String input,s,filename;
                filename=file.getName();
                StringBuffer sb = new StringBuffer();
                while (sc.hasNextLine()) {
                    input = sc.nextLine();
                    sb.append(input+" ");
                }
                //cleaning the
                s= sb.toString();
                s=s.toLowerCase(Locale.ROOT);
                s = s.replaceAll("\\s", "");
                String[] kw=s.split(",");
                p1.document_id=Integer.parseInt(filename.replace(".txt",""));
                Collections.addAll(p1.keywords,kw);
                p1.document_Name=filename;
                data_val.add(p1);
            }

        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }

        return  data_val;
    }
    ArrayList<Payload> getpayload(int s, int n_o_h){
        ArrayList<Payload> data_val=new ArrayList<Payload>(1700);
        try{
            Configuration conn=new Configuration();
            String location = conn.Output_path_for_bloomfilter;
            location += s+"_"+n_o_h;

            File folder = new File(location);
            File filesList[] = folder.listFiles();
            Scanner sc = null;
            for(File file: filesList){
                Payload p1=new Payload();
                //Instantiating the Scanner class
                sc= new Scanner(file);
                //System.out.println(file);
                p1.document_Name=file.getName();
                p1.document_id=Integer.parseInt(file.getName().replace(".txt",""));
                String bitmap,input;
                StringBuffer sb = new StringBuffer();
                while (sc.hasNextLine()) {
                    input = sc.nextLine();
                    sb.append(input+" ");
                }
                //cleaning the string
                bitmap= sb.toString();
                bitmap=bitmap.toLowerCase(Locale.ROOT);
                bitmap = bitmap.replaceAll("\\s", "");
                bitmap=bitmap.replace(" ","");
                //System.out.println("bl"+bitmap);
                int [] bloomfil=new int[s];
                int j=0;
                for(int i=0;i<bitmap.length();i++){
                    if(bitmap.charAt(i)=='0'){
                        bloomfil[j]=0;
                        j++;
                    }
                    else if(bitmap.charAt(i)=='1'){
                        bloomfil[j]=1;
                        j++;
                    }
                }
                p1.bloomfilter=bloomfil;
                data_val.add(p1);
            }
        }
        catch (Exception e){
            System.out.println("IN PAYLOAD"+e.getCause()+ e.getMessage());
        }
        return  data_val;
    }

}
