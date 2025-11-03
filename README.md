# NFA String Acceptance Checker

## Purpose
This program implements a **Non-deterministic Finite Automaton (NFA)** that checks whether a string contains **"ab"** as a substring.

An NFA is similar to a DFA but can have multiple possible next states for the same input symbol. This program explores all possible state transitions simultaneously to determine if the input string is accepted.

---

## NFA States and Transitions

### States:
- **q0**: Start state (haven't found "ab" yet, can loop on any character)
- **q1**: Just seen 'a' (waiting for 'b' to complete "ab")
- **q2**: Accept state (found "ab" substring, stay here for rest of input)

### Alphabet:
- `{a, b}` - The program accepts strings with 'a' and 'b'

### Language:
- All strings that contain "ab" as a substring
- Examples: `ab`, `aab`, `aba`, `bab`, `abab`

### Transition Table:

| Current State | Input 'a' | Input 'b' |
|---------------|-----------|-----------|
| q0 (start)    | q0, q1    | q0        |
| q1            | -         | q2        |
| q2 (accept)   | q2        | q2        |

### Explanation of Transitions:
- **From q0**: 
  - On 'a': Go to both q0 (continue searching) AND q1 (start of potential "ab")
  - On 'b': Stay in q0 (keep searching)

- **From q1**: 
  - On 'a': No transition (dead end for this path)
  - On 'b': Move to q2 (found "ab"!)

- **From q2**: 
  - On 'a' or 'b': Stay in q2 (already found "ab", accept everything after)

---

## Key Differences: NFA vs DFA

### NFA Features:
- ✅ **Multiple transitions** for the same input from one state
- ✅ **Non-deterministic** - can be in multiple states at once
- ✅ **Explores all paths** simultaneously using sets
- ✅ **Simpler design** for certain patterns (like substring matching)

### How This NFA Works:
The program tracks **all possible states** at each step:
1. Start with state set: `{q0}`
2. For each input symbol, compute ALL possible next states
3. Continue with the new set of states
4. Accept if ANY final state is q2

---

## How to Compile and Run

### Prerequisites:
- Java Development Kit (JDK) installed on your computer
- A terminal or command prompt

### Step 1: Compile the Program
Open your terminal/command prompt and navigate to the folder containing `NFAStringChecker.java`, then run:

```bash
javac NFAStringChecker.java
```

This will create a `NFAStringChecker.class` file.

### Step 2: Run the Program
After compiling, run the program with:

```bash
java NFAStringChecker
```

### Step 3: Enter Input
The program will ask you to enter a string. Type your string (only 'a' and 'b') and press Enter.

---

## Example Input/Output

### Example 1: String Containing "ab" (Accepted)
**Input:** `aab`  
**Output:** `Accepted`

```
=== NFA String Acceptance Checker ===
Enter a string: aab

--- NFA Simulation ---
Starting states: [q0]
Read 'a' -> Current states: [q0, q1]
Read 'a' -> Current states: [q0, q1]
Read 'b' -> Current states: [q0, q2]
Final states: [q0, q2]

Output: Accepted
```

**Explanation:** The string contains "ab" (at positions 1-2), so it's accepted.

---

### Example 2: String NOT Containing "ab" (Rejected)
**Input:** `bbaa`  
**Output:** `Rejected`

```
=== NFA String Acceptance Checker ===
Enter a string: bbaa

--- NFA Simulation ---
Starting states: [q0]
Read 'b' -> Current states: [q0]
Read 'b' -> Current states: [q0]
Read 'a' -> Current states: [q0, q1]
Read 'a' -> Current states: [q0, q1]
Final states: [q0, q1]

Output: Rejected
```

**Explanation:** The string never forms "ab" substring, so it's rejected.

---

### Example 3: Simple "ab" Pattern (Accepted)
**Input:** `ab`  
**Output:** `Accepted`

```
=== NFA String Acceptance Checker ===
Enter a string: ab

--- NFA Simulation ---
Starting states: [q0]
Read 'a' -> Current states: [q0, q1]
Read 'b' -> Current states: [q0, q2]
Final states: [q0, q2]

Output: Accepted
```

---

### Example 4: "ab" in the Middle (Accepted)
**Input:** `babb`  
**Output:** `Accepted`

```
=== NFA String Acceptance Checker ===
Enter a string: babb

--- NFA Simulation ---
Starting states: [q0]
Read 'b' -> Current states: [q0]
Read 'a' -> Current states: [q0, q1]
Read 'b' -> Current states: [q0, q2]
Read 'b' -> Current states: [q0, q2]
Final states: [q0, q2]

Output: Accepted
```

---

## 📊 More Test Cases

| Input | Contains "ab"? | Output |
|-------|----------------|--------|
| `ab` | Yes | Accepted ✅ |
| `aab` | Yes | Accepted ✅ |
| `aba` | Yes | Accepted ✅ |
| `bab` | Yes | Accepted ✅ |
| `abab` | Yes | Accepted ✅ |
| `ba` | No | Rejected ❌ |
| `bba` | No | Rejected ❌ |
| `bbaa` | No | Rejected ❌ |
| `aaa` | No | Rejected ❌ |
| `bbb` | No | Rejected ❌ |

---

## Program Features
- ✅ Validates input (ensures only 'a' and 'b' characters)
- ✅ Uses Map and Set for NFA state representation
- ✅ Explores multiple states simultaneously
- ✅ Shows step-by-step state transitions
- ✅ Clear and educational output

---

## Understanding the Code

### Data Structure:
```java
Map<String, Map<Character, Set<String>>> nfa
```
- **Outer Map**: Current state → Transitions
- **Inner Map**: Input symbol → Set of next states
- **Set**: Multiple possible next states (NFA's non-determinism)

### Algorithm:
1. Maintain a **Set of current states**
2. For each input symbol:
   - For each state in current set
   - Find all possible next states
   - Union them into new current set
3. Accept if q2 is in final set

---

## Learning Notes
This program demonstrates:
- **NFA concepts** (non-determinism, multiple transitions)
- **Set operations** (union, contains)
- **HashMap usage** (nested maps for state transitions)
- **Collections framework** (HashSet, HashMap, Arrays.asList)
- **State space exploration** (tracking multiple possibilities)

Perfect for learning about non-deterministic automata and advanced Java data structures! 🎓

---

**Author**: Angelou A. Nangcas  
**Course**: Theory of Computation  
**Date**: November 3, 2025
