package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionT extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'T';
    }

    @Override
    protected int getNumberOfRectangles() {
        return 2;
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        return rectangles[0].x < rectangles[1].x &&
               rectangles[0].x + rectangles[0].width  > rectangles[1].x + rectangles[1].width;
    }

}
