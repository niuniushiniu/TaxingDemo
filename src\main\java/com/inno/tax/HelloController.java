package com.inno.tax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inno.tax.GetTax;
import com.inno.tax.*;
import com.itextpdf.text.io.TempFileCache;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


@Controller
public class HelloController {

	@RequestMapping(value = "/person_info", method = RequestMethod.GET)
	public ModelAndView personInfo(){
		
		ModelAndView model2 = new ModelAndView("PersonInfo");
		model2.addObject("step1", "Personal Information");
		
		return model2;
	}
	
	@RequestMapping(value = "/confirm_person_info", method = RequestMethod.POST)
	public ModelAndView submitName(@RequestParam Map<String, String> person_info) throws IOException{
		
		ModelAndView model3 = new ModelAndView("confirmPersonInfo");
		
		String firstname	= person_info.get("firstname");
		String lastname		= person_info.get("lastname");
		String id_num		= person_info.get("id_num");
		String home_addr	= person_info.get("home_addr");
		String city_state_zip	= person_info.get("city_state_zip");
		String country			= person_info.get("country");
		String province			= person_info.get("province");
		String zipcode			= person_info.get("zipcode");
		String isSingle			= person_info.get("isSingle");
		
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.length() - 103);
		File file = new File(path + "TaxingDemo/temp.json");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("[{ ");
		bw.write("\"firstname" +"\": \""+ firstname +"\", ");
		bw.write("\"lastname" +"\": \""+lastname +"\", ");
		bw.write("\"id_num" +"\": \""+id_num +"\", ");
		bw.write("\"home_addr" +"\": \""+ home_addr +"\", ");
		bw.write("\"city_state_zip" +"\": \""+ city_state_zip +"\", ");
		bw.write("\"country" +"\": \""+ country +"\", ");
		bw.write("\"province" +"\": \""+ province +"\", ");
		bw.write("\"zipcode" +"\": \""+ zipcode +"\", ");
		bw.write("\"isSingle" +"\": \""+ isSingle +"\"}]\n");
		bw.close();

		
		model3.addObject("firstname", firstname);
		model3.addObject("lastname", lastname);
		model3.addObject("id_num", id_num);
		model3.addObject("home_addr", home_addr);
		model3.addObject("city_state_zip", city_state_zip);
		model3.addObject("country", country);
		model3.addObject("province", province);
		model3.addObject("zipcode", zipcode);
		model3.addObject("isSingle", isSingle);
		return model3;
		
	}
	
	@RequestMapping(value = "/IncomeAndTax")		
	public ModelAndView incomeAndTax(){
		
		ModelAndView model4 = new ModelAndView("IncomeAndTax");		
		return model4;
		
	}
	
	@RequestMapping(value = "/calculateTax")	
	public ModelAndView submitIncome(@RequestParam Map<String, String> IncomeAndTax) throws IOException{
	ModelAndView model5 = new ModelAndView("calculateTax");
	
	String wage	= IncomeAndTax.get("wage");
	String taxable_refunds	= IncomeAndTax.get("taxable_refunds");
	String scholarship	= IncomeAndTax.get("scholarship");
	String treaty	= IncomeAndTax.get("treaty");
	String treaty_type	= IncomeAndTax.get("treaty_type");
	
	double num7 = Double.parseDouble(wage) + Double.parseDouble(taxable_refunds) + Double.parseDouble(scholarship);
	double num8 = 0.0;
	double num9 = 0.0;
	double num10 = num7;
	
	String itemized_deduction	= IncomeAndTax.get("itemized_deduction");
	
	double num12 = num10 - Double.parseDouble(itemized_deduction);
	String exemption	= IncomeAndTax.get("exemption");
	double num14 = num12 - Double.parseDouble(exemption);
	double tax = (double) GetTax.getTax(num14);
	double num17 = tax;
	String federal_withheld_W2	= IncomeAndTax.get("federal_withheld_W2");
	String federal_withheld_1042S	= IncomeAndTax.get("federal_withheld_1042S");
	String return_of_2013	= IncomeAndTax.get("return_of_2013");
	String credit_1040C	= IncomeAndTax.get("credit_1040C");
	Double totalPayments = Double.parseDouble(federal_withheld_W2) +Double.parseDouble(federal_withheld_1042S)+
							Double.parseDouble(return_of_2013)+Double.parseDouble(credit_1040C);
	
	//write tax data to Temp
	String path = this.getClass().getResource("").getPath();;
	path = path.substring(0, path.length() - 103);
	
	
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(path + "TaxingDemo/temp.json", true)));
	bw.write("[{");
	bw.write("\"wage" +"\": \""+ wage +"\", ");
	bw.write("\"taxable_refunds" +"\": \""+ taxable_refunds +"\", ");
	bw.write("\"scholarship" +"\": \""+ scholarship +"\", ");
	bw.write("\"treaty" +"\": \""+ treaty +"\", ");
	bw.write("\"treaty_type" +"\": \""+ treaty_type +"\", ");
	bw.write("\"num7" +"\": \""+ num7 +"\", ");
	bw.write("\"num8" +"\": \""+ num8 +"\", ");
	bw.write("\"num9" +"\": \""+ num9 +"\", ");
	bw.write("\"num10" +"\": \""+ num10 +"\", ");
	bw.write("\"itemized_deduction" +"\": \""+ itemized_deduction +"\", ");
	bw.write("\"num12" +"\": \""+ num12 +"\", ");
	bw.write("\"exemption" +"\": \""+ exemption +"\", ");
	bw.write("\"num14" +"\": \""+ num14 +"\", ");
	bw.write("\"tax" +"\": \""+ tax +"\", ");
	bw.write("\"num17" +"\": \""+ num17 +"\", ");
	bw.write("\"federal_withheld_W2" +"\": \""+ federal_withheld_W2 +"\", ");
	bw.write("\"federal_withheld_1042S" +"\": \""+ federal_withheld_1042S +"\", ");
	bw.write("\"return_of_2013" +"\": \""+ return_of_2013 +"\", ");
	bw.write("\"credit_1040C" +"\": \""+ credit_1040C +"\", ");
	bw.write("\"totalPayments" +"\": \""+ totalPayments +"\"}]\n");
	bw.close();
	
	model5.addObject("wage", wage);
	model5.addObject("taxable_refunds", taxable_refunds);
	model5.addObject("scholarship", scholarship);
	model5.addObject("treaty", treaty);
	model5.addObject("treaty_type", treaty_type);
	model5.addObject("num7", num7);
	model5.addObject("num8", num8);
	
	model5.addObject("num9", num9);
	model5.addObject("num10", num10);
	model5.addObject("itemized_deduction", itemized_deduction);
	model5.addObject("num12", num12);
	
	model5.addObject("exemption", exemption);
	model5.addObject("num14", num14);
	model5.addObject("tax", tax);
	model5.addObject("num17", num17);
	model5.addObject("federal_withheld_W2", federal_withheld_W2);
	model5.addObject("federal_withheld_1042S", federal_withheld_1042S);
	model5.addObject("return_of_2013", return_of_2013);
	model5.addObject("credit_1040C", credit_1040C);
	model5.addObject("totalPayments", totalPayments);
	
	return model5;
	
}
	
	@RequestMapping(value = "/refund")
	public ModelAndView refund(@RequestParam Map<String, String> refund) throws IOException{
		ModelAndView model6 = new ModelAndView("refund");
		int i = 0;
		BufferedReader reader = null;
		String [] jsonContext = new String[2];
		
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.length() - 103);		
		FileInputStream fileinputStream = new FileInputStream(path + "/TaxingDemo/temp.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileinputStream, "UTF-8");
		reader = new BufferedReader(inputStreamReader);
		String tempString = null;
		
		while((tempString = reader.readLine())!= null){
			jsonContext[i] = tempString;
			i++;
		}
		//String JsonContext = new Util().ReadFile(path + "TaxingDemo/temp.json");
		
		JSONArray jsonArray = JSONArray.fromObject(jsonContext[1]);
		JSONObject jsonObject = jsonArray.getJSONObject(0);		
		
		String totalPayments = jsonObject.get("totalPayments").toString();
		String tax = jsonObject.get("num17").toString();

		double overpaid = 0;
		double owe = 0;
		String refundorowe = null;
		if (Double.parseDouble(totalPayments) > Double.parseDouble(tax)) {
			overpaid = Double.parseDouble(totalPayments) - Double.parseDouble(tax);
			refundorowe = "Congratulations, you have refund available!";
		} else {
			owe = Double.parseDouble(tax) - Double.parseDouble(totalPayments);
			refundorowe = "Sorry, you need to pay more tax!";
		}
		model6.addObject("overpaid", overpaid);
		model6.addObject("owe", owe);
		model6.addObject("refundorowe", refundorowe);
		reader.close();
		return model6;
	}
	
	@RequestMapping(value = "/refundConfirm")
	public ModelAndView refundConfirm(@RequestParam Map<String, String> refundConfirm) throws IOException {
		ModelAndView model7 = new ModelAndView("refundConfirm");
		String overpaid	= refundConfirm.get("overpaid");
		String refund		= refundConfirm.get("refund");
		String routing_num		= refundConfirm.get("routing_num");
		String checkingOrSaving	= refundConfirm.get("checkingOrSaving");
		String account_num	= refundConfirm.get("account_num");
		String mail_outUS_add			= refundConfirm.get("mail_outUS_add");
		String tax_2015			= refundConfirm.get("tax_2015");
		String zipcode			= refundConfirm.get("zipcode");
		String owe			= refundConfirm.get("owe");
		
		String path = this.getClass().getResource("").getPath();;
		path = path.substring(0, path.length() - 103);
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path + "TaxingDemo/temp.json", true)));
		bw.write("[{");
		bw.write("\"overpaid" +"\": \""+ overpaid +"\", ");
		bw.write("\"refund" +"\": \""+ refund +"\", ");
		bw.write("\"routing_num" +"\": \""+ routing_num +"\", ");
		bw.write("\"checkingOrSaving" +"\": \""+ checkingOrSaving +"\", ");
		bw.write("\"account_num" +"\": \""+ account_num +"\", ");
		bw.write("\"mail_outUS_add" +"\": \""+ mail_outUS_add +"\", ");
		bw.write("\"tax_2015" +"\": \""+ tax_2015 +"\", ");
		bw.write("\"zipcode" +"\": \""+ zipcode +"\", ");		
		bw.write("\"owe" +"\": \""+ owe +"\"}]");
		bw.close();
		
		model7.addObject("overpaid", overpaid);
		model7.addObject("refund", refund);
		model7.addObject("routing_num", routing_num);
		model7.addObject("checkingOrSaving", checkingOrSaving);
		model7.addObject("account_num", account_num);
		model7.addObject("mail_outUS_add", mail_outUS_add);
		model7.addObject("tax_2015", tax_2015);
		model7.addObject("mail_outUS_add", mail_outUS_add);
		return model7;
	}
	
	@RequestMapping(value = "/page2")		
	public ModelAndView page2(){
		
		ModelAndView model9 = new ModelAndView("page2");
		return model9;
		
	}
		
	@RequestMapping(value = "/generatePdf")
	ModelAndView getPdf(@RequestParam Map<String, String> page2) throws IOException {
		ModelAndView model8 = new ModelAndView("generatePdf");
		
		String citizenship	= page2.get("citizenship");
		System.out.println(citizenship);
		String residence		= page2.get("residence");
		String greencard		= page2.get("greencard");
		String US_citizen	= page2.get("US_citizen");
		String greencard_holder	= page2.get("greencard_holder");
		String e			= page2.get("e");
		String f			= page2.get("f");
		String f_yes			= page2.get("f_yes");
		String enterUSA			= page2.get("enterUSA");
		String departedUSA	= page2.get("departedUSA");
		String h1		= page2.get("h1");
		String h2		= page2.get("h2");
		String h3		= page2.get("h3");
		String I		= page2.get("I");
		String I2	= page2.get("I2");
		String country2	= page2.get("country2");
		String article_20			= page2.get("article_20");
		String prior_tax_year_month			= page2.get("prior_tax_year_month");
		String amount_of_exemption_5000			= page2.get("amount_of_exemption_5000");
		String treaty_total			= page2.get("treaty_total");
		String J	= page2.get("J");
	
		
		int i = 0;
		
		BufferedReader reader = null;
		String [] jsonContext = new String[3];
		
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.length() - 103);		
		FileInputStream fileinputStream = new FileInputStream(path + "/TaxingDemo/temp.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileinputStream, "UTF-8");
		reader = new BufferedReader(inputStreamReader);
		String tempString = null;
		model8.addObject("path", path + "/TaxingDemo/FormFillPDF.pdf");
		while((tempString = reader.readLine())!= null){
			jsonContext[i] = tempString;
			i++;
		}
		//String JsonContext = new Util().ReadFile(path + "TaxingDemo/temp.json");
		
		JSONArray jsonArray0 = JSONArray.fromObject(jsonContext[0]);
		JSONObject jsonObject0 = jsonArray0.getJSONObject(0);
		
		JSONArray jsonArray1 = JSONArray.fromObject(jsonContext[1]);
		JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
		
		JSONArray jsonArray2 = JSONArray.fromObject(jsonContext[2]);
		JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
		
		String firstname = jsonObject0.get("firstname").toString();
		String lastname = jsonObject0.get("lastname").toString();
		String id_num = jsonObject0.get("id_num").toString();
		String home_addr = jsonObject0.get("home_addr").toString();
		String city_state_zip = jsonObject0.get("city_state_zip").toString();
		String country = jsonObject0.get("country").toString();
		String province = jsonObject0.get("province").toString();
		String zipcode = jsonObject0.get("zipcode").toString();
		
		String isSingle = jsonObject0.get("isSingle").toString();
		
		try {	  
		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf
		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/FormFillPDF.pdf
		// /home/ubuntu/test/test.pdf
		
			
		PdfReader readerPdf = new PdfReader(path + "TaxingDemo/test.pdf");
		  
		  
		//PdfReader reader = new PdfReader("/Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf");
		/** filling in the personal information*/
		PdfStamper stamp1 = new PdfStamper(readerPdf, new FileOutputStream(path + "/TaxingDemo/FormFillPDF.pdf"));
		//model3.addObject("showstatus", "outputed");
		AcroFields form1 = stamp1.getAcroFields();
		//Personal info=============================================
		form1.setField("f1_01_0_", firstname);
		form1.setField("f1_02_0_", lastname);
		form1.setField("f1_03_0_", id_num);
		form1.setField("f1_04_0_", home_addr);
		form1.setField("f1_05_0_", city_state_zip);
		form1.setField("TextField1", country);
		form1.setField("f1_73_0_", province);
		form1.setField("f1_301_0_", zipcode);
		form1.setField("c1_2_0_[0]", isSingle);
		form1.setField("c1_2_0_[1]", isSingle);
		
		
		String wage = jsonObject1.get("wage").toString();
		String taxable_refunds = jsonObject1.get("taxable_refunds").toString();
		String scholarship = jsonObject1.get("scholarship").toString();
		String treaty = jsonObject1.get("treaty").toString();
		String treaty_type = jsonObject1.get("treaty_type").toString();
		String num7 = jsonObject1.get("num7").toString();
		String num8 = jsonObject1.get("num8").toString();
		String num9 = jsonObject1.get("num9").toString();
		String num10 = jsonObject1.get("num10").toString();
		String itemized_deduction = jsonObject1.get("itemized_deduction").toString();
		String num12 = jsonObject1.get("num12").toString();
		String exemption = jsonObject1.get("exemption").toString();
		String num14 = jsonObject1.get("num14").toString();
		String tax = jsonObject1.get("tax").toString();
		String num17 = jsonObject1.get("num17").toString();
		String federal_withheld_W2 = jsonObject1.get("federal_withheld_W2").toString();
		String federal_withheld_1042S = jsonObject1.get("federal_withheld_1042S").toString();
		String return_of_2013 = jsonObject1.get("return_of_2013").toString();
		String credit_1040C = jsonObject1.get("credit_1040C").toString();
		String totalPayments = jsonObject1.get("totalPayments").toString();
		
		  form1.setField("f1_10_0_", wage + "");
	      form1.setField("f1_12_0_", taxable_refunds + "");
	      form1.setField("f1_14_0_", scholarship + "");   	      
	      form1.setField("f1_16_0_", treaty + "");
	      form1.setField("f1_17_0_", treaty_type);
	      
	      form1.setField("f1_18_0_", num7 );
	      
	      form1.setField("f1_24_0_", num10 + "");
	      form1.setField("f1_26_0_", itemized_deduction + "");
	      
	      form1.setField("f1_28_0_", num12 + "");
	      form1.setField("f1_30_0_", exemption + "");
	         	      
	      form1.setField("f1_32_0_", num14 + "");
	          	      
	      form1.setField("f1_34_0_", tax + "");
	      form1.setField("f1_38_0_", tax + "");
	      form1.setField("f1_40_0_", federal_withheld_W2 + "");
	      form1.setField("f1_100_0_", federal_withheld_1042S + "");
	      form1.setField("f1_42_0_", return_of_2013 + "");
	      form1.setField("f1_44_0_", credit_1040C + "");
	     
	      form1.setField("f1_46_0_", totalPayments + "");
	      
	      
	      String overpaid = jsonObject2.get("overpaid").toString();
	      String refund = jsonObject2.get("refund").toString();
	      String routing_num = jsonObject2.get("routing_num").toString();
	      String checkingOrSaving = jsonObject2.get("checkingOrSaving").toString();
	      String account_num = jsonObject2.get("account_num").toString();
	      String mail_outUS_add = jsonObject2.get("mail_outUS_add").toString();
	      String tax_2015 = jsonObject2.get("tax_2015").toString();
	      String owe = jsonObject2.get("owe").toString();
	      int tax_penalty = 0;
	      form1.setField("f1_48_0_", overpaid + "");
	      
	      form1.setField("f1_50_0_", refund + "");
	      form1.setField("f1_52_0_", routing_num + "");
	      form1.setField("c1_4_0_[0]", checkingOrSaving);
	      form1.setField("c1_4_0_[1]", checkingOrSaving);
	      form1.setField("f1_53_0_", account_num + "");
	      form1.setField("f1_74_0_", mail_outUS_add);
	      form1.setField("f1_52_0_", routing_num + "");
	      form1.setField("f1_54_0_", tax_2015);
	      
	      form1.setField("f1_56_0_", owe + "");
	      form1.setField("f1_58_0_", tax_penalty + "");
	 //======page2======================================     
	      form1.setField("f2_01_0_", citizenship);
	      form1.setField("f2_02_0_", residence);
	      form1.setField("c2_01_0_[0]", greencard);
	      form1.setField("c2_01_0_[1]", greencard);
	      form1.setField("c2_35_0_[0]", US_citizen);
	      form1.setField("c2_35_0_[1]", US_citizen);
	      form1.setField("c2_37_0_[0]", greencard_holder);
	      form1.setField("c2_37_0_[1]", greencard_holder);
	      form1.setField("f2_19_0_", e);
	      form1.setField("c2_36_0_[0]", f);
	      form1.setField("c2_36_0_[1]", f);
	      form1.setField("f2_18_0_", f_yes);
	      
	      form1.setField("f2_019_0_", enterUSA);
	      form1.setField("f2_22_0_", departedUSA);
	      form1.setField("f2_15_0_", h1);
	      form1.setField("f2_16_0_", h2);
	      form1.setField("f2_17_0_", h3);
	      
	      form1.setField("c2_22_0_[0]", I);
	      form1.setField("c2_22_0_[1]", I);
	      form1.setField("f2_129_0_", I2);
	      
	      form1.setField("f2_127_0_", country2);
	      form1.setField("f2_128_0_", article_20);
	      form1.setField("f2_129_0_", prior_tax_year_month);
	      form1.setField("f2_130_0_", amount_of_exemption_5000);
	      form1.setField("f2_143_0_", treaty_total);
	      form1.setField("c2_34_0_[0]", J);
	      form1.setField("c2_34_0_[1]", J);
	      
	    
		  
		stamp1.close();
		} catch (Exception de) {
			      de.printStackTrace();
		}
		
		reader.close();
		return model8;
	}
	
	
	
}


