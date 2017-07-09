package com.dms.business.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dms.business.service.ClassificationService;
import com.dms.model.domain.TypeDomain;

public class ClassificationServiceTest {
	
	
	ClassificationService classificationService;
	Calendar cal = Calendar.getInstance();
	
	@Before
	public void init() {
		classificationService = new ClassificationService();
	}
	//Bill date: 03 Sep 2014
	//after 24 Sep 2014.
	
	@Test
	public void test_l()
	{
		String [] text = {"Reference: 6200134481 _ _", "* 3 August 2015"};

		TypeDomain td = new TypeDomain();
		td.setName("Utilities");
		cal.setTime(classificationService.determineDateOnLetterForType(text, null));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2015);
		Assert.assertTrue(cal.get(Calendar.DATE)==3);
		Assert.assertTrue(cal.get(Calendar.MONTH)==7);
	}
	
	@Test
	public void test_k()
	{
		String [] text = {"Statement Date", "01 Mar 2015"};

		TypeDomain td = new TypeDomain();
		td.setName("Utilities");
		cal.setTime(classificationService.determineDateOnLetterForType(text, td));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2015);
		Assert.assertTrue(cal.get(Calendar.DATE)==1);
		Assert.assertTrue(cal.get(Calendar.MONTH)==2);
	}
	
	@Test
	public void test_j()
	{
		String [] text = {"Bill date: 03 Sep 2014", "fjhf", "ghfhhhh", "after 24 Sep 2014."};

		TypeDomain td = new TypeDomain();
		td.setName("Cable");
		cal.setTime(classificationService.determineDateOnLetterForType(text, td));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==3);
		Assert.assertTrue(cal.get(Calendar.MONTH)==8);
	}


	@Test
	public void test_date()
	{
		cal.add(Calendar.DATE, -5);
		Date findDate = cal.getTime();
		long diff = Calendar.getInstance().getTime().getTime() - findDate.getTime();
		Integer diffDays = (int) diff / (24 * 60 * 60 * 1000);
		System.out.println(diffDays);
		
	}

	@Test
	public void test_i()
	{
		String [] text = {"5.11.14"};

		cal.setTime(classificationService.determineDateOnLetter(text, null));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==5);
		Assert.assertTrue(cal.get(Calendar.MONTH)==10);
	}

	
	@Test
	public void test_h()
	{
		String [] text = {"02­11­2014 09:55"};
 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==2);
		Assert.assertTrue(cal.get(Calendar.MONTH)==10);
	}
	
	@Test
	public void test_g()
	{
		String [] text = {"Served On: 23/10/14 "};
 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		System.out.println(cal.getTime());
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==23);
		Assert.assertTrue(cal.get(Calendar.MONTH)==9);
	}
	
	@Test
	public void test_f()
	{
		String [] text = {"Date: O9-September-2014"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==9);
		Assert.assertTrue(cal.get(Calendar.MONTH)==8);
	}
	
	@Test
	public void test_e()
	{
		String [] text = {"'12 Dec 201 3", "1 2 Dec 2013", "clinlc on 14111113, aged 4 y 2 nn. Stre attended the appointment wtth her Dad.", "64577057 1s, 1 0 Oct 2009"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2013);
		Assert.assertTrue(cal.get(Calendar.DATE)==12);
		Assert.assertTrue(cal.get(Calendar.MONTH)==11);
	}
	
	@Test
	public void test_regex()
	{
		String  text = "29Jun2014"; 
		text = text.replaceFirst("([0-9])([a-zA-Z])", "$1 $2");
		text = text.replaceFirst("([a-zA-Z])([0-9])", "$1 $2");
		System.out.println(text);
	}
	
	
	@Test
	public void test_a()
	{
		String [] text = {"29 Jun 2014"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2014);
		Assert.assertTrue(cal.get(Calendar.DATE)==29);
		Assert.assertTrue(cal.get(Calendar.MONTH)==5);
	}
	
	@Test
	public void test_b()
	{
		String [] text = {"3 October 2011N"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2011);
		Assert.assertTrue(cal.get(Calendar.DATE)==3);
		Assert.assertTrue(cal.get(Calendar.MONTH)==9);
	}
	
	@Test
	public void test_c()
	{
		String [] text = {"September 2011"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2011);
		Assert.assertTrue(cal.get(Calendar.DATE)==1);
		Assert.assertTrue(cal.get(Calendar.MONTH)==8);
	}
	
	@Test
	public void test_d()
	{
		String [] text = {"Bifth NOtifiCatiOFl lprintea 17 December 2011)"}; 
		cal.setTime(classificationService.determineDateOnLetter(text, null));
		Assert.assertTrue(cal.get(Calendar.YEAR)==2011);
		Assert.assertTrue(cal.get(Calendar.DATE)==17);
		Assert.assertTrue(cal.get(Calendar.MONTH)==11);
	}
	
		
	

}
