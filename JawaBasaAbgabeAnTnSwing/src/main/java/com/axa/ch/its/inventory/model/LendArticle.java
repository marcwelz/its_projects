package com.axa.ch.its.inventory.model;

import java.time.LocalDate;

import com.axa.ch.its.inventory.dao.Users;

public class LendArticle implements Comparable<LendArticle>{
	
	private Article article;
	private Users user;
	private int id;
	private LendState state;
	private LocalDate lendingStartDate;
	private LocalDate lendingPlannedEndDate;
	private LocalDate returnedData;
	private String returnedByUserId;
	
	public LendArticle(Article article, Users user, int id, LendState state, LocalDate lendingStartDate,
			LocalDate lendingPlannedEndDate, LocalDate returnedData, String returnedByUserId) {
		this.article = article;
		this.user = user;
		this.id = id;
		this.state = state;
		this.lendingStartDate = lendingStartDate;
		this.lendingPlannedEndDate = lendingPlannedEndDate;
		this.returnedData = returnedData;
		this.returnedByUserId = returnedByUserId;
	}
	
	public LendArticle(int id) {
		this.id = id;
	}
	
	public LendArticle(Article article) {
		this.article = article;
	}
	
	public LendArticle(Users user) {
		this.user = user;
	}
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public LendState getState() {
		return state;
	}
	public void setState(LendState state) {
		this.state = state;
	}
	public LocalDate getLendingStartDate() {
		return lendingStartDate;
	}
	public void setLendingStartDate(LocalDate lendingStartDate) {
		this.lendingStartDate = lendingStartDate;
	}
	public LocalDate getLendingPlannedEndDate() {
		return lendingPlannedEndDate;
	}
	public void setLendingPlannedEndDate(LocalDate lendingPlannedEndDate) {
		this.lendingPlannedEndDate = lendingPlannedEndDate;
	}
	public LocalDate getReturnedData() {
		return returnedData;
	}
	public void setReturnedData(LocalDate returnedData) {
		this.returnedData = returnedData;
	}
	public String getReturnedByUserId() {
		return returnedByUserId;
	}
	public void setReturnedByUserId(String returnedByUserId) {
		this.returnedByUserId = returnedByUserId;
	}

	@Override
	public int compareTo(LendArticle o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void print() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
