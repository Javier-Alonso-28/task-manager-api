package com.javieralonso.task_manager_api.dto.response;

import com.javieralonso.task_manager_api.entity.Priority;
import com.javieralonso.task_manager_api.entity.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        LocalDate dueDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}