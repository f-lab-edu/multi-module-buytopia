package com.zeroskill.buytopia.service;

import com.zeroskill.buytopia.entity.Member;
import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private final VerificationTokenService verificationTokenService;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Transactional
    public void sendVerificationEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error)
        );

        if (!member.isActivated()) {
            String token = verificationTokenService.createVerificationToken(member.getEmail());
            String link = "http://localhost:8087/api/v1/members/verify/email?token=" + token;
            sendEmail(member.getEmail(), "Verify Your Email", "Please click on the link to verify your email: " + link);
        }
    }

    @Transactional
    public boolean verifyEmail(String token) {
        String email = verificationTokenService.validateToken(token);
        if (email != null) {
            Member member = memberRepository.findByEmail(email).orElseThrow(
                    () -> new BuytopiaException(ErrorType.DATA_NOT_FOUND, logger::error)
            );
            if (!member.isActivated()) {
                member.activateAccount();
                memberRepository.save(member);
                return true;
            }
        }
        return false;
    }
}
