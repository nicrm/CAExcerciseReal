package module1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is thread-safe and awesome
 * @author jens
 */
public class Immutable {
    
    private final int x;
    private final int y;
    
    public Immutable(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public Immutable setX(int newX) {
        Immutable newImmutable = new Immutable(newX, y);
        return newImmutable;
    }
    
    public Immutable setY(int newY) {
        Immutable newImmutable = new Immutable(x, newY);
        return newImmutable;
    }
    
}
