package com.javieralonso.task_manager_api.dto.request;

import com.javieralonso.task_manager_api.entity.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskCreateRequest(

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede superar los 150 caracteres")
        String title,

        @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
        String description,

        Priority priority,

        @FutureOrPresent(message = "La fecha de vencimiento debe ser hoy o en el futuro")
        LocalDate dueDate
) {}
