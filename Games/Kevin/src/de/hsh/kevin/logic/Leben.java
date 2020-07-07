package de.hsh.kevin.logic;

/**
 * Leben erstellt ein Counter
 * @author Kevin
 *
 */
public class Leben {
    private int leben;
    
    /**
     * Erstellt das Objekt mit der Aktuellen anzahl Leben, die in SkinConfig.getLife() liefert
     */
    public Leben() {
	leben = (int) Config.getLife();
    }
    
    /**
     * Erhöht counter um 1
     */
    public void increase() {
	leben++;
    }
    
    /**
     * Verringert counter um 1
     */
    public void decrease() {
	leben--;
    }
    
    /**
     *  Liefert Anzahl der Leben
     * @return Anzahl der Leben
     */
    public int getLeben() {
	return leben;
    }

}
