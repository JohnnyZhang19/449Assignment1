import java.util.ArrayList;

public class machine_penalties {

    public int machinePenalty(Node node, ArrayList<ArrayList<Integer>> pairs){

      int nodeMachine = node.getPair().get(0);
      int nodeTask = node.getPair().get(1);

      return pairs.get(nodeMachine-1).get(nodeTask-1);
    }
    
}
