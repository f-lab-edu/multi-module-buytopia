package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.dto.AddressDto;
import com.zeroskill.buytopia.dto.MemberDto;
import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.exception.PasswordMismatchException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberRegistrationRequest(
        @NotBlank(message = "사용자 아이디는 필수입니다.")
        String loginId,

        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,

        @NotBlank(message = "비밀번호 확인은 필수입니다.")
        String passwordConfirm,

        @Valid
        AddressDto address
) {
    public MemberRegistrationRequest {
        if(loginId == null || loginId.isEmpty()) {
            throw new EmptyFieldException("사용자 아이디는 필수입니다.");
        }

        if(name == null || name.isEmpty()) {
            throw new EmptyFieldException("이름은 필수입니다.");
        }

        if(email == null || email.isEmpty()) {
            throw new EmptyFieldException("이메일은 필수입니다.");
        }

        if (!isValidEmail(email)) {
            throw new InvalidEmailFormatException("이메일 형식이 유효하지 않습니다.");
        }

        if(password == null || password.isEmpty()) {
            throw new EmptyFieldException("비밀번호는 필수입니다.");
        }

        if(passwordConfirm == null || passwordConfirm.isEmpty()) {
            throw new EmptyFieldException("비밀번호 확인은 필수입니다.");
        }

        if (address == null ||
                address.mainAddress().isEmpty() ||
                address.subAddress().isEmpty() ||
                address.zipcode().isEmpty()) {
            throw new EmptyFieldException("주소 정보중 빈값이 있습니다.");
        }

        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException("비밀번호 확인이 일치하지 않습니다.");
        }
    }

    public static MemberDto toMemberDto(MemberRegistrationRequest request) {
        return new MemberDto(null, request.loginId(), request.name, request.email, request.password, (byte) 1, request.address);
    }

    public boolean arePasswordsMatching() {
        return password.equals(passwordConfirm);
    }
}
