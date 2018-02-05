package com.jk;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JK on 2017/1/20.
 */
public class DateTest {

    @Test
    public void parseDate() throws ParseException {
        String dateTxt = "2017-1-1";
        System.out.println(DateUtils.parseDate(dateTxt,new String[]{"yyyy-MM-dd"}));
    }

    /**
     * lambda对list的筛选过滤
     */
    @Test
    public void filterListTest(){
        List<Long> subDeptList = new ArrayList<>();
        subDeptList.add(1L);
        subDeptList.add(2L);
        subDeptList.add(3L);

        List<Long> ids = subDeptList.stream().map(id -> id).collect(Collectors.toList());
        System.out.println(ids.toString());
    }

    /**
     * lambda对list的复制
     */
    @Test
    public void copyListTest(){
        List<Long> subDeptList = new ArrayList<>();
        subDeptList.add(1L);
        subDeptList.add(2L);
        subDeptList.add(3L);

        List<Long> ids = subDeptList.stream().filter(id -> id < 3).collect(Collectors.toList());
        System.out.println(ids.toString());
    }
}
