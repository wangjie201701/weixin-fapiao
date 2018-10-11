package com.wangjie.bill.base.date;

import org.joda.time.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期计算工具
 */
public abstract class DateCalcUtil {
    
    /**
     * 获取年
     * @param date 时间
     * @return
     */
    public static int getYear(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getYear();
    }
    
    /**
     * 获取月
     * @param date 时间
     * @return
     */
    public static int getMonth(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getMonthOfYear();
    }
    
    /**
     * 获取天
     * @param date 时间
     * @return
     */
    public static int getDay(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getDayOfMonth();
    }
    
    /**
     * 获取小时
     * @param date 时间
     * @return
     */
    public static int getHour(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getHourOfDay();
    }
    
    /**
     * 获取分钟
     * @param date 时间
     * @return
     */
    public static int getMinute(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getMinuteOfHour();
    }
    
    /**
     * 获取秒
     * @param date 时间
     * @return
     */
    public static int getSecond(Date date){
        DateTime dtdate = new DateTime(date);
        return dtdate.getSecondOfMinute();
    }
    
    /**
     * 增加年数
     * @param date 时间
     * @param years 年数，负数为减
     * @return 更新后的时间
     */
    public static Date addYears(Date date, int years){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusYears(years).toDate();
    }
    
    /**
     * 增加月数
     * @param date 时间
     * @param months 月数，负数为减
     * @return 更新后的时间
     */
    public static Date addMonths(Date date, int months){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusMonths(months).toDate();
    }
    
    /**
     * 增加天数
     * @param date 时间
     * @param days 天数，负数为减
     * @return 更新后的时间
     */
    public static Date addDays(Date date, int days){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusDays(days).toDate();
    }
    
    /**
     * 增加小时数
     * @param date 时间
     * @param hours 小时数，负数为减
     * @return 更新后的时间
     */
    public static Date addHours(Date date, int hours){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusHours(hours).toDate();
    }
    
    /**
     * 增加分钟数
     * @param date 时间
     * @param minutes 分钟数，负数为减
     * @return 更新后的时间
     */
    public static Date addMinutes(Date date, int minutes){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusMinutes(minutes).toDate();
    }
    
    /**
     * 增加秒数
     * @param date 时间
     * @param seconds 秒数，负数为减
     * @return 更新后的时间
     */
    public static Date addSeconds(Date date, int seconds){
        DateTime dtdate = new DateTime(date);
        return dtdate.plusSeconds(seconds).toDate();
    }
    
    /**
     * 截断毫秒数<br>
     * 如：2016-07-27 21:18:53.287 -> 2016-07-27 21:18:53
     * @param date 时间
     * @return
     */
    public static Date truncateMillis(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtnew = dtdate.millisOfSecond().withMinimumValue();
        return dtnew.toDate();
    }
    
    /**
     * 获取所在天的开始时间<br>
     * 如：2016-07-27 00:00:00
     * @param date 时间
     * @return
     */
    public static Date getDayBegin(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtbegin = dtdate.millisOfDay().withMinimumValue();
        return dtbegin.toDate();
    }
    
    /**
     * 获取所在天的结束时间<br>
     * 如：2016-07-27 23:59:59
     * @param date 时间
     * @return
     */
    public static Date getDayEnd(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtend = dtdate.millisOfDay().withMaximumValue();
        return dtend.toDate();
    }
    
    /**
     * 获取所在周的第一天<br>
     * 如：2016-07-25 00:00:00
     * @param date 时间
     * @return
     */
    public static Date getWeekBegin(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtbegin = dtdate.dayOfWeek().withMinimumValue().millisOfDay().withMinimumValue();
        return dtbegin.toDate();
    }
    
    /**
     * 获取所在周的最后一天<br>
     * 如：2016-07-31 23:59:59
     * @param date 时间
     * @return
     */
    public static Date getWeekEnd(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtend = dtdate.dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue();
        return dtend.toDate();
    }
    
    /**
     * 获取所在月的第一天<br>
     * 如：2016-07-01 00:00:00
     * @param date 时间
     * @return
     */
    public static Date getMonthBegin(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtbegin = dtdate.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue();
        return dtbegin.toDate();
    }
    
    /**
     * 获取所在月的最后一天<br>
     * 如：2016-07-31 23:59:59
     * @param date 时间
     * @return
     */
    public static Date getMonthEnd(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime dtend = dtdate.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue();
        return dtend.toDate();
    }
    
    /**
     * 获取前一天日期
     * @param date 时间
     * @return
     */
    public static Date getPreDay(Date date){
        return addDays(date, -1);
    }
    
    /**
     * 获取后一天日期
     * @param date 时间
     * @return
     */
    public static Date getNextDay(Date date){
        return addDays(date, 1);
    }
    
    /**
     * 获取所在周的上周的起始时间
     * @param date 时间
     * @return [开始时间，结束时间]
     */
    public static Date[] getPreWeek(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime curbegin = dtdate.dayOfWeek().withMinimumValue();
        DateTime preend = curbegin.minusDays(1).millisOfDay().withMaximumValue();
        DateTime prebegin = preend.dayOfWeek().withMinimumValue().millisOfDay().withMinimumValue();
        return new Date[]{prebegin.toDate(), preend.toDate()};
    }
    
    /**
     * 获取所在月的上月的起始时间
     * @param date 时间
     * @return [开始时间，结束时间]
     */
    public static Date[] getPreMonth(Date date){
        DateTime dtdate = new DateTime(date);
        DateTime curbegin = dtdate.dayOfMonth().withMinimumValue();
        DateTime preend = curbegin.minusDays(1).millisOfDay().withMaximumValue();
        DateTime prebegin = preend.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue();
        return new Date[]{prebegin.toDate(), preend.toDate()};
    }
    
    /**
     * 按天切分日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 天起始时间列表：[[d1start,d1end],[d2start,d2end],....]
     */
    public static List<Date[]> splitInDay(Date start, Date end){
        List<Date[]> daylst = new ArrayList<Date[]>();
        if(start == null || end == null){
            return daylst;
        }
        LocalDate ldstart = new LocalDate(start);
        LocalDate ldend = new LocalDate(end);
        if(ldstart.isAfter(ldend)){
            return daylst;
        }
        
        LocalDate tmpday = ldstart;
        while(true){
            if(tmpday.isAfter(ldend)){
                break;
            }
            daylst.add(new Date[]{
                    tmpday.toDate(), 
                    tmpday.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue().toDate()});
            tmpday = tmpday.plusDays(1);
        }
        
        return daylst;
    }
    
    /**
     * 按周切分日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 周起始时间列表：[[w1start,w1end],[w2start,w2end],....]
     */
    public static List<Date[]> splitInWeek(Date start, Date end){
        List<Date[]> weeklst = new ArrayList<Date[]>();
        if(start == null || end == null){
            return weeklst;
        }
        LocalDate ldstart = new LocalDate(start);
        LocalDate ldend = new LocalDate(end);
        if(ldstart.isAfter(ldend)){
            return weeklst;
        }
        
        LocalDate tmpweekstart = ldstart;
        LocalDate tmpweekend = ldstart.dayOfWeek().withMaximumValue();
        while(true){
            if(! ldend.isAfter(tmpweekend)){
                weeklst.add(new Date[]{
                        tmpweekstart.toDate(), 
                        ldend.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue().toDate()});
                break;
            }
            weeklst.add(new Date[]{
                    tmpweekstart.toDate(), 
                    tmpweekend.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue().toDate()});
            tmpweekstart = tmpweekend.plusDays(1);
            tmpweekend = tmpweekstart.dayOfWeek().withMaximumValue();
        }
        
        return weeklst;
    }
    
    /**
     * 按月切分日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 月起始时间列表：[[m1start,m1end],[m2start,m2end],....]
     */
    public static List<Date[]> splitInMonth(Date start, Date end){
        List<Date[]> monthlst = new ArrayList<Date[]>();
        if(start == null || end == null){
            return monthlst;
        }
        LocalDate ldstart = new LocalDate(start);
        LocalDate ldend = new LocalDate(end);
        if(ldstart.isAfter(ldend)){
            return monthlst;
        }
        
        LocalDate tmpmonthstart = ldstart;
        LocalDate tmpmonthend = ldstart.dayOfMonth().withMaximumValue();
        while(true){
            if(! ldend.isAfter(tmpmonthend)){
                monthlst.add(new Date[]{
                        tmpmonthstart.toDate(), 
                        ldend.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue().toDate()});
                break;
            }
            monthlst.add(new Date[]{
                    tmpmonthstart.toDate(), 
                    tmpmonthend.toDateTimeAtCurrentTime().millisOfDay().withMaximumValue().toDate()});
            tmpmonthstart = tmpmonthend.plusDays(1);
            tmpmonthend = tmpmonthstart.dayOfMonth().withMaximumValue();
        }
        
        return monthlst;
    }
    
    /**
     * 获取两个时间的相隔天数
     * @param start 开始时间
     * @param end 结束时间
     * @return 相隔天数
     */
    public static int getIntervalDays(Date start, Date end){
        DateTime ldstart = new DateTime(start);
        DateTime ldend = new DateTime(end);
        return Days.daysBetween(ldstart, ldend).getDays();
    }
    
    /**
     * 获取两个时间的相隔字段值<br>
     * [年，月，日，时，分，秒]
     * @param start 开始时间
     * @param end 结束时间
     * @return 相隔字段值
     */
    public static int[] getPeriodFields(Date start, Date end){
        DateTime ldstart = new DateTime(start);
        DateTime ldend = new DateTime(end);
        Period period = new Period(ldstart, ldend, PeriodType.yearMonthDayTime());
        int[] fields = {
                period.getYears(),
                period.getMonths(),
                period.getDays(),
                period.getHours(),
                period.getMinutes(),
                period.getSeconds()
        };
        
        return fields;
    }
    
}
