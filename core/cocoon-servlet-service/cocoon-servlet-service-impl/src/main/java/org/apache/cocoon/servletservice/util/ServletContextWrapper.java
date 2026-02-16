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
package org.apache.cocoon.servletservice.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/**
 * @version $Id: ServletContextWrapper.java 608375 2008-01-03 08:33:00Z reinhard $
 * @since 1.0.0
 */
public class ServletContextWrapper implements ServletContext {

    protected ServletContext servletContext;

    /**
     * @param servletContext The servletContext to set.
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getContext(String uripath) {
        return this.servletContext.getContext(uripath);
    }

    public int getMajorVersion() {
        return this.servletContext.getMajorVersion();
    }

    public int getMinorVersion() {
        return this.servletContext.getMinorVersion();
    }

    public String getMimeType(String file) {
        return this.servletContext.getMimeType(file);
    }

    public Set getResourcePaths(String paths) {
        return this.servletContext.getResourcePaths(paths);
    }

    public URL getResource(String path) throws MalformedURLException {
        return this.servletContext.getResource(path);
    }

    public InputStream getResourceAsStream(String path) {
        return this.servletContext.getResourceAsStream(path);
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return this.servletContext.getRequestDispatcher(path);
    }

    public RequestDispatcher getNamedDispatcher(String name) {
        return this.servletContext.getNamedDispatcher(name);
    }

    public Servlet getServlet(String name) throws ServletException {
        return this.servletContext.getServlet(name);
    }

    public Enumeration getServlets() {
        return this.servletContext.getServlets();
    }

    public Enumeration getServletNames() {
        return this.servletContext.getServletNames();
    }

    public void log(String msg) {
        this.servletContext.log(msg);
    }

    public void log(Exception exception, String msg) {
        this.servletContext.log(exception, msg);
    }

    public void log(String msg, Throwable throwable) {
        this.servletContext.log(msg, throwable);
    }

    public String getRealPath(String path) {
        return this.servletContext.getRealPath(path);
    }

    public String getServerInfo() {
        return this.servletContext.getServerInfo();
    }

    public String getInitParameter(String path) {
        return this.servletContext.getInitParameter(path);
    }

    public Enumeration getInitParameterNames() {
        return this.servletContext.getInitParameterNames();
    }

    public Object getAttribute(String name) {
        return this.servletContext.getAttribute(name);
    }

    public Enumeration getAttributeNames() {
        return this.servletContext.getAttributeNames();
    }

    public void setAttribute(String name, Object value) {
        this.servletContext.setAttribute(name, value);
    }

    public void removeAttribute(String name) {
        this.servletContext.removeAttribute(name);
    }

    public String getServletContextName() {
        return this.servletContext.getServletContextName();
    }

    // Jakarta Servlet 5.0 API compatibility methods
    public void setResponseCharacterEncoding(String encoding) {
        // Delegate to wrapped context if it supports this method
        if (this.servletContext != null) {
            this.servletContext.setResponseCharacterEncoding(encoding);
        }
    }

    public String getResponseCharacterEncoding() {
        return this.servletContext != null ? this.servletContext.getResponseCharacterEncoding() : null;
    }

    public void setRequestCharacterEncoding(String encoding) {
        if (this.servletContext != null) {
            this.servletContext.setRequestCharacterEncoding(encoding);
        }
    }

    public String getRequestCharacterEncoding() {
        return this.servletContext != null ? this.servletContext.getRequestCharacterEncoding() : null;
    }

    public void setSessionTimeout(int sessionTimeout) {
        if (this.servletContext != null) {
            this.servletContext.setSessionTimeout(sessionTimeout);
        }
    }

    public int getSessionTimeout() {
        return this.servletContext != null ? this.servletContext.getSessionTimeout() : -1;
    }

    public String getVirtualServerName() {
        return this.servletContext != null ? this.servletContext.getVirtualServerName() : null;
    }

    public void declareRoles(String... roleNames) {
        if (this.servletContext != null) {
            this.servletContext.declareRoles(roleNames);
        }
    }

    public ClassLoader getClassLoader() {
        return this.servletContext != null ? this.servletContext.getClassLoader() : null;
    }

    public jakarta.servlet.descriptor.JspConfigDescriptor getJspConfigDescriptor() {
        return this.servletContext != null ? this.servletContext.getJspConfigDescriptor() : null;
    }

    public <T extends java.util.EventListener> T createListener(Class<T> clazz) throws jakarta.servlet.ServletException {
        return this.servletContext != null ? this.servletContext.createListener(clazz) : null;
    }

    public void addListener(String className) {
        if (this.servletContext != null) {
            this.servletContext.addListener(className);
        }
    }

    public <T extends java.util.EventListener> void addListener(T t) {
        if (this.servletContext != null) {
            this.servletContext.addListener(t);
        }
    }

    public void addListener(Class<? extends java.util.EventListener> listenerClass) {
        if (this.servletContext != null) {
            this.servletContext.addListener(listenerClass);
        }
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return this.servletContext != null ? this.servletContext.addServlet(servletName, className) : null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, jakarta.servlet.Servlet servlet) {
        return this.servletContext != null ? this.servletContext.addServlet(servletName, servlet) : null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, Class<? extends jakarta.servlet.Servlet> servletClass) {
        return this.servletContext != null ? this.servletContext.addServlet(servletName, servletClass) : null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return this.servletContext != null ? this.servletContext.addJspFile(servletName, jspFile) : null;
    }

    public <T extends jakarta.servlet.Servlet> T createServlet(Class<T> clazz) throws jakarta.servlet.ServletException {
        return this.servletContext != null ? this.servletContext.createServlet(clazz) : null;
    }

    public jakarta.servlet.ServletRegistration getServletRegistration(String servletName) {
        return this.servletContext != null ? this.servletContext.getServletRegistration(servletName) : null;
    }

    public java.util.Map<String, ? extends jakarta.servlet.ServletRegistration> getServletRegistrations() {
        return this.servletContext != null ? this.servletContext.getServletRegistrations() : java.util.Collections.emptyMap();
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return this.servletContext != null ? this.servletContext.addFilter(filterName, className) : null;
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, jakarta.servlet.Filter filter) {
        return this.servletContext != null ? this.servletContext.addFilter(filterName, filter) : null;
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends jakarta.servlet.Filter> filterClass) {
        return this.servletContext != null ? this.servletContext.addFilter(filterName, filterClass) : null;
    }

    public <T extends jakarta.servlet.Filter> T createFilter(Class<T> clazz) throws jakarta.servlet.ServletException {
        return this.servletContext != null ? this.servletContext.createFilter(clazz) : null;
    }

    public jakarta.servlet.FilterRegistration getFilterRegistration(String filterName) {
        return this.servletContext != null ? this.servletContext.getFilterRegistration(filterName) : null;
    }

    public java.util.Map<String, ? extends jakarta.servlet.FilterRegistration> getFilterRegistrations() {
        return this.servletContext != null ? this.servletContext.getFilterRegistrations() : java.util.Collections.emptyMap();
    }

    public java.util.Set<jakarta.servlet.SessionTrackingMode> getDefaultSessionTrackingModes() {
        return this.servletContext != null ? this.servletContext.getDefaultSessionTrackingModes() : java.util.Collections.emptySet();
    }

    public java.util.Set<jakarta.servlet.SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return this.servletContext != null ? this.servletContext.getEffectiveSessionTrackingModes() : java.util.Collections.emptySet();
    }

    public void setSessionTrackingModes(java.util.Set<jakarta.servlet.SessionTrackingMode> sessionTrackingModes) {
        if (this.servletContext != null) {
            this.servletContext.setSessionTrackingModes(sessionTrackingModes);
        }
    }

    public jakarta.servlet.SessionCookieConfig getSessionCookieConfig() {
        return this.servletContext != null ? this.servletContext.getSessionCookieConfig() : null;
    }

    public boolean setInitParameter(String name, String value) {
        return this.servletContext != null ? this.servletContext.setInitParameter(name, value) : false;
    }

    public int getEffectiveMajorVersion() {
        return this.servletContext != null ? this.servletContext.getEffectiveMajorVersion() : 5;
    }

    public int getEffectiveMinorVersion() {
        return this.servletContext != null ? this.servletContext.getEffectiveMinorVersion() : 0;
    }

    public String getContextPath() {
        return this.servletContext != null ? this.servletContext.getContextPath() : "";
    }

}
