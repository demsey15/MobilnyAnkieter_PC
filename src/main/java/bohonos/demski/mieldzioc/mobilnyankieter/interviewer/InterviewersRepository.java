/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bohonos.demski.mieldzioc.mobilnyankieter.interviewer;

/**
 *
 * @author Delirus
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Delirus
 */
public class InterviewersRepository {
    private List<Interviewer> interviewers = new ArrayList<Interviewer>();
    
    public InterviewersRepository() throws IOException, FileNotFoundException, ParseException{
        interviewers = new ArrayList<Interviewer>();
        this.loadFromFile();
    }
    
    /**
     * Metoda addInterviewer() dodaje ankietera do listy. Je�li si� uda�o doda� ankietera do listy (nie ma na li�cie ankieteter�w kogo� o tym samym id), to metoda zwraca true, w przeciwnym wypadku false.
     * @param interviewer
     * @return 
     */
    public boolean addInterviewer (Interviewer interviewer){
        if(checkListInterviewers(interviewer))
            return false;
        else{
            interviewers.add(interviewer);
            return true;
        }
            
    }
    /**
     * Dostajemy ankietera o podanym id.
     * @param idi
     * @return 
     */
    public Interviewer getInterviewer(String idi){
        for(Interviewer person : interviewers)
        {
            if(person.getId().equals(idi))
                return person;
        }
        return null;
    }
    
  public List<Interviewer> getAllInterviewers(){
      return interviewers;
  }
  /**
   * U�ywaj�c metody getAllInterviewers() z parametrem from otrzymujemy list� wszystkich zatrudnionych aniekter�w od podanego dnia
   * @param from
   * @return lista
   */
  /*
  public List<Interviewer> getAllInterviewers(GregorianCalendar from){ 
      List<Interviewer> lista = new ArrayList<Interviewer>();
      for(Interviewer person : interviewers){
          if(from.compareTo(person.getHiredDay())<=0)
              lista.add(person);
      }
      return lista;
  }
  */
  /**
   * U�ywaj�c metody getAllInterviewers() z parametrami from i to, dostajemy list� ankieter�w zatrudnionych od dnia from do dnia to w��cznie z tym dniem. 
   * @param from
   * @param to
   * @return lista
   */
  /*
  public List<Interviewer> getAllInterviewers(GregorianCalendar from, GregorianCalendar to){
      List<Interviewer> lista = new ArrayList<Interviewer>();
      for(Interviewer person : interviewers){
          if(from.compareTo(person.getHiredDay())>=0 && to.compareTo(person.getHiredDay())<=0) //sprawd� ten warunek
              lista.add(person);
      }
      return lista;
  }*/
  /**
   * Metoda checkListInterviewers() sprawdza czy podany ankieter nie znajduje si� ju� przypadkiem na li�cie. Je�li si� znajduje, to dostajemy true. W przeciwnym wypadku false. 
   * @param man
   * @return true or false
   */
  public boolean checkListInterviewers(Interviewer man){
      for(Interviewer person : interviewers){
          if(man.getId().equals(person.getId()))
              return true;
      }
      return false;
  }
  
  /**
   * Metoda equalsInterviewers por�wnuje dw�ch ankieter�w po id i zwraca true, je�li s� to Ci sami ankieterzy, afalse gdy nie s�.
   * @param man1
   * @param man2
   * @return true or false
   */
  public boolean equalsInterviewers(Interviewer man1,Interviewer man2){
        return man1.getId().equals(man2.getId());
  }
  
  public void saveRepository() throws FileNotFoundException, UnsupportedEncodingException {
      String interviewersPath = "C:" + File.separator + "ankieter" + File.separator + "interviewers.txt";
      //File interviewersFile = new File (interviewersPath);
      PrintWriter writer = new PrintWriter(interviewersPath, "UTF-8");
      for (Interviewer interviewer : interviewers) {
          writer.println(interviewer.interviewerToString());
      }
    writer.close();
  }
  
  public void loadFromFile() throws FileNotFoundException, IOException, ParseException {
      String interviewersPath = "C:" + File.separator + "ankieter" + File.separator + "interviewers.txt";
      //BufferedReader br = new BufferedReader(new FileReader(interviewersPath));
      Charset ch = Charset.forName("UTF-8");
      Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(interviewersPath),ch));
      //Scanner scan = new Scanner(new File(interviewersPath));
        //try {
            //StringBuilder sb = new StringBuilder();
            //String line = br.readLine();
            String line;
            //while (line != null) {
            while(scan.hasNextLine()){
                line = scan.nextLine();
                System.out.println("Wczytana linia: " + line);
                boolean addInterviewer = this.addInterviewer(Interviewer.stringToInterviewer(line));
                //line = br.readLine();
                
            }
            //String everything = sb.toString();
        //} finally {
            //br.close();
        //}
  }
  public List<String> getMacAdress(List<Interviewer> inter){
      List<String> macs = new ArrayList();

        for(Interviewer i : inter){
              //List<String> w= i.getMacAdresses();
            for(String m : i.getMacAdresses()){
                  macs.add(m);
            }
        }
        return macs;
  }
  //edytowanie listy , usuwanie ankieter�w i te sprawy trzeba doda�

    public List<Interviewer> getSelectedInterviewers(List<String> macs) {
        List<Interviewer> list = new ArrayList<Interviewer>();
        for(String mac : macs){
            System.out.println("Podany adres mac: "+mac);
            for(Interviewer interviewer : interviewers){
                if(adressMacBelongToInterviewer(interviewer,mac)){
                    list.add(interviewer);
                }
            }
        }
        return list;
    }
    
    private boolean adressMacBelongToInterviewer(Interviewer interviewer, String mac){
        for(String s : interviewer.getMacAdresses()){
            if(s.equals(mac)){
                return true;
            }
        }
        return false;
    }
}
