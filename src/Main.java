import model.Status;

public static void main(String[] args) {


    TaskManager TaskManager = new TaskManager();

    // Создание
    model.Task task1 = new model.Task("model.Task #1", "Task1 description");
    model.Task task2 = new model.Task("model.Task #2", "Task2 description");
    final int taskId1 = TaskManager.createTask(task1);
    final int taskId2 = TaskManager.createTask(task2);

    model.Epic epic1 = new model.Epic("model.Epic #1", "Epic1 description");
    model.Epic epic2 = new model.Epic("model.Epic #2", "Epic2 description");
    final int epicId1 = TaskManager.createEpic(epic1);
    final int epicId2 = TaskManager.createEpic(epic2);

    model.Subtask subtask1 = new model.Subtask("model.Subtask #1-1", "Subtask1 description", epicId1);
    model.Subtask subtask2 = new model.Subtask("model.Subtask #2-1", "Subtask1 description", epicId1);
    model.Subtask subtask3 = new model.Subtask("model.Subtask #3-2", "Subtask1 description", epicId2);
    final Integer subtaskId1 = TaskManager.createSubtask(subtask1);
    final Integer subtaskId2 = TaskManager.createSubtask(subtask2);
    final Integer subtaskId3 = TaskManager.createSubtask(subtask3);


    // Обновление
    final model.Task task = TaskManager.getTask(taskId2);
     task.setStatus(Status.DONE);
    TaskManager.updateTask(task);
    System.out.println("CHANGE STATUS: Task2 IN_PROGRESS->DONE");
    System.out.println("Задачи:");
    for (model.Task t : TaskManager.getTasks()) {
        System.out.println(t);
    }

    model.Subtask subtask = TaskManager.getSubtask(subtaskId2);
     subtask.setStatus(Status.DONE);
    TaskManager.updateSubtask(subtask);
    System.out.println("CHANGE STATUS: Subtask2 NEW->DONE");
    subtask = TaskManager.getSubtask(subtaskId3);
    subtask.setStatus(Status.NEW);
    TaskManager.updateSubtask(subtask);
    System.out.println("CHANGE STATUS: Subtask3 DONE->NEW");
    System.out.println("Подзадачи:");
    for (model.Subtask t : TaskManager.getSubtasks()) {
        System.out.println(t);
    }

    System.out.println("Эпики:");
    for (model.Epic e : TaskManager.getEpics()) {
        System.out.println(e);
        System.out.println(e.getStatus());
        for (model.Task t : TaskManager.getSubtasksByEpicId(e.getId())) {
            System.out.println("--> " + t);
        }
    }
    final model.Epic epic = TaskManager.getEpic(epicId1);
    epic.setStatus(Status.NEW);
    TaskManager.updateEpic(epic);
    System.out.println("CHANGE STATUS: Epic1 IN_PROGRESS->NEW");

    System.out.println("Эпики:");
    for (model.Task e : TaskManager.getEpics()) {
        System.out.println(e);
        System.out.println(e.getStatus());
        for (model.Task t : TaskManager.getSubtasksByEpicId(e.getId())) {
            System.out.println("--> " + t);
        }
    }

    // Удаление
    System.out.println("DELETE: Task1");
    TaskManager.deleteTask(taskId1);

}