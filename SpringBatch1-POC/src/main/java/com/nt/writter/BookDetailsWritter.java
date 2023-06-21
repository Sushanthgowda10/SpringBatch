package com.nt.writter;

import java.util.List;


import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
@Component("bdwritter")
public class BookDetailsWritter implements ItemWriter<String> {
	
	

	public BookDetailsWritter() {

	System.out.println("BookDetailsWriter() :: 0-param constructor");

	}

	

	@Override

	public void write(List<? extends String> items) throws Exception {

	System.out.println("BookDetailsWriter.write()");

	items.forEach(System.out::println);

	}

	}

	


