package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionO extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'O';
    }

    @Override
    protected int getNumberOfRectangles() {
        return 4;
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        return rectangles[0].x == rectangles[1].x &&
               rectangles[1].x == rectangles[3].x &&
               rectangles[0].x + rectangles[0].width == rectangles[2].x + rectangles[2].width &&
               rectangles[2].x + rectangles[2].width == rectangles[3].x + rectangles[3].width;
    }

}
