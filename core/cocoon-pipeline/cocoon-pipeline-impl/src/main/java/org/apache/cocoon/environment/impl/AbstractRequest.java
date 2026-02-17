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
package org.apache.cocoon.environment.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;

import org.apache.cocoon.environment.Request;

/**
 * Base class for any request
 *
 * @version $Id$
 */
public abstract class AbstractRequest 
    implements Request {

    /* (non-Javadoc)
     * @see org.apache.cocoon.environment.Request#getAttributes()
     */
    public Map getAttributes() {
        return new RequestMap(this);
    }

    /* (non-Javadoc)
     * @see org.apache.cocoon.environment.Request#getParameters()
     */
    public Map getParameters() {
        return new RequestParameterMap(this);
    }

    /* (non-Javadoc)
     * @see org.apache.cocoon.environment.Request#getHeaders()
     */
    public Map getHeaders() {
        return new RequestHeaderMap(this);
    }
    
    public int getIntHeader(String name) {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getRequestURL()
     */
    public StringBuffer getRequestURL() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getLocalAddr()
     */
    public String getLocalAddr() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getLocalName()
     */
    public String getLocalName() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getLocalPort()
     */
    public int getLocalPort() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getParameterMap()
     */
    public Map getParameterMap() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRemotePort()
     */
    public int getRemotePort() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
     */
    public boolean isRequestedSessionIdFromUrl() {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getReader()
     */
    public BufferedReader getReader() throws IOException {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRealPath(java.lang.String)
     */
    public String getRealPath(String path) {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
     */
    public RequestDispatcher getRequestDispatcher(String path) {
        // TODO The method was added when Request was made extending HttpServletRequest, implement the method
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#upgrade(java.lang.Class)
     */
    public <T extends jakarta.servlet.http.HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, jakarta.servlet.ServletException {
        // Jakarta Servlet API 5.0+ method for HTTP protocol upgrade
        throw new UnsupportedOperationException("HTTP protocol upgrade is not supported");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getPart(java.lang.String)
     */
    public jakarta.servlet.http.Part getPart(String name) throws IOException, jakarta.servlet.ServletException {
        // Jakarta Servlet API 3.0+ method for multipart file upload
        throw new UnsupportedOperationException("Multipart file upload support not implemented");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getParts()
     */
    public java.util.Collection<jakarta.servlet.http.Part> getParts() throws IOException, jakarta.servlet.ServletException {
        // Jakarta Servlet API 3.0+ method for multipart file upload
        throw new UnsupportedOperationException("Multipart file upload support not implemented");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#logout()
     */
    public void logout() throws jakarta.servlet.ServletException {
        // Jakarta Servlet API 3.0+ method for logout
        throw new UnsupportedOperationException("Logout support not implemented");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#login(java.lang.String, java.lang.String)
     */
    public void login(String username, String password) throws jakarta.servlet.ServletException {
        // Jakarta Servlet API 3.0+ method for login
        throw new UnsupportedOperationException("Login support not implemented");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#authenticate(jakarta.servlet.http.HttpServletResponse)
     */
    public boolean authenticate(jakarta.servlet.http.HttpServletResponse response) throws IOException, jakarta.servlet.ServletException {
        // Jakarta Servlet API 3.0+ method for authentication
        throw new UnsupportedOperationException("Authentication support not implemented");
    }

    public jakarta.servlet.DispatcherType getDispatcherType() {
        return jakarta.servlet.DispatcherType.REQUEST;
    }

    public String changeSessionId() {
        throw new UnsupportedOperationException("Session ID change not supported");
    }
}
