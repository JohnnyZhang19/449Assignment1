import java.util.ArrayList;
import java.util.Collections;

public class MTree {

    MNode root;

    MNode bestTreeLeaf = new MNode();
    int bestPVal = Integer.MAX_VALUE;

    ArrayList<MNode> machineOne = new ArrayList<MNode>();

    // constraint handler
    ConstraintHandler cnstr = new ConstraintHandler();
    
    public MTree(MNode nd) {
        root = nd;
    }

    public void makeTree(MNode nd, InputHandler inp, int currentPVal) {

        nd.populate();

        // save all nodes with machine = 1
        if(root.getMachine() == 0) {

            for(int i = 0; i  < root.howManyChildren(); i++) {

                machineOne.add(root.getChild(i));
            }
        }

        if(nd.getMachine() != 8) {

            for(int i = 0; i < 8; i++ ) {

                ArrayList<Integer> anc = new ArrayList<Integer>();

                // System.out.println(nd.getChild(i).getPair().toString());

                if(nd.getAncestry(anc).contains(nd.getChild(i).getTask())) {

                    continue;
                }

                // if forced partial assignment returns false
                if(!cnstr.forcedPartialAssignment(nd.getChild(i), inp.getFPA())) {
    
                    // System.out.println("FPA Fail. Node pair: " + nd.getChild(i).getPair().toString());
                    // continue;
                }
    
                // if forbidden machine returns false
                if(!cnstr.forbiddenMachine(nd.getChild(i), inp.getFM())) {
    
                    // System.out.println("FM fail. Node pair: " + nd.getChild(i).getPair().toString());
                    // continue;
                }
    
                // if too near task returns false
                if(!cnstr.tooNearTask(nd.getChild(i), inp.getTNT())) {
    
                    // System.out.println("TNT fail. Node pair: " + nd.getChild(i).getPair().toString());
                    // continue;
                }
    
                int newPVal = currentPVal + cnstr.machinePenalty(nd.getChild(i), inp.getMP()) + cnstr.tooNearPenalties(nd.getChild(i), inp.getTNP());
                
                makeTree(nd.getChild(i), inp, newPVal);
            }
        }
        else {

            ArrayList<Integer> anc = new ArrayList<Integer>();
            int ancTask = nd.getAncestry(anc).get(7);
            
            if(!cnstr.tooNearTask(ancTask, nd.getTask(), inp.getTNT())) {

                currentPVal = Integer.MAX_VALUE;
            }

            int newPVal = currentPVal + cnstr.tooNearPenalties(ancTask, nd.getTask(), inp.getTNP());

            if(newPVal <= bestPVal) {

                bestPVal = newPVal;
                bestTreeLeaf = nd;
            }
        }
    }

    public String getBest() {
        ArrayList<Integer> anc = new ArrayList<Integer>();
        bestTreeLeaf.getAncestry(anc);

        Collections.reverse(anc);

        String outP = "\"Solution\"";

        String taskLetter = "";
        
        for(int e: anc) {

            if(e == 1) {
                taskLetter = "A";
            }
            else if(e == 2) {
                taskLetter = "B";
            }
            else if(e == 3) {
                taskLetter = "C";
            }
            else if(e == 4) {
                taskLetter = "D";
            }
            else if(e == 5) {
                taskLetter = "E";
            }
            else if(e == 6) {
                taskLetter = "F";
            }
            else if(e == 7) {
                taskLetter = "G";
            }
            else if(e == 8) {
                taskLetter = "H";
            }

            outP = outP + " " + taskLetter;
        }

        outP = outP + "\"; Quality:\"" + bestPVal;

        return outP;
    }

}
