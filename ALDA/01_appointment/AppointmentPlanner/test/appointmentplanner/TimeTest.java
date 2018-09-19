/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoernNeumeyer
 */
public class TimeTest {
  
  public TimeTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
  // TEST addTime
  @Test
  public void testAddTimeCreatesNewObject() {
    Time t = new Time(1, 1);
    Time result = t.addTime(new Time(2, 2));
    assertNotEquals(t, result);
  }
  
  @Test
  public void testAddTimeAddsHours() {
    Time t = new Time(1, 1);
    Time toAdd = new Time(2, 2);
    Time result = t.addTime(toAdd);
    assertEquals(t.getHours() + toAdd.getHours(), result.getHours());
  }
  
  @Test
  public void testAddTimeAddsMinutes() {
    Time t = new Time(1, 1);
    Time toAdd = new Time(2, 5);
    Time result = t.addTime(toAdd);
    assertEquals(t.getMinutes()+ toAdd.getMinutes(), result.getMinutes());
  }
  
  // TEST isBefore
  @Test
  public void testIsBeforeChecksForHoursAndResolvesToTrue() { // TODO change name to be more descriptive
    Time t = new Time(2, 20);
    Time other = new Time(3, 5);
    assertTrue(t.isBefore(other));
  }
  
  @Test
  public void testIsBeforeChecksForHoursAndResolvesToFalse() {
    Time t = new Time(3, 2);
    Time other = new Time(2, 5);
    assertFalse(t.isBefore(other));
  }
  
  @Test
  public void testIsBeforeChecksForMinutesIfHoursAreEqualAndResolvesToTrue() {
    Time t = new Time(2, 20);
    Time other = new Time(2, 50);
    assertTrue(t.isBefore(other));
  }
  
  @Test
  public void testIsBeforeChecksForMinutesIfHoursAreEqualAndResolvesToFalse() {
    Time t = new Time(2, 20);
    Time other = new Time(2, 10);
    assertFalse(t.isBefore(other));
  }
  
  // TEST getProperTime
  @Test
  public void testGetProperTimeJustUsesTheValidValuesAsTheyArePassedToIt() {
    Time t = Time.getProperTime(2, 20);
    assertEquals(2, t.getHours());
    assertEquals(20, t.getMinutes());
  }
  
  @Test
  public void testGetProperTimeNoramlizesNegativeHoursToBeZero() {
    Time t = Time.getProperTime(-2, 10);
    assertEquals(0, t.getHours());
  }
  
  @Test
  public void testGetProperTimeSubtractsNegativeMinutesFromHours() {
    Time t = Time.getProperTime(5, -130);
    assertEquals(2, t.getHours());
  }
  
  @Test
  public void testGetProperTimeSubtractsNegativeMinutesFromHoursIfMinutesIsAMultipleOfSixty() {
    Time t = Time.getProperTime(5, -120);
    assertEquals(3, t.getHours());
  }
  
  @Test
  public void testGetProperTimeSubtractsNegativeMinutesFromMinutes() {
    Time t = Time.getProperTime(5, -130);
    assertEquals(50, t.getMinutes());
  }
  
  @Test
  public void testGetProperTimeNormalizesTooBigHoursIntoProperRange() {
    Time t = Time.getProperTime(30, 0);
    assertEquals(t.getHours() % 24, t.getHours());
  }
  
  @Test
  public void testGetProperTimeAddsOverflowingMinutesToHours() {
    Time t = Time.getProperTime(4, 300);
    assertEquals(9, t.getHours());
  }
  
  @Test
  public void testGetProperTimeAddsOverflowingMinutesToMinutes() {
    Time t = Time.getProperTime(4, 312);
    assertEquals(12, t.getMinutes());
  }

  @Test
  public void testGetProperTimeResultInMidnightIfHoursAndMinutesAreNegative() {
    Time t = Time.getProperTime(-1, -50);
    assertEquals(0, t.getHours());
    assertEquals(0, t.getMinutes());
  }
  
  @Test
  public void testAddTimeReturnsProperTimeWhenMinutesAddUpToSixty() {
    Time t = new Time(2, 20);
    Time toAdd = new Time(0, 40);
    Time result = t.addTime(toAdd);
    
    
    assertEquals(3, result.getHours());
    assertEquals(0, result.getMinutes());
  }
}
