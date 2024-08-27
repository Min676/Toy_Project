package app.mvc.controller;

import java.sql.SQLException;

import app.mvc.exception.NotFoundException;
import app.mvc.model.service.StatisService;
import app.mvc.view.EndView;
import app.mvc.view.FailView;

public class StatisController {
	static StatisService statisService = new StatisService();
	
	public static void selectTotal() {
		try {
			EndView.printTotalMessage(statisService.selectTotal());
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } 
	}
	
	public static void selectDayTotal() {
		try {
			EndView.printTotalMessageDate(statisService.selectTotalDay(),"일");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } 
	}
	
	public static void selectMonthTotal() {
		try {
			EndView.printTotalMessageDate(statisService.selectTotalMonth(),"월");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } 
	}
	
	public static void selectCatTotal(int n) {
		try {
			EndView.printTotalMessage(statisService.selectCatTotal(n));
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } 
	}
	
	public static void selectTopSellTen() {
		try {
			EndView.printTopSell(statisService.selectTopsell());
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
        } 
	}
	
	
}
