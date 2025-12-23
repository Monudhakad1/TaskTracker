package com.tracker.newtracker.mappers;

import com.tracker.newtracker.dtos.TaskDto;
import com.tracker.newtracker.models.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
