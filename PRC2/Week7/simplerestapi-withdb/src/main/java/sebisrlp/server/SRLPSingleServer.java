package sebisrlp.server;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sebisrlp.business.Command;
import sebisrlp.business.SRLPException;
import sebisrlp.business.WordPair;
import sebisrlp.business.Words;
import sebisrlp.persistence.DBWords;
import static sebisrlp.persistence.Datasource.DS;

/**
 * Single client server for the SRLP protocol.
 *
 * @author hom
 */
public class SRLPSingleServer {

    static Words dict = new DBWords( DS );

    public static void main( String[] args ) throws IOException {
        int portNumber = Integer.parseInt( System.getProperty( "sebisrlp.port",
                "8333" ) );
        System.out.println( "portNumber = " + portNumber );
        try ( final ServerSocket serverSocket = new ServerSocket( portNumber );
                final Socket clientSocket = serverSocket.accept(); ) {
            handle( clientSocket, dict );
        } catch ( IOException e ) {
            System.out.println( "Exception caught when trying to listen on"
                    + "port " + portNumber + " or listening "
                    + "for a connection" );
            System.out.println( e.getMessage() );
        }
    }

    private static void handle( final Socket clientSocket, Words dict ) throws
            IOException {
        try (
                PrintWriter out
                = new PrintWriter( clientSocket.getOutputStream(), true );
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( clientSocket.getInputStream() ) ); ) {
            String inputLine;
            inputLine = in.readLine();
            while ( inputLine != null ) {
                String[] lp = inputLine.split( "\\s+" );
                String[] words = Arrays.copyOfRange( lp, 1, lp.length );
                try {
                    List<WordPair> result = null;
                    Command cmd = null;
                    try {
                        cmd = Command.valueOf( Command.class, lp[ 0 ]
                                .toUpperCase() );
                    } catch ( IllegalArgumentException iae ) {
                        throw new SRLPException( 400, "command not found" );
                    }
                    System.out.println( cmd.name() + "+" + Arrays.toString( 
                            words ) );
                    result = cmd.process( dict, words );
                    if ( null == result ) {
                        throw new SRLPException( 500, "internal server error" );
                    }
                    out.print( "200 Ok\r\n\r\n" );
                    result.forEach( s -> {
                        out.print( s + "\r\n" );
                        System.out.println( s );
                    } );
                    out.print( "\r\n" );
                    out.flush();
                    result = null;
                } catch ( SRLPException ex ) {
                    Logger.getLogger( SRLPSingleServer.class.getName() ).log( 
                            Level.INFO, ex.getMessage(), ex );
                    out.print( ex.getStatuscode() + " " + ex.getMessage()
                            + "\r\n\r\n" );
                    out.flush();
                }
                inputLine = in.readLine();
            }
        }
    }
}
