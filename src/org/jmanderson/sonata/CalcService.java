package org.jmanderson.sonata;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalcService {

	private static final int SERVICE_INTERVAL = 5000;

	public static String calculateNextServiceDate(String begin, String last,
			String latest, int lastServiceMileage, int latestMileage) {
		Date beginDate = getDate(begin);
		Date lastServiceDate = getDate(last);
		Date latestDate = getDate(latest);
		return calculateNextServiceDate(beginDate, lastServiceDate, latestDate,
				lastServiceMileage, latestMileage);

	}

	public static String calculateNextServiceDate(Date beginDate,
			Date lastServiceDate, Date latestDate, int lastServiceMileage,
			int latestMileage) {

		StringBuffer sb = new StringBuffer();
		int daysLatest;
		int daysPrevious;
		int daysAdjusted;

		daysLatest = daysPer5K(dateDifference(lastServiceDate, latestDate),
				latestMileage);
		daysPrevious = dateDifference(beginDate, lastServiceDate)
				/ (lastServiceMileage / SERVICE_INTERVAL);
		daysAdjusted = adjustedDaysPer5K(daysPrevious, daysLatest,
				latestMileage);
		sb.append("(Historical: ").append(
				DateFormat.getDateInstance(2).format(
						nextServiceDate(lastServiceDate, daysPrevious)));
		sb.append(", Current: ").append(
				DateFormat.getDateInstance(2).format(
						nextServiceDate(lastServiceDate, daysLatest)));
		sb.append(", Blended: ").append(
				DateFormat.getDateInstance(2).format(
						nextServiceDate(lastServiceDate, daysAdjusted)))
				.append(")");

		return sb.toString();

	}

	private static int dateDifference(Date date1, Date date2) {

		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long diff = time2 - time1;
		long days = diff / (24 * 60 * 60 * 1000);
		if (days == 0) {
			days = 1;
		}

		return (int) days;

	}

	private static int daysPer5K(int days, int miles) {

		int milesMod = miles % SERVICE_INTERVAL;
		int milesPct = 100 * milesMod / SERVICE_INTERVAL;
		if (milesPct == 0) {
			milesPct = 1;
		}
		int daysPer5K = 100 * days / milesPct;
		return daysPer5K;

	}

	private static int adjustedDaysPer5K(int days_previous, int days_latest,
			int miles) {

		int milesMod = miles % SERVICE_INTERVAL;
		int milesPct = 100 * milesMod / SERVICE_INTERVAL;
		int adjustedDaysPer5K = (milesPct * days_latest + (100 - milesPct)
				* days_previous) / 100;
		return adjustedDaysPer5K;

	}

	private static Date nextServiceDate(Date baseDate, int daysPer5K) {

		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		c.setLenient(true);
		c.add(Calendar.DATE, daysPer5K);

		return c.getTime();

	}

	private static Date getDate(String date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date
				.substring(5, 7)), Integer.parseInt(date.substring(8)));
		return cal.getTime();
	}
}
