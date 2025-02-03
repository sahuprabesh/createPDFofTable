package com.priya.services;

import java.util.List;

import com.priya.models.Batch_Allocate;
import com.priya.models.Batch_Master;
import com.priya.models.Emp_Master;
import com.priya.models.FormData;
import com.priya.models.Tech_Master;

import jakarta.servlet.http.HttpServletResponse;

public interface ServiceIntf {
	public List<Batch_Master> allBatch();
	public List<Tech_Master> findTech(Integer batchId);
	public List<Emp_Master> findEmp(Integer techName);
	
	public String saveData(FormData formData);
	public List<Batch_Allocate> tableData(String name);
	public void pdfOpen(HttpServletResponse response);
}
