package com.tracker.newtracker.mappers.IMPL;

import com.tracker.newtracker.dtos.TaskListDto;
import com.tracker.newtracker.mappers.TaskListMapper;
import com.tracker.newtracker.mappers.TaskMapper;
import com.tracker.newtracker.models.Task;
import com.tracker.newtracker.models.TaskList;
import com.tracker.newtracker.models.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    // Constructor Injection (Perfect hai ye)
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),

                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto) // Har ek TaskDto ko Task Entity mein badla
                                .toList()) // Wapas List mein pack kiya
                        .orElse(null) // Agar list null thi, toh null hi return karo
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        // Record (DTO) create kar rahe hain
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks==null) return null;
        long closedTasksCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();
                 return (double) closedTasksCount/tasks.size();
    }

}