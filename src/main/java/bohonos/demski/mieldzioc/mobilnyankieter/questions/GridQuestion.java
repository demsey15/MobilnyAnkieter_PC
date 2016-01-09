package bohonos.demski.mieldzioc.mobilnyankieter.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.rits.cloning.Cloner;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;

/**
 * @author Dominik Demski
 * 
 */
public class GridQuestion extends Question {
	private static final long serialVersionUID = 1L;
	private List<String> columnLabels = new ArrayList<String>();
	private List<String> rowLabels = new ArrayList<String>();
	private List<Pair<Integer, Integer>> userAnswers = new ArrayList<Pair<Integer, Integer>>(); // Pair<rowNumber,
																								// columnNumber>

	public GridQuestion(String question) {
		super(question);
	}

	public GridQuestion(String question, boolean obligatory) {
		super(question, obligatory);
	}

	public GridQuestion(String question, boolean obligatory, String hint, List<String> columnLabels,
			List<String> rowLabels) {
		super(question, obligatory, hint);
		if (columnLabels == null) {
			this.columnLabels = new ArrayList<String>();
		} else
			this.columnLabels = columnLabels;
		if (rowLabels == null){
			this.rowLabels = new ArrayList<String>();
		}
		else{
			this.rowLabels = rowLabels;
		}
	}

	/**
	 * @return zwraca etykiety kolumn, nigdy nie zwróci null.
	 */
	public List<String> getColumnLabels() {
		return columnLabels;
	}

	/**
	 * Ustawia wartoœci etykiet kolumn, nie mog¹ byæ one nullem.
	 * 
	 * @param columnLabels
	 *            etykiety kolumn.
	 */
	public void setColumnLabels(List<String> columnLabels) {
		if (columnLabels == null) {
			throw new NullPointerException("Etykiety nie mog¹ byæ nullem");
		}

		this.columnLabels = columnLabels;
	}

	/**
	 * @return zwraca etykiety wierszy, nigdy nie zwróci null.
	 */
	public List<String> getRowLabels() {
		return rowLabels;
	}

	/**
	 * Ustawia wartoœci etykiet wierszy, nie mog¹ byæ one nullem.
	 * 
	 * @param rowLabels
	 *            etykiety wierszy.
	 */
	public void setRowLabels(List<String> rowLabels) {
		if (rowLabels == null)
			throw new NullPointerException("Etykiety nie mog¹ byæ nullem");
		this.rowLabels = rowLabels;
	}

	/**
	 * Each answer should be in format: #rowLabel# ^columnLabel^
	 * 
	 * @see bohonos.demski.mieldzioc.mobilnyankieter.questions.Question#setUserAnswers(java.util.List)
	 */
	@Override
	public boolean setUserAnswers(List<String> text) {
		userAnswers.clear();

		if (text == null) {
			return false;
		}

		Pair<Integer, Integer> coordinates;

		for (String answer : text) {
			coordinates = getCoordinatesOfAnswer(answer);
			if (coordinates == null) {
				userAnswers.clear();
				return false;
			}

			userAnswers.add(coordinates);
		}

		return true;
	}

	/**
	 * Each answer will be in format: #rowLabel# ^columnLabel^
	 * 
	 * @see bohonos.demski.mieldzioc.mobilnyankieter.questions.Question#getAnswersAsStringList()
	 */
	@Override
	public List<String> getAnswersAsStringList() {
		List<String> result = new ArrayList<String>();

		for (String row : rowLabels) {
			for (String column : columnLabels) {
				result.add("#" + row + "# ^" + column + "^");
			}
		}

		return result;
	}

	/**
	 * Each answer will be in format: #rowLabel# ^columnLabel^.
	 * 
	 * @return User's answers in format: #rowLabel# ^columnLabel^.
	 */
	@Override
	public List<String> getUserAnswersAsStringList() {
		List<String> result = new ArrayList<String>();

		for (Pair<Integer, Integer> coordinates : userAnswers) {
			result.add("#" + rowLabels.get(coordinates.getFirst()) + "# ^" + columnLabels.get(coordinates.getSecond())
					+ "^");
		}

		return result;
	}

	@Override
	public boolean isAnswered() {
		return userAnswers.size() > 0;
	}

	/**
	 * 
	 * @param answer
	 *            answer in format: #rowLabel# ^columnLabel^
	 * @return coordinates of this answer: first row, then column number; null
	 *         if format of the answer is wrong or there i any answer matches
	 *         given answer.
	 */
	public Pair<Integer, Integer> getCoordinatesOfAnswer(String answer) {
		String rowLab;
		String colLab;

		int firstHash = answer.indexOf("#");

		if (firstHash == -1) {
			return null;
		}

		int secondHash = answer.indexOf("#", firstHash + 1);
		if (secondHash == -1) {
			return null;
		}

		int firstBird = answer.indexOf("^", secondHash + 1);
		if (firstBird == -1) {
			return null;
		}

		int secondBird = answer.indexOf("^", firstBird + 1);
		if (secondBird == -1) {
			return null;
		}

		rowLab = answer.substring(firstHash + 1, secondHash);
		colLab = answer.substring(firstBird + 1, secondBird);

		int rowIndex = rowLabels.indexOf(rowLab);
		int colIndex = columnLabels.indexOf(colLab);

		if (rowIndex == -1 || colIndex == -1) {
			return null;
		}

		return new Pair<Integer, Integer>(rowIndex, colIndex);
	}

	/**
	 * 
	 * @param coordinates
	 *            coordinates of answer to return (first row's number, then
	 *            column's number).
	 * @return null if the coordinates are out of range, otherwise answer in
	 *         format #rowLabel# ^columnLabel^.
	 */
	public String getAsnwerForCoordinates(Pair<Integer, Integer> coordinates) {
		int rowNumber = coordinates.getFirst();
		int colNumber = coordinates.getSecond();

		if (rowNumber < 0 || rowNumber >= rowLabels.size() || colNumber < 0 || colNumber >= columnLabels.size())
			return null;
		return "#" + rowLabels.get(rowNumber) + "#" + " ^" + columnLabels.get(colNumber) + "^";
	}
	
	public List<Pair<String, String>> getUserAnswersAsRowColumnPairList(){
		List<Pair<String, String>> answers = new ArrayList<>();
		
		for(Pair<Integer, Integer> pair : userAnswers){
			Pair<String, String> newPair = new Pair<>(rowLabels.get(pair.getFirst()), columnLabels.get(pair.getSecond()));
			
			answers.add(newPair);
		}
		
		return answers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;

		GridQuestion o2 = (GridQuestion) o;

		return super.equals(o2) && Objects.equals(userAnswers, o2.userAnswers)
				&& Objects.equals(rowLabels, o2.rowLabels) && Objects.equals(columnLabels, o2.columnLabels);
	}
	
	public boolean ifCheckedPair(Pair<Integer, Integer> answerCoordinates){
		return userAnswers.contains(answerCoordinates);
	}

	@Override
	public GridQuestion clone() throws CloneNotSupportedException {
		return (new Cloner()).deepClone(this);
	}
}
