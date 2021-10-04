package mail_system;

public class MailInfo {
    private String fromUser;
    private String toUser;
    private String mailSubject;
    private String content;
    private int serialNo;

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", content='" + content + '\'';
    }
}
