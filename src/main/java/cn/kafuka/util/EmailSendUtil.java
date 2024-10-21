package cn.kafuka.util;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;

import javax.mail.Message.RecipientType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSendUtil {

    //邮件正则表达式
    private static final Pattern emailPattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
	
	//1.发送邮件
	public static Boolean sendEamil(String title,String from,String receiverList,String subject,String text) {
		
		//(1).设置邮件
		Email email = new Email();
		email.setFromAddress(title, from);
		email.addRecipients(receiverList, RecipientType.BCC);
		email.setSubject(subject);
		email.setText(text);

		//(2).设置邮件发送配置
		Mailer inhouseMailer = new Mailer("smtp.qq.com", 25, "630315438@qq.com", "tryethrsoktrbbag");
		inhouseMailer.sendMail(email);
		System.out.println("<-----邮件发送成功----->");
		return true;
	}

	//1.发送邮件
	public static Boolean sendEamil(String title,String subject,String text,String from,String receiverList,String fileName,byte[] data) {

		//(1).设置邮件
		Email email = new Email();
		email.setFromAddress(title, from);
		email.addRecipients(receiverList, RecipientType.BCC);
		email.setSubject(subject);
		email.setText(text);

		email.addAttachment("file.xlsx",data,"application/vnd.ms-excel");


		//(2).设置邮件发送配置
		Mailer inhouseMailer = new Mailer("smtp.qq.com", 25, "630315438@qq.com", "tryethrsoktrbbag");
		inhouseMailer.sendMail(email);
		//System.out.println("发送成功");
		return true;
	}

    public static boolean checkEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

/*    public static void main(String[] args) {

	    String emaila = "630315438@qq.com";
	    String emailb = "125562@163.COM";
	    String emailc = "aWFJKLHKEGKL";
	    String emaild = "@aWFJKLHKEGKL";

        System.out.println("A:"+checkEmail(emaila));;
        System.out.println("B:"+checkEmail(emailb));;
        System.out.println("C:"+checkEmail(emailc));;
        System.out.println("D:"+checkEmail(emaild));;
    }*/
}
