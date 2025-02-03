package com.priya.controllers;

import java.io.IOException;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.priya.models.Batch_Allocate;
import com.priya.models.Emp_Master;
import com.priya.models.FormData;
import com.priya.models.Tech_Master;
import com.priya.services.ServiceIntf;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AccessmentController {

	@Autowired
	private ServiceIntf service;
	
	@GetMapping("/")
	public String welcome(Model m)
	{
		
		m.addAttribute("Batches", service.allBatch());
		return "First";
	}
	
	@PostMapping("/findTech")
	@ResponseBody
	public List<Tech_Master> showAllClasses(@RequestParam("getData")Integer batchId) {
	     return service.findTech(batchId);
	}
	
	@PostMapping("/findEmp")
	@ResponseBody
	public List<Emp_Master> showAllEmployees(@RequestParam("getData")Integer techId) {
		List<Emp_Master> allEmp=service.findEmp(techId);
		return allEmp;
	}
	
	@PostMapping("/empData")
	public void catchEmpName(@RequestParam("getEmp")Integer EmpId)
	{
		System.out.println(EmpId);
	}
	
	@PostMapping("/formData")
	public String allFormData(@ModelAttribute FormData formData)
	{
		String saveData = service.saveData(formData);
		System.out.println(saveData);
		
		return "redirect:/";
	}
	
	@PostMapping("/showTable")
	public String showAllTableData(@RequestParam("batchNmae")String name,Model m)
	{
		List<Batch_Allocate> tableData = service.tableData(name);
		m.addAttribute("Batches", service.allBatch());
		m.addAttribute("allData", tableData);
		return "First";
	}
	
	@GetMapping("/pdf")
	public void showPdf(HttpServletResponse response)throws IOException
	{
		System.out.println("PDF Handler Method");
		service.pdfOpen(response);
	}
	
	
}
