package model;
import service.InMemoryTaskManager;
import service.TaskManager;
import org.junit.jupiter.api.Test;

import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;
class EpicTest {
  @Test
  public void epicEquals(){

    TaskManager taskManager = new InMemoryTaskManager();
    Epic epic1 = new Epic("Имя", "Описание");

    final int epicId1 = taskManager.createEpic(epic1);
    final int epicId2 = epicId1;

    assertEquals(taskManager.getEpic(epicId1), taskManager.getEpic(epicId2));
  }

@Test
  public void epicNotAllowToAdAsSubtusk(){
    TaskManager taskManager = new InMemoryTaskManager();
    Epic epic = new Epic("Имя", "Описание");

}
}