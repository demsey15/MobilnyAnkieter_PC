/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;

import java.util.*;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

/**
 *
 * @author Delirus
 */
public class SurveysStatisticsProvider {
    
    /**
     * Funkcja oblicza ile minut u�ytkownik wype�nia� ankiet�.
     * @param d1
     * @param d2
     * @return 
     */
    private float timeInfillSurvey(Date d1, Date d2)
   {
      return (float)( (d1.getTime() - d2.getTime()) / (1000 * 60 ));
   }
    
    /**
     * Funkcja oblicza �redni czas wype�niania ankiet.
     * @param surveys
     * @return 
     */
    public float getMeanTimeOfInfillSurvey(List<Survey> surveys){
        float time=0;
        float mean;
        if(surveys.size()>0){
        for(Survey survey : surveys){
            if(survey.isFinished()){
                time+=timeInfillSurvey(survey.getFinishTime().getTime(), survey.getStartTime().getTime());
            }
        }
        mean = time/getNumberOfFilledSurveys(surveys);
        return mean;
        }
        else{
            return 0;
        }
    }
    
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
     * Metoda oblicza �redni� liczb� ankiet wype�nianych na dzie�, pocz�wszy od dnia pierwszego wype�nienia tego rodzaju ankiety po dzie� dzisiejszy.
     * @param surveys
     * @return 
     */
    public float getMeanFilledSurveysOnADay(List<Survey> surveys){
        float amount = 0;
        float mean;
        GregorianCalendar begin = new GregorianCalendar();
        GregorianCalendar now = new GregorianCalendar();
        if(surveys!=null){
        for(Survey survey : surveys){
            if(survey.isFinished()){
                amount+=1;
                if(begin.compareTo(survey.getFinishTime())>=0){
                    begin=survey.getFinishTime();
                }
            }
        }
        mean = amount/daysBetween(begin.getTime(), now.getTime()); 
        return mean;
        }
        else{
                return 0;
        }
    }
    
    /**
     * Metoda oblicza �redni� liczb� ankiet wype�nianych na dzie�, pocz�wszy od konkretnego dnia po dzie� dzisiejszy.
     * @param surveys
     * @param from
     * @return 
     */
     public float getMeanFilledSurveysOnADay(List<Survey> surveys, GregorianCalendar from){
        float amount=0;
        float mean;
        GregorianCalendar now = new GregorianCalendar();
        for(Survey survey : surveys){
            if(survey.isFinished()&& survey.getFinishTime().compareTo(from)>=0){
                amount+=1;
            }
        }
        mean = amount/daysBetween(from.getTime(),now.getTime());
        return mean;
    }
     
     /**
      * Metoda oblicza �redni� liczb� ankiet wype�nianych na dzie� w wybranym przedziale czasowym.
      * @param surveys
      * @param from
      * @param to
      * @return 
      */
      public float getMeanFilledSurveysOnADay(List<Survey> surveys, GregorianCalendar from, GregorianCalendar to){
        float amount=0;
        float mean;
        for(Survey survey : surveys){
            if(survey.isFinished()&& survey.getFinishTime().compareTo(from)>=0&&survey.getFinishTime().compareTo(to)<=0){
                amount+=1;
            }
        }
        mean = amount/daysBetween(from.getTime(),to.getTime());
        return mean;
    }
    
    /**
     * Metoda zwraca liczb� wype�nionych ankiet.
     * @param surveys
     * @return 
     */
    public int getNumberOfFilledSurveys(List<Survey> surveys){
        int amount=0;
        if(surveys!=null){
        for(Survey survey : surveys){
            if(survey.isFinished()){
                amount+=1;
            }
        }
        return amount;
        }
        else{
            return 0;
        }
    }
        
    /**
     * Metoda zwraca par�: najbardziej aktywnego ankietera i procent zebranych przez niego ankiet.
     * @param surveys
     * @return 
     */
     public Pair<Interviewer,Float> getTheMostActiveInterviewerForSurveyAndHisPercentage(List<Survey> surveys){
         HashMap<Interviewer, Integer> mapa = new HashMap<Interviewer, Integer>();
         for(Survey survey : surveys){
             if(survey.isFinished()){
                 if(!mapa.containsKey(survey.getInterviewer())){
                     mapa.put(survey.getInterviewer(), 1);
                 }
                 else{
                     mapa.put(survey.getInterviewer(), mapa.get(survey.getInterviewer())+1);
                 }
             }
         }
         Pair<Interviewer,Float> pair = new Pair<Interviewer, Float>();
         float amount=0;
         float size = getNumberOfFilledSurveys(surveys);
         for(Map.Entry<Interviewer,Integer> entr: mapa.entrySet()){
             if(entr.getValue()>amount){
                 pair.setFirst(entr.getKey());
                 float percent = 100*entr.getValue()/size;
                 pair.setSecond(percent);
                 amount=entr.getValue();
             }
         }
         return pair;
     }
     
     /**
      * Metoda zwraca par�. Pierwszy argument zawiera ankietera, kt�ry zebra� najwi�cej ankiet, drugi argument pary zawiera jaki procent zebranych ankiet stanowi�y te ankiety, kt�re zebra� ankieter.
      * @param surveys
      * @param from
      * @param to
      * @return 
      */
     public Pair<Interviewer,Float> getTheMostActiveInterviewerForSurveyAndHisPercentage(List<Survey> surveys, GregorianCalendar from, GregorianCalendar to){
         List<Survey> goodSurvey = new ArrayList<Survey>();
         for(Survey survey : surveys){
             if(survey.isFinished()){
                 if(survey.getFinishTime().compareTo(from)>=0 && survey.getFinishTime().compareTo(to)<=0){
                     goodSurvey.add(survey);
                 }
             }
         }
         return getTheMostActiveInterviewerForSurveyAndHisPercentage(goodSurvey);
     }
     
     /**
      * Metoda zwraca map�, kt�rej kluczami s� dni w kt�rych ankiety zosta�y zebrane. Warto�� mapy m�wi nam ile zosta�o zebranych ankiet w konkretnym dniu.
      * @param surveys
      * @return 
      */
     public Map<GregorianCalendar,Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys) {
         HashMap<GregorianCalendar, Integer> mapa = new HashMap<GregorianCalendar, Integer>();
         for(Survey survey : surveys){
             if(survey.isFinished()){
                 GregorianCalendar data = survey.getFinishTime();
                int year = data.get(Calendar.YEAR);
                int month = data.get(Calendar.MONTH);
                int day = data.get(Calendar.DAY_OF_MONTH);
                GregorianCalendar date = new GregorianCalendar(year, month, day);
                 if(!mapa.containsKey(date)){
                     mapa.put(date, 1);
                 }
                 else{
                     mapa.put(date, mapa.get(date)+1);
                 }
             }
         }
         return mapa;
     }
     
     /**
      * Metoda zwraca map�, kt�rej kluczami s� dni w kt�rych ankiety zosta�y zebrane od pewnego dnia. Warto�� mapy m�wi nam ile zosta�o zebranych ankiet w konkretnym dniu.
      * @param surveys
      * @param from
      * @return 
      */
     public Map<GregorianCalendar,Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys, GregorianCalendar from) {
         List<Survey> goodSurvey = new ArrayList<Survey>();
         for(Survey survey : surveys){
             if(survey.isFinished()){
                 if(survey.getFinishTime().compareTo(from)>=0){
                     goodSurvey.add(survey);
                 }
             }
         }
         return getAmountOfFilledSurveysInDays(goodSurvey);
     }
     
     /**
      * Metoda zwraca map�, kt�rej kluczami s� dni w kt�rych ankiety zosta�y zebrane w okre�lonym przedziale czasowym (od jakiego� dnia, do kt�rego� dnia). Warto�� mapy dla wybranego klucza m�wi nam, ile zosta�o zebranych ankiet w tym konkretnym dniu.
      * @param surveys
      * @param from
      * @param to
      * @return 
      */
     public Map<GregorianCalendar,Integer> getAmountOfFilledSurveysInDays(List<Survey> surveys, GregorianCalendar from, GregorianCalendar to) {
         List<Survey> goodSurvey = new ArrayList<Survey>();
         for(Survey survey : surveys){
             if(survey.isFinished()){
                 if(survey.getFinishTime().compareTo(from)>=0 && survey.getFinishTime().compareTo(to)<=0){
                     goodSurvey.add(survey);
                 }
             }
         }
         return getAmountOfFilledSurveysInDays(goodSurvey);
     }
}