package com.dms.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class DbUtils {
	
	public static final String SQL_DOCUMENT_DETAILS = "select document.ID AS ID " +
                        " ,document.INDEXED_DATE AS INDEXED_DATE " +
                        " ,document.DATE_ON_LETTER AS DATE_ON_LETTER " +
                        " ,(case per_fr.COMPANY_IND when 0 then concat(per_fr.FIRST_NAME,' ',per_fr.SURNAME) else per_fr.COMPANY_NAME end) AS PER_FROM_NAME " +
                        " ,(case per_to.COMPANY_IND when 0 then concat(per_to.FIRST_NAME,' ',per_to.SURNAME) else per_to.COMPANY_NAME end) AS PER_TO_NAME " +
                        " ,per_fr.PER_ID AS PER_FROM_ID " +
                        " ,per_to.PER_ID AS PER_TO_ID " +
                        " ,type.NAME AS TYP_NAME " +
                        " ,type.TYP_ID AS TYP_ID " +
                        " ,document.COM_TEXT AS COM_TEXT " +
                        " ,document.EXT AS EXT " +
                        " ,document.CREATED_BY AS CREATED_BY " +
                        " ,document.AUTO_INSERTED AS AUTO_INSERTED  " +
                        " from ( " +
                        " ( " +
                        "  ( " +
                        "    (document left join person per_fr on((document.SENT_FROM_PER_ID = per_fr.PER_ID)))  " +
                        "  left join person per_to on((document.SENT_TO_PER_ID = per_to.PER_ID)))  " +
                        " left join type on((document.TYP_ID = type.TYP_ID))))  " +
                        " where 1=1 ";

	public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

		final ArrayList<U> dest = new ArrayList<U>();

		for (T element : source) {
			if (element == null) {
				continue;
			}
			dest.add(mapper.map(element, destType));
		}


		return dest;
	}
}