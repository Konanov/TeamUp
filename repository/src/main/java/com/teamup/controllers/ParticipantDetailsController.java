package com.teamup.controllers;

import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user01 on 12/20/16.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/participantInfo")
public class ParticipantDetailsController {

  private ServiceTest service;

  @Autowired
  public ParticipantDetailsController(ServiceTest service) {
    this.service = service;
  }

  @RequestMapping(value = "/")
  public Participant getParticipantInfo(@RequestParam String id) {
    return service.readById(new ObjectId(id));
  }

  @RequestMapping(value = "/byEmail/{email}")
  public Participant getParticipantByEmail(@PathVariable String email) {
    return service.read(email);
  }
}
