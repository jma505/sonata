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
 * Creation date: 06-21-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/gasedit.jsp"
 */
public class EditGasAction extends Action {

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
		
		if (form == null) {
			form = new GasForm();
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			}
			else {
				session.setAttribute(mapping.getAttribute(), form);
			}
		}
		GasForm gform = (GasForm) form;
		
		int id = 0;
		String s_id = request.getParameter("gas_id");
		if (s_id != null) {
			id = Integer.parseInt(s_id);
		}
		gform.setGas_id(id);
		
		if (id > 0) {
			Processor.getLogEntry(gform);
		}
		else {
			Processor.getLastGasMileage(gform);
		}
		
		return mapping.findForward("success");
	}

}

