/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package demo;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class SalaryReportForm extends ActionForm {
    
//	private static final long serialVersionUID = 1L;
	
	private String name= "";
	private String greaterThan = "";
	private String lessThan= "";
	
	public SalaryReportForm() {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        this.name="";
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        ActionErrors errs = new ActionErrors();
        return errs;
    }

    public String getName() {
        return this.name;    
    }

    public void setName(String name) {
        this.name = (name==null?"":name);
    }

    public String getLessThan() {
		return lessThan;
	}

	public void setLessThan(String lessThan) {
		this.lessThan = lessThan;
	}
	public String getGreaterThan() {
		return greaterThan;
	}

	public void setGreaterThan(String greaterThan) {
		this.greaterThan = greaterThan;
	}
	
}