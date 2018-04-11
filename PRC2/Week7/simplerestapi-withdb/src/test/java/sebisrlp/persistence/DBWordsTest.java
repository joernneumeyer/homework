package sebisrlp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import sebisrlp.business.SRLPException;
import sebisrlp.business.WordPair;
import sebisrlp.business.Words;
import static org.mockito.Mockito.*;
import static sebisrlp.persistence.DBWords.GETQ;
import static sebisrlp.persistence.DBWords.DELETEQ;
import static sebisrlp.persistence.DBWords.POSTQ;
import static sebisrlp.persistence.DBWords.PUTQ;
import static sebisrlp.persistence.TestDatasource.DS;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class DBWordsTest {

    Words words;

    public DBWordsTest() {
    }

    @Before
    public void setup() {
        words = new DBWords( TestDatasource.DS );
    }

    @BeforeClass
    public static void setupClass() throws SQLException {
        resetDataSource();
    }

    /**
     * Test the lucky case for doQuery.
     *
     * Try to get the translation for 'furious' which is 'furieux'. You may
     * assume that the data is in the database. Use the predefined
     * {@link DBWords#GETQ} query string and the English word 'furious' to get
     * the word pair 'furious'-&gt; 'furieux' as first element in the resulting
     * list.
     *
     * Assert that word1 of the first word pair element in the list is 'furious'
     * and word2 is 'furieux'.
     *
     * @throws SQLException should not happen.
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1A1_SEN1; __STUDENT_ID__; WEIGHT 20;">
    @Test
    public void testQuery() throws SQLException {
        //TODO TASK_1A1_SEN1 lucky path test for doQuery
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Test a word that is not in the dictionary. Use the predefined
     * {@link DBWords#GETQ} query string and test with the word 'dandelion'.
     * This should NOT cause an sql exception, but instead an SRLPException,
     * telling that the word has not been found. Check that the status code
     * equals 404.
     *
     * @throws SRLPException should happen.
     */
    @Test
    //<editor-fold defaultstate="expanded" desc="TASK_1B1_SEN1; __STUDENT_ID__; WEIGHT 30;">
    public void testQuerySimple() {
        //TODO TASK_1B1_SEN1 lucky path test for doQuerySimple
        fail("test not implemented");
    }

    //</editor-fold>
    /**
     * Test that an SQL exception is mapped to an SRLP exception. This is a
     * mockito test. Mock the datasource and make it throw an SQLException on
     * some method. The SUT should wrap this exception into an SRLPException
     * with status code 500. Test with the doSimpleQuery method, which should
     * show the required behaviour, and should wrapthe exception. The expected
     * resulting exception is an SRLPException with status code 500.
     *
     * Use Mockito to mock the datasource and have this mock throw an
     * SQLException on the getConnection() call. Then use this mock to create a
     * DBWords instance with the mocked datasource as only parameter to the
     * constructor.
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1B3_SEN1; __STUDENT_ID__; WEIGHT 60;">
    @Test
    public void testSQLExceptionHandlingByQuerySimple() throws SQLException {
        //TODO TASK_1B3_SEN1 test testSQLExceptionHandling
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Test the successful retrieval of the translation from english to french,
     * using the word pair furious -&gt; furieux. The setup of the test database
     * is such that this word pair exists. Use the
     * {@link Words#get(java.lang.String)} method. You may use the
     * {@link WordPair#equals(java.lang.Object)} method in the assert.
     *
     * @throws SRLPException not expected
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1C1_SEN1; __STUDENT_ID__; WEIGHT 20;">
    @Test
    public void testGetWordSuccess() throws SRLPException {
        //TODO TASK_1C1_SEN1 test testGetWordSuccess
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Test that when a word is not found, the proper exception SRLPException is
     * being thrown. Choose an English word that is not (yet) in the dictionary,
     * like 'dandelion'.
     *
     * The exception should have status code 404 (not found) and a message
     * telling that 'dandelion' was not found. It suffices that the word is
     * contained in the message of the exception.
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1C3_SEN1; __STUDENT_ID__; WEIGHT 40;">
    @Test
    public void testGetWordFail() {
        //TODO TASK_1C3_SEN1 test testGetWordFail
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Check that a word pair stored can de retrieved successfully. Ensure that
     * a word can be saved using the post method and then can be looked up by
     * the get method. Use the word pair "sausage" =&gt; "saucisse".
     *
     * @throws SRLPException should not happen.
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1D1_SEN1; __STUDENT_ID__; WEIGHT 30;">
    @Test
    public void testAStoredWordCanBeLookedUp() throws SRLPException {
        //TODO TASK_1D1_SEN1 test testAStoredWordCanBeLookedUp
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Test that word duplicates are not allowed. The code should not allow two
     * mappings for the same English word. Not that this is implemented as a
     * constraint in the words table in the database. This will cause a
     * constraint violation to bubble up as an {@link java.sql.SQLException}, so
     * the post method must handle that appropriately.
     *
     * As test word pair take 'car' and 'voiture' and try to insert it twice.
     * The second attempt should fail with an SRLPException with the correct
     * status code.
     *
     * The {@link SRLPException} status code should have the value 409.
     *
     * @throws SRLPException expected, but must be tested for the proper error
     * code.
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1D3_SEN1; __STUDENT_ID__; WEIGHT 40;">
    @Test
    public void testGetPostDuplicateFails() throws SRLPException {
        //TODO TASK_1D3_SEN1 test testGetPostDuplicateFails
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Check that a word pair update ( replacing the French part) succeeds.
     *
     * @throws SRLPException not expected
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1E1_SEN1; __STUDENT_ID__; WEIGHT 30;">
    @Test
    public void testWordPairCanBeUpdated() throws SRLPException {
        //TODO TASK_1E1_SEN1 test testWordPairCanBeUpdated
        fail("test not implemented");
    }
    //</editor-fold>

    /**
     * Test the delete method. On successful delete, the deleted word pair is
     * returned as only entry in the result. WordPair has a working (and tested)
     * equals method, that might ease your work. Assert that this value is
     * returned and that the element no longer exists in the database. If the
     * get method is implemented, you may use that for this test. Use the word
     * pair beer,bière for this test.
     *
     * @throws SRLPException
     */
    //<editor-fold defaultstate="expanded" desc="TASK_1G1_SEN1; __STUDENT_ID__; WEIGHT 40;">
    @Test
    public void testAStoredPairCanBeDeleted() throws SRLPException {
        //TODO TASK_1G1_SEN1 test testAStoredPairCanBeDeleted
        fail("test not implemented");
    }
    //</editor-fold>

    @Test
    public void testSize() throws SRLPException {
        assertEquals( 1, words.size() );
    }

    //<editor-fold defaultstate="collapsed" desc="NO EXAM WORK HERE">
    static String ddl
            = "truncate words; insert into words values ('furious','furieux'); ";

    static void resetDataSource() throws SQLException {
        try ( Connection con = DS.getConnection();
                PreparedStatement p = con.prepareStatement( ddl ); ) {
            p.execute();
        }
    }
    //</editor-fold>
}
