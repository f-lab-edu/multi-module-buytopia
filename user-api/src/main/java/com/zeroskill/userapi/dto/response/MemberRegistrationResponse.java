package com.zeroskill.userapi.dto.response;

import com.zeroskill.userapi.entity.Address;
import com.zeroskill.userapi.entity.Grade;
import com.zeroskill.userapi.entity.Member;

public record MemberRegistrationResponse(
    Long id,
    String name,
    String email,
    Grade grade,
    Address address
) {
     public MemberRegistrationResponse(Member member){
         this(member.getId(), member.getName(), member.getEmail(), member.getGrade(), member.getAddress());
     }
}
