package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    REGEX1("^register\\susername\\s(?<username>.+)\\spassword\\s(?<password>.+)$"),
    REGEX2("^[A-Za-z]+$"),
    REGEX3("^(?!^[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&^%$#@!]).{8,20}$"),
    REGEX4("^login\\susername\\s(?<username>.+)\\spassword\\s(?<password>.+)$"),
    REGEX5("^start\\sgame\\sturns\\scount\\s(?<turnsCount>\\d+)\\susername\\s(?<username>.+)$"),
    REGEX6("^change\\spassword\\sold\\spassword\\s(?<oldPassword>.+)\\snew\\spassword\\s(?<newPassword>.+)$"),
    REGEX7("^remove\\sfrom\\sbattle\\sdeck\\s(?<name>.+)$"),
    REGEX8("^add\\sto\\sbattle\\sdeck\\s(?<name>.+)$"),
    REGEX9("^buy\\scard\\s(?<name>.+)$"),
    REGEX10("^sell\\scard\\s(?<name>.+)$"),
    REGEX11("^show\\sline\\sinfo\\s(?<line>.+)$"),
    REGEX12("^move\\stroop\\sin\\sline\\s(?<lineDirection>.+)\\sand\\srow\\s(?<rowNumber>\\d+)\\s(?<direction>.+)$"),
    REGEX13("^deploy\\stroop\\s(?<troopName>.+)\\sin\\sline\\s(?<lineDirection>.+)\\sand\\srow\\s(?<rowNumber>\\d+)$"),
    REGEX14("^deploy\\sspell\\sHeal\\sin\\sline\\s(?<lineDirection>.+)\\sand\\srow\\s(?<rowNumber>\\d+)$"),
    REGEX15("deploy\\sspell\\sFireball\\sin\\sline\\s(?<lineDirection>.+)$");

    private String regex;

    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
