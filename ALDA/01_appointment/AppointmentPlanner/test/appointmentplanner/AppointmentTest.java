package appointmentplanner;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {
  @Test
  public void testCalculatesANewEndTimeIfTheStartHasBeenSet() {
    TimeSpan duration = new TimeSpan(0, 30);
    Appointment a = new Appointment("Study Assistance", duration);
    Time start = new Time(9, 0);
    Time end = start.addTime(new Time(duration.getHours(), duration.getMinutes()));

    a.setStart(start);
    assertEquals(end.getHours(), a.getEnd().getHours());
    assertEquals(end.getMinutes(), a.getEnd().getMinutes());
  }

  @Test
  public void testGetEndAlwaysReturnsTheSameInstanceIfTheStartDidNotChange() {
    TimeSpan duration = new TimeSpan(0, 30);
    Appointment a = new Appointment("Study Assistance", duration);
    Time start = new Time(9, 0);
    a.setStart(start);

    Time end = a.getEnd();
    Time secondEnd = a.getEnd();

    assertSame(end, secondEnd);
  }
}