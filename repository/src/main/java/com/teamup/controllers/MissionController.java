package com.teamup.controllers;

import com.teamup.entities.Mission;
import com.teamup.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions/")
public class MissionController {

  private ServiceTest test;

  @Autowired
  public MissionController(ServiceTest test) {
    this.test = test;
  }

  @RequestMapping(value="/{manager_id}", method= RequestMethod.GET)
  public @ResponseBody
  List<Mission> getParticipantByName(@PathVariable String manager_id) {
    List<Mission> missions = test.getUsersMissions(manager_id);
    return missions;
  }
}
