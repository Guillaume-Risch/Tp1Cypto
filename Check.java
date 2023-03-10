import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Check {
    public static String getXAuth(String emailFile) throws IOException, Exception {
        // Read the contents of the email file
        byte[] contents = Files.readAllBytes(Paths.get(emailFile));

        // Search for the X-AUTH field in the email
        String xAuth = null;
        for (int i = 0; i < contents.length - 5; i++) {
            if (contents[i] == 'X' && contents[i+1] == '-' && contents[i+2] == 'A' && contents[i+3] == 'U' && contents[i+4] == 'T' && contents[i+5] == 'H') {
                // X-AUTH field found, extract the value
                int start = i + 7;  // skip the "X-AUTH: " prefix
                int end = start;
                while (contents[end] != '\n') {
                    end++;
                }
                byte[] xAuthBytes = Arrays.copyOfRange(contents, start, end);
                xAuth = new String(xAuthBytes, StandardCharsets.UTF_8);
                break;
            }
        }

        // If the X-AUTH field was not found, return null
        if (xAuth == null) {
            return null;
        }

        // Calculate the expected X-AUTH field value
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(contents);
        String expectedXAuth = Base64.getEncoder().encodeToString(hash);

        // Compare the extracted X-AUTH field value with the expected value
        if (xAuth.equals(expectedXAuth)) {
            // X-AUTH field is valid, return it
            return xAuth;
        } else {
            // X-AUTH field is invalid, return null
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String emailFile = "C:\\Users\\guill\\Desktop\\Tp1Crypto\\src\\assets\\email1.txt";
        String xAuth = getXAuth(emailFile);
        if (xAuth == null) {
            System.out.println("Invalid X-AUTH field!");
        } else {
            System.out.println("X-AUTH field is valid.");
        }
    }
}
