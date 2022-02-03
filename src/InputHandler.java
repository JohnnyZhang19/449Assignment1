

import java.io.*;
import java.util.ArrayList;

public class InputHandler {

    // to keep track of what data is being read from input file
    int state;
    
    // labels for comparison to input file
    String nameLabel = "Name:";
    String firstHC = "forced partial assignment:";
    String secondHC = "forbidden machine:";
    String thirdHC = "too-near tasks:";
    String firstSC = "machine penalties:";
    String secondSC = "too-near penalties:";

    // valid inputs
    char[] validMachines = {'1', '2', '3', '4', '5', '6', '7', '8'};
    char[] validTasks = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    // values to set from input file 
    String inputName = "";
    ArrayList<ArrayList<String>> FPA;
    ArrayList<ArrayList<String>> FM;
    ArrayList<ArrayList<String>> TNT;
    ArrayList<ArrayList<String>> MP;
    ArrayList<ArrayList<String>> TNP;

    // read file inputs into class fields
    public void readInput(String filename) {

        File file = new File(filename);

        try{

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null) {
                
                // Look for labels and set state accordingly
                if(line.equals(nameLabel)) {
                    state = 1;
                }
                else if(line.equals(firstHC)) {
                    state = 2;
                }
                else if(line.equals(secondHC)) {
                    state = 3;
                }
                else if(line.equals(thirdHC)) {
                    state = 4;
                }
                else if(line.equals(firstSC)) {
                    state = 5;
                }
                else if(line.equals(secondSC)) {
                    state = 6;
                }

                System.out.println("Msg: '" + line + "' State: " + state);

                // record input according to state
                if(state == 1) {        // data for name
                    inputName = line;
                }
                else if(state == 2) {   // data for first hard constraint

                    if(isValidPair(line)) {
                        System.out.println("True");
                    }
                    else System.out.println("False");
                }
                else if(state == 3) {   // data for second hard constraint

                }
                else if(state == 4) {   // data for third hard constraint
                    
                }
                else if(state == 5) {   // data for first soft constraint
                    
                }
                else if(state == 6) {   // data for second soft constraint
                    
                }
            }

            br.close();
        }catch(Exception e) {
            
            System.out.println(e.toString());
        }
        
    }

    // name
    public String getName() {
        return inputName;
    }

    // forced partial assignment
    public ArrayList<ArrayList<String>> getFPA() {
        return FPA;
    }

    // forbidden machine
    public ArrayList<ArrayList<String>> getFM() {
        return FM;
    }

    // too-near tasks
    public ArrayList<ArrayList<String>> getTNT() {
        return TNT;
    }

    // machine penalties
    public ArrayList<ArrayList<String>> getMP() {
        return MP;
    }

    // too-near penalties
    public ArrayList<ArrayList<String>> getTNP() {
        return TNP;
    }

    // check if pair is in correct format
    private boolean isValidPair(String pair) {

        boolean firstVal = false;
        boolean secondVal = false;

        if(pair.length() < 5) {
            return false;
        }

        // if data is in format: (a,b)
        if(pair.charAt(0) == '(' && pair.charAt(4) == ')' && pair.charAt(2) == ',') {

            // check if values are valid machines
            for(char element : validMachines) {

                if(pair.charAt(1) == element) {
                    firstVal = true;
                }

                if(pair.charAt(3) == element) {
                    secondVal = true;
                }
            }

            // check if values are valid tasks
            for(char element : validTasks) {

                if(pair.charAt(1) == element) {
                    firstVal = true;
                }

                if(pair.charAt(3) == element) {
                    secondVal = true;
                }
            }
        }
        // if data is in format: (a, b)
        else if(pair.charAt(0) == '(' && pair.charAt(5) == ')' && pair.charAt(2) == ',') {
            
            // check if values are valid machines
            for(char element : validMachines) {

                if(pair.charAt(1) == element) {
                    firstVal = true;
                }

                if(pair.charAt(4) == element) {
                    secondVal = true;
                }
            }

            // check if values are valid tasks
            for(char element : validTasks) {

                if(pair.charAt(1) == element) {
                    firstVal = true;
                }

                if(pair.charAt(4) == element) {
                    secondVal = true;
                }
            }
        }

        return firstVal && secondVal;
    }

    // check if name is in correct format
    private boolean isValidName(String name) {

        boolean hasSpace = name.contains(" ");

        if(name.length() < 1 || hasSpace) {
            return false;
        }
        else return true;
    }

}
