package appointmentplanner;

//TODO

/**
 * @author ode
 */
public final class Appointment {

  private String description;
  private TimeSpan duration;
  private Time start, end;

  /**
   * Constructs a new appointment with given description and duration. The
   * priority (AFTER WEEK 4) is set to LOW.
   *
   * @param description The description of the appointment.
   * @param duration    The time span needed for this appointment.
   */
  public Appointment(String description, TimeSpan duration) {
    this.description = description;
    this.duration = duration;
  }

  /**
   * @return The description of this appointment.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the start time for this appointment.
   *
   * @param start The start time for this appointment.
   */
  public void setStart(Time start) {
    this.start = start;

    Time durationAsTime = new Time(this.duration.getHours(), this.duration.getMinutes());
    this.end = this.start.addTime(durationAsTime);
  }

  /**
   * Returns the duration of this appointment.
   *
   * @return The duration of this appointment.
   */
  public TimeSpan getDuration() {
    return this.duration;
  }

  /**
   * Returns the end time of this appointment. Returns null if the start time
   * has not been set.
   *
   * @return Time when this appointment is done
   */
  public Time getEnd() {
    return this.end;
  }

  /**
   * Returns the start time of this appointment.
   *
   * @return start The start time of this appointment.
   */
  public Time getStart() {
    return this.start;
  }
  //TODO AFTER WEEK 4
}
