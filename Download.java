import java.io.*;
import java.net.URL;

public class Download {
    public static void main(String[] args) {
        try {
            String fileName = "downloaded_image.png"; // Output file name
            String website = "https://picsum.photos/200"; // Valid image URL
            System.out.println("Downloading File From: " + website);

            URL url = new URL(website);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fileName);

            byte[] buffer = new byte[2048];
            int length;
            boolean firstRead = true;
            int lastRead = 0;

            while ((length = inputStream.read(buffer)) != -1) {
                lastRead = length; // Store the last read length
                outputStream.write(buffer, 0, length);
                
                if (firstRead) {
                    System.out.println("First Buffer Read length: " + length);
                    firstRead = false; // Change to false after the first read
                }
            }

            // Print the last read length
            System.out.println("Last Buffer Read length: " + lastRead);

            inputStream.close();
            outputStream.close();
            System.out.println("File downloaded successfully: " + fileName);
        }catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
