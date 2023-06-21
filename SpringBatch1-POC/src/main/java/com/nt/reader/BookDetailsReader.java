package com.nt.reader;

import java.rmi.UnexpectedException;
import java.text.ParseException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.stereotype.Component;

@Component("bdReader")
public class BookDetailsReader implements ItemReader<String> {
	String books[] = new String[] { "ABC", "EFG", "HIJ", "KLM", "NOP" };
	int count = 0;

	public BookDetailsReader() {
		System.out.println("BookDetailsReader::0-param constructor");
	}

@Override
public String read() throws Exception,UnexpectedException,ParseException,NonTransientResourceException {
System.out.println("BookDetailsReader.read()");	
if (count< books.length) {
	return books[count++];
}else
	return null;
}
}