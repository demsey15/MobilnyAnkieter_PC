/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.interviewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import com.rits.cloning.Cloner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import javax.persistence.*;
//import javafx.util;
/**
 *
 * @author Delirus
 */
//@Entity
public class Interviewer implements Serializable, Cloneable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String name, surname;
    //@Id
    private String id; //PESEL
    private GregorianCalendar hiredDay;
    private GregorianCalendar relieveDay=null;
    //private boolean isActive;
    private List<Pair<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<Pair<GregorianCalendar,GregorianCalendar>>();
    //private List<Pair<GregorianCalendar,GregorianCalendar>> outOfWorkTime = new ArrayList<>();
    //public InterviewerSurveyPrivileges privileges = new InterviewerSurveyPrivileges();
    private Map<String, InterviewerSurveyPrivileges> intervSurveyPrivileges = new TreeMap<String, InterviewerSurveyPrivileges>(); //ewentuanie HashMap
    private InterviewerPrivileges privileges = new InterviewerPrivileges();
    private List<String> macAdresses = new ArrayList<String>();
   /**
    * Konstruktor klasy Interviewer
    * @param name
    * @param surname
    * @param id
    * @param hireday 
    */ 
    public Interviewer(String name, String surname, String id, GregorianCalendar hireday) throws ParseException
    {
        this.name=name;
        this.surname=surname;
        this.id=id;
        this.hiredDay=hireday;
        System.out.println("data: " + dateFormat(this.hiredDay));
        System.out.println("data 2: " + dateFormat(stringToCal(dateFormat(this.hiredDay))));
    }


    /**
     * Metoda isActive() na podstawie okres�w czasu z outOfWorkTime zwraca warto�� true je�li ankieter jest aktywny, w p.p. zwraca false. 
     * @return 
     */
    public boolean isActive(){
        if(relieveDay!=null){
            return false;
        }
        GregorianCalendar now = new GregorianCalendar();
        for (Pair<GregorianCalendar, GregorianCalendar> entr : outOfWorkTime)
        {
            if( now.compareTo(entr.getFirst())>=0 && now.compareTo(entr.getSecond())<=0)
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
   
    public String getId(){
        return id;
    }
    
    public GregorianCalendar getHiredDay(){
        return hiredDay;
    }
    
    public GregorianCalendar getRelieveDay(){
        return relieveDay;
    }
     
    public List<Pair<GregorianCalendar,GregorianCalendar>> getOutOfWorkTime(){
        return outOfWorkTime;
    }
      
    public Map<String, InterviewerSurveyPrivileges> getIntervSurveyPrivileges(){
        return intervSurveyPrivileges;
    }
    
    public void editeName(String name){
        this.name=name;
    }
    
    public void editeSurname(String surname){
        this.surname=surname;
    }
    
    public void editeId(String id){
        this.id=id;
    }
    
    public void editeHireDay(GregorianCalendar hiredDay){
        this.hiredDay=hiredDay;
    }
    public void setRelieveDay(GregorianCalendar relieveDay){
        this.relieveDay=relieveDay;
    }
     public void setOutOfWorkTime (GregorianCalendar g1, GregorianCalendar g2){
        outOfWorkTime.add(new Pair<GregorianCalendar, GregorianCalendar>(g1, g2));
    }
    
    public void setPrivilegesForInterviewer(String numberOfSurvey, InterviewerSurveyPrivileges privileges){
        intervSurveyPrivileges.put(numberOfSurvey, privileges);
    }
    //brakuje edytowania outOfWorkTime i intervSurveyPrivileges
    public boolean getInterviewerPrivileges(){
        return privileges.getCreating();
    }
    
     public void setInterviewerPrivileges(boolean x){
        privileges.changePrivileges(x);
    }
     
    @Override
    public boolean equals(Object o)
    {
        if (this==o) 
            return true;
        if (o==null)
            return false;
        if (this.getClass()!=o.getClass())
            return false;
        Interviewer otherInterviewer = (Interviewer)o;
        if (this.id.equals(otherInterviewer.getId()))
            return true;
        else
            return false;
    }
    public void returnInterviewer(GregorianCalendar date2){
        GregorianCalendar date = relieveDay;
        this.relieveDay=null;
        setOutOfWorkTime(date, date2);
    }
    
    public List<String> getMacAdresses(){
        return macAdresses;
    }
    
    public void deleteMacAdressess(int i){
        macAdresses.remove(i);
    }
    
    public void deleteMacAdressess(String a){
        macAdresses.remove(a);
    }
    
    public void clearAllMacAdressess(){
        macAdresses.clear();
    }
    
    public void addMacAdress(String a){
        macAdresses.add(a);
    }
    
    public void setMacAdresses(ArrayList<String> a){
        macAdresses = a;
    }
    
    public static String dateFormat(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    
    public static GregorianCalendar stringToCal(String myString) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = format.parse(myString);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
    
    public String interviewerToString(){
        String line = this.id + " " + this.name + " " + this.surname + " " + dateFormat(this.hiredDay);
        for (String mac : this.macAdresses) {
            line = line + " " + mac;
        }
        return line;
    }
    
    public static Interviewer stringToInterviewer(String line) throws ParseException{
        String[] parts = line.split(" ");
        Interviewer interviewer = new Interviewer(parts[1], parts[2], parts[0], stringToCal(parts[3]));
        for (int i=4; i<parts.length; i++) {
            interviewer.addMacAdress(parts[i]);
        }
        return interviewer;
    }
    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Interviewer clone() throws CloneNotSupportedException {
	return (new Cloner()).deepClone(this);
    }
}