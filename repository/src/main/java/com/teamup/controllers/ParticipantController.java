package com.teamup.controllers;

import com.amazonaws.util.IOUtils;
import com.teamup.dto.ParticipantDTO;
import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
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
  public @ResponseBody
  Participant getParticipantByName(@PathVariable String name) {
    return test.read(name);
  }

  @RequestMapping(value="/all", method=RequestMethod.GET)
  public @ResponseBody
  List<ParticipantDTO> getAllParticipants() {
    return test.getAllParticipants();
  }

  @RequestMapping(value="/avatar/{userId}")
  public @ResponseBody
  byte[] getUserAvatar(@PathVariable String userId) throws IOException {
    return IOUtils.toByteArray(test.getUserAvatar(test.readById(new ObjectId(userId))).getInputStream());
  }
}
