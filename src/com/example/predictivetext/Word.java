package com.example.predictivetext;

public class Word {

	
	int id;
	int count;
	float probability;
	String word;
	
	public Word(){}
	
	public Word(int id, String word, int count, float probability)
	{
		this.id = id;
		this.word = word;
		this.probability = probability;
		this.count = count;
	}
	
	public Word(String word, int count, float probability)
	{
		this.word = word;
		this.probability = probability;
		this.count = count;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public float getProbability() {
		return probability;
	}

	public String getWord() {
		return word;
	}
	
	
}
