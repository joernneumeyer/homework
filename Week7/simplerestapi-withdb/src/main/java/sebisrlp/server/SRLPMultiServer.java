package sebisrlp.server;

import java.net.*;
import java.io.*;
import sebisrlp.business.Words;
import sebisrlp.persistence.DBWords;
import sebisrlp.persistence.Datasource;

/**
 * 
 * @author hom
 */
public class SRLPMultiServer {

    static Words dict = new DBWords(Datasource.DS);

    public static void main( String[] args ) throws IOException {
        System.out.println("starting " + SRLPMultiServer.class.getName() );
        int portNumber = Integer.parseInt( System.getProperty( "sebisrlp.port", "8333" ) );
        System.out.println( "portNumber = " + portNumber );
        try (
                ServerSocket serverSocket
                = new ServerSocket( portNumber ); ) {
            while ( true ) {
                Socket clientSocket = serverSocket.accept();
                printClientInfo(clientSocket);
                new Thread( new ConnectionHandler( clientSocket, dict ) ).start();
            }
        } catch ( IOException e ) {
            System.out.println( "Exception caught when trying to listen on"
                    + "port " + portNumber + " or listening "
                    + "for a connection" );
            System.out.println( e.getMessage() );
        }
    }

    private static void printClientInfo( Socket clientSocket ) {
        System.out.println( "clientSocket = " + clientSocket );
    }

}
