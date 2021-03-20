import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.awt.image.*;
import java.awt.Color;
import javax.imageio.*;
import java.util.Scanner;
/**
 * Write a description of class Encoder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Encoder
{
    public static void encodeImage(File inputText, File imageFile)throws IOException
    {
        ArrayList messageBinary = getBitArrayFromFile(inputText);
        String[] imageBinary = getBinaryArrayFromPicture(imageFile);
        String[] image = insertMessage(messageBinary, imageBinary);
        outputImage(image, imageFile);
    }

    public static void encodeImage( File imageFile)throws IOException
    {
        ArrayList messageBinary = getBitArrayFromUser();
        String[] imageBinary = getBinaryArrayFromPicture(imageFile);
        String[] image = insertMessage(messageBinary, imageBinary);
        outputImage(image, imageFile);
    }

    /**
     * Method getBinaryInputFromFile
     *This method takes in a .txt file, and coverts it to an 
     *ArrayList of bits. Each slot in the array has 8 bits or 1 character
     */
    public static ArrayList getBitArrayFromFile(File file)throws IOException
    {
        System.out.println("Prepairing message to be encoded into image...");

        String textFileContentsString = new String(Files.readAllBytes(file.toPath()));
        ArrayList<String> output = new ArrayList<String>();
        for(int i = 0; i<textFileContentsString.length();i++)
        {
            String nextCharacter = Integer.toBinaryString(textFileContentsString.charAt(i));
            if(!nextCharacter.equals("1111111111111101"))
            {
                while(nextCharacter.length()<8)
                {
                    nextCharacter = '0' + nextCharacter;
                }
                output.add(nextCharacter); 
            }
        }
        System.out.println("done");
        return output;
    }

    /**
     * Method getBinaryInputFromFile
     *This method takes in a String from the user, and coverts it to an 
     *ArrayList of bits. Each slot in the array has 8 bits or 1 character
     */
    public static ArrayList getBitArrayFromUser()
    {
        System.out.println("Prepairing message to be encoded into image...");

        System.out.println("please enter your message here: ");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.next();

        ArrayList<String> output = new ArrayList<String>();
        for(int i = 0; i<userInput.length();i++)
        {
            String nextCharacter = Integer.toBinaryString(userInput.charAt(i));
            if(!nextCharacter.equals("1111111111111101"))
            {
                while(nextCharacter.length()<8)
                {
                    nextCharacter = '0' + nextCharacter;
                }
                output.add(nextCharacter); 
            }
        }
        System.out.println("done");
        return output;
    }

    /**
     * Method getBinaryArrayFromPicture
     *This method takes in an image file, and converts it to a 
     *2 dimentional array of binary strings
     */
    public static String[] getBinaryArrayFromPicture(File fileName)throws IOException
    {
        System.out.print("converting image to String array...");

        BufferedImage image  = ImageIO.read(fileName);
        int height = image.getHeight();
        int width = image.getWidth();
        int size = height*width;
        String[]output = new String[size*4];
        for(int i = 0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                Color color = new Color(image.getRGB(j,i));
                output[((i*width)+j)*4+0] = Integer.toBinaryString(color.getAlpha());
                output[((i*width)+j)*4+1] = Integer.toBinaryString(color.getRed());
                output[((i*width)+j)*4+2] = Integer.toBinaryString(color.getGreen());
                output[((i*width)+j)*4+3] = Integer.toBinaryString(color.getBlue());
                for (int k = 0; k<4;k++)
                {
                    while(output[((i*width)+j)*4+k].length()<8)
                    {
                        output[((i*width)+j)*4+k] = "0" + output[((i*width)+j)*4+k];
                    }
                }
            }
        }
        System.out.println("done");
        return output;
    }

    /**
     * Method insertMessage
     *
     * @param textToBeInserted A parameter
     * @param imageToBeEdited A parameter
     * @return The return value
     */
    public static String[] insertMessage(ArrayList<String> textToBeInserted, String[] imageToBeEdited)
    {
        int textLength = (textToBeInserted.size()-1) * 8;
        int storageSize = imageToBeEdited.length/4;

        System.out.println("This image can support " + storageSize + " characters");
        if(storageSize>textLength/8)
        {
            System.out.print("Inserting message...");
            int index = 0;
            for(int i=0; i < textToBeInserted.size();i++)
            {//replace the two least significant bits with two bits of a letter
                String bit1 = Character.toString(textToBeInserted.get(i).charAt(0));
                String bit2 = Character.toString(textToBeInserted.get(i).charAt(1));
                String bit3 = Character.toString(textToBeInserted.get(i).charAt(2));
                String bit4 = Character.toString(textToBeInserted.get(i).charAt(3));
                String bit5 = Character.toString(textToBeInserted.get(i).charAt(4));
                String bit6 = Character.toString(textToBeInserted.get(i).charAt(5));
                String bit7 = Character.toString(textToBeInserted.get(i).charAt(6));
                String bit8 = Character.toString(textToBeInserted.get(i).charAt(7));

                imageToBeEdited[(index)+0] = imageToBeEdited[(index)+0].substring(0,6) + bit1 + bit2;
                imageToBeEdited[(index)+1] = imageToBeEdited[(index)+1].substring(0,6) + bit3 + bit4;
                imageToBeEdited[(index)+2] = imageToBeEdited[(index)+2].substring(0,6) + bit5 + bit6;
                imageToBeEdited[(index)+3] = imageToBeEdited[(index)+3].substring(0,6) + bit7 + bit8;
                index+=4;
            }
            for(int j = index;j<storageSize*4;j+=4)//Fill in any unused bits with zeros
            {
                imageToBeEdited[(j)+0] = imageToBeEdited[(j)+0].substring(0,6) + "00";
                imageToBeEdited[(j)+1] = imageToBeEdited[(j)+1].substring(0,6) + "00";
                imageToBeEdited[(j)+2] = imageToBeEdited[(j)+2].substring(0,6) + "00";
                imageToBeEdited[(j)+3] = imageToBeEdited[(j)+3].substring(0,6) + "00";
            }
            System.out.println("done");
            return imageToBeEdited;
        }
        else
        {
            System.out.println("Failed to insert text into image.\nImage is not large enough to contain this much text");
            return null;
        }
    }

    /**
     * Method outputImage
     *
     */
    public static void outputImage(String[] input, File originalImage)throws IOException//working
    {
        BufferedImage image  = ImageIO.read(originalImage);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                int alpha = Integer.parseInt(input[(i*width*4)+(j*4)],2);
                int red = Integer.parseInt(input[(i*width*4)+(j*4)+1],2);
                int green = Integer.parseInt(input[(i*width*4)+(j*4)+2],2);
                int blue = Integer.parseInt(input[(i*width*4)+(j*4)+3],2);

                Color color = new Color(red,green,blue,alpha);
                output.setRGB(j, i, color.getRGB());
            } 
        }

        File outputfile = new File("editedImage.png");
        ImageIO.write(output, "png", outputfile);
    }

}
