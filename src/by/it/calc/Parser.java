package by.it.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
    Var calc(String expression) throws CalcException {

        expression.replaceAll("\\s+", "");
        expression = removeBracket(expression);
        return getVar(expression);
    }

    private Var getVar(String expression) throws CalcException {
        List<String> operands = new ArrayList<>(
                Arrays.asList(expression.split(Patterns.OPERATION))
        );
        List<String> operations = new ArrayList<>();

        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
        while (matcher.find()) {
            operations.add(matcher.group());
        }
        while (!operations.isEmpty()) {
            int index = getIndex(operations);
            String operation = operations.remove(index);
            String left = operands.remove(index);
            String right = operands.remove(index);
            Var res = oneCalc(left, operation, right);
            operands.add(index, res.toString());
        }

        return CreateVar.createVar(operands.get(0));
    }

    private String removeBracket(String expression) throws CalcException {
        while (expression.contains("(")) {
            Matcher matcher1 = Pattern.compile(Patterns.BRACKET).matcher(expression);
            if (matcher1.find()) {
                String a = matcher1.group().replaceAll("\\)", "");
                a = a.replaceAll("\\(", "");
                int pos = matcher1.start();
                a = getVar(a).toString();
                expression = expression.replaceFirst(Patterns.BRACKET, a);
            }
        }
        return expression;
    }

    private Var oneCalc(String strLeft, String operation, String strRight) throws CalcException {
        Var right = CreateVar.createVar(strRight);
        if (operation.equals("=")) {
            return Var.save(strLeft, right);
        }
        Var left = CreateVar.createVar(strLeft);

        switch (operation) {
            case "+":
                return left.add(right);
            case "-":
                return left.sub(right);
            case "*":
                return left.mul(right);
            case "/":
                return left.div(right);
        }
        throw new CalcException("buu!!");
    }

    private int getIndex(List<String> operations) {
        int index = -1;
        int pr = -1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            if (PR.get(op) > pr) {
                index = i;
                pr = PR.get(op);
            }
        }
        return index;
    }

    private static final Map<String, Integer> PR = new HashMap<>();

    static {
        PR.put("=", 0);
        PR.put("+", 1);
        PR.put("-", 1);
        PR.put("*", 2);
        PR.put("/", 2);
    }
}