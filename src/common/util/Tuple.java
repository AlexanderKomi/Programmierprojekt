package common.util;

@Deprecated
public class Tuple <T, K> implements Comparable<Tuple<T, K>> {

    private byte size = 0;
    private T    left;
    private K    right;

    public Tuple( T left, K right ) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj != null ) {
            if ( obj instanceof Tuple ) {
                return this.equals( (Tuple) obj );

            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.left + ", " + this.right + ")";
    }

    private boolean equals( Tuple<T, K> t ) {
        if ( t.left != null && t.right != null && this.left != null && this.right != null ) {
            return this.left.equals( t.left ) &&
                   this.right.equals( t.right );
        }
        return false;
    }

    @Override
    public int compareTo( Tuple<T, K> o ) {
        if ( o != null ) {

        }
        return 0;
    }

    public T getLeft() {
        return left;
    }

    public K getRight() {
        return right;
    }
}
