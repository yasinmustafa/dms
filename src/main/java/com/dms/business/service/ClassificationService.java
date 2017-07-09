package com.dms.business.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.domain.PersonDomain;
import com.dms.model.domain.TypeDomain;

@Service("classificationService")
@Transactional(readOnly = true)
public class ClassificationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private TypeService typeService;
	
	@Autowired
    private PersonService personService;
	
	public Date determineDateOnLetterForType(String[] lines, TypeDomain type) {
		String specificTextInLine = null;
		if (type != null 
				&& type.getName().equals("Cable")) {
			specificTextInLine = "bill date";
		} else if (type != null 
				&& type.getName().equals("Utilities")) {
			specificTextInLine = "statement date";
		} 
		return determineDateOnLetter(lines, specificTextInLine);

	}
	
	
	/**
	 * Only do the line with 'bill date' in the text
	 * @param lines
	 * @return
	 */
	protected Date determineDateOnLetter(String [] lines, String specificTextInLine)
	{
		Calendar cal = Calendar.getInstance();
		try {
			String day = "([1-9]|0[1-9]|[12][0-9]|3[01])";
			String dayThStRd = "(0[1-9]|[12][0-9]|3[01])(st|th|rd|nd)";
			String month = "(([1-9]|0[1-9]|1[0-2])|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|january|february|march|april|may|june|july|august|september|october|november|december)";
			String year ="(20[0-9][0-9]|[0-9][0-9])";
			String seperator = "(/|\\s|-|\\.||\\.\\s)";
			String day2 = "([0-2][0-9]|3[01])";
	    	String month2 = "(0[1-9]|1[0-2])";
			
			
			String space = "\\s";
			String monthAplha = "(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|january|february|march|april|may|june|july|august|september|october|november|december)";
			Pattern pattern;

			
			boolean found = false;
			Set <String> finds = new HashSet <String> ();
			Map <Integer, Date> finalDates = new HashMap <Integer, Date> ();
			boolean containedSpecificText = false;
			int lineNumber = 1;
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase())
						&& (!containedSpecificText
								|| (containedSpecificText && lineNumber > 3)))
				{
					continue;
				} else if (specificTextInLine!=null
						&& lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					containedSpecificText=true;
					lineNumber++;
				} else if (containedSpecificText) {
					lineNumber++;
				}
				pattern = Pattern.compile(day + space + monthAplha  + space + year);
				String text = lines[i].toLowerCase();
				Matcher matcher = pattern.matcher(text);
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
				}
				
				pattern = Pattern.compile(dayThStRd + space + monthAplha  + space + year);
				matcher = pattern.matcher(text);
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
				}
				
				
				pattern = Pattern.compile(monthAplha  + space + year);
				matcher = pattern.matcher(text);
				while (matcher.find() && !found) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add("01 " + match);
					}
				}
				
				if (specificTextInLine!=null
						&& containedSpecificText && lineNumber > 3)
				{
					break;
				}

			}
			
		
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				pattern = Pattern.compile(day + seperator + month  + seperator + year);
				String text = lines[i].toLowerCase();
				Matcher matcher = pattern.matcher(text);
				
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
					
				}
				if (specificTextInLine!=null)
				{
					break;
				}
			}
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				pattern = Pattern.compile(day + seperator + month2  + seperator + year);
				String text = lines[i].toLowerCase();
				Matcher matcher = pattern.matcher(text);
				
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
					
				}

				if (specificTextInLine!=null)
				{
					break;
				}
			}
			
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				String text = lines[i].toLowerCase();
				
			    	pattern = Pattern.compile(day2 + month2  + year);
			    	//pattern = Pattern.compile("02­11­2014");
					//pattern = Pattern.compile("02­"+ month2  + year);
			    	Matcher matcher = pattern.matcher(text);
			    	matcher = pattern.matcher(text);
			    	while (matcher.find()) {
			    		String match = matcher.group();
			    		if (match.length()>5)
						{
			    			lines[i] = lines[i] + "###" + match;
			    			finds.add(match);
			    			found = true;
						}
					}

					if (specificTextInLine!=null)
					{
						break;
					}
			}
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				String text = lines[i].toLowerCase();
				
			    	pattern = Pattern.compile(day2 + "." + month2  + "." + year);
			    	Matcher matcher = pattern.matcher(text);
			    	matcher = pattern.matcher(text);
			    	while (matcher.find()) {
			    		String match = matcher.group();
			    		if (match.length()>5)
						{
			    			lines[i] = lines[i] + "###" + match;
			    			finds.add(match);
			    			found = true;
						}
					}

					if (specificTextInLine!=null)
					{
						break;
					}
			}
			
			Set <String> findsNoSpace = determineDateOnLetterNoSpaces(lines, specificTextInLine);
			finds.addAll(findsNoSpace);
			if (finds.isEmpty())
			{
				return cal.getTime();
			}
				
			for (String find : finds) {
				try {
					Date findDate = extractDateFromMatch(find);
					DateTime d1 = new DateTime(Calendar.getInstance().getTime());
					DateTime d2 = new DateTime(findDate.getTime());
					Integer diffDays = Math.abs(Days.daysBetween(d1, d2).getDays());
					finalDates.put(diffDays, findDate);
				} catch (Exception ex) {
					continue;
				}
				
			}
			SortedSet <Integer> sortedSet = new TreeSet<Integer>();  
			sortedSet.addAll(finalDates.keySet());
			return finalDates.get(sortedSet.first());
			
			
		} catch (Exception ex) {
			return cal.getTime();
		}
	}


	protected Date extractDateFromMatch(String finalMatch) throws Exception {
		finalMatch = finalMatch.replace("august", "aug");
		finalMatch = finalMatch.replace("rd", "");
		finalMatch = finalMatch.replace("st", "");
		finalMatch = finalMatch.replace("th", "");
		finalMatch = finalMatch.replace("nd", "");
		String [] dateParts = new String[3];
		if (finalMatch.contains("/"))
		{
			dateParts = finalMatch.split("/");
		}
		else if (finalMatch.contains("-"))
		{
			dateParts = finalMatch.split("-");
		}
		else if (finalMatch.contains(". "))
		{
			dateParts = finalMatch.split("\\. ");
		}
		else if (finalMatch.contains(" "))
		{
			dateParts = finalMatch.split(" ");
		}
		else if (finalMatch.contains("."))
		{
			dateParts = finalMatch.split("\\.");
		}  else if (!finalMatch.equals(""))
	    {
			if (!StringUtils.isAlphanumeric(finalMatch)
					&& !StringUtils.isNumeric(finalMatch)) 
			{
				String newStr = "";
				int nonNumerics=0;
				for (int i=0;i < finalMatch.length(); i++) {
					String b = finalMatch.substring(i, i+1);
					if (StringUtils.isNumeric(b))
					{
						newStr = newStr + b;
					} else {
						nonNumerics = nonNumerics + 1;
					}
						
					
				}
				if (nonNumerics==2)
				{
					finalMatch = newStr;
				}
				
			}
			
			
			if (StringUtils.isNumeric(finalMatch))
			{
				if (finalMatch.length()==8)
		    	{
		    		dateParts[0] = finalMatch.substring(0, 2);
			    	dateParts[1] = finalMatch.substring(2, 4);
			    	dateParts[2] = finalMatch.substring(4, 8);
		    	} else if (finalMatch.length()==6)
		    	{
		    		dateParts[0] = finalMatch.substring(0, 2);
			    	dateParts[1] = finalMatch.substring(2, 4);
			    	dateParts[2] = "20" + finalMatch.substring(4, 6);
		    	}
			} else if(StringUtils.isAlphanumeric(finalMatch)) {
				
				
				finalMatch = finalMatch.replaceFirst("([0-9])([a-zA-Z])", "$1 $2");
				finalMatch = finalMatch.replaceFirst("([a-zA-Z])([0-9])", "$1 $2");
				dateParts = finalMatch.split(" ");
			}
	    	
	    }
		

		String months = "(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|january|february|march|april|may|june|july|august|september|october|november|december)";
		if (dateParts[1].matches(months))
		{
			if (dateParts[1].startsWith("ja"))
				dateParts[1]="1";
			else if (dateParts[1].startsWith("f"))
				dateParts[1]="2";
			else if (dateParts[1].startsWith("mar"))
				dateParts[1]="3";
			else if (dateParts[1].startsWith("ap"))
				dateParts[1]="4";
			else if (dateParts[1].startsWith("may"))
				dateParts[1]="5";
			else if (dateParts[1].startsWith("jun"))
				dateParts[1]="6";
			else if (dateParts[1].startsWith("jul"))
				dateParts[1]="7";
			else if (dateParts[1].startsWith("au"))
				dateParts[1]="8";
			else if (dateParts[1].startsWith("s"))
				dateParts[1]="9";
			else if (dateParts[1].startsWith("o"))
				dateParts[1]="10";
			else if (dateParts[1].startsWith("n"))
				dateParts[1]="11";
			else if (dateParts[1].startsWith("d"))
				dateParts[1]="12";

		}
		
		if (dateParts[2].length()==2)
			dateParts[2] = "20" + dateParts[2]; 
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, new Integer(dateParts[0]));
		cal.set(Calendar.MONTH, new Integer(dateParts[1])-1);
		cal.set(Calendar.YEAR, new Integer(dateParts[2]));

		return cal.getTime();
	}
	
	
	public TypeDomain getCorrectType(String letterText)
	{
		String lc = letterText.toLowerCase();
		if (lc.contains("invoice number:"))
		{
			return typeService.findTypesByName().get("Invoice");
		}
		else if (lc.contains("virginmedia"))
		{
			return typeService.findTypesByName().get("Cable");
		} else if (lc.contains("hsbc")
				|| lc.contains("barclays")
				|| lc.contains("northern rock")
				|| lc.contains("newcastle")
				|| lc.contains("santander")
				|| lc.contains("firstdirect")
			)
		{
			return typeService.findTypesByName().get("Bank");
		} else if (lc.contains("doctor")
				|| lc.contains("nhs")
				|| lc.contains("hospital")
				|| lc.contains("dental")
				|| lc.contains("dentist")
				|| lc.contains("appointment")
				|| lc.contains("eye test")
				|| lc.contains("optician")
			)
		{
			return typeService.findTypesByName().get("Doctor");
		} else if (lc.contains("bradford academy")
				|| lc.contains("rainbow")
				|| lc.contains("stephens")
				|| lc.contains("school"))
		{
			return typeService.findTypesByName().get("School");
		} else if (lc.contains("insurance"))
		{
			return typeService.findTypesByName().get("Insurance");
		} else if (lc.contains("city of bradford")
			|| lc.contains("council"))
		{
			return typeService.findTypesByName().get("Council");
		} else if (lc.contains("atos ")
				|| lc.contains("nett pay"))
		{
			return typeService.findTypesByName().get("Work");
		} else if (lc.contains("electricity")
				|| lc.contains(" gas "))
		{
			return typeService.findTypesByName().get("Utilities");
		} else if (lc.contains(" tax "))
		{
			return typeService.findTypesByName().get("Tax");
		} else if (lc.contains("aldi")
				|| lc.contains(" asda ")
				|| lc.contains("sainsbury")
				|| lc.contains("morrisons")
				|| lc.contains("lidl")
				|| lc.contains("supermarket")
				|| lc.contains("primark")
				|| lc.contains(" argos")
				|| lc.contains("halal")
				|| lc.contains(" receipt ")
				|| lc.contains("invoice")
				|| lc.contains("tesco"))
		{
			return typeService.findTypesByName().get("Shopping");
		} else {
			return typeService.findTypesByName().get("None");
		}
		
	}
	
	public PersonDomain getPersonTo(String letterText)
	{
		String lc = letterText.toLowerCase();
		Iterator <String> names = personService.findAllPeopleByFirstName().keySet().iterator();
		while (names.hasNext())
		{
			String name = (String) names.next();
			if (lc.contains(name.toLowerCase()))
			{
				return personService.findAllPeopleByFirstName().get(name);
			}
		}
		return null;
		
	}
	
	public Set <String> determineDateOnLetterNoSpaces(String [] lines, String specificTextInLine)
	{

		try {
			String day = "([1-9]|0[1-9]|[12][0-9]|3[01])";
			String dayThStRd = "(0[1-9]|[12][0-9]|3[01])(st|th|rd|nd)";
			String month = "(([1-9]|0[1-9]|1[0-2])|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|january|february|march|april|may|june|july|august|september|october|november|december)";
			String year ="(20[0-9][0-9]|[0-9][0-9])";
			String seperator = "(/|-|\\.||\\.)";
			String day2 = "([0-2][0-9]|3[01])";
	    	String month2 = "(0[1-9]|1[0-2])";
			
			
			String monthAplha = "(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|january|february|march|april|may|june|july|august|september|october|november|december)";
			Pattern pattern;


			
			boolean found = false;
			Set <String> finds = new HashSet <String> (); 
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				pattern = Pattern.compile(day + monthAplha  + year);
				String text = lines[i].toLowerCase().replaceAll(" ", "");
				Matcher matcher = pattern.matcher(text);
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
				}
				
				pattern = Pattern.compile(dayThStRd + monthAplha  + year);
				matcher = pattern.matcher(text);
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
				}
				
				pattern = Pattern.compile(monthAplha  + year);
				matcher = pattern.matcher(text);
				while (matcher.find() && !found) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add("01 " + match);
					}
				}
				
				if (specificTextInLine!=null)
				{
					break;
				}

			}
			if (found)
			{
				return finds;
			}
		
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				pattern = Pattern.compile(day + seperator + month  + seperator + year);
				String text = lines[i].toLowerCase().replaceAll(" ", "");
				Matcher matcher = pattern.matcher(text);
				
				while (matcher.find()) {
					String match = matcher.group();
					if (match.length()>5)
					{
						lines[i] = lines[i] + "###" + match;
						found = true;
						finds.add(match);
					}
					
				}
				if (specificTextInLine!=null)
				{
					break;
				}
			}
			if (found)
			{
				return finds;
			}
			
			for (int i=0; i<lines.length; i++)
			{
				if (specificTextInLine!=null
						&& !lines[i].toLowerCase().contains(specificTextInLine.toLowerCase()))
				{
					continue;
				}
				String text = lines[i].toLowerCase().replaceAll(" ", "");
				
				
			    	pattern = Pattern.compile(day2 + month2  + year);
			    	Matcher matcher = pattern.matcher(text);
			    	matcher = pattern.matcher(text);
			    	while (matcher.find()) {
			    		String match = matcher.group();
			    		if (match.length()>5)
						{
			    			lines[i] = lines[i] + "###" + match;
			    			found = true;
			    			finds.add(match);
						}
					}
			    	if (specificTextInLine!=null)
					{
						break;
					}
			}
			if (found)
			{
				return finds;
			}

			
			return Collections. <String> emptySet();
			
		} catch (Exception ex) {
			return Collections. <String> emptySet();
		}
	}


}