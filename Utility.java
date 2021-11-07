

import java.io.UnsupportedEncodingException;
import java.lang.Math;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class Utility {

    //Size of bloom filter

    /*
     * We need to return 10 hashes
     *
     * */
    void setUtility(int n, int no_hash_function){
//        N=n;
//        No_hash_function=no_hash_function;
    }
    //Hash1
    int hash1(String s, int size){
        int n=s.length();
        int hashval=0;
        for(int i=0;i<n;i++){
            hashval=(hashval+((int)s.charAt(i)));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    //Hash 2
    int hash2( String s, int size){
        int n=s.length();
        int hashval=1;
        for(int i=0;i<n;i++){
            Double val=Math.pow(19,i);
            hashval=(hashval+(((int)s.charAt(i))*(val.intValue()%size)%size));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    //Hash 3
    int hash3( String s, int size){
        int n=s.length();
        int hashval=7;
        for(int i=0;i<n;i++){
            hashval=(hashval*31+((int)s.charAt(i)));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    // Hash 4
    int hash4(String s, int size){
        int n=s.length();
        int hashval=3;
        int p=3;
        for(int i=0;i<n;i++){
            Double val=Math.pow(p,i);
            hashval= (hashval+(((hashval*7) % size+ (int)s.charAt(0))*(val.intValue()%size)%size));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    //Hash 5
    int hash5(String s, int size){
        int n=s.length();
        int hashval=11;
        for(int i=0;i<n;i++){
            hashval=(hashval*31+((int)s.charAt(0)));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    //Hash 6
    int hash6( String s, int size){
        int n=s.length();
        int hashval=13;
        int p=5;
        for(int i=0;i<n;i++){
            Double val=Math.pow(p,i);
            hashval= (hashval+(((hashval*11) % size+ (int)s.charAt(i))*(val.intValue()%size)%size));
            hashval= hashval%size;
        }
        return hashval%size;
    }
    //Hash 7
    int hash7(String str, int m)
    {
        int p = 31;
        int power_of_p = 1;
        int hash_val = 0;

        // Loop to calculate the hash value
        // by iterating over the elements of String
        for(int i = 0; i < str.length(); i++)
        {
            hash_val = (hash_val + (str.charAt(i) -
                    'a' + 1) * power_of_p) % m;
            power_of_p = (power_of_p * p) % m;
        }
        return hash_val;
    }
    //Hash 8
    int hash8(String str, int m)
    {
        int p = 47;
        int power_of_p = 1;
        int hash_val = 0;

        // Loop to calculate the hash value
        // by iterating over the elements of String
        for(int i = 0; i < str.length(); i++)
        {
            hash_val = (hash_val + (str.charAt(i) -
                    'a' + 1) * power_of_p) % m;
            power_of_p = (power_of_p * p) % m;
        }
        return hash_val;
    }
    //Hash 9
    int hash9(String str, int m)
    {
        int p = 67;
        int power_of_p = 1;
        int hash_val = 0;

        // Loop to calculate the hash value
        // by iterating over the elements of String
        for(int i = 0; i < str.length(); i++)
        {
            hash_val = (hash_val + (str.charAt(i) -
                    'a' + 1) * power_of_p) % m;
            power_of_p = (power_of_p * p) % m;
        }
        return hash_val;
    }
    //Hash 10
    int hash10(String str, int m)
    {
        int p = 83;
        int power_of_p = 1;
        int hash_val = 0;

        // Loop to calculate the hash value
        // by iterating over the elements of String
        for(int i = 0; i < str.length(); i++)
        {
            hash_val = (hash_val + (str.charAt(i) -
                    'a' + 1) * power_of_p) % m;
            power_of_p = (power_of_p * p) % m;
        }
        return hash_val;
    }

    int[] BitMap(String s,int N, int No_hash_function){

        int arr[]=new int[N];
        Arrays.fill(arr,0);
        s=s.toLowerCase(Locale.ROOT);
        s = s.replaceAll("\\s", "");
        try{

            for(int i=0; i<No_hash_function;i++){
                String process= "hash"+ (i+1);
                Method m = Utility.class.getDeclaredMethod(process, String.class, int.class);
                Object rv = m.invoke(this, s,N);
                if(rv!=null && (int)rv<N){
                    arr[(int)rv]=1;
                }
            }
        }
        catch (Exception E){
            System.out.println(E.getMessage());
        }
        return arr;
    }
    int [] OR_two_array(int arr1[], int arr2[],int N){

        int arr3[]=new int[N];
        Arrays.fill(arr3,0);
        for(int i=0;i<N;i++){
            arr3[i]=arr1[i] | arr2[i];
        }
        return arr3;
    }
    int [] and_two_array(int arr1[], int arr2[], int N){

        int arr3[]=new int[N];
        Arrays.fill(arr3,0);
        for(int i=0;i<N;i++){
            arr3[i]=arr1[i] & arr2[i];
        }
        return arr3;
    }
    boolean compare_two_array(int arr1[],int arr2[], int N){
        for(int i=0;i<N;i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }
    boolean match_two_filters(int arr1[], int arr2[], int N){
        int arr3[] = and_two_array(arr1,arr2,N);
        return compare_two_array(arr1,arr3,N);
    }
    String CleanString(String s){
        s=s.toLowerCase(Locale.ROOT);
        s = s.replaceAll("\\s", "");
        return s;
    }

}