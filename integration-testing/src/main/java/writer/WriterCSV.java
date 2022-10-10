package writer;

import function.Function;
import function.logarithm.Ln;
import function.logarithm.Log;
import function.logarithm.LogarithmicFunction;
import function.system.SystemFunction;
import function.trigonometry.Sin;

import java.io.FileWriter;
import java.io.IOException;

public class WriterCSV {

    public static void writeCSV(String path, Function function, double x, double end, double step) {
        try(FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(CSVBuilder(function, x, end, step));
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String CSVBuilder(Function module, double x, double end,double step) {
        StringBuilder stringBuilder = new StringBuilder();
        for (double i = x; i < end; i+=step) {
            stringBuilder.append(i).append(",").append(module.apply(i)).append("\n");
        }
        return stringBuilder.toString();

    }


    public static void main(String[] args) {
        Ln ln = new Ln(0.001);
        Log log2 = new Log(ln, 2);
        Log log3 = new Log(ln, 3);
        Log log5 = new Log(ln, 5);
        LogarithmicFunction logarithmicFunction = new LogarithmicFunction(log2, log3, log5);
        Sin sin = new Sin(0.001);
        SystemFunction systemFunction = new SystemFunction(sin, logarithmicFunction);
        WriterCSV.writeCSV("src/logFunc.csv", logarithmicFunction,0,0.5,0.001);
    }

}
