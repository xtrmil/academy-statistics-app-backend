package com.experis.academystatisticsapp.services;

import com.experis.academystatisticsapp.models.Assignment;
import com.experis.academystatisticsapp.models.Location;
import com.experis.academystatisticsapp.repositories.AssignmentRepository;
import com.experis.academystatisticsapp.repositories.LocationRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    LocationRepository locationRepository;

    public ResponseEntity<CommonResponse> createAssignment(ObjectNode assignment){
        CommonResponse cr = new CommonResponse();
        Assignment as = new Assignment();
        as.setName(assignment.get("name").asText());
        as.setCompanyName(assignment.get("companyName").asText());

        if(!assignmentRepository.existsById(as.getId())){
            Location location = locationRepository.getLocationById(assignment.get("locationId").asLong());
            assignmentRepository.createAssignment(as.getName(), as.getCompanyName(),location.getId());
            cr.msg = "Assignment " + as.getName() + " was added successfully.";
            cr.status = HttpStatus.CREATED;
        }
        else {
            cr.msg = "Assignment: " + as.getName() + "at: " + as.getCompanyName() + " already exists";
            cr.status = HttpStatus.BAD_GATEWAY;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAssignmentById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Assignment> assignment = Optional.ofNullable(assignmentRepository.getAssignmentById(id));

        if(assignment.isPresent()){
            cr.data = assignmentRepository.getAssignmentById(id);
            cr.msg = "Assignment with id: " + id + " was found.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Assignment with id: " + id + " was not found.";
            cr.status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> updateAssignmentById(Long id, Assignment assignmentToUpdate){
        CommonResponse cr = new CommonResponse();

        Optional<Assignment> optionalAssignment = Optional.ofNullable(assignmentRepository.getAssignmentById(id));

        if(optionalAssignment.isPresent()){
            Assignment assignment = optionalAssignment.get();

            if(assignmentToUpdate.getName() != null){
                assignment.setName(assignmentToUpdate.getName());
            }
            if(assignmentToUpdate.getCompanyName() != null){
                assignment.setCompanyName(assignmentToUpdate.getCompanyName());
            }

            /*
            if(assignmentToUpdate.getLocation().getId() != null){
                Location location = locationRepository.getLocationById(assignmentToUpdate.getLocation().getId());
                assignment.setLocation(location);
            }
             */
            assignmentRepository.updateAssignmentById(assignment.getId(), assignment.getName(), assignment.getCompanyName(), assignment.getLocation().getId());
            cr.data = assignment;
            cr.msg = "Assignment: "  + assignment.getName() + " was updated successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.msg = "Unable to update assignment name : " + assignmentToUpdate.getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> deleteAssignmentById(Long id){
        CommonResponse cr = new CommonResponse();

        Optional<Assignment> optionalAssignment = Optional.ofNullable(assignmentRepository.getAssignmentById(id));

        if(optionalAssignment.isPresent()){
            assignmentRepository.deleteAssignmentById(id);
            cr.data = optionalAssignment;
            cr.msg = "Assignment: " + optionalAssignment.get().getName() + " was deleted successfully.";
            cr.status = HttpStatus.OK;
        }
        else {
            cr.data = optionalAssignment;
            cr.msg = "Unable to delete assignment with name: " + optionalAssignment.get().getName();
            cr.status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(cr, cr.status);
    }

    public ResponseEntity<CommonResponse> getAllAssignments(){
        CommonResponse cr = new CommonResponse();
        cr.data = assignmentRepository.GetAll();
        cr.status = HttpStatus.OK;
        cr.msg = "List of all users";

        return new ResponseEntity<>(cr, cr.status);
    }
}
