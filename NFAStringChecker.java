import java.util.*;

public class NFAStringChecker {
    
    private static Map<String, Map<Character, Set<String>>> nfa;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        initializeNFA();
        
        System.out.println("=== NFA String Acceptance Checker ===");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        if (!isValidString(input)) {
            System.out.println("Invalid input! Please enter only 'a' and 'b'.");
            scanner.close();
            return;
        }
        
        boolean result = processNFA(input);
        System.out.println("Output: " + (result ? "Accepted" : "Rejected"));
        
        scanner.close();
    }
    
    public static void initializeNFA() {
        nfa = new HashMap<>();
        
        // State q0: Start state
        Map<Character, Set<String>> q0Transitions = new HashMap<>();
        q0Transitions.put('a', new HashSet<>(Arrays.asList("q0", "q1")));
        q0Transitions.put('b', new HashSet<>(Arrays.asList("q0")));
        nfa.put("q0", q0Transitions);
        
        // State q1: Seen 'a'
        Map<Character, Set<String>> q1Transitions = new HashMap<>();
        q1Transitions.put('b', new HashSet<>(Arrays.asList("q2")));
        nfa.put("q1", q1Transitions);
        
        // State q2: Accept state (seen "ab")
        Map<Character, Set<String>> q2Transitions = new HashMap<>();
        q2Transitions.put('a', new HashSet<>(Arrays.asList("q2")));
        q2Transitions.put('b', new HashSet<>(Arrays.asList("q2")));
        nfa.put("q2", q2Transitions);
    }
    
    public static boolean isValidString(String input) {
        if (input.length() == 0) return false;
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != 'a' && c != 'b') {
                return false;
            }
        }
        return true;
    }
    
    public static boolean processNFA(String input) {
        Set<String> currentStates = new HashSet<>();
        currentStates.add("q0");
        
        System.out.println("\n--- NFA Simulation ---");
        System.out.println("Starting states: " + currentStates);
        
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            Set<String> nextStates = new HashSet<>();
            
            for (String state : currentStates) {
                if (nfa.containsKey(state) && nfa.get(state).containsKey(symbol)) {
                    nextStates.addAll(nfa.get(state).get(symbol));
                }
            }
            
            currentStates = nextStates;
            System.out.println("Read '" + symbol + "' -> Current states: " + currentStates);
            
            if (currentStates.isEmpty()) {
                System.out.println("No valid transitions! String rejected.");
                return false;
            }
        }
        
        System.out.println("Final states: " + currentStates);
        
        return currentStates.contains("q2");
    }
}
