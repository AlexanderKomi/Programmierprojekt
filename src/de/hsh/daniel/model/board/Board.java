package de.hsh.daniel.model.board;


import common.util.Logger;
import de.hsh.daniel.model.Card;
import de.hsh.daniel.model.Player;

import java.util.ArrayList;


/**
 * Class represents Gameboard where Cards are laid out
 */
public class Board {

    public static byte numberOfPairs = 0; // These are set externally from the menu.

    private static Card c1 = null;
    private static Card c2 = null;
    private static boolean firstCardClicked = false;
    static ArrayList<Card> cardList;
    private static Player p1;
    private static Player p2;
    private static Player winner;
    private static int matchCount = 0;


    Board() {
        cardList = BoardUtilities.initCards(numberOfPairs);
        p1 = new Player();
        p2 = new Player();
        p1.setTurn(true);
    }

    void matchCards(Card card) {
        Logger.log("P1 ACTIVE: " + p1.isMyTurn());
        Logger.log("P2 ACTIVE: " + p2.isMyTurn() + "\n");

        if (!card.isCardMatched()) {
            if (!firstCardClicked) {
                c1 = card;
                firstCardClicked = true;
            } else {
                c2 = card;
            }
            if (c1 != null && c2 == null) {
                c1.turn();
            } else if (c1 != null) {
                c2.turn();
            }
        }
        this.checkMatch();
    }

    /**
     * Checks if two cards match and resets cards after check
     * If two cards selected then cards are checked if they match
     */
    private void checkMatch() {

        if (c1 == null || c2 == null) {
            Logger.log("One card empty. \n ");
            return;
        }

        if (c1.equals(c2)) {
            Logger.log("CARDS MATCH");
            setCardsMatched();
            matchCount++;

            if (p1.isMyTurn()) {
                p1.incrementPoints();
                Logger.log("P1 points: " + p1.getPoints() + "\n ");
            } else {
                p2.incrementPoints();
                Logger.log("P2 points: " + p2.getPoints() + "\n ");
            }

            if(matchCount == numberOfPairs) {
                getWinner();
                Logger.log(winner.getName() + " WINS");
            }
            nullCards();
            return;


        } else {
            Logger.log("CARDS DON'T MATCH");

            if (p1.isMyTurn()) {
                p1.setTurn(false);
                p2.setTurn(true);
                Logger.log("IT'S P2s TURN! NOW");
            } else {
                p2.setTurn(false);
                p1.setTurn(true);
                Logger.log("IT'S P1s TURN! NOW");
            }
            return;

            /*BoardUtilities.delay(2);
            turnBackCards();
            nullCards();*/
        }


    }

    public Player getWinner() {
        winner = new Player();
        if(p1.isMyTurn()) {
            winner = p1;
        } else {
            winner = p2;
        }
        return winner;
    }
    /**
     * Locks matched cards so that they can not be turned anymore
     */
    private void setCardsMatched() {
        c1.setCardMatched(true);
        c2.setCardMatched(true);
    }


    /**
     * Turns both selected cards facedown again
     */
    public void turnBackCards() {
        c1.turn();
        c2.turn();


    }

    /**
     * Sets cards to null
     */
    public void nullCards() {
        c1 = null;
        c2 = null;
        firstCardClicked = false;
    }


    public Card getC1() {
        return this.c1;
    }

    public Card getC2() {
        return this.c2;
    }

}



