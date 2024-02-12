package bonfiglio.scozzari.ing_soft.theatersoftware.utils.enumeration;

/**
 * Utility class containing regex patterns for data validation in the theater software.
 * Each pattern is designed for specific field validation.
 * <p>
 *
 * Patterns include:
 * - {@link #LENGTH_NAME_PATTERN}: Ensures the length of name and surname is between 3 and 50 characters.
 * - {@link #FORMAT_NAME_PATTERN}: Requires the name and surname to start with an uppercase letter, followed by lowercase letters.
 * - {@link #NOT_NUMBERS_NAME_PATTERN}: Prohibits the presence of numerical digits in the name and surname.
 * - {@link #NOT_SPECIAL_CHARACTERS_NAME_PATTERN}: Forbids the inclusion of special characters in the name and surname.
 * - {@link #EMAIL_PATTERN}: Validates email addresses based on a standard format.
 * - {@link #TAX_CODE_PATTERN}: Verifies the validity of Italian tax codes using a specific regex pattern.
 * - {@link #PASSWORD_PATTERN}: Enforces password criteria, including uppercase, lowercase, digits, and special characters, with a minimum length of 8 characters.
 * - {@link #COMMON_PASSWORDS_PATTERN}: Detects common and easily guessable passwords.
 * - {@link #TEL_PATTERN}: Validates telephone numbers based on a standard format.
 * - {@link #CITY_NAME_PATTERN}: Ensures the city name is valid, allowing for spaces and hyphens.
 * - {@link #DOMAIN_NAME_PATTERN}: Validates domain names based on a standard format.
 * - {@link #IVA_PATTERN}: Verifies the validity of Italian VAT numbers using a specific regex pattern.
 * - {@link #UNIQUE_CODE_PATTERN}: Validates unique codes using a specific regex pattern.
 * - {@link #RECIPIENT_CODE_PATTERN}: Validates recipient codes using a specific regex pattern.
 *
 * <p>This class serves as a central location for regex patterns, promoting code organization and reusability in validation logic.
 *
 * @author Kevin
 * @see bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator
 */
public class RegexPatterns {

    // Regex pattern for name and surname validation
    public static final String LENGTH_NAME_PATTERN = ".{3,50}";
    public static final String FORMAT_NAME_PATTERN = "^[A-Z][a-z]*$";
    public static final String NOT_NUMBERS_NAME_PATTERN = ".*\\d.*";
    public static final String NOT_SPECIAL_CHARACTERS_NAME_PATTERN = ".*[!@#\\$%\\^&\\*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";

    // Regex pattern for email validation
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

    // Regex pattern for tax code validation
    public static final String TAX_CODE_PATTERN = "^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}" +
            "(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])" +
            "|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$";

    // Regex pattern for password validation
    public static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*\\-._]).{8,}$";

    // Regex pattern for detecting common passwords
    public static final String COMMON_PASSWORDS_PATTERN = ".*(?i:123|1234|12345|123456|1234567|12345678|123456789|password|";

    // Regex pattern for telephone number validation
    public static final String TEL_PATTERN = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";

    // Regex pattern for city name validation
    public static final String CITY_NAME_PATTERN = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";

    // Regex pattern for domain name validation
    public static final String DOMAIN_NAME_PATTERN = "(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9]";

    // Regex pattern for IVA validation
    public static final String IVA_PATTERN = "^\\d{11}$\n";

    // Regex pattern for unique code validation
    public static final String UNIQUE_CODE_PATTERN = "^\\d{6}$\n";

    // Regex pattern for recipient code validation
    public static final String RECIPIENT_CODE_PATTERN = "^\\d{7}$\n";

}
