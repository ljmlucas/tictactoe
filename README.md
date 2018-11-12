# TIC TAC TOE 2.0

* 2 players, 1 computer
* Size of the board between 3x3 and 10x10

* The game is started at the Main class. There is an object of ConfigFileReader responsible for reading the configuration file. This file provides the data to start the board and the players.
* The Main class creates an object of TicTacToe, this object is responsible to set the rules, who is the next player, if it a draw, and the most important, if there is a winner.
* TicTacToe object has an instance of ConsoleInOut. ConsoleInOut has to read the input position from the players, and also display information about the game.
* TicTacToe also has the Board. The Board knows how to set a player at the position and when the game is done.

## How to configure

* There is a file in the root folder of this project, config.txt. In this file is possible to define the size of the board and the symbol of each player

* One player is the computer and the rest are human players

* Who is starting is random

## Requirements

 * Java 8 ( https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html )
 * Gradle ( https://gradle.org/install/ )

### Running by Docker

 * Docker ( https://docs.docker.com/v17.12/install/ )
 * Docker compose ( https://docs.docker.com/compose/install/#install-compose )

## Run the project command-line

    * ./gradlew build
    * ./gradlew run

## Run the project Docker

    * docker build -t tictactoe-image .
    * docker run -it tictactoe-image
 