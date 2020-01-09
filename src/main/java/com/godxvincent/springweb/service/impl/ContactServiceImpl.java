package com.godxvincent.springweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godxvincent.springweb.component.ContactConverter;
import com.godxvincent.springweb.entity.Contact;
import com.godxvincent.springweb.model.ContactModel;
import com.godxvincent.springweb.repository.ContactRepository;
import com.godxvincent.springweb.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	private final static Log LOGGER = LogFactory.getLog(ContactServiceImpl.class);
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		// TODO Auto-generated method stub
		Contact contact = contactRepository.save(contactConverter.contactModelToContact(contactModel));
		// Lógica intermedia 
		return contactConverter.contactToContactModel(contact);
	}
	
	public List<ContactModel> listAllContacts() {
		List<ContactModel> listContactsModel = new ArrayList<ContactModel>();
		List<Contact> listContacts = contactRepository.findAll();
		listContacts.stream().map( contact -> listContactsModel.add(contactConverter.contactToContactModel(contact))).collect(Collectors.toList());
		LOGGER.info("información recuperada del repositorio (listContacts) : "+listContacts );
		LOGGER.info("información mapeada al model (listContactsModel) : "+listContactsModel  );
		
		return listContactsModel;
	}

	@Override
	public Contact findContactById(int id) {
		Contact contact = contactRepository.findById(id);
		return contact;
	}

	@Override
	public void removeContactById(int id) {
		// TODO Auto-generated method stub
		Contact contact = this.findContactById(id); 
		if ( contact != null ) {
			contactRepository.delete(contact);	
		}
		
	}

	@Override
	public ContactModel findContactModelById(int id) {
		// TODO Auto-generated method stub
		return contactConverter.contactToContactModel(this.findContactById(id));
	}

}
