/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.statistics;

import bohonos.demski.mieldzioc.common.Pair;
import bohonos.demski.mieldzioc.interviewer.Interviewer;
import bohonos.demski.mieldzioc.survey.Survey;
import java.util.*;
import static javax.swing.UIManager.get;

/**
 *
 * @author Delirus
 */
public class InterviewerStatisticsProvider {
    
    /**
     * Metoda s�uzy do obliczania dni pomi�dzy dwoma datami.
     * @param d1
     * @param d2
     * @return 
     */
    private int daysBetween(Date d1, Date d2)
   {
      return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
   }
    /**
     * metoda zwraca liczb� dni, w kt�rych ankieter by� zatrudniony
     * @param interviewer
     * @return days
     */
    private float numberOfDaysInWork(Interviewer interviewer){
        float days=0;
        List<Pair<GregorianCalendar,GregorianCalendar>> outOfWork = interviewer.getOutOfWorkTime();
        if(interviewer.isActive()){
            Date now = new Date();
            days+=daysBetween(interviewer.getHiredDay().getTime(), now);
            for(Pair<GregorianCalendar, GregorianCalendar> entr : outOfWork){
                days-=daysBetween(entr.getFirst().getTime(), entr.getSecond().getTime());
            }
        }
        else
        {
            days+= daysBetween(interviewer.getHiredDay().getTime(), interviewer.getRelieveDay().getTime());
            for(Pair<GregorianCalendar, GregorianCalendar> entr : outOfWork){
                days-=daysBetween(entr.getFirst().getTime(), entr.getSecond().getTime());
            }
        }
        return days;
    } 
    
    /**
     * Metoda s�u�y do obliczania ilo�ci dni, w kt�rych pracowa� ankieter od pewnej ustalonej daty
     * @param interviewer
     * @param from
     * @return 
     */
     private float numberOfDaysInWork(Interviewer interviewer, GregorianCalendar from){
        float days=0;
        List<Pair<GregorianCalendar,GregorianCalendar>> outOfWork = interviewer.getOutOfWorkTime();
        if(interviewer.isActive()){
            Date now = new Date();
            days+=daysBetween(from.getTime(), now);
            for(Pair<GregorianCalendar, GregorianCalendar> entr : outOfWork){
                if( from.compareTo(entr.getFirst())<=0){
                    days-=daysBetween(entr.getFirst().getTime(), entr.getSecond().getTime());
                }
                if(from.compareTo(entr.getFirst())>=0 && from.compareTo(entr.getSecond())<=0){
                    days-=daysBetween(from.getTime(), entr.getSecond().getTime());
                }
            }
        }
        else
        {
            days+= daysBetween(interviewer.getHiredDay().getTime(), interviewer.getRelieveDay().getTime());
            for(Pair<GregorianCalendar, GregorianCalendar> entr : outOfWork){
                if( from.compareTo(entr.getFirst())<=0){
                    days-=daysBetween(entr.getFirst().getTime(), entr.getSecond().getTime());
                }
                if(from.compareTo(entr.getFirst())>=0 && from.compareTo(entr.getSecond())<=0){
                    days-=daysBetween(from.getTime(), entr.getSecond().getTime());
                }
            }
        }
        return days;
    } 
     
     /**
      * Metoda s�u�y do obliczenia liczby dni przepracowanych przez ankietera w podanym okresie. Rozwa�amy cztery przypadki nak�adania si� dat.
      * @param interviewer
      * @param from
      * @param to
      * @return 
      */
      private float numberOfDaysInWork(Interviewer interviewer, GregorianCalendar from, GregorianCalendar to){
        float days=0;
        List<Pair<GregorianCalendar,GregorianCalendar>> outOfWork = interviewer.getOutOfWorkTime();
            days+=daysBetween(from.getTime(), to.getTime());
            for(Pair<GregorianCalendar, GregorianCalendar> entr : outOfWork){
                if(from.compareTo(entr.getFirst())<=0 && to.compareTo(entr.getSecond())>=0){ //nie nak�adaja si�
                     days-=daysBetween(entr.getFirst().getTime(), entr.getSecond().getTime());
                }
                if(from.compareTo(entr.getFirst())>=0 && to.compareTo(entr.getSecond())>=0 && from.compareTo(entr.getSecond())<=0){ //from jest w srodku okresu niepracuj�cego
                     days-=daysBetween(from.getTime(), entr.getSecond().getTime());
                }
                if(from.compareTo(entr.getFirst())<=0 && to.compareTo(entr.getSecond())<=0 && entr.getFirst().compareTo(to)<=0){ // to jest w �rodku okresu niepracuj�cego
                     days-=daysBetween(entr.getFirst().getTime(), to.getTime());
                }
                if(from.compareTo(entr.getFirst())>=0 && to.compareTo(entr.getSecond())<=0){//obie daty sa w srodku okresu niepracuj�cego
                     days-=daysBetween(from.getTime(), to.getTime());
                }
            }
        
    
        return days;
    } 
    
      /**
       * Metoda zwraca �redni� liczb� zebranych ankiet przez ankietera na dzie� w ci�gu okresu, wkt�rym pracowa� 
       * @param surveys
       * @param interviewer
       * @return 
       */
    public float getMeanFilledSurveysOnADay(List<Survey> surveys, Interviewer interviewer) {
        float mean;
        float days = numberOfDaysInWork(interviewer);
       long numberOfSurveys=0;
       if(surveys != null){
        for(Survey survey : surveys){
            if(survey.getFinishTime()!=null){
                numberOfSurveys+=1;
            }
        }
        mean=numberOfSurveys/days;
        return mean;
        }
       else {
           return 0;
       }
    }
    
    /**
     * Metoda zwraca �redni� liczb� zebranych ankiet przez ankietera na dzie�, od wybranego dnia, liczone s� dni, w kt�rych pracowa�
     * @param surveys
     * @param interviewer
     * @param from
     * @return 
     */
    public float getMeanFilledSurveysOnADay(List<Survey> surveys, Interviewer interviewer, GregorianCalendar from) {
        float mean;
        float days = numberOfDaysInWork(interviewer, from);
        long numberOfSurveys=0;
        for(Survey survey : surveys){
            if(survey.getFinishTime().compareTo(from)>=0){
                numberOfSurveys+=1;
            }
        }
        mean=numberOfSurveys/days;
        return mean;
    }
    
    /**
     * Metoda zwraca �rednia liczb� zebranch ankiet na dzie� przez wybranego ankietera, licz� si� tylko te dni, wkt�rych pracowa�.
     * @param surveys
     * @param interviewer
     * @param from
     * @param to
     * @return 
     */
     public float getMeanFilledSurveysOnADay(List<Survey> surveys, Interviewer interviewer, GregorianCalendar from, GregorianCalendar to) {
        float mean;
        float days = numberOfDaysInWork(interviewer, from, to);
        long numberOfSurveys=0;
        for(Survey survey : surveys){
            if(survey.getFinishTime().compareTo(from)>=0 && survey.getFinishTime().compareTo(to)<=0){
                numberOfSurveys+=1;
            }
        }
        mean=numberOfSurveys/days;
        return mean;
    }
     
     /**
      * Metoda zwraca map�, kt�ra zawiera jako klucze poszczeg�lne dni w kt�rych ankieter zebra� jakie� ankiety, za� warto�ciami s� liczby zebranych ankiet w tym dniu.
      * @param surveys
      * @param interviewer
      * @return 
      */
    public HashMap<GregorianCalendar, Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys, Interviewer interviewer){
        HashMap<GregorianCalendar, Integer> filledSurveysInDays = new HashMap<GregorianCalendar, Integer>();
        for(Survey survey : surveys){
           GregorianCalendar data = survey.getFinishTime();
           int year = data.get(Calendar.YEAR);
           int month = data.get(Calendar.MONTH);
           int day = data.get(Calendar.DAY_OF_MONTH);
           GregorianCalendar date = new GregorianCalendar(year, month, day);
           if(!filledSurveysInDays.containsKey(date)){
               filledSurveysInDays.put(date, 1);
           }
           else{
               filledSurveysInDays.put(date, filledSurveysInDays.get(date)+1);
           }
        }
        return filledSurveysInDays;
    }
    
    /**
     * Metoda zwraca map�, kt�ra zawiera jako klucze poszczeg�lne dni (od wybranego dnia - from) w kt�rych ankieter zebra� jakie� ankiety, za� warto�ciami s� liczby zebranych ankiet w tym dniu.
     * @param surveys
     * @param interviewer
     * @param from
     * @return 
     */
        public HashMap<GregorianCalendar, Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys, Interviewer interviewer, GregorianCalendar from){
        HashMap<GregorianCalendar, Integer> filledSurveysInDays = new HashMap<GregorianCalendar, Integer>();
        for(Survey survey : surveys){
           GregorianCalendar data = survey.getFinishTime();
           int year = data.get(Calendar.YEAR);
           int month = data.get(Calendar.MONTH);
           int day = data.get(Calendar.DAY_OF_MONTH);
           GregorianCalendar date = new GregorianCalendar(year, month, day);
           if(date.compareTo(from)>=0){
                if(!filledSurveysInDays.containsKey(date)){
                    filledSurveysInDays.put(date, 1);
                }
                else{
                    filledSurveysInDays.put(date, filledSurveysInDays.get(date)+1);
                }
           }
        }
        return filledSurveysInDays;
    }
        
        /**
         * Metoda zwraca map�, kt�ra zawiera jako klucze poszczeg�lne dni zadanego okresu (od from do to),w kt�rych ankieter zebra� jakie� ankiety, za� warto�ciami s� liczby zebranych ankiet w tym dniu.
         * @param surveys
         * @param interviewer
         * @param from
         * @param to
         * @return 
         */
        public HashMap<GregorianCalendar, Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys, Interviewer interviewer, GregorianCalendar from, GregorianCalendar to){
        HashMap<GregorianCalendar, Integer> filledSurveysInDays = new HashMap<GregorianCalendar, Integer>();
        for(Survey survey : surveys){
           GregorianCalendar data = survey.getFinishTime();
           int year = data.get(Calendar.YEAR);
           int month = data.get(Calendar.MONTH);
           int day = data.get(Calendar.DAY_OF_MONTH);
           GregorianCalendar date = new GregorianCalendar(year, month, day);
             if(date.compareTo(from)>=0 && to.compareTo(date)>=0){
                if(!filledSurveysInDays.containsKey(date)){
                    filledSurveysInDays.put(date, 1);
                }
                else{
                    filledSurveysInDays.put(date, filledSurveysInDays.get(date)+1);
                }
           }
        }
        return filledSurveysInDays;
    }
     
    /**
     * Metoda zwraca liczb� wype�nionych ankiet.
     * @param surveys
     * @return 
     */
    public int getAmountOfFilledSurveys(List<Survey> surveys){
        int amount=0;
        if(surveys!=null){
        for(Survey survey : surveys){
            if(survey.getFinishTime()!=null){
                amount+=1;
            }
        }
        return amount;
    }
    else{
        return 0;
    }
}
}
