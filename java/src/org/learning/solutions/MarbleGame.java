package org.learning.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarbleGame {
	private Marble[] board;
	private int size;
	private List<List<Marble>> moves;
	
	private enum Marble {
		Black,
		White,
		None
	}
	public MarbleGame(int marbles) {
		this.size = 2 * marbles + 1;
		this.board = new Marble[marbles*2 + 1];
		for(int i=0; i < marbles;i++) {
			board[i] = Marble.Black;
			board[size-1-i] = Marble.White;
		}
		board[marbles] = Marble.None;
		moves = new ArrayList<List<Marble>>();
	}
	public boolean play() {
		return play(board);
	}
	private boolean play(Marble[] board) {
		if(isGameOver(board)) {
			return true;
		}
		for(int position=0; position < size; position++) {
			if (canMove(board, position)) {
				Marble[] newBoard = makeMove(board, position);
				moves.add(Arrays.asList(newBoard.clone()));
				if(play(newBoard)) {
					return true;
				}
			}
		}
		return false;
	}
	public void showMoves() {
		for(List<Marble> move: moves) {
			System.out.println(move);
		}
	}
	private boolean canMove(Marble[] board, int position) {
		if (board[position] == Marble.None) {
			return false;
		}
		if (board[position] == Marble.Black &&  
				((position+1 < size && board[position+1] == Marble.None) || 
				 (position+2 < size && board[position+1] == Marble.White && board[position+2] == Marble.None))) {
			return true;
		}
		if (board[position] == Marble.White &&  
				((position-1 >= 0 && board[position-1] == Marble.None) || 
				 (position-2 >= 0 && board[position-1] == Marble.Black && board[position-2] == Marble.None))) {
			return true;
		}
		return false;
	}
	private Marble[] makeMove(Marble[] board, int position) {
		if (board[position] == Marble.None) {
			return board.clone();
		}
		if (board[position] == Marble.Black &&  
				(position+1 < size && board[position+1] == Marble.None)) {
			board[position+1] = Marble.Black;
			board[position] = Marble.None;
		}
		else if (board[position] == Marble.Black &&  
				(position+2 < size && board[position+1] == Marble.White && board[position+2] == Marble.None)) {
			board[position+2] = Marble.Black;
			board[position] = Marble.None;
		}
		else if (board[position] == Marble.White &&  
				(position-1 >= 0 && board[position-1] == Marble.None)) {
			board[position-1] = Marble.White;
			board[position] = Marble.None;
		}
		else if (board[position] == Marble.White &&  
				(position-2 >= 0 && board[position-1] == Marble.Black && board[position-2] == Marble.None)) {
			board[position-2] = Marble.White;
			board[position] = Marble.None;
		}
		return board.clone();
	}
	private boolean isGameOver(Marble[] board) {
		int i=0;
		int j=size-1;
		
		while(i < j) {
			if (board[i] == Marble.White && board[j] == Marble.Black) {
				i++;
				j--;
			} else {
				break;
			}
		}
		return (i == j) ? board[i] == Marble.None : false;
	}
}
