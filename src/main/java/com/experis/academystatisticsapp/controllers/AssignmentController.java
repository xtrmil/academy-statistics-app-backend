package com.experis.academystatisticsapp.controllers;

import com.experis.academystatisticsapp.models.Assignment;
import com.experis.academystatisticsapp.services.AssignmentService;
import com.experis.academystatisticsapp.services.CommonResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/assignment")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<CommonResponse> addAssignment(@RequestBody ObjectNode assignment){

        return assignmentService.createAssignment(assignment);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }

    @GetMapping("all")
    public ResponseEntity<CommonResponse> getAllAssignments(){
        return assignmentService.getAllAssignments();
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponse> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignmentToUpdate) {
        return assignmentService.updateAssignmentById(id, assignmentToUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse> deleteAssignment(@PathVariable Long id){
        return assignmentService.deleteAssignmentById(id);
    }
}

