package org.homeunix.thecave.buddi.model.impl;

import java.util.Date;

import ca.digitalcave.moss.common.DateUtil;

public class Period {
	
	private final Date startDate;
	private final Date endDate;

	public Period(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public boolean equals(Object object) {
		Period another = (Period) object;
		return this.startDate.equals(another.startDate) &&
			   this.endDate.equals(another.endDate);
	}

	public long getDayCount() {
		return DateUtil.getDaysBetween(startDate, endDate, true);
	}

	public long getOverlappingDays(Period period) {
		Date overlappingEndDate = (endDate.before(period.endDate)) ? endDate : period.endDate;
		Date overlappingStartDate = (startDate.after(period.startDate)) ? startDate : period.startDate;
		
		return new Period(overlappingStartDate, overlappingEndDate).getDayCount();
	}

}
