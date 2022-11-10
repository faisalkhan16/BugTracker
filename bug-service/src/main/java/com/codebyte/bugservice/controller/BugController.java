package com.codebyte.bugservice.controller;

import com.codebyte.bugservice.dto.BugRequestDTO;
import com.codebyte.bugservice.dto.BugResponseDTO;
import com.codebyte.bugservice.service.BugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BugController {

    private final BugService bugService;

    @PostMapping(value = "/bugs")
    public ResponseEntity create(@Valid @RequestBody BugRequestDTO bugRequestDTO)
    {
        log.info("request: create(): {}", bugRequestDTO);
        bugService.saveBug(bugRequestDTO);
        log.info("response: create(): {}", HttpStatus.CREATED);
        return new  ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/bugs")
    public ResponseEntity<List<BugResponseDTO>> getBugs()
    {
        List<BugResponseDTO> bugResponseDTOs = bugService.getBugs();
        return ResponseEntity.ok().body(bugResponseDTOs);
    }

    @DeleteMapping(value = "/bug/{bug_id}")
    public ResponseEntity delete(@PathVariable @NotBlank(message = "invalid bug_id") Long bug_id)
    {
        log.info("request: delete(): {}", bug_id);
        bugService.deleteBug(bug_id);
        log.info("response: delete(): {}", HttpStatus.NO_CONTENT);
        return new  ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PatchMapping(value = "/bug/{bug_id}")
    public ResponseEntity update(@Valid @RequestBody BugRequestDTO bugRequestDTO,@PathVariable @NotBlank(message = "invalid bug_id") Long bug_id)
    {
        log.info("request: update() BugUlid: {} {}", bugRequestDTO,bug_id);
        bugService.updateBug(bugRequestDTO,bug_id);
        log.info("response: update(): {}", HttpStatus.NO_CONTENT);

        return new  ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
