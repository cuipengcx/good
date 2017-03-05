package com.jk.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {
	
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private boolean emptyAsNull;

	private String dateFormat = DEFAULT_DATE_FORMAT;

	public DateEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public DateEditor(boolean emptyAsNull, String dateFormat) {
		this.emptyAsNull = emptyAsNull;
		this.dateFormat = dateFormat;
	}

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return value != null ? DateFormatUtils.format(value, dateFormat) : StringUtils.EMPTY;
	}

	@Override
	public void setAsText(String text) {
		if (text != null) {
			String value = text.trim();
			if (emptyAsNull && StringUtils.isEmpty(text)) {
				setValue(null);
			} else {
				try {
					setValue(DateUtils.parseDate(value,DATE_PATTERNS));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		} else {
			setValue(null);
		}
	}

}