package restaurant;

// copyleft hom
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to read a file with the lines as array of strings.
 *
 * @author ode
 * @author hom
 */
public class Input {
    /**
     * Read the whole file as a list of strings.
     * 
     * @param file
     * @return a collection of strings.
     */
    public static List<String> getLines( String file ) {
        BufferedReader reader = null;
        List<String> result = new LinkedList<String>();
        try {
            reader = new BufferedReader( new FileReader( file ) );

            String s = reader.readLine();
            while ( null != s ) {
                result.add( s );
                s = reader.readLine();
            }
        } catch ( FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch ( IOException ex ) {
                    Logger.getLogger( Input.class.getName() ).
                            log( Level.SEVERE, null, ex );
                }
            }
        }
        return result;
    }
}
