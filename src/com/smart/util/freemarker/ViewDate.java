package com.smart.util.freemarker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class ViewDate implements TemplateMethodModel {
	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arg)
			throws TemplateModelException {
		if (arg == null || arg.size() != 1) {
			throw new TemplateModelException("Wrong arguments!");
		}
		DateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = (String) arg.get(0);
		Date date = null;
		String result = "";
		try {
			date = formatter2.parse(dateStr);
			if (date == null) {
				return dateStr;
			}
			Date now = new Date();
			long diff = now.getTime() - date.getTime();
			int days = (int) diff / (1000 * 60 * 60 * 24);

			switch (days) {
			case 0:
				result = "今天  " + formatter1.format(date);
				break;
			case 1:
				result = "昨天  " + formatter1.format(date);
				break;
			default:
				result = formatter2.format(date);
				break;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
