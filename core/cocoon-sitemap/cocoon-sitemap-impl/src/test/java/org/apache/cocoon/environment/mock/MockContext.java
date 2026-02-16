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
package org.apache.cocoon.environment.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.InputStream;

import org.apache.cocoon.environment.impl.AbstractContext;

public class MockContext extends AbstractContext {

    private Hashtable attributes = new Hashtable();
    private Hashtable resources = new Hashtable();
    private Hashtable mappings = new Hashtable();
    private Hashtable initparameters = new Hashtable();

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public Enumeration getAttributeNames() {
        return attributes.keys();
    }

    public void setResource(String path, URL url) {
        resources.put(path, url);
    }

    public URL getResource(String path) throws MalformedURLException {
        return (URL)resources.get(path);
    }

    public String getRealPath(String path) {
      return path;
    }

    public String getMimeType(String file) {
        return (String)mappings.get(file.substring(file.lastIndexOf(".")+1));
    }

    public boolean setInitParameter(String name, String value) {
        if (initparameters.containsKey(name)) {
            return false; // Already exists
        }
        initparameters.put(name, value);
        return true;
    }

    public String getInitParameter(String name) {
        return (String)initparameters.get(name);
    }

    public InputStream getResourceAsStream(String path) {
        return null;
    }

    public void reset() {
        attributes.clear();
        resources.clear();
        mappings.clear();
        initparameters.clear();
    }

    public void log(Exception arg0, String arg1) {
        System.err.println("log");
    }

    public void log(String arg0, Throwable arg1) {
        System.err.println("log");
    }

    public void log(String arg0) {
        System.err.println("log");
    }

    // Jakarta Servlet 5.0 API methods

    public void setResponseCharacterEncoding(String encoding) {
        // Mock implementation - no-op
    }

    public String getResponseCharacterEncoding() {
        return null;
    }

    public void setRequestCharacterEncoding(String encoding) {
        // Mock implementation - no-op
    }

    public String getRequestCharacterEncoding() {
        return null;
    }

    public String getVirtualServerName() {
        return "mock-server";
    }

    public int getSessionTimeout() {
        return 30;
    }

    public void setSessionTimeout(int sessionTimeout) {
        // Mock implementation - no-op
    }

    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    public int getEffectiveMajorVersion() {
        return 5;
    }

    public int getEffectiveMinorVersion() {
        return 0;
    }

    public java.util.Set<jakarta.servlet.SessionTrackingMode> getDefaultSessionTrackingModes() {
        return java.util.Collections.emptySet();
    }

    public java.util.Set<jakarta.servlet.SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return java.util.Collections.emptySet();
    }

    public void setSessionTrackingModes(java.util.Set<jakarta.servlet.SessionTrackingMode> sessionTrackingModes) {
        // Mock implementation - no-op
    }

    public jakarta.servlet.SessionCookieConfig getSessionCookieConfig() {
        return null;
    }

    public jakarta.servlet.descriptor.JspConfigDescriptor getJspConfigDescriptor() {
        return null;
    }

    public void declareRoles(String... roleNames) {
        // Mock implementation - no-op
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, jakarta.servlet.Servlet servlet) {
        return null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addServlet(String servletName, Class<? extends jakarta.servlet.Servlet> servletClass) {
        return null;
    }

    public jakarta.servlet.ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return null;
    }

    public <T extends jakarta.servlet.Servlet> T createServlet(Class<T> clazz) throws jakarta.servlet.ServletException {
        return null;
    }

    public jakarta.servlet.ServletRegistration getServletRegistration(String servletName) {
        return null;
    }

    public java.util.Map<String, ? extends jakarta.servlet.ServletRegistration> getServletRegistrations() {
        return java.util.Collections.emptyMap();
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return null;
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, jakarta.servlet.Filter filter) {
        return null;
    }

    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends jakarta.servlet.Filter> filterClass) {
        return null;
    }

    public <T extends jakarta.servlet.Filter> T createFilter(Class<T> clazz) throws jakarta.servlet.ServletException {
        return null;
    }

    public jakarta.servlet.FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }

    public java.util.Map<String, ? extends jakarta.servlet.FilterRegistration> getFilterRegistrations() {
        return java.util.Collections.emptyMap();
    }

    public void addListener(String className) {
        // Mock implementation - no-op
    }

    public <T extends java.util.EventListener> void addListener(T t) {
        // Mock implementation - no-op
    }

    public void addListener(Class<? extends java.util.EventListener> listenerClass) {
        // Mock implementation - no-op
    }

    public <T extends java.util.EventListener> T createListener(Class<T> clazz) throws jakarta.servlet.ServletException {
        return null;
    }

    public String getContextPath() {
        return "";
    }
}
