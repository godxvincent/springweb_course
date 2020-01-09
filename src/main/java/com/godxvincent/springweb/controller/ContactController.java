package com.godxvincent.springweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.godxvincent.springweb.component.ContactConverter;
import com.godxvincent.springweb.constant.ViewConstant;
import com.godxvincent.springweb.model.ContactModel;
import com.godxvincent.springweb.service.ContactService;
import com.querydsl.core.types.Constant;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	private static final Log LOGGER = LogFactory.getLog(ContactController.class);
	@GetMapping("/contactform")
	private String redirectContactform(@RequestParam(name="id", required = false ) int id, 
			Model model) {
		ContactModel contactModel = new ContactModel();
		if (id != 0) {
			contactModel = contactService.findContactModelById(id);
		}
		model.addAttribute("contactModel", contactModel );
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel") 
	private String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact") 
	private String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model) {
		LOGGER.info("METHOD: addContact() -- PARMS: "+contactModel.toString());
		
		if (contactService.addContact(contactModel) != null ) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts") 
	private ModelAndView showContacts() {
		ModelAndView modelAndView = new ModelAndView(ViewConstant.CONTACTS);
		modelAndView.addObject("contacts", contactService.listAllContacts());
		return modelAndView;
	}
	
	@GetMapping("/removecontact")
	private ModelAndView removeContact(@RequestParam(name="id", required = true) int id) {
		contactService.removeContactById(id);
		return showContacts();
	}
	
}
