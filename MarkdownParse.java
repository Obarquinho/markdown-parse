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
        char exclamationPoint = '!';
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf (")", openParen);
            if (openParen == -1 || closeParen == -1 || openBracket == -1 || closeBracket == -1) {
                break;
            }

            else if (openBracket > 0 && markdown.charAt(openBracket-1) == exclamationPoint) {
                currentIndex = closeParen + 1;
            }
    
            else if (closeBracket+1 == openParen) {
                toReturn.add(markdown.substring(openParen+1, closeParen));
                currentIndex = closeParen + 1;
            }

            else {
                currentIndex = closeParen +1;
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
