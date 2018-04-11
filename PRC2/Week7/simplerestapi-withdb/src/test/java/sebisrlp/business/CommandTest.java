package sebisrlp.business;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static sebisrlp.business.Command.*;
import sebisrlp.server.MemoryWords;

/**
 * Test class for sebisrlpserver.business.Command. The method names should be
 * enough hint for the functionality tested.
 *
 * @author hom
 */
public class CommandTest {

    /**
     * Test of values method, of class Command.
     */
    @Test
    public void testValues() {
        System.out.println( "values" );
        Command[] expResult = { GET, PUT, POST, DELETE };
        Command[] result = Command.values();
        assertArrayEquals( expResult, result );
    }

    /**
     * Test of valueOf method, of class Command.
     */
    @Test
    public void testValueOf() {
        System.out.println( "valueOf" );
        String name = "PUT";
        Command expResult = Command.PUT;
        Command result = Command.valueOf( name );
        assertEquals( expResult, result );
    }

    /**
     * Test of process method, of class Command.
     *
     * @throws sebisrlp.business.SRLPException
     */
    @Test( expected = SRLPException.class )
    public void testProcessGETListEmpty() throws SRLPException {
        System.out.println( "process get" );
        Words dict = new MemoryWords();

        Command command = GET;
        try {
            command.process( dict );
        } catch ( SRLPException se ) {
            assertEquals( "status not found", 404, se.getStatuscode() );
            throw se;
        }

    }

    @Test
    public void testLuckyFind() throws SRLPException {
        System.out.println( "process get word" );

        Words dict = new MemoryWords();
        dict.post( "lucky", "bastard" );
        Command command = GET;
        List<WordPair> result = command.process( dict, "lucky" );
        WordPair l0 = result.get( 0 );
        System.out.println( "l0 = " + l0 );
//        assertEquals( result.get( 0 ).equals( "bastard" ) );
    }

    @Test( expected = SRLPException.class )
    public void testFindFails() throws SRLPException {
        System.out.println( "process get word" );

        Words dict = new MemoryWords();
        dict.put( "lucky", "bastard" );
        Command command = GET;
        List<WordPair> result = command.process( dict, "unlucky" );
    }

    @Test
    public void testGetAll() throws SRLPException {
        System.out.println( "process get all" );
        Words dict = new MemoryWords();
        WordPair wp1 = new WordPair( "lucky", "bastard" );
        WordPair wp2 = new WordPair( "hello", "world" );

        dict.post( wp1.word1, wp1.word2 );
        dict.post( wp2.word1, wp2.word2 );
        Command command = GET;
        List<WordPair> result = command.process( dict );
        assertEquals( 2, result.size() );
        System.out.println( "result = " + result );
        assertTrue( result.contains( wp1 ) );
        assertTrue( result.contains( wp2 ) );
    }

    @Test( expected = SRLPException.class )
    public void testPutTooFewArgs() throws SRLPException {
        System.out.println( "process put to few args" );

        Words dict = new MemoryWords();
        Command command = PUT;
        try {
            command.process( dict, "lucky" );
        } catch ( SRLPException se ) {
            assertEquals( "status code", 409, se.getStatuscode() );
            throw se;
        }
    }

    @Test( expected = SRLPException.class )
    public void testPostTooFewArgs() throws SRLPException {
        System.out.println( "process post too few args" );
        Words dict = new MemoryWords();

        Command command = POST;
        try {
            command.process( dict, "lucky" );
        } catch ( SRLPException se ) {
            assertEquals( "status code", 409, se.getStatuscode() );
            throw se;
        }

    }

    @Test
    public void testPostNew() throws SRLPException {
        System.out.println( "process post new" );
        Words dict = new MemoryWords();
        WordPair wp = new WordPair( "lucky", "bastard" );
        Command command = POST;
        try {
            command.process( dict, wp.word1, wp.word2 );
        } catch ( SRLPException ex ) {
            assertEquals( 409, ex.getStatuscode() );
        }
        Command get = GET;
        List<WordPair> result = get.process( dict );
        assertEquals( 1, result.size() );
        assertEquals( "lucky=bastard", wp, result.get( 0 ) );
    }

    @Test( expected = SRLPException.class )
    public void testPostDouble() throws SRLPException {
        System.out.println( "process post double" );
        Words dict = new MemoryWords();

        Command command = POST;

        command.process( dict, "lucky", "bastard" );
        try {
            command.process( dict, "lucky", "friend" );
        } catch ( SRLPException se ) {
            assertEquals( "status code", 409, se.getStatuscode() );
            throw se;
        }
    }

    @Test
    public void testPut() throws SRLPException {
        System.out.println( "process put" );
        Words dict = new MemoryWords();
        WordPair wp = new WordPair( "lucky", "bastard" );
        dict.post( wp.word1, wp.word2 );

        Command command = PUT;
        WordPair wp2 = new WordPair( "lucky", "friend" );
        command.process( dict, wp.word1, wp.word2 );
        Command get = GET;
        assertEquals( wp, get.process( dict, "lucky" ).get( 0 ) );
    }

    @Test( expected = SRLPException.class )
    public void testPutNotPresent() throws SRLPException {
        System.out.println( "process get not present" );
        Words dict = new MemoryWords();

        Command command = PUT;
        try {
            command.process( dict, "lucky", "friend" );
        } catch ( SRLPException se ) {
            assertEquals( "status code np", 404, se.getStatuscode() );
            throw se;
        }
    }

    @Test
    public void testDeleteEmptyWithDict() {
        System.out.println( "delete empty dict" );
        Words dict = new MemoryWords();

        Command command = DELETE;
        try {
            command.process( dict, "lucky","bastard" );
        } catch ( SRLPException se ) {
            assertEquals( "status code np", 404, se.getStatuscode() );
        }
    }

    @Test( expected = SRLPException.class )
    public void testDeleteNotFound() throws SRLPException {
        System.out.println( "delete not found " );
        Words dict = new MemoryWords();
        dict.put( "lucky", "bastard" );

        Command command = DELETE;
        try {
            command.process( dict, "unlucky" );
        } catch ( SRLPException se ) {
            assertEquals( "status code np", 404, se.getStatuscode() );
            throw se;
        }
    }

    @Test
    public void testDeleteSucces() throws SRLPException {
        System.out.println( "delete success " );
        Words dict = new MemoryWords();
        dict.post( "lucky", "bastard" );
        dict.post( "hello", "world" );

        Command command = DELETE;
        command.process( dict, "lucky", "bastard" );
        assertEquals( "one gone, one left", 1, dict.size() );
//        assertTrue( "hello still in", dict.get() "hello" ) );
//        assertTrue( "world still in", dict.containsValue( "world" ) );
    }

    @Test( expected = SRLPException.class )
    public void testDeleteTooFewArgs() throws SRLPException {
        Words dict = new MemoryWords();
        dict.put( "lucky", "bastard" );
        Command command = DELETE;
        try {
            command.process( dict );
        } catch ( SRLPException se ) {
            assertEquals( "status code", 409, se.getStatuscode() );
            throw se;
        }

    }

    @Test
    public void testReadHeader() throws IOException {
        String testData = "200 Ok\r\n\r\n";
        Command cmd = POST;
        List<String> result = cmd.readHeader( StringReader( testData ) );
        assertEquals( "one line", 1, result.size() );
        assertEquals( "first line", "200 Ok", result.get( 0 ) );
    }

    @Test
    public void testReadMultiLineHeader() throws IOException {
        String testData = "200 Ok\r\nlines=0\r\n\r\n";
        Command cmd = POST;
        List<String> result = cmd.readHeader( StringReader( testData ) );
        assertEquals( "one line", 2, result.size() );
        assertEquals( "first line", "200 Ok", result.get( 0 ) );
        assertEquals( "second line", "lines=0", result.get( 1 ) );
    }

    @Test
    public void testReadData() throws IOException {
        String testData = "200 Ok\r\nlines=1\r\n\r\nHello";
        Command cmd = GET;
        BufferedReader in = StringReader( testData );
        List<String> result = cmd.readHeader( in );
        assertEquals( "two line", 2, result.size() );
        assertEquals( "first line", "200 Ok", result.get( 0 ) );
        assertEquals( "second line", "lines=1", result.get( 1 ) );
        List<String> data = cmd.readData( in );
        assertEquals( "hello", "Hello", data.get( 0 ) );

        testData = "200 Ok\r\nlines=1\r\n\r\nHello\r\n\r\n";
        cmd = GET;
        in = StringReader( testData );
        result = cmd.readHeader( in );
        assertEquals( "two line", 2, result.size() );
        assertEquals( "first line", "200 Ok", result.get( 0 ) );
        assertEquals( "second line", "lines=1", result.get( 1 ) );
        data = cmd.readData( in );
        assertEquals( "hello", "Hello", data.get( 0 ) );
        assertEquals( "one line", 1, data.size() );
    }

    static BufferedReader StringReader( String testData ) {
        InputStream is = new ByteArrayInputStream( testData.getBytes() );
        return new BufferedReader( new InputStreamReader( is ) );
    }
}
