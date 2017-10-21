package web_nav;

import java.util.Scanner;
import java.util.Stack;

public class WebNavigator {

    // Fields
    private String current; // Tracks currently visited site
    private Stack<String> history;
    private Stack<String> fHistory;
    
    // Constructor
    WebNavigator () {
        current = null;
        history = new Stack<String>();
        fHistory = new Stack<String>();
    }
    
    // Methods
    // [!] YOU DO NOT HAVE TO MODIFY THIS METHOD FOR YOUR SOLUTION
    public boolean getNextUserCommand (Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");
        
        // Switch on the command (issued first in input line)
        switch(parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forward":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }
        
        System.out.println("Currently Visiting: " + current);
        
        return true;
    }
    
    /*
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     */
    public void visit (String site) {
        current = site;
        history.push(site);
        fHistory.clear();
    }
    
    /*
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
        if (history.size() > 1) {
            fHistory.push(history.pop());
            current = history.peek();
        }
    }
    
    public void forw () {
        if (!fHistory.isEmpty()) {
            history.push(fHistory.pop());
            current = history.peek();
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();
        
        System.out.println("Welcome to ForneyFox, enter a command from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {}
        System.out.println("Goodbye!");
    }

}
