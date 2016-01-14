/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bohonos.demski.mieldzioc.mobilnyankieter.desktopapplication.SurveyMenagerPanel;
import bohonos.demski.mieldzioc.mobilnyankieter.serialization.jsonserialization.JsonSurveySerializator;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Andrzej
 */
public class FileProvider {
    
    /**
     * creates .csv file
     * @param surveysList list of surveys
     * @return path to file with surveys results (if action was successful)
     */
    public String makeCsv(List<Survey> surveysList) {
        return null; // to do
    }
    
    /**
     * prepares file version of choosen survey, ready to print
     * @param survey survey, we want to print
     * @return path to file with survey (if action was successful)
     */
    public String prepareToPrint(Survey survey) {
        return null; // to do
    }
    
    /**
     * prepares file version of statistics for given list of surveys
     * @param surveysList list of surveys
     * @return path to file with statistics (if action was successful)
     */
    public String prepareStatisticsAsHtml(List<Survey> surveysList) {
        return null; // to do
    }
    
    /**
     * prepares file with list of all interviewers
     * @return path to file with the list (if action was successful)
     */
    public String prepareListOfWorkers() {
        return null; // to do
    }
    
    public void makeHtml(Survey survey){
    	GregorianCalendar date = new GregorianCalendar();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
        fmt.setCalendar(date);
        String dateFormatted = fmt.format(date.getTime());
        String templatePath = "C:" + File.separator + "ankieter" + File.separator + "htmlTemplates" + File.separator + dateFormatted + " " + survey.getTitle() + ".html";
        PrintWriter writer;
        try {
            writer = new PrintWriter(templatePath, "UTF-8");
            List<String> codeList = survey.getHtmlCode();
            for(String row : codeList) {
            	writer.println(row);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SurveyMenagerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
