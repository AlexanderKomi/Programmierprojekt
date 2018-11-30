package de.hsh.kevin.logic;

public class Leben {
    private int leben;
    
    public Leben() {
	leben = (int) Config.getLife();
    }
    
    public void increase() {
	leben++;
    }
    
    public void decrease() {
	leben--;
    }
    
    public int getLeben() {
	return leben;
    }

}
