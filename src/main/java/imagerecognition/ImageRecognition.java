package imagerecognition;

import imagerecognition.characters.CharacterDefinition;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ImageRecognition {
    
    private static final char FILLED = '1';
    // From the test cases shown in the pdf file I can infer this value...
    private static final int MIN_RECTANGLE_WIDTH = 2;

    public char getCharacter(String filename) {
        Rectangle[] rectangles = getAllRectangles(getHorizontalSegments(filename));
        return CharacterDefinition.getDefinition(rectangles).getCharacter();
    }

    HorizontalSegment[] getHorizontalSegments(String filename) {
        Scanner scanner = new Scanner(getClass().getResourceAsStream(filename));

        List<HorizontalSegment> horizontalSegments = new ArrayList<>();
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            horizontalSegments.addAll(getHorizontalSegments(i, line));
            i++;
        }
        scanner.close();

        return horizontalSegments.toArray(new HorizontalSegment[horizontalSegments.size()]);
    }

    private Collection<HorizontalSegment> getHorizontalSegments(int i, String line) {

        List<HorizontalSegment> horizontalSegments = new ArrayList<>();
        int left = -1, length = -1;

        for (int j = 0; j < line.length(); j++) {
            if (line.charAt(j) == FILLED) {
                if (left == -1) {
                    // new possible segment
                    left = j;
                    length = 1;
                } else {
                    // increase length existing segment
                    length++;
                    // If we are about to exit the loop...
                    if (j == line.length() - 1)
                        horizontalSegments.add(new HorizontalSegment(new Point(left, i), length));
                }
            } else if (left != -1) {
                // verify new segment creation
                if (length >= MIN_RECTANGLE_WIDTH) 
                    horizontalSegments.add(new HorizontalSegment(new Point(left, i), length));
                
                left = -1;
            }
        }

        return horizontalSegments;
    }

    //  Grouping vertically contiguous h-segments to build rectangles
    Rectangle[] getAllRectangles(HorizontalSegment[] horizontalSegments) {
        List<Rectangle> rectangles = new ArrayList<>();
        // Sorting by length, x coordinate and y
        Arrays.sort(horizontalSegments, new HorizontalSegmentComparator());

        // Every horizontal segment is a rectangle on his own...
        HorizontalSegment newRectangle = null;
        int height = -1;
        for (int i = 0; i < horizontalSegments.length; i++) {
            HorizontalSegment hs = horizontalSegments[i];
            if (newRectangle == null) {
                newRectangle = hs;
                height = 1;
            } else if (hs.length == newRectangle.length && 
                       hs.leftPoint.x == newRectangle.leftPoint.x && 
                       hs.leftPoint.y == horizontalSegments[i - 1].leftPoint.y + 1)
                height++;
            else {
                rectangles.add(new Rectangle(newRectangle.leftPoint.x,
                        newRectangle.leftPoint.y, newRectangle.length, height));
                newRectangle = hs;
                height = 1;
            }
            // If we are about to exit the loop...
            if (i == horizontalSegments.length - 1) 
                rectangles.add(new Rectangle(newRectangle.leftPoint.x,
                        newRectangle.leftPoint.y, newRectangle.length, height));
        }

        Collections.sort(rectangles, new RectangleComparator());
        return rectangles.toArray(new Rectangle[rectangles.size()]);
    }

    // I think I can use Rectangle and Point from java.awt package without
    // having any problem
    // (This classes, like all of the Java 2D API, uses a default coordinate
    // system called user space in which the y-axis values increase downward and
    // x-axis values increase to the right.)

    // But I'm going to create my own HorizontalSegment class
    static class HorizontalSegment {
    
        public final Point leftPoint;
        public final int length;

        public HorizontalSegment(Point leftPoint, int length) {
            this.leftPoint = leftPoint;
            this.length = length;
        }

        @Override
        public String toString() {
            return "(" + leftPoint.x + "," + leftPoint.y + ") length=" + length;
        }
    }

    private static class HorizontalSegmentComparator implements
            Comparator<HorizontalSegment> {

        @Override
        public int compare(HorizontalSegment hs1, HorizontalSegment hs2) {
            if (hs1.length < hs2.length)
                return 1;
            if (hs1.length > hs2.length)
                return -1;

            if (hs1.leftPoint.x < hs2.leftPoint.x)
                return -1;
            if (hs1.leftPoint.x > hs2.leftPoint.x)
                return 1;

            return hs1.leftPoint.y - hs2.leftPoint.y;
        }
    }

    private static class RectangleComparator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle r1, Rectangle r2) {
            if (r1.y < r2.y)
                return -1;
            if (r1.y > r2.y)
                return 1;

            return r1.x - r2.x;
        }
    }
}
