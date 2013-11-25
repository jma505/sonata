//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package org.jmanderson.sonata.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jmanderson.sonata.MPGBean;
import org.jmanderson.sonata.Processor;
import org.jmanderson.sonata.hibernate.Users;

/** 
 * MyEclipse Struts
 * Creation date: 06-17-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/gaslist.jsp"
 */
public class GasListAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		Users user = (Users) session.getAttribute("user");
		if (user == null || !user.isValidated()) {
			return mapping.findForward("login");
		}
		
		List gas = Processor.getLastFiveGasEntries();
		MPGBean mpg = Processor.getMPGBean();
		
		if (gas != null && !gas.isEmpty()) {
			session.setAttribute("gas", gas);
		}
		else {
			session.removeAttribute("gas");
		}
		
		if (mpg != null) {
			session.setAttribute("mpg", mpg);
		}
		else {
			session.removeAttribute("mpg");
		}
		
		List service = Processor.getLastFiveServiceEntries();
		
		if (service != null && !service.isEmpty()) {
			session.setAttribute("service", service);
		}
		else {
			session.removeAttribute("service");
		}
		
		return mapping.findForward("success");
	}

}

