package com.springboot.validException.data.dto;

import com.springboot.validException.config.annotation.Telephone;
import com.springboot.validException.data.group.ValidationGroup1;
import com.springboot.validException.data.group.ValidationGroup2;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRepusetDto {
    @NotBlank
    String name;

    @Email
    String email;

    @Telephone
    String phoneNumber;

    @Min(value = 20, groups = ValidationGroup1.class)
    @Max(value = 40, groups = ValidationGroup1.class)
    int age;

    @Size(min=0, max = 40)
    String description;

    @Positive(groups = ValidationGroup2.class)
    int count;

    @AssertTrue
    boolean booleanCheck;
}
