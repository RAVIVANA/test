package com.nkxgen.spring.jdbc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nkxgen.spring.jdbc.Dao.AuditLogDAO;
import com.nkxgen.spring.jdbc.Dao.AuditLogRepository;
import com.nkxgen.spring.jdbc.model.AuditLogs;

@Controller
public class AuditLogsController {
	
	
	
	private  AuditLogDAO auditLogDAO;
	private  AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogsController(AuditLogDAO auditLogDAO,AuditLogRepository auditLogRepository) {
        this.auditLogDAO = auditLogDAO;
        this.auditLogRepository = auditLogRepository;
    }
    
    
	@RequestMapping(value="logs")
	public String listAuditLogs(Model model)
	{
		List<AuditLogs>auditLogs = auditLogDAO.getAllAuditLogs();
        model.addAttribute("auditLogs", auditLogs);
		return "auditlogs";
	}
	
	@GetMapping("/auditLogs")
	public String getAuditLogs(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int pageSize,
	        Model model) {
	    org.springframework.data.domain.PageRequest pageable = org.springframework.data.domain.PageRequest.of(page, pageSize, Sort.by("timestamp").descending());
	    Page<AuditLogs> auditLogPage = auditLogRepository.findAllByOrderByTimestampDesc(pageable);

	    model.addAttribute("auditLogs", auditLogPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", auditLogPage.getTotalPages());

	    int totalPages = auditLogPage.getTotalPages();
	    int maxPageNumbers = 5; // Maximum number of page numbers to display

	    int startPage;
	    int endPage;

	    if (totalPages <= maxPageNumbers) {
	        startPage = 1; // Adjusted from 0 to 1
	        endPage = totalPages;
	    } else {
	        if (page <= maxPageNumbers / 2) {
	            startPage = 0;
	            endPage = maxPageNumbers - 1;
	        } else if (page >= totalPages - maxPageNumbers / 2) {
	            startPage = totalPages - maxPageNumbers;
	            endPage = totalPages - 1;
	        } else {
	            startPage = page - maxPageNumbers / 2;
	            endPage = page + maxPageNumbers / 2;
	        }
	    }

	    List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
	            .boxed()
	            .collect(Collectors.toList());

	    model.addAttribute("pageNumbers", pageNumbers);

	    return "auditlogs";
	}
}
