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
    static Card c2 = null;
    private static boolean firstCardClicked = false;
    static ArrayList<Card> cardList;
    private static Player p1;
    private static Player p2;
    private static Player winner;
    private static int matchCount;


    Board() {
        reset();
        cardList = BoardUtilities.initCards(numberOfPairs);
        matchCount = 0;
        p1 = new Player("P1");
        p2 = new Player("P2");
        winner = null;
        p1.setTurn(true);

    }


    /**
     * Checks if one or two cards are selected and determines which card
     * should be turned.
     *
     * @param card
     */
    void matchCards(Card card) {
        Logger.log("P1 ACTIVE: " + p1.isMyTurn());
        Logger.log("P2 ACTIVE: " + p2.isMyTurn() + "\n");

        if (!card.isCardMatched() && card.isTurned()) {
            boolean sameCard = false;
            if (!firstCardClicked) {
                c1 = card;
                firstCardClicked = true;
            } else if (firstCardClicked && card == c1) {
                Logger.log("SAME CARD SELECTED");
                sameCard = true;
                return;
            } else {
                c2 = card;
            }

            if (c1 != null && c2 == null && !sameCard) {
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
            ++matchCount;
            givePoints();
            nullCards();

        } else {
            Logger.log("CARDS DON'T MATCH");
            changeActivePlayer();
        }
    }

    /**
     * Changes active player if cards we're not matched
     */
    public void changeActivePlayer() {
        if (p1.isMyTurn()) {
            p1.setTurn(false);
            p2.setTurn(true);
            Logger.log("IT'S P2s TURN! NOW");
        } else {
            p2.setTurn(false);
            p1.setTurn(true);
            Logger.log("IT'S P1s TURN! NOW");
        }
    }

    /**
     * Determines winner from collected points
     */
    private void announceWinner() {

        if (p1.getPoints() > p2.getPoints()) {
            winner = p1;
        } else if (p1.getPoints() < p2.getPoints()) {
            winner = p2;
        } else if (p1.getPoints() == p2.getPoints()) {
            winner = p1;
            winner.setName("BOTH");
            //winner.setDraw(p1.getPoints());
        }
        Logger.log("P1: " + p1.getPoints() + "| P2:" + p2.getPoints());
    }


    /**
     * Locks matched cards so that they can not be turned anymore
     */
    private void setCardsMatched() {
        c1.setCardMatched(true);
        c2.setCardMatched(true);
    }

    /**
     * Turns both selected cards face down again
     */
    void turnBackCards() {
        c1.turn();
        c2.turn();
        BoardUtilities.delay(2);

    }

    /**
     * Sets selected cards to null
     */
    void nullCards() {
        c1 = null;
        c2 = null;
        firstCardClicked = false;
    }

    /**
     * Gives points to player who found a pair of cards
     */
    private void givePoints() {
        if (p1.isMyTurn()) {
            p1.incrementPoints();
            Logger.log("P1 points: " + p1.getPoints() + "\n ");
        } else if (p2.isMyTurn()) {
            p2.incrementPoints();
            Logger.log("P2 points: " + p2.getPoints() + "\n ");
        }

        if (matchCount == numberOfPairs) {
            announceWinner();
            Logger.log(winner.getName() + " WINS");
        }
    }


    public Board getBoard() {
        return this;
    }

    public static void reset() {
        c1 = null;
        c2 = null;
        p1 = null;
        p2 = null;
        winner = null;
        matchCount = 0;

    }

    /**
     *          *****************************************************************
     *          *                                                               *
     *          *                       GETTERS & SETTERS                       *
     *          *                                                               *
     *          *****************************************************************
     *
     */
    public Player getWinner() {
        return this.winner;
    }

    public int getP1Points() {
        return p1.getPoints();
    }

    public int getP2Points() {
        return p2.getPoints();
    }

    public boolean isP1Turn() {
        return p1.isMyTurn();
    }

    public boolean isP2Turn() {
        return p2.isMyTurn();
    }


}



