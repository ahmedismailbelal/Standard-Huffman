
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Abdelrahman Hany
 */
public class Huffman {

    /*
        File tap=new File("table.txt");
        PrintWriter table =new PrintWriter(tap);
     */
    public static void printCode(HuffmanNode root, String s) throws IOException {
        // FileWriter myWriter = new FileWriter("table.txt");

        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            System.out.println(root.c + ":" + s);

            // myWriter.write(root.c + ":" + s);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
        // myWriter.write(root.c + ":" + s);

        // myWriter.close();
    }
}
