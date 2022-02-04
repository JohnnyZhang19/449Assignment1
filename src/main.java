public class main {

    public static void main(String[] args) {

        InputHandler myParser = new InputHandler();
        myParser.readInput("src\\test.txt");

        System.out.println(myParser.toString());
    }
}
