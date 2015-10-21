package com.inno.tax;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.List;
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
	
	@RequestMapping(value = "/asdf", method = RequestMethod.GET)
	public ModelAndView fillName(){
		
		ModelAndView model2 = new ModelAndView("Name");
		model2.addObject("step1", "Step 1");
		
		return model2;
	}
	
	@RequestMapping(value = "/submitname") //method = RequestMethod.POST)
	public ModelAndView submitName(@RequestParam Map<String, String> fullname){
			
//@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname){
		
		ModelAndView model3 = new ModelAndView("Confirm");
		
		String firstname = fullname.get("firstname");
		String lastname = fullname.get("lastname");
		
		
		
		
		try {
		  
		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf
		// /Users/chizhang/Documents/workspaceEE/TaxingDemo/FormFillPDF.pdf
		// /home/ubuntu/test/test.pdf
			String path = this.getClass().getResource("").getPath();
			path = path.substring(0, path.length() - 103);
				
			PdfReader reader = new PdfReader(path + "TaxingDemo/test.pdf");
		  
		  
  	      //PdfReader reader = new PdfReader("/Users/chizhang/Documents/workspaceEE/TaxingDemo/test.pdf");
  	      /** filling in the personal information*/
  	      //System.out.println("funk you!");
  	    PdfStamper stamp1 = new PdfStamper(reader, new FileOutputStream(path + "/TaxingDemo/FormFillPDF.pdf"));
  	  model3.addObject("showstatus", "outputed");
	      AcroFields form1 = stamp1.getAcroFields();
	      //Personal info=============================================
	      form1.setField("f1_01_0_", firstname);
	      form1.setField("f1_02_0_", lastname);
	      
	      stamp1.close();
	    	} catch (Exception de) {
	    	      de.printStackTrace();
	    	}
		
		
		model3.addObject("firstname", firstname);
		model3.addObject("lastname", lastname);
		
		return model3;
	}
	
	@RequestMapping(value = "/wait")
	public ModelAndView waiting(){
		
		ModelAndView model4 = new ModelAndView("Wait");
		
		return model4;
		
	}
	
}
