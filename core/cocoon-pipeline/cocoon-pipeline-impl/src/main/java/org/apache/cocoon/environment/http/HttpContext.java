/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.environment.http;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

import org.apache.cocoon.environment.impl.AbstractContext;

/**
 * Implements the {@link org.apache.cocoon.environment.Context} interface
 *
 * @version $Id$
 */
public final class HttpContext extends AbstractContext {

    /** The ServletContext */
    private final ServletContext servletContext;

    /**
     * Constructs a HttpContext object from a ServletContext object
     */
    public HttpContext (ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public Object getAttribute(String name) {
        return servletContext.getAttribute(name);
    }

    public void setAttribute(String name, Object value) {
        servletContext.setAttribute(name, value);
    }

    public void removeAttribute(String name) {
        servletContext.removeAttribute(name);
    }

    public Enumeration getAttributeNames() {
        return servletContext.getAttributeNames();
    }

    public URL getResource(String path)
       throws MalformedURLException {
       return servletContext.getResource(path);
    }

    public InputStream getResourceAsStream(String path) {
        return servletContext.getResourceAsStream(path);
    }

    public String getRealPath(String path) {
        if (path.equals("/")) {
            String value = servletContext.getRealPath(path);
            if (value == null) {
                // Try to figure out the path of the root from that of WEB-INF
                try {
                    URL webXml = this.servletContext.getResource("/WEB-INF/web.xml");
                    // In some contexts there might not be any web.xml, then we stop
                    // guessing an just return null, which follows the servlet specification
                    if (webXml == null)
                        return null;
                    value = webXml.toString();
                } catch (MalformedURLException mue) {
                    throw new ContextURLException("Cannot determine the base URL for " + path, mue);
                }
                value = value.substring(0,value.length()-"WEB-INF/web.xml".length());
            }
            return value;
        }
        return servletContext.getRealPath(path);
    }

    public String getMimeType(String file) {
      return servletContext.getMimeType(file);
    }

    public String getInitParameter(String name) {
        return servletContext.getInitParameter(name);
    }

    /*
     * These methods are not in Cocoon's Context interface, but in the
     * ServletContext. To use them you have to downcast Cocoon's Context
     * to this HttpContext until we decide to add them to the Context
     * interface too.
     *
     * The following methods are deprecated since Servlet API 2.0 or 2.1
     * and will not be implemented here:
     * - public Servlet getServlet(String name)
     * - public Enumeration getServletNames()
     * - public Enumeration getServlets()
     * - public void log(Exception exception, String msg)
     */

    public ServletContext getContext(String uripath) {
        return this.servletContext.getContext(uripath);
    }

    public Enumeration getInitParameterNames() {
        return this.servletContext.getInitParameterNames();
    }

    public int getMajorVersion() {
        return this.servletContext.getMajorVersion();
    }

    public int getMinorVersion() {
        return this.servletContext.getMinorVersion();
    }

    public RequestDispatcher getNamedDispatcher(String name) {
        return this.servletContext.getNamedDispatcher(name);
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return this.servletContext.getRequestDispatcher(path);
    }

    public String getServerInfo() {
        return this.servletContext.getServerInfo();
    }

    /**
     * @see org.apache.cocoon.environment.impl.AbstractContext#log(java.lang.String)
     */
    public void log(String msg) {
        this.servletContext.log(msg);
    }

    /**
     * @see org.apache.cocoon.environment.impl.AbstractContext#log(java.lang.String, java.lang.Throwable)
     */
    public void log(String msg, Throwable throwable) {
        this.servletContext.log(msg, throwable);
    }

    /**
     * @see org.apache.cocoon.environment.impl.AbstractContext#log(java.lang.Exception, java.lang.String)
     */
    public void log(Exception exception, String msg) {
        this.servletContext.log(msg, exception);
    }

    /**
     * Jakarta Servlet 4.0 API - Set the response character encoding
     * @see jakarta.servlet.ServletContext#setResponseCharacterEncoding(String)
     */
    public void setResponseCharacterEncoding(String encoding) {
        this.servletContext.setResponseCharacterEncoding(encoding);
    }

    /**
     * Jakarta Servlet 4.0 API - Get the response character encoding
     * @see jakarta.servlet.ServletContext#getResponseCharacterEncoding()
     */
    public String getResponseCharacterEncoding() {
        return this.servletContext.getResponseCharacterEncoding();
    }

    /**
     * Jakarta Servlet 4.0 API - Set the request character encoding
     * @see jakarta.servlet.ServletContext#setRequestCharacterEncoding(String)
     */
    public void setRequestCharacterEncoding(String encoding) {
        this.servletContext.setRequestCharacterEncoding(encoding);
    }

    /**
     * Jakarta Servlet 4.0 API - Get the request character encoding
     * @see jakarta.servlet.ServletContext#getRequestCharacterEncoding()
     */
    public String getRequestCharacterEncoding() {
        return this.servletContext.getRequestCharacterEncoding();
    }

    /**
     * Jakarta Servlet 3.0 API - Set the session timeout
     * @see jakarta.servlet.ServletContext#setSessionTimeout(int)
     */
    public void setSessionTimeout(int sessionTimeout) {
        this.servletContext.setSessionTimeout(sessionTimeout);
    }

    /**
     * Jakarta Servlet 3.0 API - Get the session timeout
     * @see jakarta.servlet.ServletContext#getSessionTimeout()
     */
    public int getSessionTimeout() {
        return this.servletContext.getSessionTimeout();
    }

    /**
     * Jakarta Servlet 4.0 API - Get the virtual server name
     * @see jakarta.servlet.ServletContext#getVirtualServerName()
     */
    public String getVirtualServerName() {
        return this.servletContext.getVirtualServerName();
    }

    /**
     * Jakarta Servlet 3.0 API - Declare roles
     * @see jakarta.servlet.ServletContext#declareRoles(String...)
     */
    public void declareRoles(String... roleNames) {
        this.servletContext.declareRoles(roleNames);
    }

    /**
     * Jakarta Servlet 3.0 API - Set init parameter
     * @see jakarta.servlet.ServletContext#setInitParameter(String, String)
     */
    public boolean setInitParameter(String name, String value) {
        return this.servletContext.setInitParameter(name, value);
    }

    /**
     * Jakarta Servlet 2.5 API - Get context path
     * @see jakarta.servlet.ServletContext#getContextPath()
     */
    public String getContextPath() {
        return this.servletContext.getContextPath();
    }

    /**
     * Jakarta Servlet 3.0 API - Get class loader
     * @see jakarta.servlet.ServletContext#getClassLoader()
     */
    public ClassLoader getClassLoader() {
        return this.servletContext.getClassLoader();
    }

    /**
     * Jakarta Servlet 3.0 API - Get effective major version
     * @see jakarta.servlet.ServletContext#getEffectiveMajorVersion()
     */
    public int getEffectiveMajorVersion() {
        return this.servletContext.getEffectiveMajorVersion();
    }

    /**
     * Jakarta Servlet 3.0 API - Get effective minor version
     * @see jakarta.servlet.ServletContext#getEffectiveMinorVersion()
     */
    public int getEffectiveMinorVersion() {
        return this.servletContext.getEffectiveMinorVersion();
    }

    /**
     * Jakarta Servlet 3.0 API - Get JSP config descriptor
     * @see jakarta.servlet.ServletContext#getJspConfigDescriptor()
     */
    public jakarta.servlet.descriptor.JspConfigDescriptor getJspConfigDescriptor() {
        return this.servletContext.getJspConfigDescriptor();
    }

    /**
     * Jakarta Servlet 3.0 API - Create listener instance
     * @see jakarta.servlet.ServletContext#createListener(Class)
     */
    public <T extends java.util.EventListener> T createListener(Class<T> clazz)
            throws jakarta.servlet.ServletException {
        return this.servletContext.createListener(clazz);
    }

    /**
     * Jakarta Servlet 3.0 API - Add listener
     * @see jakarta.servlet.ServletContext#addListener(String)
     */
    public void addListener(String className) {
        this.servletContext.addListener(className);
    }

    /**
     * Jakarta Servlet 3.0 API - Add listener
     * @see jakarta.servlet.ServletContext#addListener(Class)
     */
    public void addListener(Class<? extends java.util.EventListener> listenerClass) {
        this.servletContext.addListener(listenerClass);
    }

    /**
     * Jakarta Servlet 3.0 API - Add listener
     * @see jakarta.servlet.ServletContext#addListener(java.util.EventListener)
     */
    public void addListener(java.util.EventListener t) {
        this.servletContext.addListener(t);
    }

    /**
     * Jakarta Servlet 3.0 API - Create filter instance
     * @see jakarta.servlet.ServletContext#createFilter(Class)
     */
    public <T extends jakarta.servlet.Filter> T createFilter(Class<T> clazz)
            throws jakarta.servlet.ServletException {
        return this.servletContext.createFilter(clazz);
    }

    /**
     * Jakarta Servlet 3.0 API - Create servlet instance
     * @see jakarta.servlet.ServletContext#createServlet(Class)
     */
    public <T extends jakarta.servlet.Servlet> T createServlet(Class<T> clazz)
            throws jakarta.servlet.ServletException {
        return this.servletContext.createServlet(clazz);
    }

    /**
     * Jakarta Servlet 3.0 API - Get filter registration
     * @see jakarta.servlet.ServletContext#getFilterRegistration(String)
     */
    public jakarta.servlet.FilterRegistration getFilterRegistration(String filterName) {
        return this.servletContext.getFilterRegistration(filterName);
    }

    /**
     * Jakarta Servlet 3.0 API - Get filter registrations
     * @see jakarta.servlet.ServletContext#getFilterRegistrations()
     */
    public java.util.Map<String, ? extends jakarta.servlet.FilterRegistration> getFilterRegistrations() {
        return this.servletContext.getFilterRegistrations();
    }

    /**
     * Jakarta Servlet 3.0 API - Get servlet registration
     * @see jakarta.servlet.ServletContext#getServletRegistration(String)
     */
    public jakarta.servlet.ServletRegistration getServletRegistration(String servletName) {
        return this.servletContext.getServletRegistration(servletName);
    }

    /**
     * Jakarta Servlet 3.0 API - Get servlet registrations
     * @see jakarta.servlet.ServletContext#getServletRegistrations()
     */
    public java.util.Map<String, ? extends jakarta.servlet.ServletRegistration> getServletRegistrations() {
        return this.servletContext.getServletRegistrations();
    }

    /**
     * Jakarta Servlet 3.0 API - Get session cookie config
     * @see jakarta.servlet.ServletContext#getSessionCookieConfig()
     */
    public jakarta.servlet.SessionCookieConfig getSessionCookieConfig() {
        return this.servletContext.getSessionCookieConfig();
    }

    /**
     * Jakarta Servlet 3.0 API - Set session tracking modes
     * @see jakarta.servlet.ServletContext#setSessionTrackingModes(java.util.Set)
     */
    public void setSessionTrackingModes(java.util.Set<jakarta.servlet.SessionTrackingMode> sessionTrackingModes) {
        this.servletContext.setSessionTrackingModes(sessionTrackingModes);
    }

    /**
     * Jakarta Servlet 3.0 API - Get default session tracking modes
     * @see jakarta.servlet.ServletContext#getDefaultSessionTrackingModes()
     */
    public java.util.Set<jakarta.servlet.SessionTrackingMode> getDefaultSessionTrackingModes() {
        return this.servletContext.getDefaultSessionTrackingModes();
    }

    /**
     * Jakarta Servlet 3.0 API - Get effective session tracking modes
     * @see jakarta.servlet.ServletContext#getEffectiveSessionTrackingModes()
     */
    public java.util.Set<jakarta.servlet.SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return this.servletContext.getEffectiveSessionTrackingModes();
    }

    /**
     * Jakarta Servlet 3.0 API - Add filter with name and class name
     * @see jakarta.servlet.ServletContext#addFilter(String, String)
     */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return this.servletContext.addFilter(filterName, className);
    }

    /**
     * Jakarta Servlet 3.0 API - Add filter with name and filter instance
     * @see jakarta.servlet.ServletContext#addFilter(String, jakarta.servlet.Filter)
     */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, jakarta.servlet.Filter filter) {
        return this.servletContext.addFilter(filterName, filter);
    }

    /**
     * Jakarta Servlet 3.0 API - Add filter with name and class
     * @see jakarta.servlet.ServletContext#addFilter(String, Class)
     */
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends jakarta.servlet.Filter> filterClass) {
        return this.servletContext.addFilter(filterName, filterClass);
    }

    /**
     * Jakarta Servlet 3.0 API - Add servlet with name and class name
     * @see jakarta.servlet.ServletContext#addServlet(String, String)
     */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return this.servletContext.addServlet(servletName, className);
    }

    /**
     * Jakarta Servlet 3.0 API - Add servlet with name and servlet instance
     * @see jakarta.servlet.ServletContext#addServlet(String, jakarta.servlet.Servlet)
     */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, jakarta.servlet.Servlet servlet) {
        return this.servletContext.addServlet(servletName, servlet);
    }

    /**
     * Jakarta Servlet 3.0 API - Add servlet with name and class
     * @see jakarta.servlet.ServletContext#addServlet(String, Class)
     */
    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, Class<? extends jakarta.servlet.Servlet> servletClass) {
        return this.servletContext.addServlet(servletName, servletClass);
    }

    /**
     * Jakarta Servlet 4.0 API - Add JSP file
     * @see jakarta.servlet.ServletContext#addJspFile(String, String)
     */
    public jakarta.servlet.ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return this.servletContext.addJspFile(servletName, jspFile);
    }
}
