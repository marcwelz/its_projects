package com.axa.ch.its.inventory.model;

import java.time.LocalDate;

public class Lend_Article {
	private int id;
	private String fk_article_id;
	private String fk_user_id;
	private int state;
	private LocalDate lending_start_date;
	private LocalDate lending_start_end_date;
	private LocalDate returned_date;
	private String fk_returned_user_id;
	
	public Lend_Article(int id,  String fk_article_id, String fk_user_id, int state, 
			LocalDate lending_start_date, LocalDate lending_start_end_date,
			LocalDate returned_date,String fk_returned_user_id) {
		
		this.id = id;
		this.fk_article_id = fk_article_id;
		this.fk_user_id = fk_user_id;
		this.state = state;
		this.lending_start_date = lending_start_date;
		this.lending_start_end_date = lending_start_end_date;
		this.fk_returned_user_id = fk_returned_user_id;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFk_article_id() {
		return fk_article_id;
	}

	public void setFk_article_id(String fk_article_id) {
		this.fk_article_id = fk_article_id;
	}

	public String getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(String fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public LocalDate getLending_start_date() {
		return lending_start_date;
	}

	public void setLending_start_date(LocalDate lending_start_date) {
		this.lending_start_date = lending_start_date;
	}

	public LocalDate getLending_start_end_date() {
		return lending_start_end_date;
	}

	public void setLending_start_end_date(LocalDate lending_start_end_date) {
		this.lending_start_end_date = lending_start_end_date;
	}

	public LocalDate getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(LocalDate returned_date) {
		this.returned_date = returned_date;
	}

	public String getFk_returned_user_id() {
		return fk_returned_user_id;
	}

	public void setFk_returned_user_id(String fk_returned_user_id) {
		this.fk_returned_user_id = fk_returned_user_id;
	}
	
   

}
