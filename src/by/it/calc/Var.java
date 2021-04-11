package by.it.calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.script.ScriptEngine.FILENAME;

abstract class Var implements Operation {

    private static Map<String, Var> varMap = new HashMap<>();

    public static Map<String, Var> getVarMap() {
        return varMap;
    }


    public static Var save(String name, Var value) throws CalcException {
        varMap.put(name, value);
        saveMap();
        return value;

    }




    private static void saveMap() throws CalcException {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (Map.Entry<String, Var> entry : varMap.entrySet()) {
                writer.printf("%s=%s\n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            throw new CalcException("FILE ERROR: ", e);
        }
    }

    static void load() throws CalcException {
        try {

            List<String> lines = Files
                    .lines(Paths.get(GetFileName.getFilename()))
                    .collect(Collectors.toList());
            Parser parser = new Parser();
            for (String line : lines) {
                parser.calc(line);
            }
        } catch (IOException e) {
            throw new CalcException(e);
        }

    }



    @Override
    public String toString() {
        return "abstract Var";
    }


    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException(String.format("Operation %s + %s impossible\n", this, other));
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException(String.format("Operation %s + %s impossible\n", this, other));

    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException(String.format("Operation %s + %s impossible\n", this, other));

    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException(String.format("Operation %s + %s impossible\n", this, other));
    }
}

