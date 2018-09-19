/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author JoernNeumeyer
 */
@RunWith(Parameterized.class)
public class TimeSpanTest {
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
      { 2, 3, 2, 3 },
      { 2, 130, 5, 10 },
      { 5, -70, 3, 50 },
      { 4, 30, 4, 30 },
      { 10, 54, 10, 54 },
    });
  }
  
  @Parameter(0)
  public int givenHours;
  @Parameter(1)
  public int givenMinutes;
  @Parameter(2)
  public int expectedHours;
  @Parameter(3)
  public int expectedMinutes;
  
  @Test
  public void testTimeSpanConstructorProcessesTheGivenHoursAndMinutesProperly() {
    TimeSpan ts = new TimeSpan(this.givenHours, this.givenMinutes);
    assertEquals(this.expectedHours, ts.getHours());
    assertEquals(this.expectedMinutes, ts.getMinutes());
  }
  
  @Test
  public void testTimeSpanConstructorProcessesTheGivenFromAndToDateProperly() {
    Time from = new Time(4, 3);
    Time to = new Time(this.expectedHours, this.expectedMinutes);
    
    TimeSpan ts = new TimeSpan(from, to);

    Time reference;

    {
      if (to.isBefore(from)) {
        reference = Time.getProperTime(
          from.getHours() - to.getHours(),
          from.getMinutes() - to.getMinutes()
        );
      } else {
        reference = Time.getProperTime(
          to.getHours() - from.getHours(),
          to.getMinutes() - from.getMinutes()
        );
      }
    }
    
    assertEquals(
      reference.getHours(),
      ts.getHours()
    );
    
    assertEquals(
      reference.getMinutes(),
      ts.getMinutes()
    );
  }
  
  @Test
  public void testGetTimeSpanInMinutes() {
    TimeSpan ts = new TimeSpan(this.givenHours, this.givenMinutes);
    int totalMinutes = ts.getHours() * 60 + ts.getMinutes();
    
    assertEquals(totalMinutes, ts.getTimeSpanInMinutes());
  }

  @Test
  public void testTimeSpanIsSmallerIsEvaluatedCorrectly() {
    TimeSpan ts = new TimeSpan(this.givenHours, this.givenMinutes);
    TimeSpan reference = new TimeSpan(3, 20);

    boolean isSmaller = ts.getTimeSpanInMinutes() < reference.getTimeSpanInMinutes();

    assertEquals(ts.isSmallerThan(reference), isSmaller);
  }

  @Test
  public void testGetMinutesInTimeSpan() {
    Time from = new Time(this.expectedHours, this.expectedMinutes);
    Time to = new Time(this.expectedHours + (int)((-.5 * Math.random()) * 5), this.expectedMinutes + (int)((-.5 * Math.random()) * 5));
    TimeSpan ts = new TimeSpan(from, to);

    assertEquals(TimeSpan.getMinutesInTimeSpan(from, to), ts.getTimeSpanInMinutes());
  }

  @Test
  public void testAddTimeSpan() {
    TimeSpan initial = new TimeSpan(this.givenHours, this.givenMinutes);
    TimeSpan toBeAdded = new TimeSpan((int)(Math.random() * 5), (int)(Math.random() * 5));

    int minutesBeforeAddingTimeSpan = initial.getTimeSpanInMinutes();
    initial.addTimeSpan(toBeAdded);
    int minutesAfterAddingTimeSpan = initial.getTimeSpanInMinutes();

    assertEquals(minutesAfterAddingTimeSpan - minutesBeforeAddingTimeSpan, toBeAdded.getTimeSpanInMinutes());
  }
}
