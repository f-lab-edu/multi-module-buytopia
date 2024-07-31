package com.zeroskill.user_api.dto.response;

import com.zeroskill.user_api.entity.Address;
import com.zeroskill.user_api.entity.Grade;
import com.zeroskill.user_api.entity.Member;

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
