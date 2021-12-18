package com.adventofcode2021.dec18;

import java.util.Optional;

class SnailfishNumber {

    private long regularValue;
    private Children children;
    private SnailfishNumber containingPair;

    private SnailfishNumber( long regularValue ) {
        this.regularValue = regularValue;
    }

    private SnailfishNumber( Children children ) {
        this.children = children;
        children.setContainingPair( this );
    }

    static SnailfishNumber ofRegularValue( long regularValue ) {
        return new SnailfishNumber( regularValue );
    }

    static SnailfishNumber ofPair( SnailfishNumber left, SnailfishNumber right ) {
        return new SnailfishNumber( new Children( left, right ) );
    }

    boolean isRegular() {
        return children == null;
    }

    boolean isPair() {
        return children != null;
    }

    private void reduce() {
        while ( true ) {
            if ( ! explodeAPair( 0 ) && ! splitARegularNumber() ) {
                break;
            }
        }
    }

    private boolean explodeAPair( int containingPairCount ) {
        if ( isPair() ) {
            if ( containingPairCount == 4 ) {
                explode();
                return true;
            } else {
                return children.left.explodeAPair( containingPairCount + 1 )
                    || children.right.explodeAPair( containingPairCount + 1 );
            }
        }

        return false;
    }

    private boolean splitARegularNumber() {
        if ( isRegular() ) {
            if ( regularValue >= 10 ) {
                split();
                return true;
            } else {
                return false;
            }
        } else if ( isPair() ) {
            return children.left.splitARegularNumber() || children.right.splitARegularNumber();
        }

        throw new IllegalStateException( "Current number is neither regular number nor pair: + " + this );
    }

    private void split() {
        long left = regularValue / 2;
        long right = left + regularValue % 2;
        changeToPair( left, right );
    }

    private void explode() {
        assert children.left.isRegular() && children.right.isRegular();
        Optional.ofNullable( containingPair ).ifPresent( p -> {
            p.handleLeftExplodedValue( new ExplodedMessage( this, children.left.regularValue ) );
            p.handleRightExplodedValue( new ExplodedMessage( this, children.right.regularValue ) );
        } );
        // notify parent
        changeToRegular( 0L );
    }

    private void handleLeftExplodedValue( ExplodedMessage message ) {
        if ( message.source() == children.right ) {
            children.left.addToRight( message.value() );
        } else {
            Optional.ofNullable( containingPair ).ifPresent( c -> c.handleLeftExplodedValue( message.withSource( this ) ) );
        }
    }

    private void handleRightExplodedValue( ExplodedMessage message ) {
        if ( message.source() == children.left ) {
            children.right.addToLeft( message.value() );
        } else {
            Optional.ofNullable( containingPair ).ifPresent( c -> c.handleRightExplodedValue( message.withSource( this ) ) );
        }
    }

    private void addToLeft( long value ) {
        if ( isPair() ) {
            children.left.addToLeft( value );
        } else {
            this.regularValue += value;
        }
    }

    private void addToRight( long value ) {
        if ( isPair() ) {
            children.right.addToRight( value );
        } else {
            this.regularValue += value;
        }
    }

    private void changeToPair( long left, long right ) {
        regularValue = 0L;
        children = new Children( SnailfishNumber.ofRegularValue( left ), SnailfishNumber.ofRegularValue( right ) );
        children.setContainingPair( this );
    }

    private void changeToRegular( long regularValue ) {
        this.regularValue = regularValue;
        children = null;
    }

    SnailfishNumber plus( SnailfishNumber other ) {
        SnailfishNumber sum = SnailfishNumber.ofPair( this, other );
        sum.reduce();
        return sum;
    }

    long magnitude() {
        if ( isRegular() ) {
            return regularValue;
        } else if ( isPair() ) {
            return 3 * children.left.magnitude() + 2 * children.right.magnitude();
        }

        throw new IllegalStateException( "Current number is neither regular number nor pair: + " + this );
    }

    @Override
    public String toString() {
        if ( isRegular() ) {
            return Long.toString( regularValue );
        } else {
            return children.toString();
        }
    }

    private static class Children {

        private final SnailfishNumber left;
        private final SnailfishNumber right;

        Children(SnailfishNumber left, SnailfishNumber right ) {
            this.left = left;
            this.right = right;
        }

        private void setContainingPair( SnailfishNumber containingPair ) {
            left.containingPair = containingPair;
            right.containingPair = containingPair;
        }

        @Override
        public String toString() {
            return "[" + left + "," + right + "]";
        }
    }
}
