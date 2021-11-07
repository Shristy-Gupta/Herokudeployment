import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BloomfilterLookup {
    int size;
    int no_of_hashes;
    Utility ul=new Utility();
    BloomfilterLookup(int size, int no_of_hashes){
        this.size=size;
        this.no_of_hashes=no_of_hashes;
    }
    //This function will return the document id based on the bitmap
    ArrayList<Integer> lookup_id(String lookup){
        ArrayList<Integer> doc_id= new ArrayList<Integer>();

        Payload pl=new Payload();
        //create the map for the lookup
        lookup=ul.CleanString(lookup);
        try{
            ArrayList<Integer> index=new ArrayList<>();

            //This logic finds out the hash values for the string
            for(int i=0; i<no_of_hashes;i++){
                String process= "hash"+ (i+1);
                Method m = Utility.class.getDeclaredMethod(process, String.class, int.class);
                Object rv = m.invoke(ul, lookup,size);
                if(rv!=null && (int)rv<size){
                    index.add((int)rv);
                }
            }

            ArrayList<Payload> finalPayload=pl.getpayload(size,no_of_hashes);
            //This logic checks if  all the hash function is present in a particular document
            for(int i=0;i<finalPayload.size();i++){
                int bloom_filter[]=finalPayload.get(i).bloomfilter;
                boolean present = true;
                for(int j=0;j<index.size();j++){
                    if(index.get(j) < size && bloom_filter[index.get(j)]!=1){
                        present = false;
                        break;
                    }
                }
                if (present){
                    doc_id.add(finalPayload.get(i).document_id);
                }
            }

        }
        catch (Exception E){
            System.out.println("IN BLOOM FILTER LOOKUP"+ E.getMessage());
        }
        return doc_id;
    }
    int[] ResponsefoundbyBloomFilter(String s,ArrayList<Integer> lookupid,ArrayList<Payload> finalPayload){
        int n=0,falsepos=0;
        for(int i=0;i<lookupid.size();i++){
            int val=lookupid.get(i);
            List<Payload> p=finalPayload.stream().filter(e->e.document_id==val).collect(Collectors.<Payload>toList());
            Payload fp=p.get(0);
            boolean found = false;
            for (int j=0; j< fp.keywords.size();j++){
                if(ul.CleanString(fp.keywords.get(j)).equals(s)){
                    n++;
                    found = true;
                    break;
                }
            }
            if(!found){
                falsepos++;
            }
        }
        return new int[]{n,falsepos};
    }
    double Accuracy(String s, ArrayList<Integer> lookupid,ArrayList<Payload> finalPayload){
        //check all the document if the s actually exist
        //Compare if the results given by bloom filter contains error
        //Accuracy = Number of correct response given by bloom filter/ Overall correct resonse
        s=ul.CleanString(s);
        int[] _ResponsefoundbyBloomFilter=ResponsefoundbyBloomFilter(s,lookupid,finalPayload);
        return (double) _ResponsefoundbyBloomFilter[1]/16;

    }
}