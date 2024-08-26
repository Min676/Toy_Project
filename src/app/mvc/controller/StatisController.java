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
	
	public static void selectDayTotal() {
		try {
			EndView.printTotalMessageDate(statisService.selectTotalDay(),"일");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectMonthTotal() {
		try {
			EndView.printTotalMessageDate(statisService.selectTotalMonth(),"월");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectCatTotal(int n) {
		try {
			EndView.printTotalMessage(statisService.selectCatTotal(n));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
