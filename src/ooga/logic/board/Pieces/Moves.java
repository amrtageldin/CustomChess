package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

public interface Moves {

  /**
   * This method would define the movement of the individual piece. For example,
   * for a pawn it would change the y-position of its location by 1
   */
  public void moves();


  /**
   * using the definition of the move method for the specific peice
   * and the piece's current position, it would create a list of
   * possible coordinates the piece could move to
   */
  public void updatePossibleMoves();


  /**
   *
   * @return the list of possible coordinates to move to
   */
  public List<Coordinate> getPossibleMoves();

  /**
   * Updates the internal coordinate of the piece after it is moved
   */
  public void updatePosition();

  /**
   * Changes the coordinate set for the piece
   */
  public void setCoordinate();

}



