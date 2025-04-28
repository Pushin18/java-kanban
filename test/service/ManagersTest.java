package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    public void checkWorkingTaskManager(){
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }
    @Test
    public void checkWorkingHistoryManager(){
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }

}