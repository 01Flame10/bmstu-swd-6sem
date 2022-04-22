package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.Dashboard;
import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService extends AbstractEntityService<Dashboard, DashboardRepository> {

    private final TeamsService teamsService;

    public Dashboard createDashboard(String name, String description, String teamIdentifier) {
        Team team = teamsService.retrieveByIdentifier(teamIdentifier);
        Dashboard dashboard = new Dashboard()
                .setName(name)
                .setDescription(description)
                .setOwner(team);
        return repository.save(dashboard);
    }

}
