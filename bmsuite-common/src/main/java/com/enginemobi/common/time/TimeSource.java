package com.enginemobi.common.time;

/**
 * Provides an abstraction from the current system time.
 * Certain aspects of the system can be run in a mode that allows the end user to override the
 * current time.
 *
 * A convenient example of this is when previewing content.   An approver may want to view
 * the site as it would appear on a particular date or time.
 *
 */
public interface TimeSource {

    long timeInMillis();
}
