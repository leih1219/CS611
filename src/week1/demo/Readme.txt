## Table of Contents
1. [General Info](#general-info)
2. [Instructions](#instructions)
3. [Classes Info](#classes-info)
4. [Extra Features](#extra-features)

### General Info
***
Name:	Lei Huang
Email:	leih1219@bu.edu
BU ID:	U67278956

### Instructions
***
Run the "PlayGame.java" file.
1. Choose the player who want to play first by inputing 'X' or 'O'.
2. Input the position where the current player want to move the chessman to.
3. Continue until it comes to a winner or satlemate.

### Classes Info
[1]PlayGame:	Main class to run the game.
[2]Board:		Use the rule to figure out the result ('Win'/'Stalemate'/'Next step') after each step.
				Draw the current board.
[3]Player:		Identify the players with different characters;
				Implement the move action by update the board after getting players' inputs.
[4]Functions:	Return a Boolean value to indicate whether the position the player chose is valid.

### Extra Features
1.	Everytime before the game starts, players can choose who takes the first step.
2.	Everytime a player wins, system will print a message and tell him how many steps he took to win the game.