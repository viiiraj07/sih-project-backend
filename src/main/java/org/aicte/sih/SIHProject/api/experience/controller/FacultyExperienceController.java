package org.aicte.sih.SIHProject.api.experience.controller;

import org.aicte.sih.SIHProject.api.experience.dto.entity.FacultyExperience;
import org.aicte.sih.SIHProject.api.experience.dto.request.FacultyExperienceAddRequest;
import org.aicte.sih.SIHProject.api.experience.services.FacultyExperienceService;
import org.aicte.sih.SIHProject.commons.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("api/v1/experience")
@CrossOrigin
public class FacultyExperienceController {

    @Autowired
    private FacultyExperienceService facultyExperienceService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<FacultyExperience>> addNewExperience(@RequestBody FacultyExperienceAddRequest facultyExperienceAddRequest) {
        APIResponse<FacultyExperience> response = new APIResponse<>();
        try {
            response.setData(facultyExperienceService.addNewExperience(facultyExperienceAddRequest));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}