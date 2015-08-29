package com.pbdmng.goShorty.entity;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.pbdmng.goShorty.utils.ipGeoLocation.CountryLocation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

public class Click {
	
	private String IP;
	private String browser;
	private String country;
	private LocalDate date;
	
	public Click(String IP, String userAgent){
		String browser = "other";
		if (userAgent.contains("Chrome/")) browser = "chrome";
		if (userAgent.contains("Firefox/")) browser = "firefox";
		if (userAgent.contains("Safari/")) browser = "safari";
		if (userAgent.contains("OPR/")) browser = "opera";
		if (userAgent.contains(";MSIE")) browser = "explorer";
		
		this.browser = browser;
		this.IP = IP;
		this.date = LocalDate.now(ZoneId.of("Europe/Rome"));
		try{
			CountryLocation countryLocation = new CountryLocation();
			this.country = countryLocation.getCountry(IP);
		}catch(IOException e){
			e.printStackTrace();
		}catch(GeoIp2Exception e){
			this.country = "Valhalla";
			e.printStackTrace();
		}
	}
	
	public Click(){
		this.date = LocalDate.now(ZoneId.of("Europe/Rome"));
	}
	
	
	// setter
	public void setIP(String IP){
		this.IP = IP;
		try{
			CountryLocation countryLocation = new CountryLocation();
			this.country = countryLocation.getCountry(IP);
		}catch(IOException e){
			e.printStackTrace();
		}catch(GeoIp2Exception e){
			this.country = "Valhalla";
			e.printStackTrace();
		}
	}
	
	public void setBrowser(String userAgent){
		String browser = "other";
		if (userAgent.contains("Chrome/")) browser = "chrome";
		if (userAgent.contains("Firefox/")) browser = "firefox";
		if (userAgent.contains("Safari/")) browser = "safari";
		if (userAgent.contains("OPR/")) browser = "opera";
		if (userAgent.contains(";MSIE")) browser = "explorer";
		this.browser = browser;
	}
	
	public void setDate(LocalDate date){
		this.date = date;
	}
	
	// getter
	public String getIP(){
		return this.IP;
	}
	
	public String getBrowser(){
		return this.browser;
	}
	
	public LocalDate getDate(){
		return this.date;
	}
	
	public String getCountry(){
		return this.country;
	}
}
