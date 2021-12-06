package ooga.view.ui.gameplaypanel;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.view.ui.SharedUIComponents;
import ooga.view.ui.controlpanel.AnimationControlPanel;
import ooga.view.ui.controlpanel.LoadControlPanel;
import ooga.view.ui.controlpanel.ViewControlPanel;

public class GameplayPanel extends SharedUIComponents {
  private int myGameplayPanelX;
  private GraveyardPanel myGraveyardPanel;
  private HistoryPanel myHistoryPanel;

  /**
   * Create the general control panel constructor
   * @param gameplayPanelX the location on the UI that the control panel should be located at
   */
  public GameplayPanel(int gameplayPanelX){
    myGameplayPanelX = gameplayPanelX;
  }

  public Node createGameplayPanel(){
    VBox newGameplayPanel = new VBox();
    newGameplayPanel.setSpacing(getInt("gameplay_panel_spacing"));
    newGameplayPanel.setAlignment(Pos.CENTER);
    newGameplayPanel.setLayoutX(myGameplayPanelX);
    newGameplayPanel.setLayoutY(getInt("gameplay_panel_y"));

    //TODO: fix description
    String myDescription = "Fix me by passing in from controller";
    VariantPanel myVariantPanel = new VariantPanel(myDescription);
    myVariantPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myVariantPanel.createVariantPanel());

    myHistoryPanel = new HistoryPanel();
    myHistoryPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myHistoryPanel.createHistoryPanel());

    myGraveyardPanel = new GraveyardPanel();
    myGraveyardPanel.setPanelListener(this.getPanelListener());
    newGameplayPanel.getChildren().add(myGraveyardPanel.createGraveyardPanel());

    return newGameplayPanel;
  }

  public void updateHistory(String historyText){
    myHistoryPanel.addHistory(historyText);
  }

  public void updateGraveyard(Piece deadPiece){
    myGraveyardPanel.addGraveyardEntry(deadPiece);
  }
}
