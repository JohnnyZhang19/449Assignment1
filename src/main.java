import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        InputHandler myParser = new InputHandler();
        myParser.readInput("src\\test.txt");
        // System.out.println(myParser.toString());

        MNode root = new MNode();
        MTree myTree = new MTree(root);

        ArrayList<Integer> used = new ArrayList<Integer>();
        myTree.makeTree(root, myParser, 0);
    }
}
