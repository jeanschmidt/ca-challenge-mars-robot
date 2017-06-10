package org.ca.challenge.nasabot.jeanschmidt.robot;

public class RobotBoundaryException extends Exception {
    protected String reason;

    public String get_reason() {
        return this.reason;
    }

    public RobotBoundaryException(String reason) {
        this.reason = reason;
    }
}
