package imagerecognition.characters;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * A character is defined by a number of contiguous rectangles and a set of
 * rules between their coordinates
 * 
 */
public abstract class CharacterDefinition {

    CharacterDefinition() {
    };

    public abstract char getCharacter();

    protected abstract int getNumberOfRectangles();

    protected abstract boolean verifyCoordinateRules(Rectangle[] rectangles);

    private static final CharacterDefinition[] characterDefinitions = {
            new CharacterDefinitionC(), new CharacterDefinitionI(),
            new CharacterDefinitionL(), new CharacterDefinitionO(),
            new CharacterDefinitionT(), new CharacterDefinitionU() };

    private static final CharacterDefinition notRecognized = new CharacterDefinitionX();

    public static CharacterDefinition getDefinition(Rectangle[] rectangles) {
        if (rectangles != null && rectangles.length > 0)
            for (CharacterDefinition characterDefinition : characterDefinitions) {
                if (characterDefinition.valid(rectangles))
                    return characterDefinition;
            }

        return notRecognized;
    }

    private boolean valid(Rectangle[] rectangles) {

        // A) The number of rectangles must be the right one...
        if (rectangles.length != getNumberOfRectangles())
            return false;

        // B) The rectangles must be vertically contiguous (at least for the
        // examples in the pdf)
        if (!verticallyContiguous(rectangles))
            return false;

        // C) Verify coordinate rules...
        if (!verifyCoordinateRules(rectangles))
            return false;

        return true;
    }
    
    // Probably I would be better off forgetting altogether this maybe too
    // involved function and using instead just 'verifyCoordinateRules'...
    private boolean verticallyContiguous(Rectangle[] r) {
        // We can assume we are receiving the rectangles ordered by Y coordinate
        // from top to bottom
        if (r.length == 1)
            return true;

        Rectangle[] rsAbove = {};
        for (int i = 1; i < r.length; i++) {
            if (r[i].y > r[i - 1].y) 
                // The new rectangle in in a different 'y' coordinate
                // we have to recompute what is above it...
                rsAbove = computeRsAbove(r, i);
            
            for (Rectangle rAbove : rsAbove)
                if (r[i].y != rAbove.y + rAbove.height ||
                    r[i].x > rAbove.x + rAbove.width   ||
                    r[i].x + r[i].width < rAbove.x)
                    return false;
        }
        
        return true;
    }

    private Rectangle[] computeRsAbove(Rectangle[] r, int currentRectangleIndex) {
        List<Rectangle> rsAbove = new ArrayList<>();

        int aboveRow = -1;
        for (int i = currentRectangleIndex - 1; i >= 0; i--) {
            if (r[i].y < r[currentRectangleIndex].y) {
                if (aboveRow == -1) {
                    aboveRow = r[i].y;
                    rsAbove.add(r[i]);
                } else {
                    if (aboveRow == r[i].y)
                        rsAbove.add(r[i]);
                    else
                        break;
                }
            }
        }

        return rsAbove.toArray(new Rectangle[rsAbove.size()]);
    }
}
