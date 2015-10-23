package com.inno.tax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inno.tax.GetTax;


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
		bw.write("\"isSingle" +"\": \""+ isSingle +"\"}, ");
		bw.close();
//	try {	  
//		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf
//		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/FormFillPDF.pdf
//		// /home/ubuntu/test/test.pdf
//		String path = this.getClass().getResource("").getPath();
//		path = path.substring(0, path.length() - 103);
//			
//		PdfReader reader = new PdfReader(path + "TaxingDemo/test.pdf");
//		  
//		  
//		//PdfReader reader = new PdfReader("/Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf");
//		/** filling in the personal information*/
//		PdfStamper stamp1 = new PdfStamper(reader, new FileOutputStream(path + "/TaxingDemo/FormFillPDF.pdf"));
//		//model3.addObject("showstatus", "outputed");
//		AcroFields form1 = stamp1.getAcroFields();
//		//Personal info=============================================
//		form1.setField("f1_01_0_", firstname);
//		form1.setField("f1_02_0_", lastname);
//		form1.setField("f1_03_0_", id_num);
//		form1.setField("f1_04_0_", home_addr);
//		form1.setField("f1_05_0_", city_state_zip);
//		form1.setField("TextField1", country);
//		form1.setField("f1_73_0_", province);
//		form1.setField("f1_301_0_", zipcode);
//		form1.setField("c1_2_0_[0]", isSingle);
//		form1.setField("c1_2_0_[1]", isSingle);
//		  
//		stamp1.close();
//		} catch (Exception de) {
//			      de.printStackTrace();
//		}
		
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
	bw.write("{");
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
	bw.write("\"totalPayments" +"\": \""+ totalPayments +"\"}] ");
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
		
		String path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.length() - 103);		
		
		String JsonContext = new Util().ReadFile(path + "TaxingDemo/temp.json");
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		JSONObject jsonObject = jsonArray.getJSONObject(1);		
		
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
		return model6;
	}
	
	@RequestMapping(value = "/refundConfirm")
	public ModelAndView refundConfirm(@RequestParam Map<String, String> refundConfirm) throws IOException {
		ModelAndView model7 = new ModelAndView("refundConfirm");
		String overPaid	= refundConfirm.get("overpaid");
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
				new FileOutputStream(path + "TaxingDemo/refundTemp.json", true)));
		bw.write("[{");
		bw.write("\"overPaid" +"\": \""+ overPaid +"\", ");
		bw.write("\"refund" +"\": \""+ refund +"\", ");
		bw.write("\"routing_num" +"\": \""+ routing_num +"\", ");
		bw.write("\"checkingOrSaving" +"\": \""+ checkingOrSaving +"\", ");
		bw.write("\"account_num" +"\": \""+ account_num +"\", ");
		bw.write("\"mail_outUS_add" +"\": \""+ mail_outUS_add +"\", ");
		bw.write("\"tax_2015" +"\": \""+ tax_2015 +"\", ");
		bw.write("\"zipcode" +"\": \""+ zipcode +"\", ");		
		bw.write("\"owe" +"\": \""+ owe +"\"}] ");
		bw.close();
		
		return model7;
	}
	
	@RequestMapping(value = "/pdf")
	public ModelAndView getPdf() {
		ModelAndView model8 = new ModelAndView("generatePdf");
		return model8;
	}
}


