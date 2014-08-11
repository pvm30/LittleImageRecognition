package imagerecognition;

import imagerecognition.ImageRecognition.HorizontalSegment;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ImageRecognitionTest {

    ImageRecognition imageRecognition = new ImageRecognition();
    
    @Test
    public void testFileO() {
        List<Rectangle> rectangles = verifyRectangles(imageRecognition, "file_O.txt", 10, 4);
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 1, 7, 3)));
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 4, 2, 1)));
        Assert.assertTrue(rectangles.contains(new Rectangle(4, 4, 4, 1)));
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 5, 7, 5)));

        // This is the public method
        Assert.assertEquals('O', imageRecognition.getCharacter("file_O.txt"));
    }

    @Test
    public void testFileL() {
        List<Rectangle> rectangles = verifyRectangles(imageRecognition, "file_L.txt", 10, 2);
        Assert.assertEquals(2, rectangles.size());
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 0, 3, 9)));
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 9, 7, 1)));

        // This is the public method
        Assert.assertEquals('L', imageRecognition.getCharacter("file_L.txt"));
    }

    @Test
    public void testFileC() {
        List<Rectangle> rectangles =  verifyRectangles(imageRecognition,"file_C.txt", 7, 3);
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 1, 7, 2)));
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 3, 3, 4)));
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 7, 7, 1)));

        // This is the public method
        Assert.assertEquals('C', imageRecognition.getCharacter("file_C.txt"));
    }

    @Test
    public void testFileI() {
        List<Rectangle> rectangles =  verifyRectangles(imageRecognition,"file_I.txt", 3, 1);
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 2, 6, 3)));

        // This is the public method
        Assert.assertEquals('I', imageRecognition.getCharacter("file_I.txt"));
    }

    @Test
    public void testFileT() {
        List<Rectangle> rectangles =  verifyRectangles(imageRecognition,"file_T.txt", 7, 2);
        Assert.assertTrue(rectangles.contains(new Rectangle(2, 0, 8, 2)));
        Assert.assertTrue(rectangles.contains(new Rectangle(6, 2, 3, 5)));

        // This is the public method
        Assert.assertEquals('T', imageRecognition.getCharacter("file_T.txt"));
    }

    @Test
    public void testFileX() {
        List<Rectangle> rectangles =  verifyRectangles(imageRecognition,"file_X.txt", 3, 3);
        Assert.assertTrue(rectangles.contains(new Rectangle(5, 0, 3, 1)));
        Assert.assertTrue(rectangles.contains(new Rectangle(2, 5, 3, 1)));
        Assert.assertTrue(rectangles.contains(new Rectangle(2, 7, 3, 1)));

        // This is the public method
        Assert.assertEquals('X', imageRecognition.getCharacter("file_X.txt"));
    }

    @Test
    public void testFileX1() {
        List<Rectangle> rectangles =  verifyRectangles(imageRecognition,"file_X1.txt", 6, 2);
        Assert.assertTrue(rectangles.contains(new Rectangle(1, 1, 3, 3)));
        Assert.assertTrue(rectangles.contains(new Rectangle(5, 1, 3, 3)));

        // This is the public method
        Assert.assertEquals('X', imageRecognition.getCharacter("file_X1.txt"));
    }

    @Test
    public void testFileU() {
        // This is the public method
        Assert.assertEquals('U', imageRecognition.getCharacter("file_U.txt"));
    }

    private List<Rectangle> verifyRectangles(ImageRecognition imageRecognition, String filename, int nSegments, int nRectangles) {
        
        HorizontalSegment[] horizontalSegments = imageRecognition.getHorizontalSegments(filename);
        Assert.assertEquals(nSegments, horizontalSegments.length);

        Rectangle[] rectangles = imageRecognition.getAllRectangles(horizontalSegments);
        Assert.assertEquals(nRectangles, rectangles.length);

        Assert.assertTrue("There are overlapping rectangles...",
                notOverlappingRectangles(rectangles));
        
        return Arrays.asList(rectangles);
    }

    // Brute force I don't have time for anything else...
    private boolean notOverlappingRectangles(Rectangle[] rectangles) {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                if (!rectangles[i].intersection(rectangles[j]).isEmpty()) {
                    System.out.println(rectangles[i] + " overlaps "
                            + rectangles[j]);
                    return false;
                }
            }
        }

        return true;
    }
}
