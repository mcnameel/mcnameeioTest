/**
 * 
 */
package edu.xavier.csci260.atinlay.service;

import edu.xavier.csci260.atinlay.dal.EmployeeDAO;
import edu.xavier.csci260.atinlay.dal.MessageDAO;
import edu.xavier.csci260.atinlay.domain.Employee;
import edu.xavier.csci260.atinlay.domain.Message;
import edu.xavier.csci260.atinlay.domain.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chris
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	private MessageDAO messageDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO, MessageDAO messageDAO) {
		this.employeeDAO = employeeDAO;
		this.messageDAO = messageDAO;
	}

	/* (non-Javadoc)
	 * @see edu.xavier.csci260.atinlay.service.EmployeeService#createEmplyee()
	 */
	@Override
	public void createEmployee(String username, String first_name, String last_name, String password, String manager_id, RoleEnum role) {
		employeeDAO.createEmployee(new Employee(username, password, first_name, last_name, role));
	}

	/* (non-Javadoc)
	 * @see edu.xavier.csci260.atinlay.service.EmployeeService#getNewMessages()
	 */
	@Override
	public List<Message> getNewMessages() {
		return null;
	}

	@Override
	public List<Message> getInbox(Employee to) {
		return messageDAO.getMessagesByRecipient(to.getUsername());
	}

	@Override
	public List<Message> getOutbox(Employee from) {
		return messageDAO.getMessagesBySender(from.getUsername());
	}

	@Override
	public Employee getEmployee(String username) {
		return employeeDAO.getEmployeeByUsername(username);
	}

	@Override
	public void createMessage(String from, String to, String body, String subject) {
		messageDAO.createMessage(new Message(from, to, body, subject));
	}
}
