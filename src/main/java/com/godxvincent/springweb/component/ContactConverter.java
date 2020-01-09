package com.godxvincent.springweb.component;

import org.springframework.stereotype.Component;

import com.godxvincent.springweb.entity.Contact;
import com.godxvincent.springweb.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public Contact contactModelToContact(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());		
		contact.setCity(contactModel.getCity());
		return contact;
	}
	
	public ContactModel contactToContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());		
		contactModel.setCity(contact.getCity());
		return contactModel;
	}
	
}
