package testcases;

import utility.TestConfig;
import utility.monitoringMail;

import javax.mail.MessagingException;

public class TestMail {
    public static void main(String[] args) throws MessagingException {

        monitoringMail mail = new monitoringMail();
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,
                TestConfig.attachmentPath,TestConfig.attachmentName);
    }
}
