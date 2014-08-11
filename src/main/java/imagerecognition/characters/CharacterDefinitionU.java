package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionU extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'U';
    }

    @Override
    protected int getNumberOfRectangles() {
        return 3;
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        return rectangles[0].y == rectangles[1].y && 
               rectangles[2].x == rectangles[0].x &&
               rectangles[2].x + rectangles[2].width == rectangles[1].x + rectangles[1].width;
    }

}
