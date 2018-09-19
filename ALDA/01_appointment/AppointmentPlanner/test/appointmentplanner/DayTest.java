package appointmentplanner;

import org.junit.Test;

import static org.junit.Assert.*;

public class DayTest {
  @Test
  public void testConstructorSetsAppointmentForLunchBreaksOnWorkingDays() {
    Day d = new Day(1);
    assertEquals(1, d.getNrOfAppointments());
  }

  @Test
  public void testConstructorSetsNoAppointmentOnTheWeekend() {
    Day d = new Day(6);
    assertEquals(0, d.getNrOfAppointments());
  }

  @Test
  public void testCanAddAppointmentOfDurationOnEmptyDay() {
    Day d = new Day(1);
    TimeSpan appointmentDuration = new TimeSpan(1, 0);
    assertTrue(d.canAddAppointmentOfDuration(appointmentDuration));
  }

  @Test
  public void testCanAddAppointmentOfDurationCanNotAddATooLargeAppointment() {
    Day d = new Day(1);
    TimeSpan appointmentDuration = new TimeSpan(5, 0);
    assertFalse(d.canAddAppointmentOfDuration(appointmentDuration));
  }

  @Test
  public void testCanAddAppointmentOfDurationResolvesToFalseOnTheWeekend() {
    Day d = new Day(6);
    TimeSpan appointmentDuration = new TimeSpan(2, 0);
    assertFalse(d.canAddAppointmentOfDuration(appointmentDuration));
  }

  @Test
  public void testAddAppointmentWithStartTimeSetAddsAProperAppointment() {
    Day d = new Day(1);
    Appointment app = new Appointment("Hello", new TimeSpan(1, 0));
    app.setStart(new Time(10, 0));
    int previousAppointmentCount = d.getNrOfAppointments();
    d.addAppointmentWithStartTimeSet(app);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(previousAppointmentCount + 1, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentWithStartTimeSetDoesNotAddAnAppointmentWithMissingStart() {
    Day d = new Day(1);
    Appointment app = new Appointment("Hello", new TimeSpan(1, 0));
    int previousAppointmentCount = d.getNrOfAppointments();
    d.addAppointmentWithStartTimeSet(app);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(previousAppointmentCount, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentWithStartTimeSetDoesNotAddAnAppointmentWithDuplicateDescription() {
    Day d = new Day(1);
    Appointment app = new Appointment("Hello", new TimeSpan(1, 0));
    app.setStart(new Time(10, 0));
    d.addAppointmentWithStartTimeSet(app);
    int previousAppointmentCount = d.getNrOfAppointments();
    app = new Appointment("Hello", new TimeSpan(2, 0));
    app.setStart(new Time(15, 0));
    d.addAppointmentWithStartTimeSet(app);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(previousAppointmentCount, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentWithStartTimeSetDoesNotAddAnAppointmentWhichStartsBeforeTheDayStart() {
    Day d = new Day(1);
    Appointment app = new Appointment("Hello", new TimeSpan(1, 0));
    app.setStart(new Time(7, 0));
    int previousAppointmentCount = d.getNrOfAppointments();
    d.addAppointmentWithStartTimeSet(app);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(previousAppointmentCount, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentWithStartTimeSetDoesNotAddAnAppointmentWhichStartsDuringAnotherAppointment() {
    Day d = new Day(1);
    Appointment app = new Appointment("Hello", new TimeSpan(1, 0));
    app.setStart(new Time(9, 0));
    d.addAppointmentWithStartTimeSet(app);
    int previousAppointmentCount = d.getNrOfAppointments();
    app = new Appointment("World", new TimeSpan(2, 0));
    app.setStart(new Time(9, 30));
    d.addAppointmentWithStartTimeSet(app);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(previousAppointmentCount, postAppointmentCount);
  }

  @Test
  public void testGetAvailableTimeGapsReturnsTheExpectedTimeGapsOnAnEmptyDay() {
    Day d = new Day(1);
    TimeGap timeFromDayStartToLunchBreak = new TimeGap(Day.DAY_START, Day.LUNCH_TIME);
    TimeGap timeFromLunchBreakEndToDayEnd = new TimeGap(Day.LUNCH_BREAK.getEnd(), Day.DAY_END);
    TimeGap[] timeGaps = d.getAvailableTimeGaps();
    assertArrayEquals(new TimeGap[]{timeFromDayStartToLunchBreak, timeFromLunchBreakEndToDayEnd}, timeGaps);
  }

  @Test
  public void testGetAvailableTimeGapsReturnsTheExpectedTimeGapsOnADayWithAppointments() {
    Day d = new Day(1);
    Appointment a = new Appointment("appointment", new TimeSpan(1, 0));
    a.setStart(new Time(9, 0));
    d.addAppointmentWithStartTimeSet(a);
    TimeGap timeFromDayStartToTheAppointment = new TimeGap(Day.DAY_START, a.getStart());
    TimeGap timeFromAppointmentEndToLunchBreakStart = new TimeGap(a.getEnd(), Day.LUNCH_BREAK.getStart());
    TimeGap timeFromLunchBreakEndToDayEnd = new TimeGap(Day.LUNCH_BREAK.getEnd(), Day.DAY_END);
    TimeGap[] timeGaps = d.getAvailableTimeGaps();
    assertArrayEquals(new TimeGap[]{timeFromDayStartToTheAppointment, timeFromAppointmentEndToLunchBreakStart, timeFromLunchBreakEndToDayEnd}, timeGaps);
  }

  @Test
  public void testContainsAppointmentWithDescriptionBehavesCorrectly() {
    Day d = new Day(1);
    Appointment a = new Appointment("appointment", new TimeSpan(1, 0));
    a.setStart(new Time(9, 0));
    d.addAppointmentWithStartTimeSet(a);
    assertTrue(d.containsAppointmentWithDescription("appointment"));
    assertFalse(d.containsAppointmentWithDescription("hello world"));
  }

  @Test
  public void testGetAvailableStartTimesForAppointmentsOfDurationReturnsFittingStartTimesForAFittingTimeSpan() {
    Day d = new Day(1);
    Time[] startTimes = d.getAvailableStartTimesForAppointmentsOfDuration(new TimeSpan(4, 0));
    assertTrue(startTimes.length > 0);
  }

  @Test
  public void testGetAvailableStartTimesForAppointmentsOfDurationReturnsNoStartingTimesIfTheDurationIsTooLarge() {
    Day d = new Day(1);
    Time[] startTimes = d.getAvailableStartTimesForAppointmentsOfDuration(new TimeSpan(5, 0));
    assertEquals(0, startTimes.length);
  }

  @Test
  public void testAddAppointmentValidCase() {
    Day d = new Day(1);
    Appointment a = new Appointment("Hello World!", new TimeSpan(1, 0));
    int prevAppointmentCount = d.getNrOfAppointments();
    d.addAppointment(a);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(prevAppointmentCount + 1, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentDoesNotAddAppointmentsWithDuplicateNames() {
    Day d = new Day(1);
    Appointment a = new Appointment("Hello World!", new TimeSpan(1, 0));
    Appointment ab = new Appointment("Hello World!", new TimeSpan(1, 0));
    d.addAppointment(a);
    int prevAppointmentCount = d.getNrOfAppointments();
    d.addAppointment(ab);
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(prevAppointmentCount, postAppointmentCount);
  }

  @Test
  public void testAddAppointmentDoesAddAppointmentAtTheFirstAvailableTime() {
    Day d = new Day(1);
    Appointment a = new Appointment("Hello World!", new TimeSpan(1, 0));
    d.addAppointment(a);
    assertEquals(Day.DAY_START, a.getStart());
  }

  @Test
  public void testRemoveAppointmentRemovesTheGivenAppointment() {
    Day d = new Day(1);
    Appointment a = new Appointment("app", new TimeSpan(1, 0));
    d.addAppointment(a);
    int prevAppointmentCount = d.getNrOfAppointments();
    d.removeAppointment("app");
    int postAppointmentCount = d.getNrOfAppointments();
    assertEquals(postAppointmentCount, prevAppointmentCount - 1);
  }
}