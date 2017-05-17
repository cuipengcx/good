package com.jk.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TaskTest {

	public void run() {
        for (int i = 0; i < 1; i++) {
			log.debug(i + " run......................................" + (new Date()));
		}

	}

}
