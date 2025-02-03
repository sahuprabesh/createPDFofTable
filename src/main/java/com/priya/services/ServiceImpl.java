package com.priya.services;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.priya.models.Assesment_Mark;
import com.priya.models.Batch_Allocate;
import com.priya.models.Batch_Master;
import com.priya.models.Emp_Master;
import com.priya.models.FormData;
import com.priya.models.Tech_Master;
import com.priya.repos.AssesmentRepo;
import com.priya.repos.Batch_Allocate_Repo;
import com.priya.repos.Batch_Master_Repo;
import com.priya.repos.Emp_Master_Repo;
import com.priya.repos.Tech_Master_Repo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ServiceImpl implements ServiceIntf {

	@Autowired
	private AssesmentRepo assesmentRepo;
	
	@Autowired
	private Batch_Allocate_Repo BatchAllocateRepo;
	
	@Autowired
	private Batch_Master_Repo BMRepo;
	
	@Autowired
	private Emp_Master_Repo empRepo;
	
	@Autowired
	private Tech_Master_Repo techRepo;
	
	
	public List<Batch_Master> allBatch()
	{
		return BMRepo.findAll();
	}
	
	public List<Tech_Master> findTech(Integer batchId)
	{
		List<Tech_Master> techByBatchId = techRepo.findTechByBatchId(batchId);
		return techByBatchId;
	}
	
	public List<Emp_Master> findEmp(Integer techId)
	{
		List<Emp_Master> allEmpByTechId = empRepo.getAllEmpByTechId(techId);
		System.out.println(allEmpByTechId);
		return allEmpByTechId;
	}
	
	
	public String saveData(FormData formData)
	{
		//System.out.println(formData.getBatchName()+"BatchName"+formData.getEmpName()+"EmpName"+formData.getTechName()+"TechNAme"+formData.getMark()+"jkkkkkkkkkkk");

		Batch_Allocate batchAllocate=new Batch_Allocate();
		
		Batch_Master batch_Master = BMRepo.findById(Integer.parseInt(formData.getBatchName())).get();
		batchAllocate.setBatchId(batch_Master);

		Tech_Master tech_Master = techRepo.findById(Integer.parseInt(formData.getTechName())).get();
		batchAllocate.setTechId(tech_Master);
		
		Emp_Master emp_Master = empRepo.findById(Integer.parseInt(formData.getEmpName())).get();
		batchAllocate.setEmpId(emp_Master);
		
		
	
		Assesment_Mark assessment=new Assesment_Mark();
		
		assessment.setMark(formData.getMark());
		assessment.setEmpObject(emp_Master);
		
		assesmentRepo.save(assessment);
		
		batchAllocate.setMarkk(assessment);
		
		

		//Saved All object into batchAllocation Table
		Batch_Allocate save = BatchAllocateRepo.save(batchAllocate);
		
		if(save!=null)
		{
			return"Record Saved Successfully";
		}
		else
		{
			return "Record Did't Saved";
		}
		
	}
	
	
	List<Batch_Allocate> allByBatch;
	public List<Batch_Allocate> tableData(String name)
	{
		//TableData td=new TableData();
		System.out.println(name);
		
		 allByBatch = BatchAllocateRepo.findAllByBatch(Integer.parseInt(name));
		System.out.println(allByBatch);

		//List<Assesment_Mark> allMark = assesmentRepo.findAll();
		
		
		
		return allByBatch;
	}
	
	
	
	public void pdfOpen(HttpServletResponse response)
	{
		try {
		String title="THis is all About Stored Data";
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		Document document=new Document();
		
		PdfWriter.getInstance(document, out);
		
		document.open();
		
		Font fontTitle=FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
		
		Paragraph paragraph=new Paragraph(title,fontTitle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		
		PdfPTable table=new PdfPTable(6);
		
		Stream.of("Batch Name", "Start Date", "Tech Name", "Emp Name", "Emp Mob", "Marks")
        .forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(headerTitle, headFont));
            table.addCell(header);
        });

    // Fetch Data
    for (Batch_Allocate one : allByBatch) {

        // Handle possible NULL Mobile Number
        String empMobile = (one.getEmpId() != null && one.getEmpId().getEmpMob() != null) 
                ? String.valueOf(one.getEmpId().getEmpMob()) 
                : "N/A";

        System.out.println("Employee Mobile: " + empMobile);

        // Handle possible NULL Marks
        String marks = (one.getMarkk() != null) 
                      ? String.valueOf(one.getMarkk().getMark()) 
                      : "N/A";
        System.out.println("Marks: " + marks);

        // Add Data to Table
        table.addCell(new PdfPCell(new Phrase(one.getBatchId().getBatchName())));
        table.addCell(new PdfPCell(new Phrase(String.valueOf(one.getBatchId().getBatchStartDate()))));
        table.addCell(new PdfPCell(new Phrase(one.getTechId().getTechName())));
        table.addCell(new PdfPCell(new Phrase(one.getEmpId().getEmpName())));
        table.addCell(new PdfPCell(new Phrase(empMobile)));
        table.addCell(new PdfPCell(new Phrase(marks)));
    }

        document.add((Element) table);
        document.close();

        // Write to response output stream
        OutputStream outputStream = response.getOutputStream();
        out.writeTo(outputStream);
        outputStream.flush();
        outputStream.close();
		
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

	}

}
