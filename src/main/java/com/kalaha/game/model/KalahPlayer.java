package com.kalaha.game.model;

/**
 * Represents the player of a Kalah Game.
 * 
 * @author Ravish
 *
 */
public class KalahPlayer {

  /**
   * Player's id
   */
  private int id;

  /**
   * Player's kalah index.
   */
  private int kalah;

  public KalahPlayer() {

  }

  public KalahPlayer(int id, int kalah) {
    this.id = id;
    this.kalah = kalah;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getKalah() {
    return kalah;
  }

  public void setKalah(int kalah) {
    this.kalah = kalah;
  }

  /**
   * Determines if the Kalah belongs to player.
   */
  public boolean isMyKalah(int pit) {
    return kalah == pit;
  }

  /**
   * Determines if the pit belongs to player.
   */
  public boolean isMyPit(int pit) {
    if (id == 1)
      return 0 <= pit && pit <= 5;
    else
      return 7 <= pit && pit <= 12;
  }

}
