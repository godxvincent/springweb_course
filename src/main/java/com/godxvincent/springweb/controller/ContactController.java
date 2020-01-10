package com.godxvincent.springweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.godxvincent.springweb.constant.ViewConstant;
import com.godxvincent.springweb.model.ContactModel;
import com.godxvincent.springweb.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	private static final Log LOGGER = LogFactory.getLog(ContactController.class);
	
	// Al aplicar el preauthorize los metodos deben ser public y no private
	// El role creado en la base de datos debe estar creado como ROLE_XXXX
	// @PreAuthorize("permitAll()")
	// @PreAuthorize("hasRole('ROLE_ADMIN') And hasRole('ROLE_USER')")
	@PreAuthorize("permitAll()")
	@GetMapping("/contactform")
	public String redirectContactform(@RequestParam(name="id", required = false ) int id, 
			Model model) {
		ContactModel contactModel = new ContactModel();
		if (id != 0) {
			contactModel = contactService.findContactModelById(id);
		}
		model.addAttribute("contactModel", contactModel );
		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel") 
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact") 
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
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
	public ModelAndView showContacts() {
		ModelAndView modelAndView = new ModelAndView(ViewConstant.CONTACTS);
		LOGGER.error("Valor Objeto 'contactService': " + contactService);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		modelAndView.addObject("contacts", contactService.listAllContacts());
		modelAndView.addObject("username", user.getUsername() );
		return modelAndView;
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required = true) int id) {
		contactService.removeContactById(id);
		return showContacts();
	}
	
}
