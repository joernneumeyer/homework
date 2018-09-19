package appointmentplanner;

import java.util.Objects;

/**
 * TimeSpan is a period of time, consisting of a number of hours and a number of
 * minutes. The biggest possible TimeSpan is 23 hours and 59 minutes.
 *
 * @author ode
 */
public class TimeSpan {
  
  private Time span;
  
  /**
   * Creates a new TimeSpan. The pre-conditions on the parameters are the same
   * as for the constructor of Time.
   *
   * @param hours hours part of the time span.
   * @param minutes minutes part of the time span.
   */
  public TimeSpan(int hours, int minutes) {
    this.span = Time.getProperTime(hours, minutes);
  }
  
  /**
   * A constructor that takes a start- and end-time as definition of a certain
   * time span. If from is not before to, the to time will be taken as the
   * start time and the from time will be taken as the end time for the new
   * time span!
   *
   * @param from The start time of the time span.
   * @param to The end time of the time span.
   */
  public TimeSpan(Time from, Time to) {
    if (to.isBefore(from)) {
      Time temp = from;
      from = to;
      to = temp;
    }
    
    this.span = Time.getProperTime(
      to.getHours() - from.getHours(),
      to.getMinutes() - from.getMinutes()
    );
  }
  
  /**
   * Just a getter.
   *
   * @return the amount of hours in this time span.
   */
  public int getHours() {
    return span.getHours();
  }
  
  /**
   * Just a getter.
   *
   * @return the amount of minutes in this time span.
   */
  public int getMinutes() {
    return span.getMinutes();
  }
  
  /**
   * This method converts the complete time span into the corresponding number
   * of minutes.
   *
   * @return The length of this time span in minutes.
   */
  public int getTimeSpanInMinutes() {
    return this.getHours() * 60 + this.getMinutes();
  }
  
  /**
   * Compares this time span to another one. Returns true is this time span is
   * the smallest.
   *
   * @param timeSpan the time span to be compared to this time span.
   * @return true if this time span is shorter than the timeSpan.
   */
  public boolean isSmallerThan(TimeSpan timeSpan) {
    return this.getTimeSpanInMinutes() < timeSpan.getTimeSpanInMinutes();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TimeSpan other = (TimeSpan) obj;
    return Objects.equals(this.span, other.span);
  }
  
  @Override
  public int hashCode() {
    int hash = 5;
    hash = 83 * hash + Objects.hashCode(this.span);
    return hash;
  }
  
  @Override
  public String toString() {
    return "TimeSpan{" + "span=" + span + '}';
  }
  
  /**
   * Adds a time span to this time span. The method takes care that the pre
   * condition of the TimeSpan constructor is respected.
   *
   * @param timeSpan the time span to be added to this
   */
  public void addTimeSpan(TimeSpan timeSpan) {
    this.span = Time.getProperTime(this.getHours() + timeSpan.getHours(), this.getMinutes() + timeSpan.getMinutes());
  }
  
  /**
   * A static utility method for converting a time span into a number of
   * minutes. The value is always positive whether 'from' is before 'to' or
   * not.
   *
   * @param from Begin of time span.
   * @param to End of time span.
   * @return The number of minutes in this time interval.
   */
  public static int getMinutesInTimeSpan(Time from, Time to) {
    TimeSpan ts = new TimeSpan(from, to);
    return ts.getTimeSpanInMinutes();
  }
}
