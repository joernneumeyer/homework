package sebisrlp.client;

import java.io.*;
import java.net.*;
import java.util.List;
import sebisrlp.business.Command;

/**
 * Simpler Rest Like Protocol client.
 * @author hom
 */
public class SRLPClient {

    
    public static void main( String[] args ) throws IOException {
        String hostName = System.getProperty( "sebisrlp.server", "127.0.0.1" );
        int portNumber = Integer.parseInt( System.getProperty( "sebisrlp.port", "8333" ) );
        try (
                Socket echoSocket = new Socket( hostName, portNumber );
                PrintWriter out = new PrintWriter( echoSocket.getOutputStream(),
                        true ); // true denotes autoflush
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( echoSocket.getInputStream() ) );
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader( System.in ) ) ) {
            playWithIt( stdIn, out, in );
        } catch ( UnknownHostException e ) {
            System.err.println( "Don't know about host " + hostName );
            System.exit( 1 );
        } catch ( IOException e ) {
            System.err.println( "Couldn't get I/O for the connection to "
                    + hostName );
            System.exit( 1 );
        }
    }

    private static void playWithIt( final BufferedReader stdIn, final PrintWriter out, final BufferedReader in ) throws IOException {
        String userInput;
        while ( ( userInput = stdIn.readLine() ) != null ) {
            String[] lp = userInput.split( "\\s+" );
            out.println( userInput );
            Command cmd = Command.valueOf( lp[ 0 ].toUpperCase() );
            List<String> header = cmd.readHeader( in );
            System.out.println( "header = " + header );
            if ( header.get( 0 ).startsWith( "200" ) ) {
                List<String> response = cmd.readData( in );
                response.stream().filter( s -> !s.isEmpty() ).forEach( System.out::println );
            } else {
                System.out.println( "command failed, cause:  " + header.get( 0 ) );
            }
        }
    }
}
