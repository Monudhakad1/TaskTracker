package com.tracker.newtracker.services.impl;

import com.tracker.newtracker.models.TaskList;
import com.tracker.newtracker.repositories.TaskListRepository;
import com.tracker.newtracker.services.TaskListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {


    private final TaskListRepository taskListRepository;


    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listAllTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("New TaskList cannot already have an ID");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList title cannot be null or blank");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));


    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }
}
