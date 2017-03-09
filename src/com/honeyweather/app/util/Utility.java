package com.honeyweather.app.util;

import android.text.TextUtils;

import com.honeyweather.app.model.City;
import com.honeyweather.app.model.County;
import com.honeyweather.app.model.HoneyWeatherDB;
import com.honeyweather.app.model.Province;

public class Utility {
	/*
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvinceResponse(
			HoneyWeatherDB honeyWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					honeyWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * �����ʹ�����������ص��м�����
	 */
	public static boolean handleCitiesResponse(HoneyWeatherDB honeyWeatherDB,
			String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					honeyWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * �����ʹ�����������ص��ؼ�����
	 */

	public static boolean handleCountiesResponse(HoneyWeatherDB honeyWeatherDB,
			String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					honeyWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
