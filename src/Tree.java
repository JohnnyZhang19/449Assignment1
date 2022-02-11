import java.util.ArrayList;

public class Tree {
    
    MNode realRoot;
    ArrayList<MNode> machineOne = new ArrayList<MNode>();

    MNode bestTreeLeaf = new MNode();
    int bestPenVal = Integer.MAX_VALUE;

    public Tree(MNode root) {
        realRoot = root;
    }

    public void makeTree(InputHandler inpFile, MNode root, ArrayList<Integer> usedTasks, int score) {

        // current penalty value
        int penVal = score;

        // for testing constraints
        ConstraintHandler cnstr = new ConstraintHandler();

        // create children
        root.populate();

        // save all nodes with machine = 1
        if(root.getMachine() == 0) {

            System.out.println("Adding children to root");

            for(int i = 0; i  < root.howManyChildren(); i++) {

                machineOne.add(root.getChild(i));
            }
        }

        // add task to used tasks
        usedTasks.add(root.getPair().get(1));

        // if current machine is not equal to 8
        if(root.getMachine() != 8) {

            System.out.println("Not equal to 8. Value = " + root.getPair().get(1));
            System.out.println("Number of children: " + root.howManyChildren());
            System.out.println("Penalty Value: " + penVal);

            // iterate through children
            for(int i = 0; i < root.howManyChildren(); i++) {

                System.out.println("Child: " + i + " Pair Info: " + root.getChild(i).getPair().get(0) + ", " + root.getChild(i).getPair().get(1));

                int newPenVal = 0;

                // remove child if task is already used
                if(usedTasks.contains(root.getChild(i).getPair().get(1))) {
                    
                    System.out.println("TASK ALREADY USED");
                    continue;
                }

                // remove child if it fails any hard constrains
                if(!cnstr.forcedPartialAssignment(root.getChild(i), inpFile.getFPA()) ||
                    !cnstr.forbiddenMachine(root.getChild(i), inpFile.getFM()) ||
                    !cnstr.tooNearTask(root.getChild(i), inpFile.getTNT())) {

                    System.out.println("FAILED HARD CONSTRAINT");
                    continue;
                }

                // sum of penalty values until this node
                newPenVal = penVal + cnstr.machinePenalty(root.getChild(i), inpFile.getMP()) + cnstr.tooNearPenalties(root.getChild(i), inpFile.getTNP());

                // recursive call
                makeTree(inpFile, root.getChild(i), usedTasks, newPenVal);
            }
        }
        else {

            System.out.println("Machine 1 set size: " + machineOne.size());

            // look at nodes with machine = 1 as children if current node machine = 8
            for(int i = 0; i < machineOne.size(); i++) {

                int newPenVal = 0;

                // temporarily change parent of machine one node
                machineOne.get(i).setParent(root);

                if(!cnstr.tooNearTask(machineOne.get(i), inpFile.getTNT())) {

                    // set machine one node back to real root
                    machineOne.get(i).setParent(realRoot);
                    continue;
                }

                newPenVal = penVal + cnstr.tooNearPenalties(machineOne.get(i), inpFile.getTNP());

                if(newPenVal < bestPenVal) {

                    bestPenVal = newPenVal;
                    bestTreeLeaf = root;
                }

                // set machine one node back to real root
                machineOne.get(i).setParent(realRoot);
            }
        }
    }

    public int getScore() {

        return bestPenVal;
    }


}
