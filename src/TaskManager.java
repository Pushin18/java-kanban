import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    // + хэш мапы для эпиков и подзадач
    private int generatedId = 0;

    public int createTask(Task task) {
        task.setId(generatedId++);
        tasks.put(task.getId(), task);

        return task.getId();
    }

    public int createEpic(Epic epic) {
        epic.setId(generatedId++);
        epics.put(epic.getId(), epic);

        return epic.getId();
    }

    public int createSubtask(Subtask subtask) {
        subtask.setId(generatedId++);
        subtasks.put(subtask.getId(), subtask);

        // epics.get(subtask.getEpicID()).setSubtaskIds(generatedId);
        epics.get(subtask.getEpicID()).getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epics.get(subtask.getEpicID()));
        return subtask.getId();
    }


// проверить, что эпик, заданный в subtask, существует

// пересчитать статус эпик (а также при удалении и обновлении подзадачи)

// добавить подзадачу в поле-список нужно эпика


    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteSubtask(int id) {
        subtasks.remove(id);
    }

    public void deleteEpic(int id) {
        epics.remove(id);
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateEpicStatus(epic);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateEpicStatus(epics.get(subtask.getEpicID()));
        }

    }

    public ArrayList<Subtask> getSubtasksByEpicId(int epicId) {
        List<Integer> subtasksIDList = epics.get(epicId).getSubtaskIds();
        ArrayList<Subtask> subtasksNameList = new ArrayList<>();
        for (Integer integer : subtasksIDList) {
            subtasksNameList.add(subtasks.get(integer));
        }
        return subtasksNameList;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getSubtasks() {

        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getEpics() {

        return new ArrayList<>(epics.values());
    }

    private void updateEpicStatus(Epic epic) {
        ArrayList<Status> statusOfSubtasks = new ArrayList<>();
        int countNew = 0;
        int countDone = 0;
        for (int i = 0; i < epic.getSubtaskIds().size(); i++) {
            statusOfSubtasks.add(subtasks.get(epic.getSubtaskIds().get(i)).getStatus());
        }
        for (int i = 0; i < statusOfSubtasks.size(); i++) {
            if (statusOfSubtasks.get(i).equals(Status.NEW)) countNew++;
        }
        for (int i = 0; i < statusOfSubtasks.size(); i++) {
            if (statusOfSubtasks.get(i).equals(Status.DONE)) countDone++;
        }
        if ((epic.getSubtaskIds().isEmpty()) || (countNew == statusOfSubtasks.size())) {
            epic.setStatus(Status.NEW);

        } else if (countDone == statusOfSubtasks.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}

