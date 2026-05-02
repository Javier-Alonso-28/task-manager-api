package com.javieralonso.task_manager_api.service.impl;

import com.javieralonso.task_manager_api.dto.request.TaskCreateRequest;
import com.javieralonso.task_manager_api.dto.request.TaskUpdateRequest;
import com.javieralonso.task_manager_api.dto.response.TaskResponse;
import com.javieralonso.task_manager_api.entity.Task;
import com.javieralonso.task_manager_api.entity.TaskStatus;
import com.javieralonso.task_manager_api.entity.User;
import com.javieralonso.task_manager_api.exception.ResourceNotFoundException;
import com.javieralonso.task_manager_api.mapper.TaskMapper;
import com.javieralonso.task_manager_api.repository.TaskRepository;
import com.javieralonso.task_manager_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse create(TaskCreateRequest request) {
        User currentUser = getCurrentUser();
        Task task = taskMapper.toEntity(request);
        task.setUser(currentUser);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getById(Long id) {
        Task task = findTaskById(id);
        verifyOwnership(task);
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponse> getAll(TaskStatus status, Pageable pageable) {
        User currentUser = getCurrentUser();
        Page<Task> tasks = (status == null)
                ? taskRepository.findByUserId(currentUser.getId(), pageable)
                : taskRepository.findByUserIdAndStatus(currentUser.getId(), status, pageable);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public TaskResponse update(Long id, TaskUpdateRequest request) {
        Task task = findTaskById(id);
        verifyOwnership(task);
        if (request.title() != null) task.setTitle(request.title());
        if (request.description() != null) task.setDescription(request.description());
        if (request.status() != null) task.setStatus(request.status());
        if (request.priority() != null) task.setPriority(request.priority());
        if (request.dueDate() != null) task.setDueDate(request.dueDate());
        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    public void delete(Long id) {
        Task task = findTaskById(id);
        verifyOwnership(task);
        taskRepository.delete(task);
    }

    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void verifyOwnership(Task task) {
        User currentUser = getCurrentUser();
        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("No tienes permiso para acceder a esta tarea");
        }
    }
}