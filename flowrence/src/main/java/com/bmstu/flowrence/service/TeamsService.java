package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.Team;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.exception.ObjectNotFoundExpeception;
import com.bmstu.flowrence.exception.UserNotFoundException;
import com.bmstu.flowrence.repository.TeamRepository;
import com.bmstu.flowrence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamsService {

    private final TeamRepository teamRepository;
    private final UserService userService;


    public List<Team> listAllUsersTeams(String userIdentifier) {
        User user = userService.retrieveUser(userIdentifier);
        return teamRepository.findAllByUsersContaining(user);
    }

    public Team addUserToTeam(String userIdentifier, String teamIdentifier) {
        User user = userService.retrieveUser(userIdentifier);
        Team team = teamRepository.findById(teamIdentifier)
                .orElseThrow(() -> new ObjectNotFoundExpeception(teamIdentifier, Team.class));

        team.getUsers().add(user);
        return teamRepository.save(team);
    }

    public Team removeUserFromTeam(String userIdentifier, String teamIdentifier) {
        User user = userService.retrieveUser(userIdentifier);
        Team team = teamRepository.findById(teamIdentifier)
                .orElseThrow(() -> new ObjectNotFoundExpeception(teamIdentifier, Team.class));

        team.getUsers().remove(user);
        return teamRepository.save(team);
    }

}
