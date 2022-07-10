import java.util.Scanner;

public class Main {
    public static String[] items = new String[3];

    public static int ArithmeticRim(String first, String second, String sign) {
        if (first.matches("\\d") || second.matches("\\d")) {
            try {
                throw new Exception("т.к. используются одновременно разные системы счисления");
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            RimNumbers rimNumbers = new RimNumbers();
            int a = rimNumbers.rimToArabian(first);
            int b = rimNumbers.rimToArabian(second);
            if (a > 10 || b > 10){
                try {
                    throw new Exception("одно или более чисел больше 10");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                switch (sign) {
                    case "+":
                        return a + b;
                    case "-":
                        if (b > a) {
                            try {
                                throw new Exception("т.к. в римской системе счисления отсутствуют отрицательные числа");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return a - b;
                    case "*":
                        return a * b;
                    case "/":
                        return a / b;
                    default:
                        return -1;
                }
            }
        }
        return -1;
    }

    public static int Arithmetic(String first, String second, String sign) {
        int a = Integer.parseInt(first);
        int b = Integer.parseInt(second);
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }
    static class RimNumbers {
        boolean isRim(String a) {
            return switch (a) {
                case "I" -> true;
                case "II" -> true;
                case "III" -> true;
                case "IV" -> true;
                case "V" -> true;
                case "VI" -> true;
                case "VII" -> true;
                case "VIII" -> true;
                case "IX" -> true;
                case "X" -> true;
                default -> false;
            };
        }

        public int rimToArabian(String a) {
            // System.out.println(a.split(""));
            int result = 0;
            int lastNumber = 0;
            for (int i = a.length() - 1; i >= 0; i--) {
                char x = a.charAt(i);
                switch (x) {
                    case 'I':
                        result = checkLastNumberAndSumm(lastNumber, 1, result);
                        lastNumber = 1;
                        break;
                    case 'V':
                        result = checkLastNumberAndSumm(lastNumber, 5, result);
                        lastNumber = 5;
                        break;
                    case 'X':
                        result = checkLastNumberAndSumm(lastNumber, 10, result);
                        lastNumber = 10;
                        break;
                    case 'L':
                        result = checkLastNumberAndSumm(lastNumber, 50, result);
                        lastNumber = 50;
                        break;
                    case 'C':
                        result = checkLastNumberAndSumm(lastNumber, 100, result);
                        lastNumber = 100;
                        break;
                }
            }
            return result;
        }

        public int checkLastNumberAndSumm(int a, int b, int c) {
            if (a > b) {
                return c - b;
            } else
                return c + b;
        }

        public String arabianToRim(int a) {
            String result = "";
            int number = a;
            while (number > 0) {
                if (number / 50 != 0) {
                    result += "L";
                    number = number - 50;
                } else if (number / 40 != 0) {
                    result += "XL";
                    number = number - 40;
                } else if (number / 10 != 0) {
                    result += "X";
                    number = number - 10;
                } else if (number / 9 != 0) {
                    result += "IX";
                    number = number - 9;
                } else if (number / 5 != 0) {
                    result += "V";
                    number = number - 5;
                } else if (number / 4 != 0) {
                    result += "IV";
                    number = number - 4;
                } else {
                    result += "I";
                    number = number - 1;
                }
            }
            return result;
        }
    }
    static String findSign (String[] s){
        String newSign = "";
        for (int i = 0; i< s.length;i++){
            if(!(s[i] == "")){
                newSign = s[i];
            }
        }
        return newSign;
    }
    class input{
        static String Scan(){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            return input;
        }
    }
  static String calc(String input){
       input = input.trim().replace(" ","");
       items = input.split("\\W");
       String newSign = findSign(input.split("\\w"));
       RimNumbers rimNumbers = new RimNumbers();
       if (items.length > 2 || items.length < 2) {
           try {
               throw new Exception("т.к. строка не является математической операцией");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       else if (rimNumbers.isRim(items[0]) || rimNumbers.isRim(items[1])) {
           return (rimNumbers.arabianToRim(ArithmeticRim(items[0], items[1], newSign)));
       } else {String result = String.valueOf(Arithmetic(items[0], items[1],newSign));
       return result;}
return "";
   }

    public static void main(String[] args) {
        String input =  Main.input.Scan();
        System.out.println(calc(input));
    }
    static class Exception extends java.lang.Exception {
        public Exception(String message) {
            super(message);
        }

    }
}
