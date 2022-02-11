// Author: Matthew Low

import java.io.*;
import java.util.ArrayList;

public class InputHandler {

    // to keep track of what data is being read from input file
    int state = 0;
    
    // labels for comparison to input file
    String nameLabel = "Name:";
    String firstHC = "forced partial assignment:";
    String secondHC = "forbidden machine:";
    String thirdHC = "too-near tasks:";
    String firstSC = "machine penalties:";
    String secondSC = "too-near penalities";

    // to keep track of how many lines are being read for machine penalties
    int mpLine = 0;

    // valid inputs
    char[] validMachines = {'1', '2', '3', '4', '5', '6', '7', '8'};
    char[] validTasks = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    // values to set from input file 
    String inputName = "";
    ArrayList<ArrayList<String>> FPA = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> FM = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> TNT = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<Integer>> MP = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<String>> TNP = new ArrayList<ArrayList<String>>();

    // read file inputs into class fields
    public void readInput(String filename) {

        // clear fields
        state = 0;
        inputName = "";
        FPA.clear();
        FM.clear();
        TNT.clear();
        MP.clear();
        TNP.clear();
        mpLine = 0;

        File file = new File(filename);

        try{

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null) {

                // Format input line
                line = formatLine(line);

                // Skip blank lines
                if(line.equals("")) {
                    continue;
                }
                
                // Look for labels and set state accordingly
                if(line.equals(nameLabel)) {
                    if(state == 0) {
                        state = 1;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }
                else if(line.equals(firstHC)) {
                    
                    if(state == 1 && !inputName.equals("")) {
                        state = 2;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }
                else if(line.equals(secondHC)) {
                    
                    if(state == 2) {
                        state = 3;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }
                else if(line.equals(thirdHC)) {
                    
                    if(state == 3) {
                        state = 4;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }
                else if(line.equals(firstSC)) {
                    
                    if(state == 4) {
                        state = 5;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }
                else if(line.equals(secondSC)) {
                    
                    if(state == 5 && mpLine == 8) {
                        state = 6;
                    }
                    else {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                }

                // System.out.println("Msg: '" + line + "' State: " + state);
                // System.out.println(line);

                // record input according to state
                if(state == 1) {        // data for name

                    if(!line.equals(nameLabel) && inputName.equals("")) {
                        
                        if(isValidName(line)) {
                            inputName = line;
                        }
                        else {
                            System.out.println("Error while parsing input file");
                            break;
                        }
                    }
                    else if(!line.equals(nameLabel) && !inputName.equals("")) {
                        System.out.println("Error while parsing input file");
                        break;
                    }
                    
                }
                else if(state == 2) {   // data for first hard constraint
                    
                    if(!line.equals(firstHC)) {

                        if(isValidPair(line)) {

                            if(hasCharacter(validMachines, line.charAt(1)) && hasCharacter(validTasks, line.charAt(line.length() - 2))) {
                                
                                ArrayList<String> inp = new ArrayList<>(2);

                                inp.add(Character.toString(line.charAt(1)));
                                inp.add(Character.toString(line.charAt(line.length() - 2)));

                                FPA.add(inp);
                            }
                            else {
                                System.out.println("invalid machine/task");
                                break;
                            }
                        }
                        else {
                            System.out.println("invalid machine/task");
                            break;
                        }
                    }
                }
                else if(state == 3) {   // data for second hard constraint

                    if(!line.equals(secondHC)) {

                        if(isValidPair(line)) {

                            if(hasCharacter(validMachines, line.charAt(1)) && hasCharacter(validTasks, line.charAt(line.length() - 2))) {
                                
                                ArrayList<String> inp = new ArrayList<>(2);

                                inp.add(Character.toString(line.charAt(1)));
                                inp.add(Character.toString(line.charAt(line.length() - 2)));

                                FM.add(inp);
                            }
                            else {
                                System.out.println("invalid machine/task");
                                break;
                            }
                        }
                        else {
                            System.out.println("invalid machine/task");
                            break;
                        }
                    }
                }
                else if(state == 4) {   // data for third hard constraint

                    if(!line.equals(thirdHC)) {

                        if(isValidPair(line)) {

                            if(hasCharacter(validTasks, line.charAt(1)) && hasCharacter(validTasks, line.charAt(line.length() - 2))) {
                                
                                ArrayList<String> inp = new ArrayList<>(2);

                                inp.add(Character.toString(line.charAt(1)));
                                inp.add(Character.toString(line.charAt(line.length() - 2)));

                                TNT.add(inp);
                            }
                            else {
                                System.out.println("invalid machine/task");
                                break;
                            }
                        }
                        else {
                            System.out.println("invalid machine/task");
                            break;
                        }
                    }
                }
                else if(state == 5) {   // data for first soft constraint

                    if(!line.equals(firstSC)) {

                        mpLine++;

                        // error if more than 8 lines of input
                        if(mpLine > 8) {
                            System.out.println("Error while parsing input file");
                            break;
                        }

                        String[] parsedLine = parseToArray(line);

                        // if line is a valid set, add numbers to MP array
                        if(isValidSet(parsedLine)) {

                            ArrayList<Integer> input = new ArrayList<Integer>(8);

                            for(String num: parsedLine) {

                                int temp = Integer.parseInt(num);
                                input.add(temp);
                            }

                            MP.add(input);
                        }
                        else {
                            System.out.println("machine penalty error");
                            break;
                        }
                    }
                }
                else if(state == 6) {   // data for second soft constraint

                    if(!line.equals(secondSC)) {

                        if(isValidTrio(line)) {

                            // change to format: (a,b,c) 
                            line = formatTrio(line);
                            
                            int val;
                            try {
                                val = Integer.parseInt(line.substring(5, line.length() - 1));
                            }
                            catch(NumberFormatException e) {
                                System.out.println("Error while parsing input file");
                                break;
                            }
                            
                            if(val > 0) {

                                ArrayList<String> inp = new ArrayList<>(3);

                                inp.add(Character.toString(line.charAt(1)));
                                inp.add(Character.toString(line.charAt(3)));
                                inp.add(line.substring(5, line.length() - 1));

                                TNP.add(inp);
                            }
                            else {
                                System.out.println("Error while parsing input file");
                                break;
                            }
                        }
                        else {
                            System.out.println("invalid task");
                            break;
                        }
                    }
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
    public ArrayList<ArrayList<Integer>> getMP() {
        return MP;
    }

    // too-near penalties
    public ArrayList<ArrayList<String>> getTNP() {
        return TNP;
    }

    // convert input to string
    public String toString() {
        String str =    nameLabel + "\n" + inputName + "\n" +
                        firstHC + "\n" + FPA.toString() + "\n" + 
                        secondHC + "\n" + FM.toString() + "\n" + 
                        thirdHC + "\n" + TNT.toString() + "\n" + 
                        firstSC + "\n" + MP.toString() + "\n" + 
                        secondSC + "\n" + TNP.toString();
        
        return str;    
    }

    // check if pair is in correct format
    private boolean isValidPair(String pair) {

        boolean firstVal = false;
        boolean secondVal = false;

        if(pair.length() < 5 || pair.length() > 6) {
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
        else if(pair.charAt(0) == '(' && pair.charAt(5) == ')' && pair.charAt(2) == ',' && pair.charAt(3) == ' ') {
            
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

    // check if trio is in correct format
    private boolean isValidTrio(String trio) {

        boolean firstVal = false;
        boolean secondVal = false;

        if(trio.length() < 7) {
            return false;
        }

        // if data is in format: (a,b,c)
        if(trio.charAt(0) == '(' && trio.charAt(2) == ',' && trio.charAt(4) == ',' && trio.charAt(trio.length() - 1) == ')') {

            // check if values are valid tasks
            for(char element : validTasks) {

                if(trio.charAt(1) == element) {
                    firstVal = true;
                }

                if(trio.charAt(3) == element) {
                    secondVal = true;
                }
            }
        }
        // if data is in format: (a, b, c)
        else if(trio.charAt(0) == '(' && trio.charAt(trio.length() - 1) == ')' && trio.charAt(2) == ',' && trio.charAt(5) == ',' && trio.charAt(3) == ' ' && trio.charAt(6) == ' ') {

            // check if values are valid tasks
            for(char element : validTasks) {

                if(trio.charAt(1) == element) {
                    firstVal = true;
                }

                if(trio.charAt(4) == element) {
                    secondVal = true;
                }
            }
        }

        return firstVal && secondVal;
    }

    // check if line is valid set of 8 numbers (machine penalties)
    private boolean isValidSet(String[] set) {

        boolean isValid = true;
        int temp;

        if(set.length != 8) {

            isValid = false;
            return isValid;
        }
        
        for(String num : set) {

            try {
                temp = Integer.parseInt(num);
                
                if(temp < 0) {

                    isValid = false;
                    break;
                }
            }
            catch(NumberFormatException e) {

                isValid = false;
                break;
            }
        }

        return isValid;
    }

    // check if name is in correct format
    private boolean isValidName(String name) {

        boolean hasSpace = name.contains(" ");

        if(name.length() < 1 || hasSpace) {
            return false;
        }
        else return true;
    }

    // format any trailing spaces
    private String formatLine(String line) {
        String temp = line;

        while(temp.length() > 0 && temp.charAt(temp.length() - 1) == ' ') {
            temp = temp.substring(0, temp.length() - 1);
        }
        
        return temp;
    }

    // format a trio by removing spaces
    private String formatTrio(String line) {
        String temp = line;

        if(temp.length() >= 9) {

            StringBuilder sb = new StringBuilder(temp);
            
            sb.deleteCharAt(6);
            sb.deleteCharAt(3);

            temp = sb.toString();
        }

        return temp;
    }

    // parse string into an array split by spaces
    private String[] parseToArray(String line) {

        String[] ans = line.split(" ");

        return ans;
    }

    // check if a char array contains a char value
    private boolean hasCharacter(char[] arr, char value) {
        // from https://stackoverflow.com/questions/18581531/in-java-how-can-i-determine-if-a-char-array-contains-a-particular-character

        boolean contains = false;

        for(char c: arr) {
            
            if(c == value) {
                contains = true;
                break;
            }
        }

        return contains;
    }
}
