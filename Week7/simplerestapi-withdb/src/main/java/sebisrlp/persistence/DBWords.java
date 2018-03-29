package sebisrlp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import javax.annotation.Resource;
import sebisrlp.business.SRLPException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sebisrlp.business.WordPair;
import sebisrlp.business.Words;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class DBWords implements Words {

    /**
     * This datasource is either injected by the container or by the package
     * private constructor.
     */
    @Resource
    private DataSource ds;

    /**
     * Constructor for test purposes. Database will be provided by test
     * environment.
     *
     * @param dswp
     */
    public DBWords( DataSource ds ) {
        this.ds = ds;
    }

    /**
     * Default constructor as required by jee managed beans.
     */
    public DBWords() {
    }

    static final String GETQ
            = "select english,french from words where english=?";
    static final String GETALLQ
            = "select english,french from words";
    static final String POSTQ
            = "insert into words (english,french) values(?,?) returning *";
    static final String PUTQ
            = "update words set french=? where english=? returning *";
    static final String DELETEQ
            = "delete from words where english =? and french=? returning *";
    static final String COUNTQ
            = "select count(1) from words";

    //<editor-fold defaultstate="expanded" desc="TASK_1C2_JAVA2; __STUDENT_ID__; WEIGHT 10;">
    @Override
    public List<WordPair> get( String english ) throws SRLPException {
        //TODO TASK_1C2_JAVA2 implement List<WordPair> get(...)
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1D2_JAVA2; __STUDENT_ID__; WEIGHT 30;">
    @Override
    public List<WordPair> post( String english, String french ) throws
            SRLPException {
        //TODO TASK_1D2_JAVA2 implement List<WordPair> post(...)
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1E2_JAVA2; __STUDENT_ID__; WEIGHT 10;">
    @Override
    public List<WordPair> put( String english, String french ) throws
            SRLPException {
        //TODO TASK_1E2_JAVA2 implement List<WordPair> put(...)
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="expanded" desc="TASK_1G2_JAVA2; __STUDENT_ID__; WEIGHT 10;">
    @Override
    public List<WordPair> delete( String english, String french ) throws
            SRLPException {
        //TODO TASK_1G2_JAVA2 implement List<WordPair> delete(...)
        return null;
    }
    //</editor-fold>

    /**
     * Helper for get, put and delete to wrpa SQLException into a 404 when the
     * result list is empty. They get, put and delete all may assume that the
     * query is correct and any sql exception can only have an unrecoverable
     * cause.
     *
     * The words array has minimum length one. The first element of words may be
     * used in the error message, e.g. when a word is not found. Reuse, as in
     * CALL the doQuery method, but translate the sql exception into a
     * SRLPException code 500 (internal server error).
     *
     * @param queryText to send to the database
     * @param words the parameters to the query
     *
     * @return the word pair
     * @throws SRLPException wrapping and SQL exception with statuscode 500
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1B2_JAVA2; __STUDENT_ID__; WEIGHT 50;">
    List<WordPair> doQuerySimple( String queryText, String... words ) throws
            SRLPException {
        //TODO TASK_1B2_JAVA2 implement doQuerySimple 
        return null;
    }
    //</editor-fold>

    /**
     * Helper function to contact that database and do the result set handling.
     * All queries in this class return a list of word pairs
     *
     * @param queryText the query text to use.
     * @param words the lists that are to be used and substituted into the
     * query.
     *
     * @return the result of the query.
     * @throws SQLException
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1A2_JAVA2; __STUDENT_ID__; WEIGHT 80;">
    List<WordPair> doQuery( String queryText, String... words ) throws
            SQLException {
        //TODO TASK_1A2_JAVA2 implement doQuery
        return null;
    }
    //</editor-fold>

    @Override
    public List<WordPair> getAll() throws SRLPException {
        return doQuerySimple( GETALLQ );
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        try {
            return 0 == size();
        } catch ( SRLPException ex ) {
            Logger.getLogger( DBWords.class.getName() ).log( Level.SEVERE, null,
                    ex );
        }
        return result;
    }

    @Override
    public Iterator<WordPair> iterator() {
        Iterator<WordPair> result = Collections.EMPTY_LIST.iterator();
        try {
            result= getAll().iterator();
        } catch ( SRLPException ex ) {
            Logger.getLogger( DBWords.class.getName() ).log( Level.SEVERE, null,
                    ex );
        }
        return result;
    }

    @Override
    public int size() throws SRLPException {
        int result = 0;
        try ( Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement( COUNTQ ); ) {
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                result = rs.getInt( 1 );
            }
        } catch ( SQLException ex ) {
            throw new SRLPException( 500, "internal server error", ex );
        }
        return result;
    }
}
