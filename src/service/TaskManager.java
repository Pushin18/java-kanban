package service;

import model.Status;
import model.Subtask;
import model.Task;
import model.Epic;

import java.util.ArrayList;

public interface TaskManager {
    int createTask(Task task);

    int createEpic(Epic epic);

    int createSubtask(Subtask subtask);

    void deleteTask(int id);

    void deleteSubtask(int id);

    void deleteEpic(int id);

    void deleteTasks();

    void deleteSubtasks();

    void deleteEpics();

    model.Task getTask(int id);

    model.Subtask getSubtask(int id);

    model.Epic getEpic(int id);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    ArrayList<Subtask> getSubtasksByEpicId(int epicId);

    ArrayList<model.Task> getTasks();

    ArrayList<Subtask> getSubtasks();

    ArrayList<model.Epic> getEpics();

    void updateEpicStatus(Epic epic);

    void updateStatus(Task taskToChangeStatus, Status status);

}
