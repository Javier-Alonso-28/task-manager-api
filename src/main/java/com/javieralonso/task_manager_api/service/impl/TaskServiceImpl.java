package com.javieralonso.task_manager_api.service.impl;

import com.javieralonso.task_manager_api.dto.request.TaskCreateRequest;
import com.javieralonso.task_manager_api.dto.request.TaskUpdateRequest;
import com.javieralonso.task_manager_api.dto.response.TaskResponse;
import com.javieralonso.task_manager_api.entity.Role;
import com.javieralonso.task_manager_api.entity.Task;
import com.javieralonso.task_manager_api.entity.TaskStatus;
import com.javieralonso.task_manager_api.entity.User;
import com.javieralonso.task_manager_api.exception.ResourceNotFoundException;
import com.javieralonso.task_manager_api.mapper.TaskMapper;
import com.javieralonso.task_manager_api.repository.TaskRepository;
import com.javieralonso.task_manager_api.repository.UserRepository;
import com.javieralonso.task_manager_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse create(TaskCreateRequest request) {
        User currentUser = getOrCreateDemoUser();
        Task task = taskMapper.toEntity(request);
        task.setUser(currentUser);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getById(Long id) {
        Task task = findTaskById(id);
        return taskMapper.toResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskResponse> getAll(TaskStatus status, Pageable pageable) {
        User currentUser = getOrCreateDemoUser();
        Page<Task> tasks = (status == null)
                ? taskRepository.findByUserId(currentUser.getId(), pageable)
                : taskRepository.findByUserIdAndStatus(currentUser.getId(), status, pageable);
        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public TaskResponse update(Long id, TaskUpdateRequest request) {
        Task task = findTaskById(id);
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
        taskRepository.delete(task);
    }

    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id: " + id));
    }

    // TEMPORAL: usuario demo hasta que implementemos JWT en la siguiente sesión
    private User getOrCreateDemoUser() {
        return userRepository.findByUsername("demo")
                .orElseGet(() -> userRepository.save(User.builder()
                        .username("demo")
                        .email("demo@example.com")
                        .password("demo123")
                        .role(Role.USER)
                        .build()));
    }
}