package com.tracker.newtracker.services.impl;

import com.tracker.newtracker.models.Task;
import com.tracker.newtracker.models.TaskList;
import com.tracker.newtracker.models.TaskPriority;
import com.tracker.newtracker.models.TaskStatus;
import com.tracker.newtracker.repositories.TaskListRepository;
import com.tracker.newtracker.repositories.TaskRepository;
import com.tracker.newtracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class taskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public taskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID id) {
        return List.of();
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {

        if (task.getId() != null) {
            throw new IllegalArgumentException("New Task cannot already have an ID");
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = Optional.ofNullable(task.getStatus())
                .orElse(TaskStatus.OPEN);

        LocalDateTime now = LocalDateTime.now();

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "TaskList not found"
                        )
                );

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {


        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null){
            throw new IllegalArgumentException("Task ID cannot be null for update");
        }
        if(!task.getId().equals(taskId)){
            throw new IllegalArgumentException("Task ID in the path and body must match");
        }
        if(null==task.getPriority() || null==task.getStatus()){
            throw new IllegalArgumentException("Task must have priority and status");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task not found"
                ));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteAllByTaskListIdAndId(taskListId,taskId);
    }

}
