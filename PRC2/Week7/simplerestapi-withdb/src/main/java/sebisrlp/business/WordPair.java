package sebisrlp.business;

import java.util.Objects;

/**
 * Simple value object with hashCode and equals.
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class WordPair {
    public final String word1,word2;

    public WordPair( String word1, String word2 ) {
        this.word1 = word1;
        this.word2 = word2;
    }
            
    @Override
    public String toString() {
        return word1 + "=" + word2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode( this.word1 );
        hash = 59 * hash + Objects.hashCode( this.word2 );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final WordPair other = ( WordPair ) obj;
        if ( !Objects.equals( this.word1, other.word1 ) ) {
            return false;
        }
        return Objects.equals( this.word2, other.word2 );
    }
}
