package model;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.TaskManager;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {
    @Test
    public void TaskEquals(){

        TaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Имя", "Описание");

        final int taskId1 = taskManager.createTask(task1);
        final int taskId2 = taskId1;

        assertEquals(taskManager.getTask(taskId1), taskManager.getTask(taskId2));
    }

    @Test
    public void addNewTask(){
        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Имя", "Описание");
        final int taskId = taskManager.createTask(task);

        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();
        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    public void add(){

            HistoryManager historyManager = new InMemoryHistoryManager();
            Task task = new Task("Имя", "Описание");
            historyManager.add(task);
            final List<Task> history = historyManager.getHistory();
            assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
            assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
            assertEquals("Имя", task.getName());
            assertEquals("Описание", task.getDescription());
    }

}