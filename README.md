# NFA String Acceptance Checker
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
### How This NFA Works:
The program tracks **all possible states** at each step:
1. Start with state set: `{q0}`
2. For each input symbol, compute ALL possible next states
3. Continue with the new set of states
4. Accept if ANY final state is q2
---
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

##  More Test Cases

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
**Author**: Angelou A. Nangcas  
**Course**: Computer Science 
**Date**: November 3, 2025
