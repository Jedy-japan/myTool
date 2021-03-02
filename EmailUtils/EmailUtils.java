import com.wisdomkey.office.bean.example.EmailExample;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils111 {

    /**
     * メールを送信する
     *
     * @param emailExample    メールオブジェクト
     * @return message メール内容
     * @throws Exception
     */
    public void sendMailTest(EmailExample emailExample) throws Exception {
        // 1. メールサーバを接続するためのパラメータ構成を作成する
        Properties props = new Properties();                    // パラメータ構成
        props.setProperty("mail.transport.protocol", "smtp");   // 使用するプロトコル（JavaMail仕様）
        props.setProperty("mail.smtp.host", emailExample.getMyEmailSMTPHost());   // 送信者のSMTPサーバアドレス
        props.setProperty("mail.smtp.auth", "true");            // 認証をもとめる必要がある

        //メールサーバによってSMTP接続にSSLセキュリティ認証が必要となる(セキュリティを高めるため、SSL接続を対応し、自分で開くことでいい)
        //メールサーバに接続できない場合、コンソールに印刷されたログをみて、[接続に失敗いました、SSLで接続してください]などのエラーがある
        //下記の注釈を取消し、SSL接続を開く

        //SMTPサーバのポート(SSL接続以外のポートはデフォルトで25になっており、追加しなくてもいい、SSLを開いたら、
        //                  変更する必要があるSMTPサーバのポート、詳しくはメールサービスのヘルプをご覧ください。)


        //SSLセキュリティ認証noの場合
        if (emailExample.isSSL()) {
            final String smtpPort = emailExample.getSmtpPort();
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        }


        // 2. 構成に応じてメールサーバと対話するためにセッションオブジェクトを作成する
        Session session = Session.getInstance(props);

        //debugモードにして、詳しい送信ログをみることができます、
        session.setDebug(true);

        // 3. メールを作成
        MimeMessage message = createMimeMessage(session, emailExample);

        // 4. セッションに応じて、送信オブジェクトを取得する
        Transport transport = session.getTransport();

        // 5. メールアカウントとパスワードでメールサーバを接続、ここに認証したメールはメッセージの中の送信者と一致しなければならない
        //
        //    PS_01: サーバに接続が失敗した場合、コンソールで失敗原因のログを出力する、
        //    よく失敗原因をみて、メールサーバの中には、エラーコードを返信したり、エラーの種類のリンクを見たりする
        //    返信したエラーの種類によってメールサービスのヘルプサイトで具体的なエラー原因を確認する
        //
        //    PS_02: 接続失敗な原因は通常以下の通りものであり、よくソースを確認:
        //           (1) メールはSMTPサービスを開かない
        //           (2) メールのパスワードが間違い、例えばあるメールは独自パスワードが開いている
        //           (3) メールサーバは必ずSSLでセキュリティ接続を使用する要求がある
        //           (4) リクエストを多すぎ、メールサーバに断られた
        //           (5) 上記の点を確認完了、メールサービスサイトへヘルプを探す

        transport.connect(emailExample.getMyEmailAccount(), emailExample.getMyEmailPassword());

        // 6. メールを送り、全ての宛先に届く、message.getAllRecipients()　取得されるのは、オブジェクトの作成時に追加されたすべての受信者、cc、bccです
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 接続を閉じる
        transport.close();
    }

    /**
     * メール内容を作成する
     *
     * @param session     サーバセッション
     * @param emailExample    メールオブジェクト
     * @return message メール内容
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, EmailExample emailExample) throws Exception {

        // 1. メールを作成する
        MimeMessage message = new MimeMessage(session);

        // 2. From: 送信者
        message.setFrom(new InternetAddress(emailExample.getMyEmailAccount(), emailExample.getFromName(), "UTF-8"));

        // 3. To: 宛先（宛先、cc、bccを複数追加可）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailExample.getReceiveMailAccount(), emailExample.getRecipientName(), "UTF-8"));

        // 4. Subject: 件名
        message.setSubject(emailExample.getMailTitle(), "UTF-8");

        // 5. Content: メール内容（htmlタグを使える）
        message.setContent(emailExample.getMailContent(), "text/html;charset=UTF-8");

        // 6. 送信時間を設定する
        message.setSentDate(emailExample.getShowSendDateTime());

        // 7. 設定を保存する
        message.saveChanges();

        return message;
    }

}
