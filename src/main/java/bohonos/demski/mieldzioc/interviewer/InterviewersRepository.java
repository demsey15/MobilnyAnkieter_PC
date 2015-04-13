/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.interviewer;

/**
 *
 * @author Delirus
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Delirus
 */
public class InterviewersRepository {
    private List<Interviewer> interviewers = new ArrayList<Interviewer>();
    
    public void addInterviewer (Interviewer interviewer){
        interviewers.add(interviewer);
    }
    
    public Interviewer getInterviewer(int idi){
        for(Interviewer person : interviewers)
        {
            if(person.id==idi)
                return person;
        }
        return null;
    }
  public List<Interviewer> getAllInterviewers(){
      return interviewers;
  }
  public List<Interviewer> getAllInterviewers(GregorianCalendar from){ 
      List<Interviewer> lista = new ArrayList<Interviewer>();
      for(Interviewer person : interviewers){
          if(from.compareTo(person.hiredDay)<=0)
              lista.add(person);
      }
      return lista;
  }
  public List<Interviewer> getAllInterviewers(GregorianCalendar from, GregorianCalendar to){
      List<Interviewer> lista = new ArrayList<Interviewer>();
      for(Interviewer person : interviewers){
          if(from.compareTo(person.hiredDay)>=0 && to.compareTo(person.hiredDay)<=0) //sprawdŸ ten warunek
              lista.add(person);
      }
      return lista;
  }
  
}
