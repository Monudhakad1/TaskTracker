package com.tracker.newtracker.mappers;

import com.tracker.newtracker.dtos.TaskListDto;
import com.tracker.newtracker.models.TaskList;

public interface TaskListMapper
{
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
