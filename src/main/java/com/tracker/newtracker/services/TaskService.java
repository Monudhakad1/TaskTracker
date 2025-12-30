package com.tracker.newtracker.services;

import com.tracker.newtracker.models.Task;

import java.util.*;


public interface TaskService {


   List<Task> listTasks(UUID id);


   Task createTask(UUID taskListId, Task task);

   Optional<Task> getTask(UUID taskListId,UUID taskId);

   Task updateTask(UUID taskListId, UUID taskId, Task task);


   void deleteTask(UUID taskListId, UUID taskId);
}
