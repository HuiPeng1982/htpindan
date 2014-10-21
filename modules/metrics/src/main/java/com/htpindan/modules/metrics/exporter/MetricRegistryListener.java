package com.htpindan.modules.metrics.exporter;

import com.htpindan.modules.metrics.Counter;
import com.htpindan.modules.metrics.Histogram;
import com.htpindan.modules.metrics.Timer;

public interface MetricRegistryListener {

	void onCounterAdded(String name, Counter counter);

	void onHistogramAdded(String name, Histogram histogram);

	void onTimerAdded(String name, Timer timer);
}
