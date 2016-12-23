package com.teamup.controllers;


import com.teamup.dto.ParticipantDTO;
import com.teamup.entities.Participant;
import com.teamup.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

  private ServiceTest accountRepository;

  @Autowired
  public LoginController(ServiceTest accountRepository) {
    this.accountRepository = accountRepository;
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ParticipantDTO login(@RequestBody ParticipantDTO participant) {
    Participant user = this.accountRepository.read(participant.getEmail(), participant.getPassword());
    return this.accountRepository.convertParticipant(this.accountRepository.save(user));
  }
}
