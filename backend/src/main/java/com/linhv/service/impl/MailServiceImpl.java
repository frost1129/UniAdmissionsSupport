/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.service.impl;

import com.linhv.pojo.User;
import com.linhv.service.MailService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author prodi
 */
@Service
public class MailServiceImpl implements MailService{
    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendNewQuestionMail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            String subject = "Thông báo về câu hỏi tuyển sinh mới";
            String content = "<p>Kính chào tư vấn viên " + user.getFirstName() + ",</p>"
                    + "<p>Có người quan tâm vừa gửi câu hỏi mới về hệ đào tạo " + user.getUserAdmissionType().getName() + ".</p>"
                    + "<p>Hãy ghé qua và trả lời những câu hỏi ấy sớm nhất có thể nhé!</p>"
                    + "<br><p>Đội ngũ Tuyển sinh Trường Đại học Mở TP.HCM</p>";
            
            helper.setFrom("ouadmisison@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);
            
            this.mailSender.send(message);
        }
        catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendQuestionAnswered(User user)  {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            String subject = "Câu hỏi của bạn đã được giải đáp";
            String content = "<p>" + user.getFirstName() + " ơi,</p>"
                    + "<p>Câu hỏi của bạn về Trường Đại học Mở TP.HCM đã được giải đáp rồi.</p>"
                    + "<p>Hãy đăng nhập vào trang web tuyển sinh của trường để xem được câu trả lời nhé!</p>"
                    + "<br><p>Đội ngũ Tuyển sinh Trường Đại học Mở TP.HCM</p>";
            
            helper.setFrom("ouadmisison@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);
            
            this.mailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
