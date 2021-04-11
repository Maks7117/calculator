package by.it.calc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

enum Logger {
    GET;

    private static final String LOG_NAME = "log.txt";



    void log(String logtxt) throws CalcException {
        String filename = GetFileName.getFilename()+LOG_NAME;

        try (PrintWriter out = new PrintWriter(new FileWriter(filename, true));) {
            out.println(logtxt+"    Time and Date:  "+ LocalDateTime.now());
        } catch (IOException e) {
            throw new CalcException("FILE ERROR: ", e);
        }
    }
}



