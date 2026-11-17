# 331-Battleship-Project
Final Project for CSC 331

A two-board game of Battleship written in Java with a Swing GUI. The player
places a fleet of five ships on a 10 x 10 grid and takes turns firing at the
computer's hidden fleet. The first side to sink every enemy ship wins.

## Project layout

```
src/        Java source files
bin/        compiled .class files (not committed)
```

| File | What it holds |
|---|---|
| `src/GameDefaults.java` | Constants for the whole game: board size, cell codes, ship names and lengths |
| `src/Coordinate.java` | One cell on the board, a row and a column |
| `src/Player.java` | One player: their board loaded from a file, the shots they have fired, taking a turn |
| `src/HumanPlayer.java` | A player whose shots are typed on the console |
| `src/ComputerPlayer.java` | A player whose shots are picked at random |
| `src/Battleship.java` | Runs a console game, you against the computer |
| `src/CoordinateTester.java`, `src/PlayerTester.java` | Quick checks for the classes above |
| `testfile1.txt`, `testfile2.txt` | Test boards (`0` ocean, `*` ship), one full fleet each |

More classes are going to be added later! (ships, board, GUI).

## Compile and run

From the project folder:

```
javac -d bin src/*.java
java -cp bin Battleship
```

Type a row and a column (for example `3 7`) to fire. You and the computer
take turns, one shot each. On screen `~` is ocean, `*` is a ship, `X` is a
hit and `O` is a miss. To run the testers instead:

```
java -cp bin CoordinateTester
java -cp bin PlayerTester
```

Requires Java 21 (or any JDK 17+). Swing comes with the JDK, nothing extra
to install.

Never push straight to `main`.
