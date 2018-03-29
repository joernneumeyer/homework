package sebisrlp.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;

/**
 * Commands and utilities for use at server and client side.
 *
 * @author hom
 */
public enum Command {

    /**
     * CRUD-Read Get command appends empty line to response.
     */
    GET {
        /**
         * Server side Process the get command working with dict.
         *
         * @param dict dictionary to use
         * @param args the command argument list
         * @return the response line with and empty line appended.
         * @throws SRLPException on error of the input or word not found.
         */
        @Override
        public List<WordPair> process( Words dict, String... args ) throws
                SRLPException {
            List<WordPair> result = new ArrayList<>();
            if ( args.length == 0 ) {
                if ( dict.isEmpty() ) {
                    throw new SRLPException( 404,
                            "Empty dictionary, no translations found" );
                }
                result = dict.getAll();
            } else {
                String word = args[ 0 ];
                result = dict.get( args[ 0 ] );
            }
            return result;
        }

        /**
         * Client side method, only get has real output.
         *
         * @param in
         * @return
         * @throws IOException
         */
        @Override
        public List<String> readData( BufferedReader in ) throws IOException {
            return readLines( in );
        }

    },
    /**
     * CRUD-Update Put command.
     */
    PUT {
        /**
         * Server side process PUT command.
         *
         * @param dict the dictionary to work with
         * @return empty list. This command has no return value.
         * @throws not found error if the key is not found, or if the args count
         * is less then 2.
         */
        @Override
        public List<WordPair> process( Words dict, String... args )
                throws SRLPException {
            checkArgCount( 2, args );
            if ( null == dict.put( args[ 0 ], args[ 1 ] ) ) {
                throw new SRLPException( 404, "key " + args[ 0 ] + " not found" );
            }
            return Collections.EMPTY_LIST;
        }
    },
    /**
     * CRUD-Create command.
     */
    POST {
        /**
         * Server side process POST command.
         *
         * @param dict the dictionary to work with
         * @return empty list. This command has no return value.
         * @throws error if the key is found, or if the args count is less then
         * 2.
         */
        @Override
        public List<WordPair> process( Words dict, String... args )
                throws SRLPException {
            checkArgCount( 2, args );
            return dict.post( args[ 0 ], args[ 1 ] );
        }
    },
    /**
     * CRUD-Delete command.
     */
    DELETE {
        /**
         * Server side process DELETE command.
         *
         * @param dict the dictionary to work with
         * @return empty list. This command has no return value.
         * @throws error if the key notfound, or if the args count is less then
         * 1.
         */

        @Override
        public List<WordPair> process( Words dict, String... args )
                throws SRLPException {
            checkArgCount( 2, args );
            return dict.delete( args[ 0 ], args[ 1 ] );
        }

    };

    /**
     * Check the argment count.
     *
     * @param args
     * @throws SRLPException
     */
    private static void checkArgCount( int minCount, String[] args ) throws
            SRLPException {
        if ( args.length < minCount ) {
            throw new SRLPException( 409, "to few arguments" );
        }
    }

    public abstract List<WordPair> process( Words dict, String... args ) throws
            SRLPException;

    /**
     * Reads the 'header' from the input stream. The header is a set of lines
     * terminated with an empty line. The empty line is not included in the
     * result.
     *
     * @param in stream to read
     * @return the lines making up the header
     * @throws IOException when something happens to the in stream, like close.
     */
    public List<String> readHeader( BufferedReader in ) throws IOException {
        return readLines( in );
    }

    /**
     * Reads lines from the stream until an empty line or the end of the input,
     * signalled by a NULL read.
     *
     * @param in to read from
     * @return list of lines
     * @throws IOException when the in channel is closed unexpectedly.
     */
    List<String> readLines( BufferedReader in ) throws IOException {
        List<String> result = new ArrayList<>();
        String l = in.readLine();
        do {
            result.add( l );
            l = in.readLine();
        } while ( l != null && !l.isEmpty() );
        return result;
    }

    /**
     * Reads the 'header' from the input stream. The header is a set of lines
     * terminated with an empty line. The empty line is not included in the
     * result. A empty line or null from the input stop the reading.
     *
     * @param in stream to read
     * @return the lines making up the header
     * @throws java.io.IOException when in closes prematurely.
     */
    public List<String> readData( BufferedReader in ) throws IOException {
        return Collections.EMPTY_LIST;
    }
}
