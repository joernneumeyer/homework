package sebisrlp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sebisrlp.business.Command;
import sebisrlp.business.SRLPException;
import sebisrlp.business.WordPair;
import sebisrlp.business.Words;

/**
 *
 * @author hom
 */
class ConnectionHandler implements Runnable {

    final Socket socket;
    final Words dict;

    public ConnectionHandler( Socket socket, Words dict ) {
        this.socket = socket;
        this.dict = dict;
    }

    private void handle() throws IOException {
        try (
                PrintWriter out
                = new PrintWriter( socket.getOutputStream(), true );
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( socket.getInputStream() ) ); ) {
            String inputLine;
            inputLine = in.readLine();
            while ( inputLine != null ) {
                String[] lp = inputLine.split( "\\s+" );
                String[] words = Arrays.copyOfRange( lp, 1, lp.length );
                try {
                    List<WordPair> result;
                    Command cmd = null;
                    try {
                        cmd = Command.valueOf( Command.class, lp[ 0 ].toUpperCase() );
                    } catch ( IllegalArgumentException iae ) {
                        throw new SRLPException( 400, "command not found" );
                    }
                    System.out.println( cmd.name() + "+" + Arrays.toString( words ) );
                    result = cmd.process( dict, words );
                    if ( null == result ) {
                        throw new SRLPException( 500, "internal server error" );
                    }
                    out.print( "200 Ok\r\n\r\n" );
                    result.forEach( s -> {
                        out.print( s + "\r\n" );
                        System.out.println( s );
                    } );
                    out.flush();
                } catch ( SRLPException ex ) {
                    Logger.getLogger( SRLPSingleServer.class.getName() ).log( Level.INFO, ex.getMessage(), ex );
                    out.print( ex.getStatuscode() + " " + ex.getMessage() + "\r\n\r\n" );
                    out.flush();
                }
                inputLine = in.readLine();
            }
        }
    }

    @Override
    public void run() {
        try {
            handle();
        } catch ( IOException ex ) {
            Logger.getLogger( ConnectionHandler.class.getName() ).log( Level.INFO, null, ex );
            throw new RuntimeException( ex.getMessage() );
        }
    }

}
