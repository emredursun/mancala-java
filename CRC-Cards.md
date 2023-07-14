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