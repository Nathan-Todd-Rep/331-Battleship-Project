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

More classes are added one small push at a time (ships, board, players, GUI).

## Compile and run

From the project folder:

```
javac -d bin src/*.java
java -cp bin CoordinateTester
```

Requires Java 21 (or any JDK 17+). Swing comes with the JDK, nothing extra
to install.

Never push straight to `main`.
