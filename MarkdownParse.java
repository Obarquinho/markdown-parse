import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int imagePointer = markdown.indexOf("!", currentIndex);
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            if (imagePointer == openBracket-1) {
                currentIndex++;
                continue;
            }
            
            if (markdown.charAt(closeBracket+1) != ('(')){
                currentIndex++;
                continue;
            }
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf (")", openParen);
            if (openParen == -1 || closeParen == -1) {
                break;
            }
            else {
                toReturn.add(markdown.substring(openParen+1, closeParen));
                currentIndex = closeParen + 1;
            }


            /*
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int openParen = markdown.indexOf("(", currentIndex);
            int closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

             */
        }
        return toReturn;
    }
}
