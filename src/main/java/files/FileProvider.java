/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import bohonos.demski.mieldzioc.survey.Survey;
import java.util.List;

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
}
