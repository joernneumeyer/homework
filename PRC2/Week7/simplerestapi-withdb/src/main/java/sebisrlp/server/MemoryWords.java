/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sebisrlp.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sebisrlp.business.SRLPException;
import sebisrlp.business.WordPair;
import sebisrlp.business.Words;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class MemoryWords implements Words {

    private final Map<String, String> words = new ConcurrentHashMap<>();

    @Override
    public List<WordPair> get( String word ) throws SRLPException {
        List<WordPair> result = new ArrayList<>();
        String value = words.get( word );
        if ( !words.containsKey( word ) ) {
            throw new SRLPException( 404, "cannot find word " + word );
        }
        result.add( new WordPair( word, words.get( word ) ) );
        return result;
    }

    @Override
    public List<WordPair> post( String word, String translation ) throws
            SRLPException {
        List<WordPair> result = new ArrayList<>();
        String v = words.putIfAbsent( word, translation );
        if ( v != null ) {
            throw new SRLPException( 409, "already mapped" );
        }
        result.add( new WordPair( word, translation ) );
        return result;
    }

    @Override
    public List<WordPair> put( String word, String translation ) throws
            SRLPException {
        List<WordPair> result = new ArrayList<>();
        try {
            boolean v = words.replace( word, words.get( word ), translation );
        } catch ( NullPointerException npe ) {
            throw new SRLPException( 404, "word not mapped", npe );
        }
        result.add( new WordPair( word, translation ) );
        return result;
    }

    @Override
    public List<WordPair> delete( String word, String translation ) throws
            SRLPException {
        List<WordPair> result = new ArrayList<>();
        boolean v = words.remove( word, translation );
        if ( !v ) {
            throw new SRLPException( 404, "pair not mapped" );
        }
        result.add( new WordPair( word, translation ) );
        return result;
    }

    @Override
    public boolean isEmpty() {
        return words.isEmpty();
    }

    @Override
    public Iterator<WordPair> iterator() {
        return new Iterator<WordPair>() {

            Iterator<Map.Entry<String, String>> mapitr = words.entrySet()
                    .iterator();

            @Override
            public boolean hasNext() {
                return mapitr.hasNext();
            }

            @Override
            public WordPair next() {
                Map.Entry<String, String> pair = mapitr.next();
                return new WordPair( pair.getKey(), pair.getValue() );
            }
        };
    }

    @Override
    public int size() {
        return words.size();
    }

}
