import java.util.ArrayList;
import java.io.*;

public class main {

    public static void main(String[] args) {

        InputHandler myParser = new InputHandler();
        myParser.readInput(args[0] + ".txt");
        // System.out.println(myParser.toString());

        MNode root = new MNode();
        MTree myTree = new MTree(root);

        ArrayList<Integer> used = new ArrayList<Integer>();
        myTree.makeTree(root, myParser, 0);

        System.out.println(myTree.getBest());

        try{
            File newFile = new File(args[1] + ".txt");
            String str = myTree.getBest();
            BufferedWriter writer = new BufferedWriter(new FileWriter (newFile));
            writer.write(str);

            writer.close();
        }
            catch(Exception e){
                System.err.println("Error while creating output file!");
            }
    }
}
