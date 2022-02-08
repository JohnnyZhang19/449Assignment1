import java.util.ArrayList;

public class hard {

    public boolean forced(Node node, ArrayList<ArrayList<String>> pairs){
        
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


    public boolean forbidden(Node node, ArrayList<ArrayList<String>> pairs){
        
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

}
