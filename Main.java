import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;

public class Main {
    public static String getXAuth(String emailFile) throws IOException, Exception {
        // Read the contents of the email file
        byte[] contents = Files.readAllBytes(Paths.get(emailFile));

        // Calculate the MD5 hash of the email contents
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(contents);

        // Calculate the X-AUTH field by base64 encoding the hash value
        String xAuth = Base64.getEncoder().encodeToString(hash);

        // Return the X-AUTH field
        return xAuth;
    }
    
        public static void main(String[] args) throws Exception {
        String emailFile = "C:\\Users\\guill\\Desktop\\Tp1Crypto\\src\\assets\\email1.txt";
        String xAuth = getXAuth(emailFile);
        System.out.println(xAuth);
    }
}

