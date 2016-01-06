/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.statistics;
import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
import bohonos.demski.mieldzioc.mobilnyankieter.interviewer.Interviewer;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import bohonos.demski.mieldzioc.mobilnyankieter.questions.*;
import bohonos.demski.mieldzioc.mobilnyankieter.survey.Survey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
    public double getMean(List<Survey> surveys, int number){    //pamiêtaj o indeksowaniu pytañ w survey
        float sum=0;
        int answers=0; // liczba odpowiedzi na wybrane pytanie
        for(Survey survey : surveys){
            if(survey.isFinished()){
                int typeQuestion = survey.getQuestion(number).getQuestionType();
                //System.out.println("Typ pytania " + typeQuestion);
                if(typeQuestion==5){
                    ScaleQuestion question = (ScaleQuestion) survey.getQuestion(number); 
                    //System.out.println("OdpowiedŸ: " + question.getUserAnswer());
                    sum+=question.getUserAnswer();
                    answers+=1;
                }
            }
        }
        if(answers!=0){
            //System.out.println("Suma i liczba odpowiedzi " + sum +" "+answers);
            return (double) sum/answers;
        }
        else{
            return 0;
        }
    }
    
    /**
     * Metoda zwraca œredni¹ dla wybranego pytania z pewnego rodzaju ankiety.
     * @param surveys
     * @param question
     * @return 
     */
      public double getMean(List<Survey> surveys, Question question){    //pamiêtaj o indeksowaniu pytañ w survey
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
            return (double) sum/answers;
        }
        else{
            return -1;
        }
        
    }
    
      /**
       * Metoda zwraca odchylenie standardowe dla wybranego pytania z konkretego rodzaju ankiety.
       * @param surveys
       * @param questionNumber
       * @return 
       */
    public double getStandardDeviation(List<Survey> surveys, int questionNumber){
        int answers = 0;
        float variance= 0;
        double mean = getMean(surveys, questionNumber);
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
        double standardDeviation;
        if(answers != 0){
            standardDeviation =  Math.sqrt(variance/answers);
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
                if(survey.isFinished() && survey.getQuestion(questionNumber).isAnswered()){
                    List<String> userAnswers = new ArrayList<String>();
                    userAnswers = survey.getQuestion(questionNumber).getUserAnswersAsStringList();
                    for(String userAnswer : userAnswers){           
                        mapa.put(userAnswer, mapa.get(userAnswer)+1);                   
                    }
                }
            }
        }
        for(HashMap.Entry<String,Integer> entr: mapa.entrySet()){           
            float percent = (float) 100*entr.getValue()/surveys.size();
            lista.add(percent);                         
        }
        
        return lista;
    }
    
   public int getNumberUsersAnswers(List<Survey> surveys, int numberOfQuestion){
       int number=0;
       for(Survey survey : surveys){
           if(survey.getQuestion(numberOfQuestion).isAnswered()){
               number++;
           }
       }
       return number;
   }
   
   public List<Pair<String, Integer>> getTheMostFrequentAnswers(List<Survey> surveys, int numberOfQuestion){
        List<Pair<String, Integer>> lista = new ArrayList<Pair<String, Integer>>();
        
        for(Survey survey : surveys){
            int typeQuestion = survey.getQuestion(numberOfQuestion).getQuestionType();
            if(typeQuestion==6 || typeQuestion==7 || typeQuestion==4){
               if(survey.getQuestion(numberOfQuestion).isAnswered()){
                   String ans = "";
                   //= survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList();
                   if(typeQuestion==6){
                       ans = getDateQuestionAsString(survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList());
                   }
                   if(typeQuestion==4){
                       ans = getTextQuestionAsString(survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList());
                   }
                   if(typeQuestion==7){
                       ans = getDateQuestionAsString(survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList());
                        //System.out.println("OdpowiedŸ time: " + survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList());
                   }
                   
                   if(lista.isEmpty()){
                       Pair<String,Integer> couple = new Pair<String, Integer>();
                       couple.setFirst(ans);
                       couple.setSecond(1);
                       lista.add(couple);
                   }
                   else{
                       boolean ster = true;
                       for(Pair<String, Integer> pair : lista){ 
                           if(pair.getFirst().equals(ans)){
                               pair.setSecond(pair.getSecond()+1);
                               ster=false;
                               break;
                           }
                       }
                       if(ster){
                           Pair<String,Integer> coup = new Pair<String, Integer>();
                           coup.setFirst(ans);
                           coup.setSecond(1);
                           lista.add(coup);
                       }
                   }
               }
            }
            if(typeQuestion==3){
                if(survey.getQuestion(numberOfQuestion).isAnswered()){
                    //String ans = "";
                    List<String> answers = survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList();
                    //System.out.println("Odpowiedx grid: "+ survey.getQuestion(numberOfQuestion).getUserAnswersAsStringList());
                    for(String ans : answers){
                        if(lista.isEmpty()){
                            Pair<String,Integer> couple = new Pair<String, Integer>();
                            couple.setFirst(ans);
                            couple.setSecond(1);
                            lista.add(couple);
                        }
                        else{
                            boolean ster = true;
                            for(Pair<String, Integer> pair : lista){ 
                                if(pair.getFirst().equals(ans)){
                                     pair.setSecond(pair.getSecond()+1);
                                     ster=false;
                                    break;
                                }
                            }
                            if(ster){
                                Pair<String,Integer> coup = new Pair<String, Integer>();
                                coup.setFirst(ans);
                                coup.setSecond(1);
                                lista.add(coup);
                            }
                        }
                    }
                }
            }
        }
        
        lista.sort(new Comparator<Pair<String, Integer>>() {
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            if (o1.getSecond() > o2.getSecond()) {
                return -1;
            } else if (o1.getSecond().equals(o2.getSecond())) {
                return 0; // You can change this to make it then look at the
                          //words alphabetical order
            } else {
                return 1;
            }
        }
    });
        
        return lista;
    }
   
   private String getDateQuestionAsString(List<String> list){
       String a = list.get(0)+"."+list.get(1)+"."+list.get(2);
       return a;
   }

    private String getTextQuestionAsString(List<String> answer) {
        String a = "";
        for(String ans : answer){
            a+=ans+" ";
        }
       return a;
    }
}
