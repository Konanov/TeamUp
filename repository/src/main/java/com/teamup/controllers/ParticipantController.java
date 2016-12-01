package com.teamup.controllers;

import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user01 on 11/28/16.
 */

@RestController
@RequestMapping("/participants/")
public class ParticipantController {

  private ServiceTest test;

  @Autowired
  public ParticipantController(ServiceTest test) {
    this.test = test;
  }

  @RequestMapping(value="/{name}", method=RequestMethod.GET)
  public @ResponseBody Participant getParticipantByName(@PathVariable String name) {
    return test.read(name);
  }
}
