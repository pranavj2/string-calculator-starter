package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator {

    public static int calledCount= 0;

    public int add(String input) throws Exception {
        return add(input,",");
    }

    public int getCalledCount(){
        return calledCount;
    }

    public int add(String input,String delimeter) throws Exception {
        ++calledCount;

        // Check if input string is empty
        if (input.isBlank())
            return 0;

        // check in input string ends with new line
        if (input.charAt(input.length() - 1) == '\n')
            throw new Exception("Invalid Input");
        List<Integer> numberArray = this.splitStringAndGetNumbers(input,delimeter);

        int addition = 0;
        List<Integer> negativeNumber= new ArrayList<>();

        for (Integer number : numberArray) {

            if (number < 0)    // check for negative numbers
                negativeNumber.add(number);
            else if (number < 1000)  // add number only if it is less than 1000
                addition += number;
        }

        // throw exception if string contains negative numbers
        if (!negativeNumber.isEmpty()){
            throw new Exception("negatives not allowed : "+ negativeNumber);
        }
        return addition;

    }

    /**
     * Method will return list of numbers extracted from the string
     * @param inputString : actual string
     * @param delimeter : delimeter
     * @return : List of numbers
     */
    private List<Integer> splitStringAndGetNumbers(String inputString, String delimeter){
        String[] finalArray;
        List<Integer> numbersInString = new ArrayList<>();
        Pattern pattern;
        Matcher matcher;

        // Split the string according to delimeter and add numbers in list
        switch (delimeter) {
            case "multilength" -> {
                // use regular expression in case string has multiple delimeter
                String[] newString = inputString.split("\\\\n");
                pattern = Pattern.compile("//\\[(.*)\\]");
                matcher = pattern.matcher(newString[0]);
                if (matcher.find()) {
                    inputString = newString[1].replace(matcher.group(1), ",");
                }
                finalArray = inputString.split(",");
            }
            case "multiple" -> {

                // use regular expression in case string has delimeter of any length
                String[] newString1 = inputString.split("\\\\n");
                pattern = Pattern.compile("\\[(.*?)\\]");
                matcher = pattern.matcher(newString1[0]);
                while (matcher.find()) {
                    newString1[1] = newString1[1].replace(matcher.group(1), ",");
                }
                inputString = newString1[1];
                finalArray = inputString.split(",");
            }
            default -> finalArray = inputString.split(",|;|@");
        }

        // check if string contains new lines
        for (String str : finalArray) {
            if (str.contains("\\n")) {
                String[] str2 = str.split("\\\\n");
                for (String innerStr : str2)
                    numbersInString.add(Integer.parseInt(innerStr));
            }else
                numbersInString.add(Integer.parseInt(str));

        }
        return numbersInString;

    }

}