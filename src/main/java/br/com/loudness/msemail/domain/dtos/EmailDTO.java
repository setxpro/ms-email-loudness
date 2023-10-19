package br.com.loudness.msemail.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record EmailDTO(
        @NotBlank
        String ownerRef,
        @NotBlank
        @Email
        String emailFrom,
        @NotBlank
        @Email
        String emailTo,
        @NotBlank
        String subject,
        @NotBlank
        String text
) {

}
