package org.ca.challenge.nasabot.jeanschmidt.robot;

import org.ca.challenge.nasabot.jeanschmidt.robot.RobotBoundaryException;

public class MarsRobot {
    protected int x_pos;
    protected int y_pos;
    protected int orientation;

    protected int x_max;
    protected int y_max;

    public MarsRobot(int x_max, int y_max) {
        this.x_pos = 0;
        this.y_pos = 0;
        this.orientation = 0;

        this.x_max = x_max;
        this.y_max = y_max;
    }

    public int get_x_pos() {
        return this.x_pos;
    }

    public int get_y_pos() {
        return this.y_pos;
    }

    public int get_orientation() {
        return this.orientation;
    }

    public char get_orientation_char() {
      char ret = 'F';
      switch(this.orientation) {
          case 0:
            ret = 'N';
            break;
          case 1:
            ret = 'E';
            break;
          case 2:
            ret = 'S';
            break;
          case 3:
            ret = 'W';
            break;
      }
      return ret;
    }

    public void turn_left() {
        this.orientation = (this.orientation + 3) % 4;
    }

    public void turn_right() {
        this.orientation = (this.orientation + 1) % 4;
    }

    public void move() throws RobotBoundaryException {
        switch(this.orientation) {
            case 0:
              this.y_pos++;
              break;
            case 1:
              this.x_pos++;
              break;
            case 2:
              this.y_pos--;
              break;
            case 3:
              this.x_pos--;
              break;
        }
        this.check_boundaries();
    }

    protected void check_boundaries() throws RobotBoundaryException {
        if (this.y_pos >= this.y_max || this.y_pos < 0) {
            throw new RobotBoundaryException("Robot Y axys overrun!");
        }
        if (this.x_pos >= this.x_max || this.x_pos < 0) {
            throw new RobotBoundaryException("Robot X axys overrun!");
        }
    }
}
