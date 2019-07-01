public class Utils {
    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    public static boolean isValidateProduct(final String[] columns) {
        if (columns == null || columns.length != 4) return false;
        return isNumeric(columns[0]) && isNumeric(columns[3]);
    }

    public static boolean isValidateOrder(final String[] columns) {
        if (columns == null || columns.length != 4) return false;
        return isNumeric(columns[0])
                && isNumeric(columns[1])
                && isNumeric(columns[3])
                && (columns[3].equals("0") || columns[3].equals("1"));
    }

}
