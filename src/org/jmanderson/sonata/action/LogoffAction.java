//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package org.jmanderson.sonata.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jmanderson.sonata.hibernate.SessionFactory;
import org.jmanderson.sonata.hibernate.Users;

/** 
 * MyEclipse Struts
 * Creation date: 06-21-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/login"
 */
public class LogoffAction extends Action {

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
		
		if (user != null) {
			servlet.log("LogoffAction: User " + user.getUserName() + " logged off in session " + session.getId());
		}
		else {
			servlet.log("LogoffAction: Unknown user logged off in session " + session.getId());
		}
		
		SessionFactory.closeSession();
		
		session.removeAttribute("user");
		session.invalidate();
		
		return mapping.findForward("success");
	}

}

