public class MailService {
    private static MailService mailService = null;

    private MailService() {
    }

    static MailService getInstance(){
        if(mailService == null) {
            mailService = new MailService();
        }
        return mailService;
    }

    public void sendEBook(Product product, String email){
        System.out.println("EBook in is sent to: " + email);
    }
}
