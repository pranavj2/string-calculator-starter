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

        if (delimeter.equals("multilength")){
            String[] newString = input.split("\\\\n");
            Pattern pattern = Pattern.compile("//\\[(.*)\\]");
            Matcher matcher = pattern.matcher(newString[0]);
            if (matcher.find()){
                input = newString[1].replace(matcher.group(1),",");
            }
            delimeter = ",";
        }
        if (delimeter.equals("multiple")){
            String[] newString = input.split("\\\\n");
            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(newString[0]);
            while (matcher.find()){
                newString[1] = newString[1].replace(matcher.group(1),",");
            }
            input = newString[1];
            delimeter = ",";
        }

        if (input.isBlank())
            return 0;
        else{

            String[] numberArray = input.split(delimeter);

            char lastChar = input.charAt(input.length() - 1);
            if (lastChar == '\n'){
                throw new Exception("Invalid Input");
            }

            int addition = 0;
            List<Integer> negativeNumber= new ArrayList<>();

            for (String str : numberArray) {

                if(str.contains("\\n")){
                    String[] str2 = str.split("\\\\n");
                    for (String innerStr : str2) {

                        int number = Integer.parseInt(innerStr);
                        if(number < 0) {
                            negativeNumber.add(number);
                        }else
                            if(number < 1000)
                                addition +=number;
                    }
                }
                else{
                    int number = Integer.parseInt(str);
                    if(number < 0) {
                        negativeNumber.add(number);
                    }else {
                        if (number < 1000)
                            addition += number;
                    }
                }

            }
            if (!negativeNumber.isEmpty()){
                throw new Exception("negatives not allowed : "+ negativeNumber);
            }
            return addition;
        }
    }

}