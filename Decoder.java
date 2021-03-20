import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import javax.imageio.*;
/**
 * Write a description of class EncoderDecoder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Decoder
{
    /**
     * Method printHiddenMessage
     *
     * This method takes in as file pointer to an image, and reads pulls out a 
     * steganographic message
     */
    public static void printHiddenMessage(File editedImage)throws IOException
    {
        BufferedImage edited  = ImageIO.read(editedImage);

        int height = edited.getHeight();
        int width = edited.getWidth();
        String outputBinary;
        char outputChar;
        int count = 0;

        System.out.println("");
        for(int i = 0; i < height; i++)
        {
            for(int j = 0 ;j<width; j++)
            {
                Color color = new Color(edited.getRGB(j,i),true);
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                String bit1And2 = Integer.toBinaryString(alpha);
                String bit3And4 = Integer.toBinaryString(red);
                String bit5And6 = Integer.toBinaryString(green);
                String bit7And8 = Integer.toBinaryString(blue);

                if(bit1And2.length() == 1)
                {
                    bit1And2 = "0" + bit1And2;
                }
                if(bit3And4.length() == 1)
                {
                    bit3And4 = "0" + bit3And4;
                }
                if(bit5And6.length() == 1)
                {
                    bit5And6 = "0" + bit5And6;
                }
                if(bit7And8.length() == 1)
                {
                    bit7And8 = "0" + bit7And8;
                }

                String bit1 = Character.toString(bit1And2.charAt(bit1And2.length()-2));
                String bit2 = Character.toString(bit1And2.charAt(bit1And2.length()-1));
                String bit3 = Character.toString(bit3And4.charAt(bit3And4.length()-2));
                String bit4 = Character.toString(bit3And4.charAt(bit3And4.length()-1));
                String bit5 = Character.toString(bit5And6.charAt(bit5And6.length()-2));
                String bit6 = Character.toString(bit5And6.charAt(bit5And6.length()-1));
                String bit7 = Character.toString(bit7And8.charAt(bit7And8.length()-2));
                String bit8 = Character.toString(bit7And8.charAt(bit7And8.length()-1));

                outputBinary = bit1+bit2+bit3+bit4+bit5+bit6+bit7+bit8;
                outputChar = (char)Integer.parseInt(outputBinary,2);

                if(outputBinary.equals("00000000"))
                {
                    i = height;
                    j = width;
                }
                else
                {
                    System.out.print(outputChar);
                }
                count++;
            }
        }
    }
}
