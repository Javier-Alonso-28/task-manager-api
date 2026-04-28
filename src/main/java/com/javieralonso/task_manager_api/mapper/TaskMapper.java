package com.javieralonso.task_manager_api.mapper;

import com.javieralonso.task_manager_api.dto.request.TaskCreateRequest;
import com.javieralonso.task_manager_api.dto.response.TaskResponse;
import com.javieralonso.task_manager_api.entity.Priority;
import com.javieralonso.task_manager_api.entity.Task;
import com.javieralonso.task_manager_api.entity.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskCreateRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .status(TaskStatus.PENDING)
                .priority(request.priority() != null ? request.priority() : Priority.MEDIUM)
                .dueDate(request.dueDate())
                .build();
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
