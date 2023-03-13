package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's meetings in the address book.
 */
public class Meeting {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Meeting
     *
     * @param start start date and time of meeting
     * @param end   end date and time of meeting. Must be after start
     */
    public Meeting(LocalDateTime start, LocalDateTime end) throws Exception {
        if (end.isBefore(start)) {
            throw new Exception("End time is after start!");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if there are timing clashes between this meeting and m
     * Returns true if timings do clash, false otherwise
     *
     * @param m Meeting to be compared with
     */
    public boolean checkTimeClash(Meeting m) {
        //if m starts and end at the same time
        if (m.start.equals(this.start) && m.end.equals(this.end)) {
            return true;
        }
        //if m ends at this.start
        if (m.end.equals(this.start)) {
            return true;
        }
        //if m ends in between
        if (m.end.isAfter(this.start) & m.end.isBefore(this.end)) {
            return true;
        }
        //if m starts at the same time
        if (m.start.equals(this.start)) {
            return true;
        }
        //if m starts in between
        if (m.start.isAfter(this.start) & m.start.isBefore(this.end)) {
            return true;
        }
        //if m starts at the end
        if (m.start.equals(this.end)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Meeting)) {
            return false;
        }
        Meeting mt = (Meeting) o;
        return mt.start.equals(this.start) && mt.end == this.end;
    }

    @Override
    public String toString() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("eeee MMMM d u HH:mm");
        String startDnT = start.format(customFormat);
        String endDnT = end.format(customFormat);
        return "(Starts at: " + startDnT + " Ends at: " + endDnT + ")";
    }
}