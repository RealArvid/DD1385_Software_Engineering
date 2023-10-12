package VersionBonus;
//import java.util.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Bonus version running");
        
        // int numButtons;
        // Scanner scanner = new Scanner(System.in);
        // while(true){
        //     try{
        //         numButtons = Integer.valueOf(args[0]);
        //         break;
        //     }
        //     catch(Exception e){
        //         try{
        //             numButtons = Integer.valueOf(scanner.next());
        //             if(numButtons > 0){
        //                 break;
        //             }
        //         }
        //         catch(Exception e2){
        //             continue;
        //         }
        //     }             
        // }

        // String[][] buttonTexts = new String[numButtons][2];
        // if(args.length - 1 < numButtons*2){
        //     for(int i = 0; i < numButtons; i++){
        //         String text1 = scanner.next();
        //         String text2 = scanner.next();
        //         String[] test = {text1, text2};
        //         buttonTexts[i] = test;
        //     }
        // }
        // else{
        //     for(int i = 0; i < numButtons; i++){
        //         buttonTexts[i] = Arrays.copyOfRange(args, 2*i+1, 2*i+3);
        //     }
        // }
        // scanner.close();
        String[][] buttonTexts = {
            {"Off", "On"},
            {"Not Pressed", "Pressed"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
            {"Not Clicked", "Clicked"},
        };
        
        new MyFrame(buttonTexts);
    }
}