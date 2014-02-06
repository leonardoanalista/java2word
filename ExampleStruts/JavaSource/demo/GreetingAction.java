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
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GreetingAction extends org.apache.struts.action.Action {

    // Global Forwards
    public static final String GLOBAL_FORWARD_getName = "getName";

    // Local Forwards
    private static final String FORWARD_sayhello = "sayhello";
    
//    static Logger log = Logger.getLogger(GreetingAction.class);

    public GreetingAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = ((demo.GetNameForm)form).getName();
        String greeting = "Hello, "+name+"!";
        request.setAttribute("greeting", greeting);
                
        
        return mapping.findForward(FORWARD_sayhello);
    }
}