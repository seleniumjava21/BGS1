package utilities;

import java.util.HashMap;
import java.util.List;

public class Placeholder {

	public Placeholder() {
		action = new PageAction();
	}

	private PageAction action;
	private String stringData;
	private int intData;
	private float floatData;
	private HashMap<String, String> hashmap;

	private List<String> arrayList;
	private List<HashMap<String, List<String>>> hashmap1;

	public List<HashMap<String, List<String>>> getHashmap1() {
		return hashmap1;
	}

	public void setHashmap1(List<HashMap<String, List<String>>> list) {
		this.hashmap1 = list;
	}

	public List<String> getArrayList() {
		return arrayList;
	}

	public void setArrayList(List<String> arrayList) {
		this.arrayList = arrayList;
	}

	public HashMap<String, String> getHashmap() {
		return hashmap;
	}

	public void setHashmap(HashMap<String, String> hashmap) {
		this.hashmap = hashmap;
	}

	public String getStringData() {
		action.logInfo("Getting string placeholder data :- " + stringData);
		return stringData;
	}

	public void setStringData(String stringData) {
		this.stringData = stringData;
		action.logInfo("Setting string placeholder data :- " + stringData);
	}

	public int getIntData() {
		return intData;
	}

	public void setIntData(int intData) {
		this.intData = intData;
		action.logInfo("setting int placeholder data :- " + intData);
	}

	public float getFloatData() {

		return floatData;
	}

	public void setFloatData(float floatData) {
		this.floatData = floatData;
		action.logInfo("setting int placeholder data :- " + floatData);

	}

}
