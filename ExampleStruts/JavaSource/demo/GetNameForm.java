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

public class GetNameForm extends ActionForm {
    private String name="";

    public GetNameForm() {
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
}