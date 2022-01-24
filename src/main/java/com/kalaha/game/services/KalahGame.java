package com.kalaha.game.services;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.kalaha.game.model.KalahPlayer;
import com.kalaha.game.model.Status;



/**
 * Represent a Kalah Game with associated players and status.
 * 
 * @author Ravish
 *
 */
public class KalahGame  {

  final static int noOfPits = 14;

  final static int startingPeebles = 6;

  private int[] board;
  private Status status;
  private String message;
  private KalahPlayer player1;
  private KalahPlayer player2;
  


  /**
   * Initializes Kalah game with start board configuration and status.
   */
  public static KalahGame init() {
    KalahGame aGame = new KalahGame();
    int[] board = new int[noOfPits];
    for (int i = 0; i < board.length; i++) {
      if (i == 6 || i == 13) {
        continue;
      }
      board[i] = startingPeebles;
    }
    aGame.setBoard(board);
    aGame.setPlayer1(new KalahPlayer(1, 6));
    aGame.setPlayer2(new KalahPlayer(2, 13));
    aGame.setStatus(Status.PLAYER1TURN);
    aGame.setMessage("Welcome to Game of Kalah.");
    return aGame;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public int[] getBoard() {
    return board;
  }

  public void setBoard(int[] board) {
    this.board = board;
  }

  public KalahPlayer getPlayer1() {
    return player1;
  }

  public void setPlayer1(KalahPlayer player1) {
    this.player1 = player1;
  }

  public KalahPlayer getPlayer2() {
    return player2;
  }

  public void setPlayer2(KalahPlayer player2) {
    this.player2 = player2;
  }

  /**
   * Determines and update player turn.
   */
  private void updateNextTurn() {

    if (status.equals(Status.PLAYER1TURN)) {
      status = Status.PLAYER2TURN;
      return;
    }
    if (status.equals(Status.PLAYER2TURN)) {
      status = Status.PLAYER1TURN;
      return;
    }

  }

  /**
   * 
   * Determines if the move is a valid one.
   */
  public boolean isValidMove(int pit) {
    if (status.equals(Status.PLAYER1TURN)) {
      return 0 <= pit && pit <= 5;
    } else if (status.equals(Status.PLAYER2TURN)) {
      return 7 <= pit && pit <= 12;
    }
    return false;
  }

  /**
   * Execute the game as per the player move and update the board position.
   */
  public void play(int pit) {
    if (this.status.equals(Status.PLAYER1TURN))
      play(pit, player1);
    else if (this.status.equals(Status.PLAYER2TURN))
      play(pit, player2);

  }

  private void play(int pit, KalahPlayer player) {
    int peebles = collectPebbles(pit);
    int lastDropPit = pit;
    while (peebles > 0) {
      int dropPit = next(lastDropPit);
      if (isKalah(dropPit) && !player.isMyKalah(dropPit)) {
        lastDropPit = dropPit;
        continue;
      }
      board[dropPit] += 1;
      peebles -= 1;
      lastDropPit = dropPit;
    }

    if (player.isMyPit(lastDropPit) && board[lastDropPit] == 1) {
      board[player.getKalah()] += board[lastDropPit] + board[12 - lastDropPit];
      board[lastDropPit] = 0;
      board[12 - lastDropPit] = 0;
    }

    if (!player.isMyKalah(lastDropPit))
      updateNextTurn();

    checkGameStatus();

  }

  /**
   * Determines if game has concluded and winner.
   */
  private void checkGameStatus() {
   
    int pitSumSideOne = IntStream.of(Arrays.copyOfRange(board, 0, 6)).sum(); 
    int pitSumSideTwo = IntStream.of(Arrays.copyOfRange(board, 7, 12)).sum();
    
    if (pitSumSideOne == 0 || pitSumSideTwo == 0) {
      board[player1.getKalah()] += pitSumSideOne;
      board[player2.getKalah()] += pitSumSideTwo;
      if (board[player1.getKalah()] > board[player2.getKalah()]) {
        status = Status.PLAYER1WINS;
      } else {
        status = Status.PLAYER2WINS;
      }
      resetBoard();
    }
  }

  private void resetBoard() {
    for (int i = 0; i < noOfPits; i++) {
      if (i != 6 && i != 13)
        board[i] = 0;
    }
  }

  private boolean isKalah(int pit) {
    return pit == 6 || pit == 13;
  }

  private int next(int pit) {
    pit += 1;
    return pit % noOfPits;
  }

  private int collectPebbles(int pit) {
    int peebles = board[pit];
    board[pit] = 0;
    return peebles;
  }
}
