package com.bmstu.flowrence.service;

import com.bmstu.flowrence.dto.request.personal.TaskCreateRequestDto;
import com.bmstu.flowrence.dto.request.personal.TaskUpdateRequestDto;
import com.bmstu.flowrence.entity.Dashboard;
import com.bmstu.flowrence.entity.Task;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService extends AbstractEntityService<Task, TaskRepository> {

    private final DashboardService dashboardService;
    private final UserService userService;

    public Task createTask(TaskCreateRequestDto request) {
        User reporter = userService.retrieveByIdentifier(request.getUserUuid());
        User assignee = Optional.ofNullable(request.getAssigneeUuid())
                .map(userService::retrieveByIdentifier)
                .orElse(null);
        Dashboard dashboard = dashboardService.retrieveByIdentifier(request.getDashboardUuid());

        Long nextSimpleIdentifierNumber = repository.findMaxSimpleIdentifierNumber(dashboard.getPrefix())
                .orElse(1L);

        Task task = new Task() // TODO: to mapper
                .setSimpleIdentifierNumber(nextSimpleIdentifierNumber)
                .setSimpleIdentifierPrefix(dashboard.getPrefix())
                .setDashboard(dashboard)
                .setHeader(request.getHeader())
                .setDescription(request.getDescription())
                .setPriority(Task.TaskPriority.valueOf(request.getPriority()))
                .setType(Task.TaskType.valueOf(request.getType()))
                .setStatus(Task.TaskStatus.IDEA)
                .setReporter(reporter)
                .setAssignee(assignee);
        return repository.save(task);
    }

    public Task updateTask(TaskUpdateRequestDto request) {
        Task task = retrieveByIdentifier(request.getTaskUuid());

        if (request.getAssigneeUuid() != null) {
            User assignee = userService.retrieveByIdentifier(request.getAssigneeUuid());
            task.setAssignee(assignee);
        }

        task.setHeader(request.getHeader()) // TODO: to mapper
                .setDescription(request.getDescription())
                .setPriority(Task.TaskPriority.valueOf(request.getPriority()));

        return repository.save(task);
    }
}
