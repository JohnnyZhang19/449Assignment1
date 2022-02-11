import java.util.ArrayList;

public class Too_Near_Penalty {
    InputHandler inputs = new InputHandler ();

    public int penalties(int p){
        int total = 0;
        int i = 0;
        ArrayList<String> pairs = inputs.getTNP ().get (i);
        while (i < pairs.size ()){
            ArrayList<String> first = inputs.getTNP ().get (i);
            ArrayList<String> next = inputs.getTNP ().get (i+1);
            String first_first = first.get (0);
            String first_second = first.get (1);
            String second_first = next.get (0);
            String second_second = next.get (1);
            p = Integer.parseInt (first.get (2));
            if (first_first.equals (second_first)
                    && first_second.equals (second_second)){
                total += p;
            }
            i++;
        }
        return total;
    }

}
