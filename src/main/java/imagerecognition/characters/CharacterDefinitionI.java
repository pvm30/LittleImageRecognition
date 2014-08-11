package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionI extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'I';
    }

    @Override
    protected int getNumberOfRectangles() {
        return 1;
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        return true;
    }

}
