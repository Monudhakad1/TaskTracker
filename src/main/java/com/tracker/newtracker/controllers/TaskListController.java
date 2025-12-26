package com.tracker.newtracker.controllers;

import com.tracker.newtracker.models.dtos.TaskListDto;
import com.tracker.newtracker.mappers.TaskListMapper;
import com.tracker.newtracker.models.TaskList;
import com.tracker.newtracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;
    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> listTaskLists() {
        // Implementation will go here
        return   taskListService.listAllTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        // Implementation will frontend send request with body
       TaskList createdTaskList= taskListService.createTaskList(
               taskListMapper.fromDto(taskListDto)
       );
       return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskListById(taskListId).map(taskListMapper::toDto);

    }
}
