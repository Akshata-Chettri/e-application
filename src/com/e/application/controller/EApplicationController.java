package com.e.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.e.application.model.Details;
import com.e.application.model.Principal;
import com.e.application.model.Staff;
import com.e.application.model.Student;
import com.e.application.service.EApplicationService;

@Controller
public class EApplicationController {

	@Autowired
	Details details;
	@Autowired
	Student student;
	@Autowired
	Staff staff;
	@Autowired
	Principal principal;
	@Autowired
	EApplicationService eApplicationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView userLogin() {
		return new ModelAndView("loginEApplication");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginAuthentication(@ModelAttribute("loginEApplication") Details detail) {
		int id = detail.getUniqueid();
		String userRole = eApplicationService.findRole(id);
		if (null == userRole) {
			return new ModelAndView("loginEApplication");
		} else if (userRole.equals("Student")) {
			details = (Details) eApplicationService.findDetails(id);
			eApplicationService.saveDetails(details, "student");
			return new ModelAndView("studentPortal", "student", details);
		} else if (userRole.equals("Staff")) {
			details = (Details) eApplicationService.findDetails(id);
			eApplicationService.saveDetails(details, "staff");
			return new ModelAndView("staffPortal", "staff", details);
		} else if (userRole.equals("Principal")) {
			details = (Details) eApplicationService.findDetails(id);
			eApplicationService.saveDetails(details, "principal");
			return new ModelAndView("principalPortal", "principal", details);
		} else {
			return new ModelAndView("loginEApplication");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView userLogout() {
		return new ModelAndView("loginEApplication");
	}

//									Student Portal
	@RequestMapping(value = "/backToStudentPortal", method = RequestMethod.GET)
	public ModelAndView studentPortal() {
		int id = student.getUniqueid();
		details = (Details) eApplicationService.findDetails(id);
		return new ModelAndView("studentPortal", "student", details);
	}

	@RequestMapping(value = "/studentBonafide", method = RequestMethod.GET)
	public ModelAndView studentBonafideRequest() {
		student = (Student) eApplicationService.addCertificate("Bonafide");
		int id = student.getUniqueid();
		details = (Details) eApplicationService.findDetails(id);
		return new ModelAndView("studentPortal", "student", details);
	}

	@RequestMapping(value = "/studentTransfer", method = RequestMethod.GET)
	public ModelAndView studentTransferRequest() {
		student = (Student) eApplicationService.addCertificate("Transfer");
		int id = student.getUniqueid();
		details = (Details) eApplicationService.findDetails(id);
		return new ModelAndView("studentPortal", "student", details);
	}

	@RequestMapping(value = "/tracker/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentTracker(@PathVariable("uniqueid") int uniqueid) {
		details = (Details) eApplicationService.findDetails(uniqueid);
		return new ModelAndView("tracking", "student", details);
	}

	@RequestMapping(value = "/trackBonafideStatus/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentBonafideTracker(@PathVariable("uniqueid") int uniqueid) {
		student = (Student) eApplicationService.findStudentDetails(uniqueid);
		return new ModelAndView("trackingBonafideStatus", "student", student);
	}

	@RequestMapping(value = "/trackTransferStatus/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentTransferTracker(@PathVariable("uniqueid") int uniqueid) {
		student = (Student) eApplicationService.findStudentDetails(uniqueid);
		return new ModelAndView("trackingTransferStatus", "student", student);
	}

	@RequestMapping(value = "/backToTracking", method = RequestMethod.GET)
	public ModelAndView tracking() {
		details = (Details) eApplicationService.findDetails(student.getUniqueid());
		return new ModelAndView("tracking", "student", details);
	}

//									Staff Portal
	@RequestMapping(value = "/backToStaffPortal", method = RequestMethod.GET)
	public ModelAndView staffPortal() {
		int id = staff.getUniqueid();
		details = (Details) eApplicationService.findDetails(id);
		return new ModelAndView("staffPortal", "staff", details);
	}

	@RequestMapping(value = "/requestedBonafide", method = RequestMethod.GET)
	public ModelAndView staffBonafideRequest() {
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Bonafide");
		return new ModelAndView("requestedBonafide", "staff", staff);
	}

	@RequestMapping(value = "/requestedTransfer", method = RequestMethod.GET)
	public ModelAndView staffTransferRequest() {
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Transfer");
		return new ModelAndView("requestedTransfer", "staff", staff);
	}

//	Bonafide
	@RequestMapping(value = "/approveBonafideStaff/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approveBonafideStaff(@PathVariable("uniqueid") int id) {
		eApplicationService.approveRequest("Bonafide", "Bonafide Accepted", id);
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Bonafide");
		return new ModelAndView("requestedBonafide", "staff", staff);
	}

	@RequestMapping(value = "/rejectBonafideStaff", method = RequestMethod.GET)
	public ModelAndView rejectBonafideStaff(@RequestParam int uniqueid) {
		eApplicationService.rejectRequest("Bonafide", "Bonafide Rejected", uniqueid);
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Bonafide");
		return new ModelAndView("requestedBonafide", "staff", staff);
	}

//	Transfer
	@RequestMapping(value = "/approveTransferStaff/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approveTransferStaff(@PathVariable("uniqueid") int id) {
		eApplicationService.approveRequest("Transfer", "Transfer Accepted", id);
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Transfer");
		return new ModelAndView("requestedTransfer", "staff", staff);
	}

	@RequestMapping(value = "/rejectTransferStaff", method = RequestMethod.GET)
	public ModelAndView rejectTransferStaff(@RequestParam int uniqueid) {
		eApplicationService.rejectRequest("Transfer", "Transfer Rejected", uniqueid);
		List<Staff> staff = (List<Staff>) eApplicationService.findStaffList("Transfer");
		return new ModelAndView("requestedTransfer", "staff", staff);
	}

//									Principal Portal
	@RequestMapping(value = "/backToPrincipalPortal", method = RequestMethod.GET)
	public ModelAndView principalPortal() {
		int id = principal.getUniqueid();
		details = (Details) eApplicationService.findDetails(id);
		return new ModelAndView("principalPortal", "principal", details);
	}

	@RequestMapping(value = "/approvedBonafide", method = RequestMethod.GET)
	public ModelAndView approvedBonafideRequest() {
		List<Principal> principal = (List<Principal>) eApplicationService.findPrincipalList("Bonafide");
		return new ModelAndView("approvedBonafide", "principal", principal);
	}

	@RequestMapping(value = "/approvedTransfer", method = RequestMethod.GET)
	public ModelAndView approvedTransferRequest() {
		List<Principal> principal = (List<Principal>) eApplicationService.findPrincipalList("Transfer");
		return new ModelAndView("approvedTransfer", "principal", principal);
	}

	@RequestMapping(value = "/approvedBonafideRequest/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approvedBonafide(@PathVariable("uniqueid") int id) {
		eApplicationService.acceptedRequest("Bonafide", "Bonafide Accepted", id);
		List<Principal> principal = (List<Principal>) eApplicationService.findPrincipalList("Bonafide");
		return new ModelAndView("approvedTransfer", "principal", principal);
	}

	@RequestMapping(value = "/approvedTransferRequest/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approvedTransfer(@PathVariable("uniqueid") int id) {
		eApplicationService.acceptedRequest("Transfer", "Transfer Accepted", id);
		List<Principal> principal = (List<Principal>) eApplicationService.findPrincipalList("Transfer");
		return new ModelAndView("approvedTransfer", "principal", principal);
	}
}
