
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Abdelrahman Hany
 */
public class Main {

    static HashMap<Character, Integer> map_prob = new HashMap<Character, Integer>();

//static ArrayList<Character> chararr= new ArrayList<Character>();
//static ArrayList<Integer> frqarr= new ArrayList<Integer>();
    public static void decompress(File compress, File table) throws FileNotFoundException {

        Map<String, Character> map = new HashMap<String, Character>();

        Scanner tablef = new Scanner(table);
        Scanner compressf = new Scanner(compress);
        File origenal2 = new File("origenal2.txt");
        PrintWriter fout = new PrintWriter(origenal2);
        while (tablef.hasNextLine()) {
            String data = tablef.nextLine();
            map.put(data.substring(2, data.length()), data.charAt(0));
        }
        while (compressf.hasNextLine()) {
            String data2 = compressf.nextLine();
            // System.out.print(data2);
            boolean flag = true;
            for (int i = 0; i < data2.length(); i++) {
                flag = true;
                int key = i;

                while (flag) {
                    String sup = data2.substring(key, i + 1);
                    if (map.containsKey(sup)) {
                        // System.out.println(sup);
                        fout.write(map.get(sup));
                        flag = false;
                    } else {
                        //System.out.println(sup);
                        i++;
                    }
                }

            }
            fout.close();

        }

    }

    public static void compress(File origenal, File table) throws FileNotFoundException {
        Scanner origenalf = new Scanner(origenal);
        Scanner tablef = new Scanner(table);
        File compress = new File("compress.txt");
        PrintWriter fout = new PrintWriter(compress);

        Map<Character, String> map = new HashMap<Character, String>();
        while (tablef.hasNextLine()) {
            String data = tablef.nextLine();
            map.put(data.charAt(0), data.substring(2, data.length()));
        }
        while (origenalf.hasNextLine()) {
            String data2 = origenalf.nextLine();

            boolean flag = true;
            for (int i = 0; i < data2.length(); i++) {
                if (map.containsKey(data2.charAt(i))) {
                    fout.write(map.get(data2.charAt(i)));
                }

            }

        }
        fout.close();

    }

    public static void getprop(String data) {

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            Integer val = map_prob.get(c);
            if (val != null) {
                map_prob.put(c, new Integer(val + 1));
            } else {
                map_prob.put(c, 1);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner input = new Scanner(System.in);
        int choise;
        File compresss = new File("compress.txt");
        File original = new File("original.txt");
        int n_origenal=0;
        int n_compress=0;
        Scanner out = new Scanner(original);
        while (out.hasNextLine()) {
            String data = out.nextLine();
            n_origenal+=data.length();
            getprop(data);

        }

        Set<Character> keySet = map_prob.keySet();
        ArrayList<Character> chararr = new ArrayList<Character>(keySet);
        Collection<Integer> values = map_prob.values();
        ArrayList<Integer> frqarr = new ArrayList<Integer>(values);

        System.out.println("frequancy");
        System.out.println(chararr + " " + frqarr);

        Scanner s = new Scanner(System.in);

        PriorityQueue<HuffmanNode> q
                = new PriorityQueue<HuffmanNode>(chararr.size(), new MyComparator());

        for (int i = 0; i < chararr.size(); i++) {

            HuffmanNode hn = new HuffmanNode();

            hn.c = chararr.get(i);
            hn.data = frqarr.get(i);

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.data = x.data + y.data;
            f.c = '-';

            f.left = x;

            f.right = y;

            root = f;

            q.add(f);
        }

        Huffman.printCode(root, "");
        File table = new File("table.txt");

        compress(original, table);
        System.out.println("COmpression done");

        System.out.println("Enter 1 to decompression");
        choise = input.nextInt();
        if (choise == 1) {
            decompress(compresss, table);
            System.out.println("Dcompression done");
        }

        Scanner a =new Scanner(compresss);
        
         while (a.hasNextLine()) {
            String dataaa = a.nextLine();
            n_compress+=dataaa.length();
            

        }
         System.out.println("numder of char ="+n_origenal);
         System.out.println("numder of sample ="+n_compress);
         
        double ratio = (double) n_compress / (int)n_origenal*8;
            System.out.println("Compression Ratio = "+ratio);
    }
    
    

}
