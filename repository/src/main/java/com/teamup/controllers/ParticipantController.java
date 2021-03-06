package com.teamup.controllers;

import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by user01 on 11/28/16.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/participants/")
public class ParticipantController {

  private ServiceTest test;

  @Autowired
  public ParticipantController(ServiceTest test) {
    this.test = test;
  }

  @RequestMapping(value="/{name}", method=RequestMethod.GET)
  public Participant getParticipantByName(@PathVariable String name) {
    return test.read(name);
  }

  @RequestMapping(value="/all", method=RequestMethod.GET)
  public List<Participant> getCurrentParty() {
    return test.getAllParticipants();
  }

  @RequestMapping(value="/avatar/{userId}")
  public byte[] getUserAvatar(@PathVariable String userId) throws IOException {
    return IOUtils.toByteArray(test.getUserAvatar(test.readById(new ObjectId(userId))).getInputStream());
  }

  @RequestMapping(value="/byMission/{mission_id}", method=RequestMethod.GET)
  public List<Participant> getCurrentParty(@PathVariable String mission_id) {
    return test.getCurrentParty(mission_id);
  }
}
