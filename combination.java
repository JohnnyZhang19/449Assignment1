import java.util.ArrayList;
import java.util.Arrays;

public class combination {

	public static void main(String[] args){
		ArrayList<String> allCombinations = new ArrayList<String>();
		ArrayList<String> possible = new ArrayList<String>();
		ArrayList<String> tasks = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

		// creates an array list of all combinations (40,320)
        calcCombinations(allCombinations, tasks, "");

		// creates array list of possible combinations given constraints
        mainLoop:
		for(int i = 0; i<allCombinations.size(); i++) {
            
            String combo = allCombinations.get(i);


            

            for(int j = 0; j<forced.size(); j++) {
				
				int forcedMachine = Integer.parseInt(forced.get(j).substring(0, 1));
				String forcedTask = forced.get(j).substring(1,2); 

				if(forcedTask!="X" && !forcedTask.equals(combo.substring(forcedMachine-1,forcedMachine))) {

					//System.out.println(j+ " "  + forcedTask + " " + combo + " " + combo.substring(j,j+1));
					continue mainLoop;

				}
			}

        }
		
	}
        

	static void calcCombinations(ArrayList<String> a, ArrayList<String> b, String str) {
		// base case
		if(b.size() == 0) a.add(str);
		else {
			for(int i=0; i<b.size(); i++) {
				ArrayList<String> c = new ArrayList<>(b);
				String temp = str + c.get(i);
				c.remove(i);
				calcCombinations(a, c, temp);

			}
		}
	}
}
