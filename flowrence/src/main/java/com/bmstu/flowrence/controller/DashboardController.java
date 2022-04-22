package com.bmstu.flowrence.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/dashboards")
@RequiredArgsConstructor
public class DashboardController {

    // maybe some kind of annotation-based wrapper for error handling
//    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<TeamInfoDto> createTeam(@RequestBody TeamCreateRequestDto request) {
//        log.debug("Creating team with name {}", request.getName());
//        try {
//            Team teams = teamsService.createTeam(request.getName());
//            TeamInfoDto responseDto = teamToTeamInfoMapper.mapSourceToDestination(teams);
//
//            return new ResponseEntity<>(responseDto, HttpStatus.OK);
//        } catch (Exception e) {
//            log.error("Error processing request", e);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

}
