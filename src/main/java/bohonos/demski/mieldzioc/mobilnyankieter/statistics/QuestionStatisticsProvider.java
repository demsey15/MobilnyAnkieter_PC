/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import bohonos.demski.mieldzioc.mobilnyankieter.questions.*;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Delirus
 */
public class QuestionStatisticsProvider {
    
    /**
     * Metoda zwraca œredni¹ dla pewnego numeru pytania, które pochodzi z wybranego rodzaju ankiety.
     * @param surveys
     * @param number
     * @return 
     */
    public float getMean(List<Survey> surveys, int number){    //pamiêtaj o indeksowaniu pytañ w survey
        float sum=0;
        int answers=0; // liczba odpowiedzi na wybrane pytanie
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int typeQuestion = survey.getQuestion(number).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion question = (ScaleQuestion) survey.getQuestion(number); 
                    sum+=question.getUserAnswer();
                    answers+=1;
                }
            }
        }
        if(answers!=0){
            return (float) sum/answers;
        }
        else
            return 0;
    }
    
    /**
     * Metoda zwraca œredni¹ dla wybranego pytania z pewnego rodzaju ankiety.
     * @param surveys
     * @param question
     * @return 
     */
      public float getMean(List<Survey> surveys, Question question){    //pamiêtaj o indeksowaniu pytañ w survey
        float sum=0;
        int answers=0; // liczba odpowiedzi na wybrane pytanie
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int numberOfQuestion = survey.indexOfQuestion(question);
                int typeQuestion = survey.getQuestion(numberOfQuestion).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(numberOfQuestion); 
                    sum+=quest.getUserAnswer();
                    answers+=1;
                }
            }
        }
        if(answers!=0){
            return (float) sum/answers;
        }
        else
            return -1;
    }
    
      /**
       * Metoda zwraca odchylenie standardowe dla wybranego pytania z konkretego rodzaju ankiety.
       * @param surveys
       * @param questionNumber
       * @return 
       */
    public float getStandardDeviation(List<Survey> surveys, int questionNumber){
        int answers = 0;
        float variance= 0;
        float mean = getMean(surveys, questionNumber);
        for(Survey survey : surveys){
            if(survey.isFinished()){               
                int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(questionNumber); 
                    variance+=Math.pow((quest.getUserAnswer() - mean),2);
                    answers+=1;
                }
            }
        }     
        float standardDeviation;
        if(answers == 0){
            standardDeviation = (float) Math.sqrt(variance/answers);
        }
        else{
            standardDeviation = -1;
        }
        return standardDeviation;
    }
    
    /**
     * Metoda zwraca medianê dla wybranego pytania typu skala dla konkretego rodzaju ankiety.
     * @param surveys
     * @param questionNumber
     * @return 
     */
    public float getMedian(List<Survey> surveys, int questionNumber){
        float median ;
        List<Integer> lista = new ArrayList<Integer>();
        for(Survey survey : surveys){
            if(survey.isFinished()){               
                int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(questionNumber); 
                    lista.add(quest.getUserAnswer());
                }
            }
        }
        Collections.sort(lista);
        int middle = lista.size()/2;
        if(lista.size()%2==1)
        {
            median = lista.get(middle);
        }
        else{
            median = (float) ((lista.get(middle-1) + lista.get(middle)) / 2.0);
        }
        return median;
    }
    
    /**
     * Metoda zwraca najczêœciej udzielan¹ odpowiedŸ dla wybranego pytania, które pochodzi z pewnego rodzaju ankiety.
     * @param surveys
     * @param questionNumber
     * @return 
     */
    public float getMode(List<Survey> surveys, int questionNumber){
        float mode=0;
        int answer = 0;
        HashMap<Integer, Integer> mapa =new HashMap<Integer, Integer>();
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(questionNumber);
                    if(mapa.containsKey(quest.getUserAnswer())){
                        mapa.put(quest.getUserAnswer(), mapa.get(quest.getUserAnswer()) +1);
                    }
                    else{
                        mapa.put(quest.getUserAnswer(), 1);
                    }
                }
            }
        }
        for(HashMap.Entry<Integer,Integer> entr: mapa.entrySet()){           
            if(mode<entr.getValue()){
                mode=entr.getValue();
                answer=entr.getKey();
            }              
        }
        return answer;
    }
    
    /**
     * Metoda zwraca minimaln¹ otrzyman¹ odpowiedŸ dla pytania skala pochodz¹cego z jakiegoœ rodzaju ankiet.
     * @param surveys
     * @param questionNumber
     * @return 
     */
    public float getMinValue(List<Survey> surveys, int questionNumber){
        float min=0;
        List<Integer> lista = new ArrayList<Integer>();
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(questionNumber);
                    lista.add(quest.getUserAnswer());
                }
            }
        }
        Collections.sort(lista);
        min=lista.get(0);
        return min;
    }
    
    /**
     * Metoda zwraca maksymaln¹ otrzyman¹ odpowiedŸ dla pytania skala pochodz¹cego z jakiegoœ rodzaju ankiet.
     * @param surveys
     * @param questionNumber
     * @return 
     */
    public float getMaxValue(List<Survey> surveys, int questionNumber){
        float max=0;
        List<Integer> lista = new ArrayList<Integer>();
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
                if(typeQuestion==5){
                    ScaleQuestion quest = (ScaleQuestion) survey.getQuestion(questionNumber);
                    lista.add(quest.getUserAnswer());
                }
            }
        }
        Collections.sort(lista);
        max=lista.get(lista.size()-1);
        return max;
    }
    
    /**
     * Metoda obliczaj¹ca jaki procent ankietowanych wybra³ odpowiedni¹ odpowiedŸ w konkretnym pytaniu.
     * @param surveys
     * @param questionNumber
     * @return 
     */
    public List<Float> getPrecentageOfChoosedOptions(List<Survey> surveys, int questionNumber){
        List<Float> lista = new ArrayList<Float>();
        HashMap<String, Integer> mapa =new HashMap<String, Integer>();
        List<String> answers = new ArrayList<String>();
        //inincjalizujemy mozliwe odpowiedzi w Hashmapie
        answers = surveys.get(0).getQuestion(questionNumber).getAnswersAsStringList();
        for(String answer : answers){
            mapa.put(answer, 0);
        }
        
        for(Survey survey : surveys){
            int typeQuestion = survey.getQuestion(questionNumber).getQuestionType();
            if(typeQuestion==0 || typeQuestion==1 ){
                if(survey.isFinished()){
                    List<String> userAnswers = new ArrayList<String>();
                    userAnswers = survey.getQuestion(questionNumber).getUserAnswersAsStringList();
                    for(String userAnswer : userAnswers){           
                        mapa.put(userAnswer, mapa.get(userAnswer)+1);                   
                    }
                }
            }
        }
        for(HashMap.Entry<String,Integer> entr: mapa.entrySet()){           
            float percent = 100*entr.getValue()/surveys.size();
            lista.add(percent);                         
        }
        
        return lista;
    }
    
   
}
