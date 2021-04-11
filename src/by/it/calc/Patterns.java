package by.it.calc;

public interface Patterns {
    String BRACKET = "\\({1}[^\\(\\)]*?\\)";
    String OPERATION="(?<=[^-=+/*,{])[-+*/=]";
    String SCALAR="-?[0-9]+(\\.[0-9]+)?";
    String VECTOR="\\{"+SCALAR+"?(,"+SCALAR+")*}";
    String MATRIX="\\{"+VECTOR+"?(,"+VECTOR+")*}";
}
