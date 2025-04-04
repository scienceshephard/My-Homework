
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;
    
    private static final Pattern PERSON_ID_PATTERN = Pattern.compile("^\\d{6}[+-A]\\d{3}[0-9A-Z]$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getBirthDate(){
        return birthDate;
    }

    public String setPersonID(final String personID){
        if (personID == null || !checkPersonIDNumber(personID)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
    

        String date = "";
        try{
            String day = personID.substring(0, 2);
            String month = personID.substring(2, 4);
            String year = personID.substring(4, 6);
            char centuryChar = personID.charAt(6);
    
            int century = 1;
            if (centuryChar == '+')
                century = 1800;
            else if(centuryChar == '-')
                century = 1900;
            else if(centuryChar == 'A')
                century = 2000;
            else return ConstantValues.INVALID_BIRTHDAY;
            date = day + "." + month + "." + (century + Integer.parseInt(year));
        }catch(NumberFormatException | IndexOutOfBoundsException e){
            
            return ConstantValues.INVALID_BIRTHDAY;
        }

        if(!checkBirthdate(date)){
            return ConstantValues.INVALID_BIRTHDAY;
        }

        if(!checkValidCharacter(personID)){
            return ConstantValues.INCORRECT_CHECKMARK;
        }
        this.birthDate = date;
        return "Ok";

    }
    private boolean checkPersonIDNumber(final String personID){
        if(personID == null || !PERSON_ID_PATTERN.matcher(personID).matches()){
            return false;
        }
        char centuryChar = personID.charAt(6);
        return centuryChar == '+' || centuryChar == '-' || centuryChar == 'A';
    }
    
    private boolean checkLeapYear(int year){
        return Year.isLeap(year);
    }
    private boolean checkValidCharacter(final String personID){
         // Special test case
         if (personID == null || !PERSON_ID_PATTERN.matcher(personID).matches()) {
            return false;
        }
        char centuryChar = personID.charAt(6);
        return centuryChar == '+' || centuryChar == '-' || centuryChar == 'A';
    }
    private boolean checkBirthdate(String date){
          try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
