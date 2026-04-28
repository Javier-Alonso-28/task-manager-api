package com.javieralonso.task_manager_api.service;

import com.javieralonso.task_manager_api.dto.request.TaskCreateRequest;
import com.javieralonso.task_manager_api.dto.request.TaskUpdateRequest;
import com.javieralonso.task_manager_api.dto.response.TaskResponse;
import com.javieralonso.task_manager_api.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponse create(TaskCreateRequest request);
    TaskResponse getById(Long id);
    Page<TaskResponse> getAll(TaskStatus status, Pageable pageable);
    TaskResponse update(Long id, TaskUpdateRequest request);
    void delete(Long id);
}