package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("bdProcessor")
public class BookDetailProcessor implements ItemProcessor<String, String> {

	public BookDetailProcessor() {
		System.out.println("BookDetailsProcessor::0-param-constructor");
	}

	@Override
public String  process(String item ) throws Exception {
		System.out.println("BookDetailsProcessor.process()");
		String bookWithTitle = null;
		if (item.equalsIgnoreCase("ABC")) {
			bookWithTitle =item+"by HS AND PN";}
			else if (item.equalsIgnoreCase("TIJ"))

				bookWithTitle = item + " by HE";

				

				else if (item.equalsIgnoreCase("hfg"))

					bookWithTitle = item + "kl";

				else if (item.equalsIgnoreCase("ej"))

					bookWithTitle = item + "ko";

				else if (item.equalsIgnoreCase("bbj"))

			bookWithTitle = item + "bj";
		return bookWithTitle;
	
}}