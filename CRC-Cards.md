## Scenarios:

Here are 4 scenarios for Mancala Game

### Scenario 1: Initializing the game

- Create a MancalaGame object with two Player objects, each with six Bowl objects and a Kalaha object.
- Each Bowl object starts with four stones.

### Scenario 2: Making a move

- A Player object selects a Bowl object on their side.
- The Player object takes all the stones from the selected Bowl object and distributes them anti-clockwise one at a time to the next Bowl object, including their own Kalaha object but not the opponent's.
- If the last stone lands in the Player object's Kalaha object, the Player object gets an extra turn.
- If the last stone lands in an empty Bowl object on the Player object's side, the Player object takes that Bowl object's stones and the direct opposite Bowl object's stone and puts them in their Kalaha object.

### Scenario 3: Ending the game

- The game ends if all Bowl objects on the current turn player's side are empty.

### Scenario 4: Determining the winner

- The winner is the Player object with the most stones in their Bowl objects and Kalaha object combined.

Based on these scenarios, here's an object-oriented model for Mancala:

## CRC Cards:

### Class: MancalaGame
Responsibilities:

- Initialize the game with players and a board
- Manage the game flow and turns (handle moves and distribute stones)
- Determine game completion and winner

Collaborators:

- Player
- Bowl
- Kalaha

### Class: Player
Responsibilities:

- Make moves by selecting a bowl
- Track and update score
- Check if the player has any moves left

Collaborators:

- Bowl
- Kalaha

### Class: Bowl
Responsibilities:

- Hold and manage stones
- Perform operations on stones (add, remove, empty)
- Notify the player or board about specific events

Collaborators:

- Player

### Class: Kalaha
Responsibilities:

- Hold and manage stones
- Perform operations on stones (add)
- Notify the player or board specific events

Collaborators:

- Player
- Bowl


These CRC cards represent the classes involved in the Mancala game.
Each class has a set of responsibilities and collaborators that it interacts with.
These cards can be used to refine the design of the game and to identify potential issues that may arise during development.