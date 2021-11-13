package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn extends Piece implements MoveLogic, CaptureLogic, PromoteLogic {

  public Pawn(){
    setMyCoordinate(new CoordinateUseCase(1,1));
    updateRankAndFile();
  }



//  @Override
//  public void captures(Coordinate captureCoordinate) {
//    if (canCapture(captureCoordinate)){
//      myCoordinate.setCoordinate(captureCoordinate);
//      // TODO: remove the piece thats on this square in the board
//    }
//  }

//  @Override
//  public void moves() {
//  }

  @Override
  public List<Coordinate> getPossibleCaptures() {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;
    int[] addXAmount = new int[]{-1,1};
    int[] addYAmount = new int[]{1};

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        newCapture = new CoordinateUseCase(getMyXCoordinate() + xAmt, getMyYCoordinate() + yAmt);
        if (isValidSquare(newCapture)){
          myCoordinateList.add(newCapture);
        }
      }
    }
    return myCoordinateList;
  }

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
   return true;
  }



  @Override
  public List<Coordinate> getPossibleMoves() {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    CoordinateUseCase moveCoordinate = new CoordinateUseCase(getMyXCoordinate(), getMyYCoordinate() + 1);
    myPossibleMoves.add(moveCoordinate);
    if (getMyRank() == 1){
      moveCoordinate.setCoordinate(getMyXCoordinate(), getMyYCoordinate() + 2);
      myPossibleMoves.add(moveCoordinate);
    }
    return myPossibleMoves;
  }

  @Override
  public void updatePosition(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }


  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }

  @Override
  public void setState() {

  }

  @Override
  public List<Coordinate> promotionSquares() {
    List<Integer> xOfSquares = new ArrayList<>();
    for (int i = 0; i < 8; i++){
      xOfSquares.add(i);
    }
    List<Integer> yOfSquares = new ArrayList<>();
      yOfSquares.add(7);

    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    for (int xPos : xOfSquares){
      for (int yPos: yOfSquares){
        newCapture = new CoordinateUseCase(xPos,yPos);
        myCoordinateList.add(newCapture);
      }
    }
    return myCoordinateList;
  }

  @Override
  public List<PieceLogic> possiblePromotionPieces(){
  List<PieceLogic> boops = new ArrayList<>();
  // define: Bishop rook knight queen for now
  return boops;
  }

  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }

}
