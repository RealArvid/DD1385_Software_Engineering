import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TextClient{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try{
            Socket socket = new Socket("localhost",4713);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            out.println(name);
            out.flush();
            System.out.println(in.readLine()); // Retrieves "Hello, "+name
            while(true){
                System.out.print("Enter input: ");
                String input = scanner.nextLine();
                if(input.equals("")){
                    out.println("");
                    out.flush();
                    break;
                }
                out.println(input);
                out.flush();
                System.out.println(in.readLine());
            }
        }
		catch(IOException ioe){
			System.out.println(ioe);
		}
        scanner.close();
    }
}