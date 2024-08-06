package com.zeroskill.user_api.dto.response;

import com.zeroskill.common.entity.Address;
import com.zeroskill.common.entity.Grade;
import com.zeroskill.common.entity.Member;

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
