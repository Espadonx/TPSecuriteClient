/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientsocket;

/**
 *
 * @author Fabien-portable
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



public class FileManager {

    public String readFile(String nomfichier) throws IOException {
        String everything;
        BufferedReader br = new BufferedReader(new FileReader(nomfichier));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }

        return everything;

    }

    public void tofile(String nomfichier, List<String> lines) throws IOException {
        Path file = Paths.get(nomfichier);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

}
