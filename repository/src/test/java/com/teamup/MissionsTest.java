package com.teamup;

import com.teamup.database.MongoTest;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Role;
import com.teamup.entities.Task;
import com.teamup.service.ServiceTest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoTest.class, ServiceTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MissionsTest {

  @Autowired
  private ServiceTest serviceTest;

  private Participant managerVasya = new Participant("Вася", "Васильев", "vasya@gmail", "vasyaolol");
  private Participant participantKolya = new Participant("Коля", "Колин", "kolya@gmail", "kolyaolol");
  private Participant participantPetya = new Participant("Петя", "Петин", "petya@gmail", "petyaolol");
  private Participant participantSlava = new Participant("Слава", "Славин", "slava@gmail", "slavaolol");
  private Participant participantZhenja = new Participant("Женя", "Женин", "zhenja@gmail", "zhenjaolol");
  private List<Role> managerRoles = new ArrayList<>(Arrays.asList(Role.MANAGER, Role.PARTICIPANT));
  private List<Role> participantRoles = new ArrayList<>(Collections.singletonList(Role.PARTICIPANT));
  private List<Task> tasksKolya = new ArrayList<>(Arrays.asList(new Task("Проверить аппарат на месте проведения", managerVasya.get_id(), participantKolya.get_id(), "[34,5938723; 75,4235234]"),
    new Task("Встретить группу в аэропорту", managerVasya.get_id(), participantKolya.get_id(), "[34,5912313; 75,012396]")));
  private List<Task> tasksPetya = new ArrayList<>(Collections.singletonList(new Task("Забрать железо на реп базе", managerVasya.get_id(), participantPetya.get_id(), "[34,5911723; 75,223434]")));
  private List<Task> tasksSlava = new ArrayList<>(Arrays.asList(new Task("Распечатать списки гостей", managerVasya.get_id(), participantSlava.get_id(), "[34,11234723; 75,423425]"),
    new Task("Привезти с собой мерч", managerVasya.get_id(), participantSlava.get_id(), "[34,0240313; 75,152696]")));
  private Mission mission = new Mission(managerVasya.getUser_id(), "Организовать концерт 19.01.20017");

  @Before
  public void prepareDB() {
    managerVasya.setRoles(managerRoles);
    participantKolya.setRoles(participantRoles);
    participantKolya.setTasks(tasksKolya);
    participantPetya.setRoles(participantRoles);
    participantPetya.setTasks(tasksPetya);
    participantSlava.setRoles(participantRoles);
    participantSlava.setTasks(tasksSlava);
    participantZhenja.setRoles(participantRoles);
    serviceTest.save(managerVasya);
    managerVasya = serviceTest.read(managerVasya.getEmail());
    serviceTest.addParticipant(serviceTest.save(participantKolya), mission);
    serviceTest.addParticipant(serviceTest.save(participantPetya), mission);
    serviceTest.addParticipant(serviceTest.save(participantSlava), mission);
    serviceTest.addParticipant(serviceTest.save(participantZhenja), mission);
    serviceTest.save(mission);
    mission.setManager_id(managerVasya.get_id().toHexString());
    serviceTest.updateMission("manager_id", managerVasya.get_id().toHexString(), mission.getDescription());
    //managerVasya.setCurrentMissionId(mission.get_id().toHexString());
    //serviceTest.update("currentMissionId", mission.get_id().toHexString(), managerVasya.getEmail());
  }

  @Test
  public void testManager() {
    Assert.notNull(serviceTest.read("vasya@gmail"));
  }

  @Test
  public void getUsersMissions() {
    Assert.notEmpty(serviceTest.getUsersMissions(managerVasya.getUser_id()));
  }

  /*@After
  public void destroyDB() {
    serviceTest.dropDatabase("teamUp");
  }*/
}
