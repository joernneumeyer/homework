package restaurant;

import java.util.Arrays;

/**
 * Utilities which have no direct connection to the main code and problem.
 *
 * @author hom
 */
public class Utils {

    /**
     * Helper to place a text in a banner. Banner is 80 chars wide.
     *
     * @param centerText
     */
    static String createSeparator( String centerText ) {
        return createSeparator( centerText, '=' );
    }

    /**
     * Helper to place a text in a banner. Banner is 80 chars wide.
     *
     * @param centerText at the center
     * @param filler to fill up banner
     */
    static String createSeparator(String centerText, char filler){
        char[] bannerX = new char[ 80 ];
        Arrays.fill( bannerX, filler );
        StringBuilder sb = new StringBuilder();
        sb.append( bannerX );
        int start = ( 80 - centerText.length() ) / 2 - 1;
        sb.replace( start, start + centerText.length() + 2, " " + centerText
                + " " );
        return sb.substring( 0, 80).toString();
    }
    /**
     * Helper to print a text centered in '=' chars.
     * @param centerText 
     */
    static void printSeparator( String centerText ) {
        System.out.println( createSeparator( centerText,'=' ) );
    }
    /**
     * Helper to print a text centered in '=' chars.
     * @param centerText 
     * @param filler to fill up the banner.
     */
    static void printSeparator( String centerText, char filler ) {
        System.out.println( createSeparator( centerText, filler ) );
    }
}
