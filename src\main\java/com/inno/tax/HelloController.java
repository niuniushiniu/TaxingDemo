package com.inno.tax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


@Controller
public class HelloController {
	
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView helloWorld(){
		
		ModelAndView model1 = new ModelAndView("HelloPage");
		model1.addObject("msg", "Are You Ready");
		String path = this.getClass().getResource("").getPath();
		//path = path.substring(0, path.length() - 103);
		model1.addObject("pathofjava", path);
		
		return model1;
	}
	
	@RequestMapping(value = "/person_info", method = RequestMethod.GET)
	public ModelAndView personInfo(){
		
		ModelAndView model2 = new ModelAndView("PersonInfo");
		model2.addObject("step1", "Personal Information");
		
		return model2;
	}
	
	@RequestMapping(value = "/submit_person_info", method = RequestMethod.POST)
	public ModelAndView submitName(@RequestParam Map<String, String> person_info) throws IOException{
			
//@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname){
		
		ModelAndView model3 = new ModelAndView("sumbitPersonInfo");
		
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
		File file = new File(path + "TaxingDemo/temp");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("firstname::" + firstname +"\n");
		bw.write("lastname::" +lastname +"\n");
		bw.write("id_num::" +id_num +"\n");
		bw.write("home_addr::" + home_addr +"\n");
		bw.write("city_state_zip::" + city_state_zip +"\n");
		bw.write("country::" + country +"\n");
		bw.write("province::" + province +"\n");
		bw.write("zipcode::" + zipcode +"\n");
		bw.write("isSingle::" + isSingle +"\n");
		bw.close();
//	try {
//	  
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
//		String wage	= sumbitTax.get("wage");
//		model4.addObject("wage",wage);
		
		
		
		return model4;
		
	}
	
}
