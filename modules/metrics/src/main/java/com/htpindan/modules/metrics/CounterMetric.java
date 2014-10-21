package com.htpindan.modules.metrics;

public class CounterMetric {
	public long totalCount;
	public double meanRate;
	public long lastCount;
	public double lastRate;

	@Override
	public String toString() {
		return "CounterMetric [totalCount=" + totalCount + ", meanRate=" + meanRate + ", lastCount=" + lastCount
				+ ", lastRate=" + lastRate + "]";
	}
}
