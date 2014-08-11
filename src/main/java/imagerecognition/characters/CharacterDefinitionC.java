package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionC extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'C';
    }

    @Override
    protected int getNumberOfRectangles() {
        return 3;
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        return rectangles[0].x == rectangles[1].x &&
               rectangles[1].x == rectangles[2].x && 
               rectangles[0].width == rectangles[2].width && 
               rectangles[1].width < rectangles[0].width;
    }

}
