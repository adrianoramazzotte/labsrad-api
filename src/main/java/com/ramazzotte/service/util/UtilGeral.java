package com.ramazzotte.service.util;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UtilGeral {
	private int id;
	
	
	public UtilGeral(int id) {
		super();
		this.id = id;
	}
	public UtilGeral() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date localDataParaData(LocalDate lc) {
		Date date = Date.from( lc.atStartOfDay( ZoneId.systemDefault() ).toInstant() );		
		return date;		
	}

    public LocalDate localDataParaDate(Date dt) {
		LocalDate localDate = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				return localDate;
	}
    
    
}
