package sebisrlp.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface Words extends Iterable<WordPair>{

    /**
     * Get a translation from this dictionary.
     *
     * @param word the word or phrase to translate.
     * @return a list of possible translations
     * @throws SRLPException when there is no such translation.
     */
    List<WordPair> get( String word ) throws SRLPException;

    /**
     * Add a translation to this dictionary.
     *
     * @param word as key
     * @param translation translation for this word
     * @return the translation pair
     * @throws SRLPException when the pair already exists
     */
    List<WordPair> post( String word, String translation ) throws SRLPException;

    /**
     * Replace an existing translation.
     *
     * @param word to translate
     * @param translation of the word or phrase
     * @return the new pair
     * @throws SRLPException when the word does not exist or when then new pair
     * already exists
     */
    List<WordPair> put( String word, String translation ) throws SRLPException;

    /**
     * Delete the translation pair. The delete will only delete where both
     * strings match.
     *
     * @param word to delete
     * @param translation to delete
     * @return the delete pair, word first, translation second.
     * @throws SRLPException when pair does not exist
     */
    List<WordPair> delete( String word, String translation ) throws SRLPException;

    /**
     * Get all word pairs.
     * @return the words.
     * @throws when empty.
     */
    default List<WordPair> getAll() throws SRLPException {
        return StreamSupport.stream( spliterator(), false ).collect( Collectors.toList());
    }
    
    public boolean isEmpty()throws SRLPException;

    public int size()  throws SRLPException;
    
}
