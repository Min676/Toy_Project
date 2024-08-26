package app.mvc.controller;

import app.mvc.model.service.StatisService;

public class StatisController {
	static StatisService statisService = new StatisService();
	
	public static void selectTotal() {
		try {
			statisService.selectTotal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
