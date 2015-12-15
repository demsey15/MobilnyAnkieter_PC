package bohonos.demski.mieldzioc.mobilnyankieter.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import com.rits.cloning.Cloner;

/**
 * @author Dominik Demski
 */
public class DateTimeQuestion extends Question {
	private static final long serialVersionUID = 1L;

	private GregorianCalendar userAnswer; // sometimes may be null

	private boolean onlyTime;
	private boolean onlyDate;

	public DateTimeQuestion(String question) {
		super(question);
		onlyDate = false;
		onlyTime = false;
	}

	public DateTimeQuestion(String question, boolean obligatory, boolean onlyTime, boolean onlyDate) {
		super(question, obligatory);
		this.onlyDate = onlyDate;
		this.onlyTime = onlyTime;
	}
	
	public DateTimeQuestion(String question, boolean obligatory, String hint, boolean onlyTime, boolean onlyDate) {
				super(question, obligatory, hint);
				this.onlyDate = onlyDate;
				this.onlyTime = onlyTime;
			}

	/**
	 * Notice! List with answers should contains 3 or 6 elements in order: day
	 * (number) , month (number from 1 to 12), year (four digit number, after
	 * 1970), hour, minute, second If you don't want to have day, month or year
	 * just put any correct values.
	 * 
	 * @see bohonos.demski.mieldzioc.mobilnyankieter.questions.Question#setUserAnswers(java.util.List)
	 */
	@Override
	public boolean setUserAnswers(List<String> text) {
		userAnswer = null;

		if (text == null) {
			return false;
		}

		int textSize = text.size();

		if (!(textSize == 3 || textSize == 6)) {
			return false;
		}

		int day = 0;
		int month = 0;
		int year = 0;
		int hour = 1;
		int minute = 1;
		int second = 1;

		try {
			day = Integer.parseInt(text.get(0));
			month = Integer.parseInt(text.get(1));
			year = Integer.parseInt(text.get(2));
			
			if (textSize == 6) {
				hour = Integer.parseInt(text.get(3));
				minute = Integer.parseInt(text.get(4));
				second = Integer.parseInt(text.get(5));
			}
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			GregorianCalendar answer = new GregorianCalendar(year, month - 1, day, hour, minute, second);
			userAnswer = answer;
			
			return true;
		} catch (Exception e) {
			userAnswer = null;

			return false;
		}
	}

	/**
	 * Zwraca listê z odpowiedziami u¿ytkownika; w zaleznoœci od typu pytania na
	 * kolejnych miejscach znajduj¹ siê: dzieñ, miesi¹c (cyfra od 1 do 12), rok
	 * albo godzina, minuta, sekunda. W przypadku braku odpowiedzi zwracana jest
	 * pusta lista.
	 */
	@Override
	public List<String> getUserAnswersAsStringList() {
		List<String> list = new ArrayList<String>(3);
		if (userAnswer != null) {
			if (isOnlyDate()) {
				list.add("" + userAnswer.get(GregorianCalendar.DAY_OF_MONTH));
				list.add("" + (userAnswer.get(GregorianCalendar.MONTH) + 1));
				list.add("" + userAnswer.get(GregorianCalendar.YEAR));
			} else if (isOnlyTime()) {
				list.add("" + (userAnswer.get(GregorianCalendar.HOUR_OF_DAY)));
				list.add("" + userAnswer.get(GregorianCalendar.MINUTE));
				list.add("" + userAnswer.get(GregorianCalendar.SECOND));
			}
		}
		return list;
	}

	@Override
	public List<String> getAnswersAsStringList() {
		return null;
	}

	@Override
	public boolean isAnswered() {
		return userAnswer != null;
	}

	public boolean isOnlyTime() {
		return onlyTime;
	}

	public void setOnlyTime(boolean onlyTime) {
		this.onlyTime = onlyTime;
		this.onlyDate = !onlyTime;
	}

	public boolean isOnlyDate() {
		return onlyDate;
	}

	public void setOnlyDate(boolean onlyDate) {
		this.onlyDate = onlyDate;
		this.onlyTime = !onlyDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (this.getClass() != o.getClass()) {
			return false;
		}

		DateTimeQuestion o2 = (DateTimeQuestion) o;

		return super.equals(o2) && Objects.equals(userAnswer, o2.userAnswer) && this.onlyDate == o2.onlyDate
				&& this.onlyTime == o2.onlyTime;
	}

	@Override
	public DateTimeQuestion clone() throws CloneNotSupportedException {
		return (new Cloner()).deepClone(this);
	}
}
