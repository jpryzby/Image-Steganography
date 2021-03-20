import java.io.*;
import java.util.Scanner;
public class main
{
    public static void main(String[]args)throws IOException
    {
        inputDriver();
    }

    public static void inputDriver()throws IOException
    {
        Encoder e = new Encoder();
        Decoder d = new Decoder();
        File editedImageFile = new File("editedImage.png");
        File imageFile = new File("book.jpg");
        //File imageFileNameSmol = new File("smallPic.jpg");
        File inputText = new File("inputText.txt");
        
        System.out.println("Greetings human!(づ｡◕‿‿◕｡)づ");
        System.out.println("This program is capable of encoding and decoding messages in images");

        boolean looping = true;
        Scanner scan = new Scanner(System.in);
        while(looping)
        {
            System.out.println("Type \"E\" to encode \ntype \"D\" to decode\ntype \"Z\" to exit the program");

            String userInput = scan.next().toUpperCase();

            if(userInput.equals("E"))
            {
                System.out.println("༼ つ ̥◕͙_̙◕͖ ͓༽つ");
                e.encodeImage(inputText, imageFile);
            }
            else if(userInput.equals("D"))
            {
                d.printHiddenMessage(editedImageFile);
                System.out.println("\n(ﾉ◕｡ヮ◕｡)ﾉ*:･ﾟ✧");
            }
            else if(userInput.equals("Z"))
            {
                looping = false;
                System.out.println("(づ｡◕‿‿◕｡)つ\nBaiiii!");
            }
            else
            {
                System.out.println("\n(づ｡-_-｡⊂)\nC'mon Bruhh.");
            }
        }

        
    }
}