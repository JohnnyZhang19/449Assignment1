import java.util.ArrayList;

public class MNode {
    
    ArrayList<MNode> children = new ArrayList<MNode>();
    MNode parent = null;
    int machine = 0;
    int task = 0;

    public MNode() {

    }

    public MNode(MNode prnt, int mchn, int tsk) {
        parent = prnt;
        machine = mchn;
        task = tsk;
    }

    public MNode getParent() {
        return parent;
    }

    public MNode getChild(int i) {
        return children.get(i);
    }

    public int getMachine() {
        return machine;
    }

    public int getTask() {
        return task;
    }

    public ArrayList<Integer> getPair() {

        ArrayList<Integer> pair = new ArrayList<Integer>();
        pair.add(machine);
        pair.add(task);

        return pair;
    }

    public int howManyChildren() {
        return children.size();
    }

    public ArrayList<Integer> getAncestry(ArrayList<Integer> arr) {

        if(machine == 0) {
            return arr;
        }

        arr.add(task);

        return this.getParent().getAncestry(arr);
    }

    public void setParent(MNode nd) {
        parent = nd;
    }

    public void populate() {
        
        int next_machine = machine + 1;

        for(int i = 1; i < 9; i++) {

            MNode new_node = new MNode(this, next_machine, i);
            this.children.add(new_node);
        }
    }
}
