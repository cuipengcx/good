package com.jk;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by JK on 2017/1/20.
 */
public class DateTest {

    @Test
    public void parseDate() throws ParseException {
        String dateTxt = "2017-1-1";
        System.out.println(DateUtils.parseDate(dateTxt,new String[]{"yyyy-MM-dd"}));
    }
}
