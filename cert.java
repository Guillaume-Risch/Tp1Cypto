import java.io.*;
import java.security.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.Queue;

public class cert {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        File file = new File("C:\\Users\\guill\\Desktop\\Tp1Crypto\\src\\assets\\email1.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Queue<String> Tail = new LinkedList<>();

        String line = reader.readLine();
        while (line != null) {
            Tail.add(line);
            if (Tail.size() > 4) {
                Tail.poll();
            }
            line = reader.readLine();
            System.out.println(line);
        }
        reader.close();

        StringBuilder sb = new StringBuilder();
        String TailString = sb.toString();


        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] hash = md5.digest(TailString.getBytes());

        for (byte b : hash) {
            System.out.printf("%02x", b);

        }
    }

}
