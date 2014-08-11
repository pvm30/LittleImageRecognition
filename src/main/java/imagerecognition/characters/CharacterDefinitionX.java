package imagerecognition.characters;

import java.awt.Rectangle;

public class CharacterDefinitionX extends CharacterDefinition {

    @Override
    public char getCharacter() {
        return 'X';
    }

    @Override
    protected int getNumberOfRectangles() {
        throw new RuntimeException("Not to be invoked");
    }

    @Override
    protected boolean verifyCoordinateRules(Rectangle[] rectangles) {
        throw new RuntimeException("Not to be invoked");
    }

}
