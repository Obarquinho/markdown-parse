// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MarkdownParse_copy {
    ArrayList<Integer> openBracketIndexs = new ArrayList<>();
    int currentIndex;

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<Integer> openBracketIndexList = new ArrayList<>();
        ArrayList<Integer> closeBracketIndexList = new ArrayList<>();
        int openBracketIndex = 0;
        int closeBracketIndex =0;
        int nextBracketIndex;
        while(openBracketIndex < markdown.length()) {
            openBracketIndex = markdown.indexOf("[", openBracketIndex);
            openBracketIndexList.add(openBracketIndex);
            openBracketIndex = markdown.indexOf("[", openBracketIndex+1);
            if (openBracketIndex == -1) {
                break;
            }
        }

        while(closeBracketIndex < markdown.length()) {
            closeBracketIndex = markdown.indexOf("]", closeBracketIndex);
            closeBracketIndexList.add(closeBracketIndex);
            closeBracketIndex = markdown.indexOf("]", closeBracketIndex+1);
            if (closeBracketIndex == -1) {
                break;
            }
        }

        ArrayList<int[]> validBracketPairs = new ArrayList<>();
        if (openBracketIndexList.size() < closeBracketIndexList.size()) {
            for (int x = 0; x < openBracketIndexList.size();x++ ) {
                

            }
        }

        else {

        }


        for (int x = 0, y = 1; x < openBracketIndexList.size() && y < openBracketIndexList.size(); x++, y++) {
            int outerOpenBracket = x;
            int innerOpenBracket = y;
            if (markdown.indexOf("]",outerOpenBracket) == markdown.indexOf("]",innerOpenBracket)) {
                openBracketIndexList.remove(x);
            }
        }

        
        for (int x = closeBracketIndexList.size()-2, y = closeBracketIndexList.size()-1;
         x >= 0  && y >= 0; x--, y--) {
            int innerCloseBracket = x;
            int OuterCloseBracket = y;
            if (markdown.lastIndexOf("[",x) == markdown.lastIndexOf("[",y)) {
                closeBracketIndexList.remove(y);
            }
        }
    
        System.out.println("Open bracket list size" + openBracketIndexList.size());
        System.out.println("close bracket list size" + closeBracketIndexList.size());


        int currentIndex = 0;
        int openBracket;
        int closeBracket;
        int openParen=0;
        int closeParen;

        for (int x = openBracketIndexList.size()-1 ; x >= 0; x--){
            openBracket = markdown.indexOf("[", openBracketIndexList.get(x));
            closeBracket = markdown.indexOf("]", openBracket);
            if (markdown.indexOf("(", closeBracket) == openParen) {
                continue;
            }
            System.out.println(openBracket);
            openParen = markdown.indexOf("(", closeBracket);
            closeParen = markdown.indexOf (")", openParen);
            if (markdown.charAt(openBracket-1) == '!') {
                continue;
            }
            else if (closeParen == -1 || closeBracket == -1) {
                break;
            }
            else {
                toReturn.add(markdown.substring(openParen+1, closeParen));
            }

        }
        /*
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int nextOpenBracket = markdown.indexOf("[", closeBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf (")", openParen);
            currentIndex = nextOpenBracket;
        

            if (openParen == -1 || closeParen == -1 || openBracket == -1 || closeBracket ==  -1) {
                break;
            }
            else {
                toReturn.add(markdown.substring(openParen+1, closeParen));
            }
            System.out.println("openBracket: " + openBracket);
        }
        */
        /*
        int testIndex = 0;
        while (testIndex < markdown.length()) {
            System.out.println("Index: " + testIndex + "   Char: " + markdown.charAt(testIndex));
            testIndex++;
        }
        */

        return toReturn;
    }
    

    public static void main(String[] args) throws IOException {
        System.out.println("test3");
        Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}