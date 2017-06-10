package org.ca.challenge.nasabot.jeanschmidt.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.ca.challenge.nasabot.jeanschmidt.rest.InvalidPathException;

import org.ca.challenge.nasabot.jeanschmidt.robot.MarsRobot;
import org.ca.challenge.nasabot.jeanschmidt.robot.RobotBoundaryException;

@RestController
@RequestMapping("/rest/")
public class MarsRestController {

    protected String directions_regex = "[LRM]*";
    protected MarsRobot robot;

    @RequestMapping(value="/mars/", method=RequestMethod.POST)
    public String mars_empty() {
        return "(0, 0, N)";
    }

    @RequestMapping(value="/mars/{directions}", method=RequestMethod.POST)
    public String mars(@PathVariable String directions) {
        MarsRobot robot = new MarsRobot(5, 5);

        if (!directions.matches(this.directions_regex)) {
            throw new InvalidPathException("Unrecognized characters");
        }

        try {
            for (int i = 0; i < directions.length(); i++){
                char command = directions.charAt(i);
                switch (command) {
                    case 'M':
                      robot.move();
                      break;
                    case 'L':
                      robot.turn_left();
                      break;
                    case 'R':
                      robot.turn_right();
                      break;                }
            }
        } catch (RobotBoundaryException e) {
            throw new InvalidPathException(
                "Moved to invalid position: " + e.get_reason()
            );
        }

        return String.format("(%d, %d, %c)", robot.get_x_pos(), robot.get_y_pos(),
                             robot.get_orientation_char());
    }

    @ExceptionHandler(InvalidPathException.class)
    public ResponseEntity<String> invalidPathExceptionHandler() {
        return new ResponseEntity<String>("400 Bad Request", HttpStatus.BAD_REQUEST);
    }
}
