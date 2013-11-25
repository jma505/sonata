//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package org.jmanderson.sonata.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jmanderson.sonata.Processor;
import org.jmanderson.sonata.form.LoginForm;
import org.jmanderson.sonata.hibernate.Users;

/** 
 * MyEclipse Struts
 * Creation date: 06-14-2006
 * 
 * XDoclet definition:
 * @struts.action path="/login" name="loginForm" input="/login.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/gaslist.jsp"
 */
public class LoginAction extends Action {

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
		
		ActionErrors errors = new ActionErrors();
		LoginForm loginForm = (LoginForm) form;

		Users user = Processor.authenticate(loginForm);
		if (user == null) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("error.password.mismatch"));
			saveErrors(request, errors);
			return new ActionForward(mapping.getInput());
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.removeAttribute(mapping.getAttribute());
			}
			else {
				session.removeAttribute(mapping.getAttribute());
			}
		}
		
		return mapping.findForward("success");
	}

}

