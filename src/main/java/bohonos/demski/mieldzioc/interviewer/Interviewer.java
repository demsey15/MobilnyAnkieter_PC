/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.interviewer;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
//import javafx.util;
/**
 *
 * @author Delirus
 */
public class Interviewer {
    public String name, surname;
    public int id; //PESEL
    public GregorianCalendar hiredDay;
    public GregorianCalendar relieveDay=null;
    //private boolean isActive;
    public List<Entry<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<Entry<GregorianCalendar,GregorianCalendar>>();
    //private List<Pair<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<>();
    //public InterviewerSurveyPrivileges privileges = new InterviewerSurveyPrivileges();
    public Map<Integer, InterviewerSurveyPrivileges> intervSurveyPrivileges = new TreeMap<Integer, InterviewerSurveyPrivileges>(); //ewentuanie HashMap
    public InterviewerPrivileges privileges = new InterviewerPrivileges();
    Interviewer(String name, String surname, int id, GregorianCalendar hireday)
    {
        this.name=name;
        this.surname=surname;
        this.id=id;
        this.hiredDay=hireday;
        //isActive=true;
    }

    Interviewer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setOutOfWorkTime (GregorianCalendar g1, GregorianCalendar g2){
        outOfWorkTime.add(new SimpleEntry<GregorianCalendar, GregorianCalendar>(g1, g2));
    }
    
    public void setPrivilegesForInterviewer(int numberOfSurvey, InterviewerSurveyPrivileges privileges){
        intervSurveyPrivileges.put(id, privileges);
    }
    public boolean isActive(){
        GregorianCalendar now = new GregorianCalendar();
        for (Entry<GregorianCalendar, GregorianCalendar> entr : outOfWorkTime)
        {
            if( now.compareTo(entr.getKey())>=0 && now.compareTo(entr.getValue())<=0)
                    return false;
        }
        return true;
    }
    
}