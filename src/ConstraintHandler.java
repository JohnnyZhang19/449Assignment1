import java.util.ArrayList;

public class ConstraintHandler {
        
    // Forced partial assignment
    public boolean forcedPartialAssignment(Node node, ArrayList<ArrayList<String>> pairs){
            
        for(int i = 0; i < pairs.size(); i++)
        {
            String machineFPA = pairs.get(i).get(0);
            String taskFPA = pairs.get(i).get(1);

            String nodeTask = "";

            if(node.getPair().get(1) == 1) {
                nodeTask = "A";
            }
            else if(node.getPair().get(1) == 2) {
                nodeTask = "B";
            }
            else if(node.getPair().get(1) == 3) {
                nodeTask = "C";
            }
            else if(node.getPair().get(1) == 4) {
                nodeTask = "D";
            }
            else if(node.getPair().get(1) == 5) {
                nodeTask = "E";
            }
            else if(node.getPair().get(1) == 6) {
                nodeTask = "F";
            }
            else if(node.getPair().get(1) == 7) {
                nodeTask = "G";
            }
            else if(node.getPair().get(1) == 8) {
                nodeTask = "H";
            }
        
            if((node.getPair().get(0) == Integer.parseInt(machineFPA) && nodeTask != taskFPA))
            {
                return false;
            }
        }

        return true;
    }

    // Forbidden machine
    public boolean forbiddenMachine(Node node, ArrayList<ArrayList<String>> pairs){
            
        for(int i = 0; i < pairs.size(); i++)
        { // cycle through list containing forbidden machine/task pairs
            String machineFB = pairs.get(i).get(0);
            String taskFB = pairs.get(i).get(1);

            String nodeTask = "";

            if(node.getPair().get(1) == 1) {
                nodeTask = "A";
            }
            else if(node.getPair().get(1) == 2) {
                nodeTask = "B";
            }
            else if(node.getPair().get(1) == 3) {
                nodeTask = "C";
            }
            else if(node.getPair().get(1) == 4) {
                nodeTask = "D";
            }
            else if(node.getPair().get(1) == 5) {
                nodeTask = "E";
            }
            else if(node.getPair().get(1) == 6) {
                nodeTask = "F";
            }
            else if(node.getPair().get(1) == 7) {
                nodeTask = "G";
            }
            else if(node.getPair().get(1) == 8) {
                nodeTask = "H";
            }

            if(Integer.parseInt(machineFB) == node.getPair().get(0) && taskFB == nodeTask)
            {
                return false; // if current node is forbidden, return false. false because constraint failed
            }
            
        }
        return true;
    }

    // Too-near task
    public boolean tooNearTask(Node node,ArrayList<ArrayList<String>> pairs, Node child) {

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

    // Machine penalties
    public int machinePenalty(Node node, ArrayList<ArrayList<Integer>> pairs){

        int nodeMachine = node.getPair().get(0);
        int nodeTask = node.getPair().get(1);

        return pairs.get(nodeMachine-1).get(nodeTask-1);
    }

    // Too-near penalties
    public int tooNearPenalties(Node node, ArrayList<ArrayList<String>> TNP) {

        String node_task = "";
        String parent_task = "";

        // change node task to letters
        if(node.getPair().get(1) == 1) {
            node_task = "A";
        }
        else if(node.getPair().get(1) == 2) {
            node_task = "B";
        }
        else if(node.getPair().get(1) == 3) {
            node_task = "C";
        }
        else if(node.getPair().get(1) == 4) {
            node_task = "D";
        }
        else if(node.getPair().get(1) == 5) {
            node_task = "E";
        }
        else if(node.getPair().get(1) == 6) {
            node_task = "F";
        }
        else if(node.getPair().get(1) == 7) {
            node_task = "G";
        }
        else if(node.getPair().get(1) == 8) {
            node_task = "H";
        }

        // change node task to letters
        if(node.getParent().getPair().get(1) == 1) {
            parent_task = "A";
        }
        else if(node.getParent().getPair().get(1) == 2) {
            parent_task = "B";
        }
        else if(node.getParent().getPair().get(1) == 3) {
            parent_task = "C";
        }
        else if(node.getParent().getPair().get(1) == 4) {
            parent_task = "D";
        }
        else if(node.getParent().getPair().get(1) == 5) {
            parent_task = "E";
        }
        else if(node.getParent().getPair().get(1) == 6) {
            parent_task = "F";
        }
        else if(node.getParent().getPair().get(1) == 7) {
            parent_task = "G";
        }
        else if(node.getParent().getPair().get(1) == 8) {
            parent_task = "H";
        }
        
        int index = -1;

        // find the last penalty for the pair to use
        for(int i = 0; i < TNP.size(); i++) {

            if(parent_task == TNP.get(i).get(0) && node_task == TNP.get(i).get(1)) {
                index = i;
            }
        }

        // if the pair has a penalty value
        if(index != -1) {
            
            return Integer.parseInt(TNP.get(index).get(2));
        }
        else {
            return 0;
        }
    }
}
