package service;

import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    int createTask(model.Task task);

    int createEpic(model.Epic epic);

    int createSubtask(model.Subtask subtask);

    void deleteTask(int id);

    void deleteSubtask(int id);

    void deleteEpic(int id);

    void deleteTasks();

    void deleteSubtasks();

    void deleteEpics();

    model.Task getTask(int id);

    model.Subtask getSubtask(int id);

    model.Epic getEpic(int id);

    void updateTask(model.Task task);

    void updateEpic(model.Epic epic);

    void updateSubtask(model.Subtask subtask);

    ArrayList<Subtask> getSubtasksByEpicId(int epicId);

    ArrayList<model.Task> getTasks();

    ArrayList<Subtask> getSubtasks();

    ArrayList<model.Epic> getEpics();

    void updateEpicStatus(model.Epic epic);

    void updateStatus(Task taskToChangeStatus, Status status);

}
