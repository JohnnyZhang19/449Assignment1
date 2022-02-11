package src;
import java.util.ArrayList;
public class TooNearPenalty {


    




    //Need to fix Input names for pairs
    public int nearPenalty(ArrayList<ArrayList<String>>TNP, ArrayList<ArrayList<String>> pairs ){

        int penalty;
        int totalPenalty = 0;                                                                                   
        String task1;
        String task2;


        //Enter TNP array
        for (int k = 0; k < TNP.size(); k++){
            
            //Assign Current TNP that we are checking against           //Could add a for loop if needed
            task1 = TNP.get(k).get(0);
            task2 = TNP.get(k).get(1);
            penalty = Integer.parseInt(TNP.get(k).get(2));
            
            //Enter pairs array
            for (int i = 0; i < pairs.size(); i++ ) {
             
        
                //Check each pair on whether it matches the current TNP

                if (i = 7) {
                    if((task1 == pairs.get(i).get(1) && task2 == pairs.get(0).get(1)) || (task2 == pairs.get(i).get(1) && task1 == pairs.get(0).get(1))) {

                        totalPenalty += totalPenalty + penalty;
                    }
                }
                
                //Need help with formatting
                 if((task1 == pairs.get(i).get(1) && task2 == pairs.get(i+1).get(1)) || (task2 == pairs.get(i).get(1) && task1 == pairs.get(i+1).get(1))){

                    totalPenalty += totalPenalty + penalty;


                }
                
            }
            
        }  
        
        return(totalPenalty);
    }
}
    
}
