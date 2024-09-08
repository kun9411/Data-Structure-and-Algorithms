package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {


        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list=" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list=" + suffixExpressionList);

        System.out.printf("expression=%d",calculate(suffixExpressionList));

//        String  suffixExpression = "3 4 + 5 * 6 -";
//
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList=" + rpnList);
//
//        int res = calculate(rpnList);
//        System.out.println("计算的结果是=" + res);
    }

    private static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();

        for (String item : ls){
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }




    public static List<String> toInfixExpressionList(String s){
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57 ){
                ls.add(""+c);
                i++;
            }else {
                str = "";
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String item : ls){
            //\\d+ 是一个正则表达式，用于匹配一个或多个数字字符
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 -num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //"" + res 是利用字符串连接的方式将 res 转换为字符串
                stack.push("" + res);

            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = SUB;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;

    }
}


