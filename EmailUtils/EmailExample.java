import java.util.Date;

public class EmailExample {

    //送信者のメールアドレス
    private String myEmailAccount;

    //送信者のパスワード
    private String myEmailPassword;

    //送信者メールのSMTPサーバアドレス、正確しなければいけない。例：smtp.xxx.com
    private String myEmailSMTPHost;

    //SMTPサーバのポート
    private String smtpPort;

    //SSL接続か否か
    private boolean isSSL;

    //宛先メールアドレス
    private String receiveMailAccount;

    //送信者名称
    private String fromName;

    //宛先名称
    private String recipientName;

    //件名
    private String mailTitle;

    //メール内容
    private String mailContent;

    //表示の送信時間
    private Date showSendDateTime;
    
    public String getMyEmailAccount() {
        return myEmailAccount;
    }

    public void setMyEmailAccount(String myEmailAccount) {
        this.myEmailAccount = myEmailAccount;
    }

    public String getMyEmailPassword() {
        return myEmailPassword;
    }

    public void setMyEmailPassword(String myEmailPassword) {
        this.myEmailPassword = myEmailPassword;
    }

    public String getMyEmailSMTPHost() {
        return myEmailSMTPHost;
    }

    public void setMyEmailSMTPHost(String myEmailSMTPHost) {
        this.myEmailSMTPHost = myEmailSMTPHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isSSL() {
        return isSSL;
    }

    public void setSSL(boolean SSL) {
        isSSL = SSL;
    }

    public String getReceiveMailAccount() {
        return receiveMailAccount;
    }

    public void setReceiveMailAccount(String receiveMailAccount) {
        this.receiveMailAccount = receiveMailAccount;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Date getShowSendDateTime() {
        return showSendDateTime;
    }

    public void setShowSendDateTime(Date showSendDateTime) {
        this.showSendDateTime = showSendDateTime;
    }

    @Override
    public String toString() {
        return "EmailExample{" +
                "myEmailAccount='" + myEmailAccount + '\'' +
                ", myEmailPassword='" + myEmailPassword + '\'' +
                ", myEmailSMTPHost='" + myEmailSMTPHost + '\'' +
                ", smtpPort='" + smtpPort + '\'' +
                ", isSSL=" + isSSL +
                ", receiveMailAccount='" + receiveMailAccount + '\'' +
                ", fromName='" + fromName + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", mailTitle='" + mailTitle + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", showSendDateTime=" + showSendDateTime +
                '}';
    }
}
