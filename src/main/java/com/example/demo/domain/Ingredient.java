package com.example.demo.domain;



public class Ingredient {


	public Ingredient(Long ingredientId, String ingredientName, String ingredientDescr) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientDescr = ingredientDescr;
	}
	public Ingredient() {
	}
	private Long ingredientId;
	private String ingredientName;
	private String ingredientDescr;
	public Long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getIngredientDescr() {
		return ingredientDescr;
	}
	public void setIngredientDescr(String ingredientDescr) {
		this.ingredientDescr = ingredientDescr;
	}
	
	
	
}
