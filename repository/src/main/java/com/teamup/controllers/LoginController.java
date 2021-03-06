package com.teamup.controllers;

import com.teamup.dto.ParticipantDTO;
import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

  private ServiceTest accountService;

  private Map<String, String> loggedInUsers = new HashMap<>();

  @Autowired
  public LoginController(ServiceTest accountRepository) {
    this.accountService = accountRepository;
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public Participant login(@RequestBody Participant participant) {
    if (loggedInUsers.containsKey(participant.getEmail())) {
      return participant;
    } else {
      Participant fullParticipant = this.accountService.read(participant.getEmail(), participant.getPassword());
      if (fullParticipant != null) {
        loggedInUsers.put(fullParticipant.getEmail(), fullParticipant.getPassword());
        return fullParticipant;
      }
      return new Participant(null, null, null, null);
    }
  }

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public Participant register(@RequestBody ParticipantDTO participant) {
    Participant fullParticipant = this.accountService.read(participant.getEmail());
    if (fullParticipant != null) {
      return new Participant(null, null, null, null);
    } else {
      accountService.save(new Participant(participant.getName(), participant.getSurname(), participant.getEmail(), participant.getPassword()));
      return fullParticipant;
    }
  }
}
