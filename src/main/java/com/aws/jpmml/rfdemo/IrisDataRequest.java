package com.aws.jpmml.rfdemo;

/*
 * Copyright 2012-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

public class IrisDataRequest {
	
	String device_memory;
	String data_plan;
	String active_app_user;
	String international_travel_plan_opted;
	String num_visits_2_day;
	String num_pages_2_days;
	String num_days_visited_1_month;
	String num_calls_15_days;
	String total_call_duration_last_1_month;
	String response_rate_last_6_months;
	String ads_sent_1_month;
	String total_data_used_1_yr;
	String days_since_last_applogin;
	String days_since_activation;
	

	
	public String getdevice_memory() {
		return device_memory;
	}
	public void setdevice_memory(String device_memory) {
		this.device_memory = device_memory;
	}
	public String getdata_plan() {
		return data_plan;
	}
	public void setdata_plan(String data_plan) {
		this.data_plan = data_plan;
	}
	public String getactive_app_user() {
		return active_app_user;
	}
	public void setactive_app_user(String active_app_user) {
		this.active_app_user = active_app_user;
	}
	public String getinternational_travel_plan_opted() {
		return international_travel_plan_opted;
	}
	public void setinternational_travel_plan_opted(String international_travel_plan_opted) {
		this.international_travel_plan_opted = international_travel_plan_opted;
	}
	
	public String getnum_visits_2_day() {
		return num_visits_2_day;
	}
	public void setnum_visits_2_day(String num_visits_2_day) {
		this.num_visits_2_day = num_visits_2_day;
	}
	public String getnum_pages_2_days() {
		return num_pages_2_days;
	}
	public void setnum_pages_2_days(String num_pages_2_days) {
		this.num_pages_2_days = num_pages_2_days;
	}
	public String getnum_days_visited_1_month() {
		return num_days_visited_1_month;
	}
	public void setnum_days_visited_1_month(String num_days_visited_1_month) {
		this.num_days_visited_1_month = num_days_visited_1_month;
	}
	public String getnum_calls_15_days() {
		return num_calls_15_days;
	}
	public void setnum_calls_15_days(String num_calls_15_days) {
		this.num_calls_15_days = num_calls_15_days;
	}

	public String gettotal_call_duration_last_1_month() {
		return total_call_duration_last_1_month;
	}
	public void settotal_call_duration_last_1_month(String total_call_duration_last_1_month) {
		this.total_call_duration_last_1_month = total_call_duration_last_1_month;
	}
	public String getresponse_rate_last_6_months() {
		return response_rate_last_6_months;
	}
	public void setresponse_rate_last_6_months(String response_rate_last_6_months) {
		this.response_rate_last_6_months = response_rate_last_6_months;
	}
	public String getads_sent_1_month() {
		return ads_sent_1_month;
	}
	public void setads_sent_1_month(String ads_sent_1_month) {
		this.ads_sent_1_month = ads_sent_1_month;
	}
	public String gettotal_data_used_1_yr() {
		return total_data_used_1_yr;
	}
	public void settotal_data_used_1_yr(String total_data_used_1_yr) {
		this.total_data_used_1_yr = total_data_used_1_yr;
	}
	
	public String getdays_since_last_applogin() {
		return days_since_last_applogin;
	}
	public void setdays_since_last_applogin(String days_since_last_applogin) {
		this.days_since_last_applogin = days_since_last_applogin;
	}
	public String getdays_since_activation() {
		return days_since_activation;
	}
	public void setdays_since_activation(String days_since_activation) {
		this.days_since_activation = days_since_activation;
	}
	
	@Override
	public String toString() {
		return "IrisDataRequest [device_memory=" + device_memory + ", data_plan=" + data_plan + ", active_app_user="
			+ active_app_user + ", international_travel_plan_opted=" + international_travel_plan_opted + ",     		num_visits_2_day=" + num_visits_2_day +", num_pages_2_days=" + num_pages_2_days + ", num_days_visited_1_month=" + num_days_visited_1_month + ", num_calls_15_days=" + num_calls_15_days + ", total_call_duration_last_1_month=" + total_call_duration_last_1_month + ", response_rate_last_6_months=" + response_rate_last_6_months + ", ads_sent_1_month=" + ads_sent_1_month + ", total_data_used_1_yr=" +total_data_used_1_yr + ", days_since_last_applogin=" + days_since_last_applogin + ", days_since_activation=" + days_since_activation +"]";
	}
	
	

}
