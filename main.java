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

        String decodeImageFilename = "editedImage.png";
        String encodeImageFilename = "book.jpg";
        String encodeTextFilename = "inputText.txt";

        File decodeImageFile = new File(decodeImageFilename);
        File encodeImageFile = new File(encodeImageFilename);
        File inputText = new File(encodeTextFilename);

        System.out.println("Greetings human!(づ｡◕‿‿◕｡)づ");
        System.out.println("This program is capable of encoding and decoding messages in images");

        boolean looping = true;
        Scanner scan = new Scanner(System.in);
        while(looping)
        {
            
            System.out.println("I am currently looking at these files:\n");
            System.out.println("decode from: " + decodeImageFile);
            System.out.println("encode from: "  + inputText);
            System.out.println("encode to: " + encodeImageFile);
            System.out.println("\nType \"E\" to encode \nType \"D\" to decode\nType \"C\" to change files\nType \"Z\" to exit the program");

            String userInput = scan.next().toUpperCase();

            if(userInput.equals("E"))
            {
                System.out.println("༼ つ ̥◕͙_̙◕͖ ͓༽つ");
                e.encodeImage(inputText, encodeImageFile);
            }
            else if(userInput.equals("D"))
            {
                d.printHiddenMessage(decodeImageFile);
                System.out.println("\n(ﾉ◕｡ヮ◕｡)ﾉ*:･ﾟ✧");
            }
            else if(userInput.equals("C"))
            {
                System.out.println("(づ｡◕‿‿◕｡)つ\nWhich file would you like to change?\nType \"I\" to change the image you are encoding and decoding.\nType \"T\" to change the text that you are encoding into the image.");
                String userInput2 = scan.next().toUpperCase();
                if(userInput2.equals("I"))
                {
                    System.out.print("(づ｡◕‿‿◕｡)つ\nWhat is the name of the file that you would like to use instead?");

                    encodeImageFilename = scan.next();
                    encodeImageFile = new File(encodeImageFilename);
                }
                else if(userInput2.equals("T"))
                {
                    System.out.print("(づ｡◕‿‿◕｡)つ\nWhat is the name of the file that you would like to use instead?");

                    encodeTextFilename = scan.next();
                    inputText = new File(encodeTextFilename);
                }
                else
                {
                    System.out.println("\n(づ｡-_-｡⊂)\nC'mon Bruhh. That wasn\'t one of the options");
                }
            }
            else if(userInput.equals("Z"))
            {
                looping = false;
                System.out.println("(づ｡◕‿‿◕｡)つ\nBye!");
            }
            else
            {
                System.out.println("\n(づ｡-_-｡⊂)\nC'mon Bruhh. That wasn\'t one of the options");
            }
        }

    }
}