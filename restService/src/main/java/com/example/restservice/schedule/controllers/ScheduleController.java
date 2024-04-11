package com.example.restservice.schedule.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.schedule.entities.Schedule;
import com.example.restservice.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getScheduleById(@PathVariable int id) {
        return Objects.nonNull(scheduleService.findById(id)) ?
                ResponseEntity.ok().body(scheduleService.findById(id)) :
                ResponseEntity.status(404).body(new GeneralMessage("Schedule not found!", "ID: " + id + " does not exist!", true));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllSchedules() {
        return Objects.nonNull(scheduleService.findAll()) ?
                ResponseEntity.ok().body(scheduleService.findAll()) :
                ResponseEntity.status(404).body(new GeneralMessage("Schedules not found!", "No saved records!", true));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/time/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getScheduleTimeById(@PathVariable int id) {
        return Objects.nonNull(scheduleService.getScheduleTimeById(id)) ?
                ResponseEntity.ok().body(new GeneralMessage("Schedule time is:", scheduleService.getScheduleTimeById(id), false)) :
                ResponseEntity.status(404).body(new GeneralMessage("Schedule not found!", "ID: " + id + " does not exist!", true));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule) {
        var newScheduleId = scheduleService.addSchedule(schedule);
        return ResponseEntity.ok().body(new GeneralMessage("Successfully added schedule", "New schedule ID: " + newScheduleId, false));
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/delete/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> addSchedule(@PathVariable("id") int id) {
        scheduleService.deleteById(id);
        return ResponseEntity.ok().body(new GeneralMessage("Successfully deleted schedule", "Deleted schedule ID: " + id, false));
    }
}
