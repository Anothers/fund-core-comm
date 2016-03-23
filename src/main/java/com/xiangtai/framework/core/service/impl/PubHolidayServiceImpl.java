package com.xiangtai.framework.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangtai.framework.core.entity.HolidayFormMap;
import com.xiangtai.framework.core.mapper.HolidayMapper;
import com.xiangtai.framework.core.service.PubHolidayService;
import com.xiangtai.framework.core.utils.CalenderUtils;
import com.xiangtai.framework.core.utils.DateUtil;


/**
 * 类描述：假期维护
 * 创建人：范兴乾
 * 创建时间：2016-3-4 下午2:14:48
 */
@Service
public class PubHolidayServiceImpl implements PubHolidayService {
    @Inject
    private HolidayMapper holidayMapper;

    /**
     * 方法说明：得到 所传日期 距离假期结束的天数
     * 创建人：范兴乾
     * 返回类型：int
     * 创建时间：2016-3-4 下午2:28:24
     *
     * @return 返回距所传日期参数还有几天假期结束，若所传日期不在假期内，则返回0；
     * 所传参数+返回天数 = 下个工作日
     * （说明：节假日配置表中，若假期连上周末，请将周末也配置为假期或调休）
     * @throws Exception
     */
    public int inHoliday(Date date) throws Exception {
        String date2;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date2 = formatter.format(date);
        HolidayFormMap map = new HolidayFormMap();
        map.put("day", date2);
        int days = 0;
        List<HolidayFormMap> lists = holidayMapper.findHoliday(map);
        if (lists != null && lists.size() > 0) {
            HolidayFormMap entMap = lists.get(0);
            String times1 = (String) entMap.get("st_dt");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date2));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(times1));
            long time2 = cal.getTimeInMillis();
            double between_days = (time1 - time2) / (1000 * 3600 * 24);
            days = (int) (between_days) + 1;//已经过了几天假期
            int days2 = Integer.parseInt((String) entMap.get("days"));//一共有几天假期
            days = days2 - days;
        }

        return days;
    }

    /**
     * 方法说明：得到所传日期的day个工作日后的日期 ,跳过周末和假期
     * 创建人：范兴乾
     * 创建时间：2016-3-4 下午2:27:58
     *
     * @param date 源日期 【格式 ： yyyy-MM-dd】
     * @param day  天数【要加工作日数】
     * @return 几个工作日后的时间 【date +day = add_day_work_date 格式 ： yyyy-MM-dd】
     * @throws Exception
     */
    public String addDayByWorkDate(String date, int day) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        String next_date = date.replace("-", "");
        int w;
        for (int i = 0; i < day; i++) {
            cal.setTime(sdf.parse(next_date));
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            int add_day = 0;
             /*当周五时，得到周日，当周六或周日时，得到周一；总之是一个工作日*/
            if (i < day - 1 && w == 5) {
                add_day = 2;
                next_date = CalenderUtils.getDateSubtractDays(next_date, -add_day);
            } else if (w == 6) {
                add_day = 2;
                next_date = CalenderUtils.getDateSubtractDays(next_date, -add_day);
            } else if (w == 0) {
                add_day = 1;
                next_date = CalenderUtils.getDateSubtractDays(next_date, -add_day);
            }

            Date date_0 = sdf.parse(next_date);//当天是否在假期校验：当天在假期中， 得到假期后第一个工作日【当天+假期余天数+1】；当天在假期前一天，得到假期中最后一天【当天+假期余天数+1】
            int surplus_days = inHoliday(date_0);
            if (surplus_days > 0) {
                if (add_day == 0) {//若周末和假期周时满足，不能重复加1
                    surplus_days++;
                }
                next_date = CalenderUtils.getDateSubtractDays(next_date, -surplus_days);
            }
            if (i < day - 1) {
                Date date_1 = sdf.parse(CalenderUtils.getDateSubtractDays(next_date, -1));//T+1天是否在假期校验
                surplus_days = inHoliday(date_1);
                if (surplus_days > 0) {
                    if (add_day == 0) {
                        surplus_days++;
                    }
                    next_date = CalenderUtils.getDateSubtractDays(next_date, -surplus_days);
                }
                next_date = CalenderUtils.getDateSubtractDays(next_date, -1);
            }
        }
        return DateUtil.date2Str(DateUtil.parseDate(next_date, DateUtil.YYYYMMDD), new SimpleDateFormat("yyyy-MM-dd"));
    }

}
