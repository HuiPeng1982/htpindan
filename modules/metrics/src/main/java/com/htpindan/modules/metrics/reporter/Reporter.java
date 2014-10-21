package com.htpindan.modules.metrics.reporter;

import java.util.Map;

import com.htpindan.modules.metrics.Counter;
import com.htpindan.modules.metrics.Histogram;
import com.htpindan.modules.metrics.Timer;

/**
 * Reporter的公共接口，被ReportScheduler定时调用。
 * 
 * 
 */
public interface Reporter {
	void report(Map<String, Counter> counters, Map<String, Histogram> histograms, Map<String, Timer> timers);
}
