package com.springboot.validException.controller;

import com.springboot.validException.data.dto.ValidRepusetDto;
import com.springboot.validException.data.group.ValidationGroup1;
import com.springboot.validException.data.group.ValidationGroup2;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ValidationController.class);

    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(
            @Valid @RequestBody ValidRepusetDto validRepusetDto
            ){
        LOGGER.info(validRepusetDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRepusetDto.toString());
    }

    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1(@Validated(ValidationGroup1.class) @RequestBody ValidRepusetDto validRepusetDto){
        LOGGER.info(validRepusetDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRepusetDto.toString());
    }

    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2(@Validated(ValidationGroup2.class) @RequestBody ValidRepusetDto validRepusetDto){
        LOGGER.info(validRepusetDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRepusetDto.toString());
    }
}
