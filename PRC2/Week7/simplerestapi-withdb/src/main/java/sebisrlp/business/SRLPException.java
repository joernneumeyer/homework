package sebisrlp.business;

/**
 * Simple Rest Like Protocol Exception. Has status code that can be passed in at
 * construction time and also a {@link java.lang.Throwable} cause, to chain to
 * the actual cause of this exception.
 *
 * @author hom
 */
public class SRLPException extends Exception {

    private static final long serialVersionUID = 1L;
    private final int statuscode;

    /**
     * Exception with status code.
     *
     * @param statuscode to add
     * @param msg of exception
     */
    public SRLPException( int statuscode, String msg ) {
        super( msg );
        this.statuscode = statuscode;
    }

    /**
     * Exception with status code message and cause (other exception).
     *
     * @param statuscode to add
     * @param msg to add
     * @param cause deeper cause of this exception.
     */
    public SRLPException( int statuscode, String msg, Exception cause ) {
        super( msg, cause );
        this.statuscode = statuscode;
    }

    public int getStatuscode() {
        return statuscode;
    }

}
