package com.tracker.newtracker.services;

import com.tracker.newtracker.models.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {

    List<TaskList> listAllTaskLists();

    TaskList createTaskList(TaskList taskList);

    Optional<TaskList> getTaskListById(UUID taskListId);

}
