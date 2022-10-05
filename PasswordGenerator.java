import java.util.Random;

public class PasswordGenerator {
    // http://www.gutenberg.org/cache/epub/19033/pg19033.txt
    static final String[][][] book = {
        {
            {"ALICE was beginning to get very tired of sitting by her sister on the\n",
                "bank, and of having nothing to do. Once or twice she had peeped into the\n",
                "book her sister was reading, but it had no pictures or conversations in\n",
                "it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n",
            "conversations?\"\n"},
            {"So she was considering in her OWN mind (as well as she could, for the\n",
                "day made her feel very sleepy and stupid), whether the pleasure of\n",
                "making a daisy-chain would be worth the trouble of getting up and\n",
                "picking the daisies, when suddenly a White Rabbit with pink eyes ran\n",
            "close by her.\n"},
            {"There was nothing so very remarkable in that, nor did Alice think it so\n",
                "very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
                "dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
                "out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
                "started to her feet, for it flashed across her mind that she had never\n",
                "before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
                "out of it, and, burning with curiosity, she ran across the field after\n",
                "it and was just in time to see it pop down a large rabbit-hole, under\n",
            "the hedge. In another moment, down went Alice after it!"}
        },
        {
            {"\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n",
            "telescope!\"\n"},
            {"And so it was indeed! She was now only ten inches high, and her face\n",
                "brightened up at the thought that she was now the right size for going\n",
            "through the little door into that lovely garden.\n"},
            {"After awhile, finding that nothing more happened, she decided on going\n",
                "into the garden at once; but, alas for poor Alice! When she got to the\n",
                "door, she found she had forgotten the little golden key, and when she\n",
                "went back to the table for it, she found she could not possibly reach\n",
                "it: she could see it quite plainly through the glass and she tried her\n",
                "best to climb up one of the legs of the table, but it was too slippery,\n",
                "and when she had tired herself out with trying, the poor little thing\n",
            "sat down and cried.\n"},
            {"\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
                "sharply. \"I advise you to leave off this minute!\" She generally gave\n",
                "herself very good advice (though she very seldom followed it), and\n",
                "sometimes she scolded herself so severely as to bring tears into her\n",
            "eyes.\n"},
            {"Soon her eye fell on a little glass box that was lying under the table:\n",
                "she opened it and found in it a very small cake, on which the words \"EAT\n",
                "ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
                "Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
                "makes me grow smaller, I can creep under the door: so either way I'll\n",
            "get into the garden, and I don't care which happens!\"\n"},
            {"She ate a little bit and said anxiously to herself, \"Which way? Which\n",
                "way?\" holding her hand on the top of her head to feel which way she was\n",
                "growing; and she was quite surprised to find that she remained the same\n",
            "size. So she set to work and very soon finished off the cake."}
        },
        {
            {"The King and Queen of Hearts were seated on their throne when they\n",
                "arrived, with a great crowd assembled about them--all sorts of little\n",
                "birds and beasts, as well as the whole pack of cards: the Knave was\n",
                "standing before them, in chains, with a soldier on each side to guard\n",
                "him; and near the King was the White Rabbit, with a trumpet in one hand\n",
                "and a scroll of parchment in the other. In the very middle of the court\n",
                "was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
            "trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n"},
            {"The judge, by the way, was the King and he wore his crown over his great\n",
                "wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
            "(some were animals and some were birds) I suppose they are the jurors.\"\n"},
            {"Just then the White Rabbit cried out \"Silence in the court!\"\n"},
            {"\"HERALD! read the accusation!\" said the King."}
        }
    };

    /* Initialize count variables for errors */
    static int singleCount = 0;
    static int newLineCount = 0;
    static int equalCount = 0;
    static int lengthCount = 0;
    static int upperCount = 0;
    static int lowerCount = 0;
    static int specialCount = 0;
    /* create random variable for shuffling */
    static Random random = new Random();

    private static String randomLine() {
        /* returns a random line from a random paragraph from a random page */
        int page = random.nextInt(book.length);                             // random page index
        int paragraph = random.nextInt(book[page].length);                  // random paragraph index
        int line = random.nextInt(book[page][paragraph].length);            // random line index
        return book[page][paragraph][line];
    }

    private static String[] toStringArray() {
        /* converts the line of type String into an array of words */
        String line = randomLine();                                         // get random line
        String[] strArray = line.split(" ");                                // split the line into array
        return strArray;
    }

    private static String randomWord() {
        /* returns random word from random line */
        String[] strArray = toStringArray();                                // get array
        int wordIndex = random.nextInt(strArray.length);                    // random word index
        return strArray[wordIndex];
    }

    private static boolean isSingleCharacter(String word) {
        /* checks for single character */
        return word.length() == 1;
    }

    private static boolean containsNewline(String word) {
        /* checks for newline character */
        return word.contains("\n");
    }

    private static String generateWord() {
        /* verifies the validity of the random word */
        String word = randomWord();
        while (isSingleCharacter(word) || containsNewline(word)) {          // check for single character or newline
            if (isSingleCharacter(word)) {                                  // find single character
                singleCount++;
                word = randomWord();                                        // choose another word
                continue;
            }
            if (containsNewline(word)) {                                    // find newline
                newLineCount++;
                word = randomWord();                                        // chose another word
            }
        }
        return word;
    }

    public static String generatePassword() {
        /* generates password that meets all the conditions */
        String word1 = generateWord();
        String word2 = generateWord();
        String word3 = generateWord();
        while (word1.compareTo(word2) == 0 || word1.compareTo(word3) == 0 || word2.compareTo(word3) == 0) {
            equalCount++;
            if (word1.compareTo(word2) == 0 || word1.compareTo(word3) == 0)
                return generatePassword();                                  // return to step 2 (generate random words)
            else
                return generatePassword();
        }
        String password = word1.concat(word2).concat(word3);                // concatenate to form candidate password
        if (password.length() > 16 || password.length() < 8) {
            lengthCount++;
            return generatePassword();
        }
        
        boolean upper = false, lower = false, special = false;
        for (int i = 0; i < password.length(); i++) {                       // iterate through string
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90)       // check for uppercase letter
                upper = true;
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122)      // check for lowercase letter
                lower = true;
            if (password.charAt(i) >= 32 && password.charAt(i) <= 47        // check for special character
                    || password.charAt(i) >= 58 && password.charAt(i) <= 64 ||
                    password.charAt(i) >= 91 && password.charAt(i) <= 96
                    || password.charAt(i) >= 123 && password.charAt(i) <= 126)
                special = true;
        }
        
        if (!upper) {                                                       // no uppercase
            upperCount++;
            return generatePassword();                                      // restart
        }

        if (!lower) {                                                       // no lowercase
            lowerCount++;
            return generatePassword();                                      // restart
        }
        
        if (!special) {                                                     // no special
            specialCount++;
            return generatePassword();                                      // restart
        }
        
        return password;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the password generator game!\n");
        int passwordCount = 0;
        while (passwordCount != 10000) {
            String password = generatePassword();
            System.out.println(String.format(
                    "Password = %s\tNewline = %s\tSingle = %d\tEqual = %d\tLength = %d\tUpper = %d\tLower = %d\tSpecial = %d",
                    password.length() >= 13 ? password : password.length() == 8 ? password + "\t\t": password + "\t", newLineCount / 10 == 0 ? newLineCount + "\t": newLineCount, singleCount,
                    equalCount, lengthCount, upperCount, lowerCount, specialCount));
            passwordCount++;
            if (lowerCount > 0)
                break;
            singleCount = 0;
            newLineCount = 0;
            equalCount = 0;
            lengthCount = 0;
            upperCount = 0;
            lowerCount = 0;
            specialCount = 0;
        }
        System.out.println("\nTotal password generated: " + passwordCount);
        System.out.println("\nThanks for using the password generator game. Good bye.");
    }
}