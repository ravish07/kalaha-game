package com.kalaha.game.model;

import com.kalaha.game.services.KalahGame;

/**
 * Serves as transfer object for a game play.
 *
 * @author Ravish
 */
public class GamePlay {
  
  /**
   * Pit selected by the player.
   */
  private int pit;
  
  /**
   * Current game state.
   */
  private KalahGame game;

  public int getPit() {
    return pit;
  }

  public void setPit(int pit) {
    this.pit = pit;
  }

  public KalahGame getGame() {
    return game;
  }

  public void setGame(KalahGame game) {
    this.game = game;
  }

}
