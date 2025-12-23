package com.tracker.newtracker.dtos;

import com.tracker.newtracker.models.TaskPriority;
import com.tracker.newtracker.models.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime dueDate
) {
}
