package app.mvc.controller;

import app.mvc.model.service.StatisService;
import app.mvc.view.EndView;

public class StatisController {
	static StatisService statisService = new StatisService();
	
	public static void selectTotal() {
		try {
			EndView.printTotalMessage(statisService.selectTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
