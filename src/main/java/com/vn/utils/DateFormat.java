package com.vn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateFormat {
	
	SimpleDateFormat format = new SimpleDateFormat();
	
	public boolean checkNgay(String s) {
        String mau = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        if (s.matches(mau)) {
            int year = Integer.parseInt(s.substring(0,4));
            int month = Integer.parseInt(s.substring(5, 7));
            int day = Integer.parseInt(s.substring(8, 10));
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: {
                    return true;
                }
                case 2: {
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        if (day <= 29) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                case 4:
                case 6:
                case 9:
                case 11: {
                    if (day <= 30) {
                        return true;
                    } else {
                        return false;
                    }
                }
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
	
	 public String toString(Date date, String pattern) {
	        format.applyPattern(pattern);
	        return format.format(date);
	    }
}
