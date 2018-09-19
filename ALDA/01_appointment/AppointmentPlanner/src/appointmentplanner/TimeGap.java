package appointmentplanner;

import java.util.Objects;

/**
 * A class for a time span at a certain start time. Derived class is
 * Appointment.
 *
 * @author ode
 */
public class TimeGap {

  private final TimeSpan length;
  private Time start;
  private Time end;

  public TimeGap(TimeSpan length, Time start) {
    this.start = start;
    this.length = length;
    this.end = start.addTime(new Time(length.getHours(), length.getMinutes()));
  }

  public TimeGap(Time start, Time end) {
    this.start = start;
    this.end = end;
    this.length = new TimeSpan(start, end);
  }

  public Time getStart() {
    return this.start;
  }

  public Time getEnd() {
    return this.end;
  }

  public TimeSpan getLength() {
    return this.length;
  }

  @Override
  public String toString() {
    return this.start + " - " + this.end + ": " + this.length;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TimeGap other = (TimeGap) obj;
    if (!Objects.equals(this.length, other.length)) {
      return false;
    }
    if (!Objects.equals(this.start, other.start)) {
      return false;
    }
    if (!Objects.equals(this.end, other.end)) {
      return false;
    }
    return true;
  }


}
