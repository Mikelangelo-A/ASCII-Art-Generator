import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class ArtGenerator {
    public static void main(String[] args)throws Exception {
        boolean averageBright = false;
        boolean luminosity = false;
        Scanner scanner = new Scanner(System.in);
        while(!averageBright && !luminosity) {
            System.out.println("Average or Luminosity brightness mapping? Type 1 for average, 2 for luminosity");
            int number = scanner.nextInt();
            if (number == 1) {
                averageBright = true;
            } else if (number == 2) {
                luminosity = true;
            } else {
                System.out.println("No correct input detected, try again.");
            }
        }
        File image = new File("C:\\Users\\aauza\\Desktop\\Java\\IdeaProjects\\Tutorials\\ASCII-ArtGenerator\\NewTestImage.jpg");
        BufferedImage img = ImageIO.read(image);
        if(img == null){
            throw new NullPointerException("Something went wrong with loading image.");
        }
        System.out.println("Successfully loaded image!");
        int width = img.getWidth();
        int height = img.getHeight();
        System.out.println("Image is " + width + "x" + height);

        RGBPixel[][] pixelsAsRGB = new RGBPixel[height][width];
//      Create a 2D array of pixels, that holds the rgb values for each pixel.
        pixelsAsRGB = addPixelsToArray(width,height,pixelsAsRGB, img);

//      Create new array which contains the brightness of each pixel
//      I can achieve this by calculating the average of RGB values of each pixel.
        int[][] brightnessPixels = new int[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int red = pixelsAsRGB[y][x].getRed();
                int green = pixelsAsRGB[y][x].getGreen();
                int blue = pixelsAsRGB[y][x].getBlue();
                if(averageBright) {
                    int average = Math.round(red + green + blue) / 3;
                    brightnessPixels[y][x] = average;
                } else if(luminosity){
                    int pixel = (int) Math.round(0.21 * red + 0.72 * green + 0.07 * blue);
                    brightnessPixels[y][x] = pixel;

                }
            }
        }

//      Time to convert the brightness matrix to an ASCII character matrix.
//      Using the string below :
//      "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
//      I must map the corresponding character to that brightness amount.
        String charMatrix = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        int length = charMatrix.length();
        double range = 255 / (double) length;
        char[][] finalPixels = new char[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                double result = (brightnessPixels[y][x] / range);
                int index = (int) Math.floor(result) - 1;
                if(index < 0){
                    finalPixels[y][x] = charMatrix.charAt(0);
                    continue;
                }
                finalPixels[y][x] = charMatrix.charAt(index);
            }
        }

//      Time to print the image. This will be done by using a nested loop, which adds a new-line
//      whenever the end of a row has been reached.
//        File file = new File("ResultASCII.txt");
//        PrintWriter writer = new PrintWriter("ResultASCII.txt", "UTF-16");
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
//                writer.print(finalPixels[y][x]);
//                writer.print(finalPixels[y][x]);
//                writer.print(finalPixels[y][x]);
                System.out.print(finalPixels[y][x]);
//                System.out.print(finalPixels[y][x]);
//                System.out.print(finalPixels[y][x]);
            }

//            writer.println();
            System.out.println();
        }
    }
    public static RGBPixel[][] addPixelsToArray(int width, int height, RGBPixel[][] array, BufferedImage img){
//      Go through all the pixels, reading and printing all the tuples
//      that represent the RGB values in each pixel.
//      With each pixel, add it into a 2D matrix.
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int pixelColor = img.getRGB(x,y);
                int blue = pixelColor & 0xff;
                int green = (pixelColor & 0xff00) >> 8;
                int red = (pixelColor & 0xff0000) >> 16;
                RGBPixel pixel = new RGBPixel(red, green, blue);
//                System.out.println("Returned pixel : {" + pixel.getRed() + "," + pixel.getGreen() + "," + pixel.getBlue() + "}");
                array[y][x] = pixel;
            }
        }
        return array;
    }
    }