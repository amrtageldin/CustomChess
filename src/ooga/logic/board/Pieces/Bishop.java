package ooga.logic.board.Pieces;

import ooga.logic.board.GameCoordinate;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.Pieces.SpotCollection.finalRankPromotionSpots;

public class Bishop extends Piece {
  public Bishop(int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    updateRankAndFile();
    setMyMovement(new KingMovement());
    setMyCapture(new KingMovement());
    setMyPromotionSpots(new finalRankPromotionSpots());
    setMyPromotionPieces(new DefaultPromotionPieces());
  }


}
