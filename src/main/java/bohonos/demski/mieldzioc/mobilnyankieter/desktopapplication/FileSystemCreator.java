/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 *
 * @author Andrzej
 */
public class FileSystemCreator {
    
    public FileSystemCreator() throws IOException {
        System.out.println("Wchodzê do konstruktora CreateFileSystem");

        String templatesPath = "C:" + File.separator + "ankieter" + File.separator + "templates";
        String activeTemplatesPath = "C:" + File.separator + "ankieter" + File.separator + "activeTemplates";
        String surveysPath = "C:" + File.separator + "ankieter" + File.separator + "surveys";
        String interviewersPath = "C:" + File.separator + "ankieter" + File.separator + "interviewers.txt";
        String maxIdPath = "C:" + File.separator + "ankieter" + File.separator + "maxId.txt";
        String outcomes = "C:" + File.separator + "ankieter" + File.separator + "outcomes";
        
        File templatesCatalog = new File(templatesPath);
        File activeTemplatesCatalog = new File(activeTemplatesPath);
        File surveysCatalog = new File(surveysPath);
        File interviewersFile = new File (interviewersPath);
        File maxIdFile = new File(maxIdPath);
        File outcomesCatalog = new File(outcomes);
        
        templatesCatalog.mkdirs(); 
        activeTemplatesCatalog.mkdir();
        surveysCatalog.mkdir();
        interviewersFile.createNewFile();
        maxIdFile.createNewFile();
        outcomesCatalog.mkdirs();

    }
}
