package com.teamup;

import com.teamup.database.MongoTest;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import com.teamup.service.ServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by user01 on 11/29/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MongoTest.class, ServiceTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongoDdTest {

  @Autowired
  private ServiceTest serviceTest;

  private Participant participant1;
  private Participant participant2;

  @Before
  public void prepareDB() {
    participant1 = serviceTest.create("Вася", "Петечкин", "vasya@gmail.com", "vasya121");
    participant2 = serviceTest.create("Костя", "Сидоров", "kostya@gmail.com", "kostya121");
    Task task = new Task("Купить цветы на оптовой базе", participant1.get_id(), participant2.get_id(), "35.987234, 55.123498");
    participant2.setCurrentTask(task);
    serviceTest.save(participant1);
    serviceTest.save(participant2);
  }

  @Test
  public void createsParticipant() {
    Participant returnedParticipant1 = serviceTest.read("Вася");
    Participant returnedParticipant2 = serviceTest.read("Костя");
    Assert.assertEquals(participant1, returnedParticipant1);
    Assert.assertEquals(participant2, returnedParticipant2);
  }

  @Test
  public void assignsTask() {
    Participant manager = serviceTest.read("Вася");
    Participant executor = serviceTest.read("Костя");
    serviceTest.assignTask("Купить цветы на оптовой базе", manager.get_id(), executor.get_id(), "35.987234, 55.123498");
    Participant executorReRead = serviceTest.read("Костя");
    Task task = executorReRead.getTasks().get(0);
    Assert.assertNotNull(task);
  }

  @Test
  public void deletesParticipant() {
    serviceTest.delete("Вася");
    Assert.assertEquals(null, serviceTest.read("Вася"));
  }

  @Test
  public void getAllParticipants() {
    Assert.assertEquals(2, serviceTest.getAllParticipants().size());
  }

  @Test
  public void saveUserAvatar() throws IOException {
    Participant manager = serviceTest.read("Вася");
    Participant executor = serviceTest.read("Костя");
    serviceTest.assignTask("Купить цветы на оптовой базе", manager.get_id(), executor.get_id(), "35.987234, 55.123498");
    Participant executorReRead = serviceTest.read("Костя");
    serviceTest.saveUserAvatar(new File("/home/user01/Projects/mapApp/map-app/repository/src/test/resources/avatar-placeholder.png"), participant1);
    serviceTest.saveUserAvatar(new File("/home/user01/Projects/mapApp/map-app/repository/src/test/resources/logo-large.png"), participant2);
  }

    /*@After
  public void destroyDB() {
    serviceTest.dropDatabase("teamUp");
  }*/
}
