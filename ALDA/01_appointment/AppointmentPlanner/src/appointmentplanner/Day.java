package appointmentplanner;

//TODO

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ode
 */
public final class Day {

  public final static String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
  public final static Time DAY_START = new Time(8, 30);
  public final static Time DAY_END = new Time(17, 30);
  public final static Time LUNCH_TIME = new Time(12, 30);
  public final static TimeSpan DAY_PART = new TimeSpan(4, 0);
  public final static Appointment LUNCH_BREAK = new Appointment("lunch break", new TimeSpan(1, 0));

  private final static Time AFTERNOON_START = new Time(13, 30);
  private Node<Appointment> appointments = null;
  private final int nr;

  /**
   * The number of appointments added by the user.
   */
  private int nrOfAppointments;

  private static class Node<E> {
    E item;
    Node<E> next;

    int sizeOfDummyHeadList(Node<E> list) {
      //TODO: implement a linked list with a dummy head.
      Node<E> iterator = list;
      int i = 1;
      for (; iterator.next != null; ++i) {
        iterator = iterator.next;
      }
      return i;
    }

    Node(E item) {
      this(item, null);
    }

    Node(E item, Node<E> next) {
      this.item = item;
      this.next = next;
    }
  }

  /**
   * Creates a Day object with no appointments. A week does not contain a
   * weekend: no appointments on Saturdays or Sundays. Days of the week are
   * numbered as follows: Monday = 1 .. Friday = 5.
   *
   * @param nr day of the week: Monday = 1 .. Sunday = 7.
   */
  public Day(int nr) {
    this.nr = nr;
    LUNCH_BREAK.setStart(LUNCH_TIME);
    this.nrOfAppointments = 0;
    //TODO check for invalid day number
    if (nr >= 1 && nr <= 5) {
      this.appointments = new Node<>(LUNCH_BREAK);
      ++this.nrOfAppointments;
    }
  }

  /**
   * Returns the number of appointments on this day, including the lunch break
   * which is always present.
   *
   * @return Number of appointments on this day!
   */
  public int getNrOfAppointments() {
    return nrOfAppointments;
  }

  // METHODS INTRODUCED IN WEEK 3 OF APPOINTMENT PROJECT

  /**
   * Returns the name of the day in English. So if number of this day is 1,
   * the return value is "Monday" etc.
   *
   * @return String holding name of this day.
   */
  public String getNameOfTheDay() {
    return DAYS[nr - 1];
  }

  /**
   * Checks if an appointment of given duration can be made. Returns true if
   * an appointment can be made, false otherwise.
   *
   * @param duration time length of appointment
   * @return true if there is a gap of at least the size of duration in the
   * planning.
   */
  public boolean canAddAppointmentOfDuration(TimeSpan duration) {
    TimeGap[] availableTimeGaps = this.getAvailableTimeGaps();
    for (TimeGap t : availableTimeGaps) {
      if (t.getLength().getTimeSpanInMinutes() >= duration.getTimeSpanInMinutes()) {
        return true;
      }
    }
    return false;
  }


  /**
   * Adds a new appointment to this day. The description of the appointment
   * should be unique for this day. Objective is to insert an appointment in
   * such a way that the list keeps all of its appointments ordered in time.
   * <pre>
   * pre:  The start time of the appointment has been set.<br>
   *       The start time lies outside the time spans of other appointments.
   *       The appointment does not overlap with existing appointments on this day.
   * post: The appointment has been added to this day.
   * </pre> In case the pre-conditions are not fulfilled, the appointment will
   * not be added to this day.
   *
   * @param appointment start time of the appointment has been set in advance
   */
  public void addAppointmentWithStartTimeSet(Appointment appointment) {
    if (appointment.getStart() == null) return;
    if (appointment.getStart().isBefore(Day.DAY_START)) return ;

    Node<Appointment> i;
    for (i = this.appointments; i != null; i = i.next) {
      if (
        appointment.getStart().isBefore(i.item.getEnd())
          && i.item.getStart().isBefore(appointment.getEnd())
      ) {
        return;
      }

      if (appointment.getDescription().equalsIgnoreCase(i.item.getDescription())) {
        return;
      }
    }

    if (appointment.getEnd().isBefore(this.appointments.item.getStart())) {
      this.appointments = new Node<>(appointment, this.appointments);
      ++this.nrOfAppointments;
      return;
    }

    Node<Appointment> previousAppointmentNode = appointments;
    for (i = this.appointments; i != null; i = i.next) {
      System.out.println("second!");
      if (!(i.item.getStart().isBefore(appointment.getStart()))) {
        break;
      }
      previousAppointmentNode = i;
    }

    previousAppointmentNode.next = new Node<>(appointment, i);

    ++this.nrOfAppointments;
  }

  /**
   * Adds a new appointment to this day. The method searches a time gap for an
   * appointment of given duration. This will be done according to the first
   * fit approach. The description of the appointment should be unique for
   * this day. The start time will be set by this method, and the appointment
   * is added to this day. The result is that the list of appointments in this
   * class is ordered in time.
   *
   * @param appointment The appointment that will be added to this day.
   */
  public void addAppointment(Appointment appointment) {
    Time[] startTimes = this.getAvailableStartTimesForAppointmentsOfDuration(appointment.getDuration());
    if (startTimes.length == 0) return;
    Time start = startTimes[0];
    appointment.setStart(start);
    this.addAppointmentWithStartTimeSet(appointment);
  }

  /**
   * Removes the appointment with the given description.
   *
   * @param description The description of the appointment for which a search
   *                    has to be done.
   */
  public void removeAppointment(String description) {
    Node<Appointment> lastAppointmentNode = this.appointments;
    Node<Appointment> iterator = this.appointments;
    for (; iterator != null; iterator = iterator.next) {
      if (iterator.item.getDescription().equalsIgnoreCase(description)) {
        break;
      }
      lastAppointmentNode = iterator;
    }
    if (iterator == null) {
      return;
    }
    if (iterator == this.appointments) {
      this.appointments = this.appointments.next;
    }
    if (iterator.next == null) {
      lastAppointmentNode.next = null;
    }
    lastAppointmentNode.next = iterator.next;
    --this.nrOfAppointments;
  }

  /**
   * Checks if an appointment with given description exists.
   *
   * @param description The description of the appointment for which this
   *                    method finds out if it exists. Each description is unique for
   *                    appointments on the same day.
   * @return Returns true if an appointment with given description exists.
   */
  public boolean containsAppointmentWithDescription(String description) {
    for (Node<Appointment> i = this.appointments; i != null; i = i.next) {
      if (i.item.getDescription().equalsIgnoreCase(description)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method finds all start times that are available on this day for an
   * appointment of given duration.
   *
   * @param duration the requested duration for an appointment
   * @return an array of start times on which an appointment can be scheduled
   */
  public Time[] getAvailableStartTimesForAppointmentsOfDuration(TimeSpan duration) {
    List<Time> availableStartTimes = new ArrayList<>();
    for (TimeGap t : this.getAvailableTimeGaps()) {
      if (t.getLength().getTimeSpanInMinutes() < duration.getTimeSpanInMinutes()) {
        continue;
      }
      availableStartTimes.add(t.getStart());
    }
    Time[] buffer = new Time[availableStartTimes.size()];
    return availableStartTimes.toArray(buffer);
  }

  // METHODS INTRODUCED IN WEEK 4 OF APPOINTMENT PROJECT

  /**
   * This method gives a list of available time gaps on this day. Each time
   * gap holds the available time between appointments, and possibly at the
   * begin and at the end of the day.
   *
   * @return an array of TimeGaps containing all available time slots that are
   * available on this day.
   */
  public TimeGap[] getAvailableTimeGaps() {
    ArrayList<TimeGap> availableTimeGaps = new ArrayList<>();

    Time referenceTime = Day.DAY_START;
    Appointment lastAppointment = null;
    for (Node<Appointment> i = this.appointments; i != null; i = i.next) {
      TimeGap timeBetweenAppointments = new TimeGap(referenceTime, i.item.getStart());
      if (timeBetweenAppointments.getLength().getTimeSpanInMinutes() != 0) {
        availableTimeGaps.add(timeBetweenAppointments);
      }
      referenceTime = i.item.getEnd();
      lastAppointment = i.item;
    }

    if (lastAppointment != null) {
      TimeGap timeFromLastAppointmentToDayEnd = new TimeGap(lastAppointment.getEnd(), Day.DAY_END);
      if (timeFromLastAppointmentToDayEnd.getLength().getTimeSpanInMinutes() != 0) {
        availableTimeGaps.add(timeFromLastAppointmentToDayEnd);
      }
    }

    TimeGap[] result = new TimeGap[availableTimeGaps.size()];
    return availableTimeGaps.toArray(result);
  }
}
