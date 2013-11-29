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
import org.jmanderson.sonata.Processor;
import org.jmanderson.sonata.form.GasForm;
import org.jmanderson.sonata.hibernate.Users;

/** 
 * MyEclipse Struts
 * Creation date: 06-22-2006
 * 
 * XDoclet definition:
 * @struts.action path="/saveGas" name="gasForm" input="/gasedit.jsp" scope="request" validate="true"
 * @struts.action-forward name="delete" path="/delete.do"
 * @struts.action-forward name="success" path="/gasList.do"
 */
public class SaveGasAction extends Action {

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
		
		GasForm gasForm = (GasForm) form;
		
		if (isCancelled(request)) {
			if (mapping.getAttribute() != null) {
				session.removeAttribute(mapping.getAttribute());
			}
			return mapping.findForward("success");
		}
		
		String delete = (String) request.getParameter("delete");
		if (delete != null) {
			Processor.delete(gasForm);
		}
		else {
			Processor.save(gasForm);
		}
		
		return mapping.findForward("success");
	}

}

