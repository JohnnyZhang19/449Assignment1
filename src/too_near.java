import java.util.ArrayList;

public class too_near {


    public boolean too_near_sort(Node node,ArrayList<ArrayList<String>> pairs, Node child) {

      //  int parent_machine = node.getPair().get(0);
        String parent_task = "";

      //  int child_machine = child.getPair().get(0);
        String child_task = "";
       
       // change parent task to letters
        if(node.getPair().get(1) == 1) {
            parent_task = "A";
        }
        else if(node.getPair().get(1) == 2) {
            parent_task = "B";
        }
        else if(node.getPair().get(1) == 3) {
            parent_task = "C";
        }
        else if(node.getPair().get(1) == 4) {
            parent_task = "D";
        }
        else if(node.getPair().get(1) == 5) {
            parent_task = "E";
        }
        else if(node.getPair().get(1) == 6) {
            parent_task = "F";
        }
        else if(node.getPair().get(1) == 7) {
            parent_task = "G";
        }
        else if(node.getPair().get(1) == 8) {
            parent_task = "H";
        }

        // change child tasks to letters
        if(child.getPair().get(1) == 1) {
          child_task = "A";
        }
        else if(child.getPair().get(1) == 2) {
            child_task = "B";
        }
        else if(child.getPair().get(1) == 3) {
          child_task = "C";
        }
        else if(child.getPair().get(1) == 4) {
          child_task = "D";
        }
        else if(child.getPair().get(1) == 5) {
          child_task = "E";
        }
        else if(child.getPair().get(1) == 6) {
          child_task = "F";
        }
        else if(child.getPair().get(1) == 7) {
          child_task = "G";
        }
        else if(child.getPair().get(1) == 8) {
          child_task = "H";
        }

        // check if parent and child task match invalid pair
        for (int i = 0; i < pairs.size(); i ++) {
            
            if(parent_task == pairs.get(i).get(0) && child_task == pairs.get(i).get(1)) {
                return false;
            }
        }

        return true;
    }
}
