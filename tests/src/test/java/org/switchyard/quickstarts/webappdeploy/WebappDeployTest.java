/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2010-2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more 
 * details. You should have received a copy of the GNU Lesser General Public 
 * License, v.2.1 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  
 * 02110-1301, USA.
 */

package org.switchyard.quickstarts.webappdeploy;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.test.ShrinkwrapUtil;
import org.switchyard.test.mixins.HTTPMixIn;

@RunWith(Arquillian.class)
public class WebappDeployTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkwrapUtil.getSwitchYardWebArchive(
                "org.switchyard.quickstarts.demos",
                "switchyard-quickstart-demo-webapp-deploy");
    }

    @Test
    public void invokeOrderWebService() throws Exception {
        HTTPMixIn httpMixIn = new HTTPMixIn();

        httpMixIn.initialize();

        // Use the HttpMixIn to invoke the SOAP binding endpoint with a SOAP input (from the test classpath)
        // and compare the SOAP response to a SOAP response resource (from the test classpath)...
        httpMixIn.postResourceAndTestXML("http://localhost:18001/OrderService", "/xml/soap-request.xml", "/xml/soap-response.xml");
    }
}

