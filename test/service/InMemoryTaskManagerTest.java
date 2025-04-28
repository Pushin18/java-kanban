package service;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    public void checkAddAndFindingTasksByID(){
        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Имя", "Описание");
        final int taskID = taskManager.createTask(task);
        Epic epic = new Epic("Имя", "Описание");
        final int epicID = taskManager.createEpic(epic);
        Subtask subtask = new Subtask("Имя", "Описание", epic.getId());
        final int subtaskID = taskManager.createSubtask(subtask);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(epic);
        taskList.add(subtask);
        assertNotNull(taskList);
        assertEquals(2, epicID);
    }

}