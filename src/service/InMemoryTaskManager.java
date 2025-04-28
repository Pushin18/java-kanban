package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager, HistoryManager {
    HashMap<Integer, model.Task> tasks = new HashMap<>();
    HashMap<Integer, model.Subtask> subtasks = new HashMap<>();
    HashMap<Integer, model.Epic> epics = new HashMap<>();

    private int generatedId = 1;

    ArrayList<Task> history = new ArrayList<>();

    @Override
    public void add(Task task){
        if (history.size() == 10) {
           history.removeFirst();
        }
        history.add(task);
    }

    @Override
    public int createTask(model.Task task) {
        task.setId(generatedId++);
        tasks.put(task.getId(), task);

        return task.getId();
    }

    @Override
    public int createEpic(model.Epic epic) {
        epic.setId(generatedId++);
        epics.put(epic.getId(), epic);

        return epic.getId();
    }

    @Override
    public int createSubtask(model.Subtask subtask) {
        subtask.setId(generatedId++);
        subtasks.put(subtask.getId(), subtask);

        // epics.get(subtask.getEpicID()).setSubtaskIds(generatedId);
        epics.get(subtask.getEpicID()).getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epics.get(subtask.getEpicID()));
        return subtask.getId();
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        int epicId = subtask.getEpicID();
        Epic epic = epics.get(epicId);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        subtaskIds.remove(subtask);
    }

    @Override
    public void deleteEpic(int id) {
        Epic epic = epics.get(id);
        List<Integer> subtaskIds = epic.getSubtaskIds();
        subtaskIds.clear();
        epics.remove(id);
    }

    @Override
    public void deleteTasks(){
        tasks.clear();
    }

    @Override
    public void deleteSubtasks(){

        for (Integer key : subtasks.keySet()) {
            Subtask subtask = subtasks.get(key);
            int epicId = subtask.getEpicID();
            Epic epic = epics.get(epicId);
            List<Integer> subtaskIds = epic.getSubtaskIds();
            subtaskIds.remove(subtask);
        }
        subtasks.clear();
    }

    @Override
    public void deleteEpics(){

        for (Integer key : epics.keySet()) {
            Epic epic = epics.get(key);
            List<Integer> subtaskIds = epic.getSubtaskIds();
            subtaskIds.clear();
        }
        epics.clear();
    }

    @Override
    public model.Task getTask(int id) {
        if (tasks.get(id) == null) {
            throw new NullPointerException("Данной задачи нет в списке");
        }
        add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public model.Subtask getSubtask(int id) {
        if (subtasks.get(id) == null) {
            throw new NullPointerException("Данной подзадачи нет в списке");
        }
        add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public model.Epic getEpic(int id) {
        if (epics.get(id) == null) {
            throw new NullPointerException("Данного эпика нет в списке");
        }
        add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void updateTask(model.Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);

        }
    }

    @Override
    public void updateEpic(model.Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateEpicStatus(epic);
        }
    }

    @Override
    public void updateSubtask(model.Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateEpicStatus(epics.get(subtask.getEpicID()));
        }

    }

    @Override
    public ArrayList<model.Subtask> getSubtasksByEpicId(int epicId) {
        List<Integer> subtasksIDList = epics.get(epicId).getSubtaskIds();
        ArrayList<model.Subtask> subtasksNameList = new ArrayList<>();
        for (Integer integer : subtasksIDList) {
            subtasksNameList.add(subtasks.get(integer));
        }
        return subtasksNameList;
    }

    @Override
    public ArrayList<model.Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<model.Subtask> getSubtasks() {

        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<model.Epic> getEpics() {

        return new ArrayList<>(epics.values());
    }

    @Override
    public void updateEpicStatus(model.Epic epic) {
        ArrayList<Status> statusOfSubtasks = new ArrayList<>();
        int countNew = 0;
        int countDone = 0;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            statusOfSubtasks.add(subtasks.get(epic.getSubtaskIds().get(i)).getStatus());
        }
        for (Status ofSubtask : statusOfSubtasks) {
            if (ofSubtask.equals(Status.NEW)) countNew++;
        }
        for (Status statusOfSubtask : statusOfSubtasks) {
            if (statusOfSubtask.equals(Status.DONE)) countDone++;
        }
        if ((epic.getSubtaskIds().isEmpty()) || (countNew == statusOfSubtasks.size())) {
            epic.setStatus(Status.NEW);

        } else if (countDone == statusOfSubtasks.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public void updateStatus(Task taskToChangeStatus, Status status){
        taskToChangeStatus.setStatus(status);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }
}

