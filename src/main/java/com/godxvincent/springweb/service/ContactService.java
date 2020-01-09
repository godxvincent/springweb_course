package com.godxvincent.springweb.service;


import java.util.List;

import com.godxvincent.springweb.entity.Contact;
import com.godxvincent.springweb.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAllContacts();
	
	public abstract Contact findContactById(int id);
	
	public abstract ContactModel findContactModelById(int id);
	
	public abstract void removeContactById(int id);
	
	
}
