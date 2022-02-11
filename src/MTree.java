import java.util.ArrayList;

public class MTree {

    MNode root;

    int count = 0;

    MNode bestTreeLeaf = new MNode();
    int bestPVal = Integer.MAX_VALUE;

    // constraint handler
    ConstraintHandler cnstr = new ConstraintHandler();
    
    public MTree(MNode nd) {
        root = nd;
    }

    public void makeTree(MNode nd, InputHandler inp, int currentPVal) {

        nd.populate();

        

        if(nd.getMachine() != 8) {

            for(int i = 0; i < 8; i++ ) {

                ArrayList<Integer> anc = new ArrayList<Integer>();

                System.out.println(nd.getChild(i).getPair().toString());

                if(nd.getAncestry(anc).contains(nd.getChild(i).getTask())) {

                    continue;
                }

                // if forced partial assignment returns false
                if(!cnstr.forcedPartialAssignment(nd.getChild(i), inp.getFPA())) {
    
                    System.out.println("FPA Fail. Node pair: " + nd.getChild(i).getPair().toString());
                    continue;
                }
    
                // if forbidden machine returns false
                if(!cnstr.forbiddenMachine(nd.getChild(i), inp.getFM())) {
    
                    System.out.println("FM fail. Node pair: " + nd.getChild(i).getPair().toString());
                    continue;
                }
    
                // if too near task returns false
                if(!cnstr.tooNearTask(nd.getChild(i), inp.getTNT())) {
    
                    System.out.println("TNT fail. Node pair: " + nd.getChild(i).getPair().toString());
                    continue;
                }
    
                int newPVal = currentPVal + cnstr.machinePenalty(nd.getChild(i), inp.getMP()) + cnstr.tooNearPenalties(nd.getChild(i), inp.getTNP());
                
                makeTree(nd.getChild(i), inp, newPVal);
            }
        }
        else {
            count++;
        }

        System.out.println(count);
    }
}
