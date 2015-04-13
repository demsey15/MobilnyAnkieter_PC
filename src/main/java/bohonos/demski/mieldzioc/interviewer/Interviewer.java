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
    private String name, surname;
    private int id; //PESEL
    private GregorianCalendar hiredDay;
    private GregorianCalendar relieveDay=null;
    //private boolean isActive;
    private List<Entry<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<Entry<GregorianCalendar,GregorianCalendar>>();
    //private List<Pair<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<>();
    //public InterviewerSurveyPrivileges privileges = new InterviewerSurveyPrivileges();
    private Map<Integer, InterviewerSurveyPrivileges> intervSurveyPrivileges = new TreeMap<Integer, InterviewerSurveyPrivileges>(); //ewentuanie HashMap
    private InterviewerPrivileges privileges = new InterviewerPrivileges();
   
   /**
    * Konstruktor klasy Interviewer
    * @param name
    * @param surname
    * @param id
    * @param hireday 
    */ 
    Interviewer(String name, String surname, int id, GregorianCalendar hireday)
    {
        this.name=name;
        this.surname=surname;
        this.id=id;
        this.hiredDay=hireday;
    }


    /**
     * Metoda isActive() na podstawie okresów czasu z outOfWorkTime zwraca wartoœæ true jeœli ankieter jest aktywny, w p.p. zwraca false. 
     * @return 
     */
    public boolean isActive(){
        GregorianCalendar now = new GregorianCalendar();
        for (Entry<GregorianCalendar, GregorianCalendar> entr : outOfWorkTime)
        {
            if( now.compareTo(entr.getKey())>=0 && now.compareTo(entr.getValue())<=0)
                    return false;
        }
        return true;
    }
    
     public String getName(){
        return name;
    }
     
     public String getSurname(){
        return surname;
    }
   
    public int getId(){
        return id;
    }
    
    public GregorianCalendar getHiredDay(){
        return hiredDay;
    }
    
    public GregorianCalendar getRelieveDay(){
        return relieveDay;
    }
     
    public List<Entry<GregorianCalendar,GregorianCalendar>> getOutOfWorkTime(){
        return outOfWorkTime;
    }
      
    public Map<Integer, InterviewerSurveyPrivileges> getIntervSurveyPrivileges(){
        return intervSurveyPrivileges;
    }
    
    public void editeName(String name){
        this.name=name;
    }
    
    public void editeSurname(String surname){
        this.surname=surname;
    }
    
    public void editeId(int id){
        this.id=id;
    }
    
    public void editeHireDay(GregorianCalendar hiredDay){
        this.hiredDay=hiredDay;
    }
    public void setRelieveDay(GregorianCalendar relieveDay){
        this.relieveDay=relieveDay;
    }
     public void setOutOfWorkTime (GregorianCalendar g1, GregorianCalendar g2){
        outOfWorkTime.add(new SimpleEntry<GregorianCalendar, GregorianCalendar>(g1, g2));
    }
    
    public void setPrivilegesForInterviewer(int numberOfSurvey, InterviewerSurveyPrivileges privileges){
        intervSurveyPrivileges.put(id, privileges);
    }
    //brakuje edytowania outOfWorkTime i intervSurveyPrivileges
}